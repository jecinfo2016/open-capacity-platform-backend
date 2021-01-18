package com.open.capacity.oss.controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.open.capacity.oss.service.ModuleService;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import com.open.capacity.oss.common.Binary;
import com.open.capacity.oss.md2html.MarkDown2HtmlWrapper;
import com.open.capacity.oss.model.*;
import com.open.capacity.oss.service.HFileService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.oss.config.OssServiceFactory;
import com.open.capacity.oss.service.FileService;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 文件上传 同步oss db双写 目前仅实现了阿里云,七牛云
 * 参考src/main/view/upload.html
 */
@RestController
@Api(tags = "FILE API")
public class FileController {

    @Autowired
    private OssServiceFactory fileServiceFactory;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HFileService hfileService;

    @Autowired
    private ModuleService moduleService;


    @Value("${file.show.url}")
    public String url;

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 文件上传
     * 根据fileType选择上传方式
     *
     * @param file
     * @return
     * @throws Exception
     */
    @LogAnnotation(module = "file-center", recordRequestParam = false)
    @PostMapping("/files-anon")
    public FileInfo upload(@RequestParam("file") MultipartFile file) throws Exception {

        String fileType = FileType.QINIU.toString();
        FileService fileService = fileServiceFactory.getFileService(fileType);
        return fileService.upload(file);
    }

    /**
     * 保存
     *
     * @param fileInfo
     * @return
     */
    @PostMapping("/files-save")
    public Result save(FileInfo fileInfo) {

        int save = hfileService.save(fileInfo);

        if (save > 0) {
            return Result.succeed("保存成功");
        } else {
            return Result.failed("保存失败");
        }
    }

    /**
     * layui富文本文件自定义上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @LogAnnotation(module = "file-center", recordRequestParam = false)
    @PostMapping("/files/layui")
    public Map<String, Object> uploadLayui(@RequestParam("file") MultipartFile file)
            throws Exception {

        FileInfo fileInfo = upload(file);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("src", fileInfo.getUrl());
        map.put("data", data);

        return map;
    }

    /**
     * 文件删除
     *
     * @param id
     */
    @LogAnnotation(module = "file-center", recordRequestParam = false)
    @PreAuthorize("hasAuthority('file:del')")
    @DeleteMapping("/files/{id}")
    public Result delete(@PathVariable String id) {

        try {
            FileInfo fileInfo = fileServiceFactory.getFileService(FileType.QINIU.toString()).getById(id);
            if (fileInfo != null) {
                FileService fileService = fileServiceFactory.getFileService(fileInfo.getSource());
                fileService.delete(fileInfo);
            }
            return Result.succeed("操作成功");
        } catch (Exception ex) {
            return Result.failed("操作失败");
        }

    }

    /**
     * 文件查询
     *
     * @param params
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/files")
    public PageResult<FileInfo> findFiles(@RequestParam Map<String, Object> params) {
        return hfileService.findList(params);
    }


    /**
     * MD转html
     *
     * @param fileUrl
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "md转html", notes = "通过hbase下载接口获取地址转换")
    @RequestMapping(value = "/files/markdown2html", method = {RequestMethod.POST, RequestMethod.GET}, produces = {"application/json"})
    public String markdown2html(@RequestParam(value = "fileUrl") String fileUrl, @RequestParam(value = "id") String id) throws IOException {
        MarkdownEntity html = MarkDown2HtmlWrapper.ofUrl(fileUrl, id);
        return html.toString();
    }

    /**
     * @param fileUpdate
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "更新文档", notes = "字符串转MultipartFile更新")
    @PostMapping(value = "/files/updateMd", produces = {"application/json"})
    public Map<String, Object> updateMd(@RequestBody FileUpdate fileUpdate) throws IOException {
        String namespace = fileUpdate.getNamespace();
        Integer id = fileUpdate.getId();
        Map<String, Object> result = null;
        byte[] buffer = new byte[1024 * 10];
        buffer = fileUpdate.getContent().getBytes(StandardCharsets.UTF_8);
        MultipartFile file = null;
        if (namespace == null || namespace.equals("")) {
            namespace = "develop";
            file = new MockMultipartFile(namespace, namespace, "text/markdown", buffer);
        }
        file = new MockMultipartFile(namespace, namespace, "text/markdown", buffer);
        if (file != null) {
            logger.error("content type=" + file.getContentType());
            //保存新文档
            result = upload(file, namespace);
            //修改文档模块
            ModuleInfo moduleInfo = new ModuleInfo();
            moduleInfo.setId(id);
            moduleInfo.setShowUrl(result.get("data").toString());
            moduleService.updateFile(moduleInfo);
        } else {
            Integer code = 500;
            String message = "请先上传文件";
            result.put("code", code);
            result.put("message", message);
        }
        return result;
    }

    /**
     * @param namespace
     * @param id
     * @return
     */
    @ApiOperation(value = "删除文件", notes = "根据文件在Hbase中的唯一ID删除")
    @RequestMapping(value = "delete", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}, produces = {"application/json"})
    public Map<String, Object> delete(@RequestParam(value = "namespace") String namespace,
                                      @RequestParam(value = "id") String id) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            hfileService.removeFile(namespace, id);
            hfileService.delete(id);
            result.put("code", HttpStatus.SC_OK);
            result.put("message", "Delete success");
        } catch (Exception e) {
            logger.error("Delete occur {} .", e.getMessage(), e);
            result.put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.put("message", "Delete failed");
        }
        return result;
    }

    /**
     * @param namespace
     * @param name
     * @return
     */
    @ApiOperation(value = "删除文件", notes = "根据文件名删除")
    @RequestMapping(value = "deleteByName", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}, produces = {"application/json"})
    public Map<String, Object> deleteByName(@RequestParam(value = "namespace") String namespace,
                                            @RequestParam(value = "name") String name) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            hfileService.removeFile(namespace, name);
            result.put("code", HttpStatus.SC_OK);
            result.put("message", "Delete success");
        } catch (Exception e) {
            logger.error("Delete occur {} .", e.getMessage(), e);
            result.put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.put("message", "Delete failed");
        }
        return result;
    }

    @LogAnnotation(module = "file-center", recordRequestParam = false)
    @RequestMapping(value = "/files/upload", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> upload(@RequestParam(value = "file", required = true) MultipartFile file,
                                      @RequestParam(value = "namespace", required = false) String namespace) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        if (namespace == null || namespace.isEmpty()) {
            namespace = "wudi";
        }
        Integer code = 0;
        String message = "OK";
        if (file != null) {
            logger.error("content type=" + file.getContentType());
            SmallFile smallFile = new SmallFile();
            smallFile.setName(file.getOriginalFilename());
            smallFile.setNameSpace(namespace);
            smallFile.setContentType(file.getContentType());
            smallFile.setContent(new Binary(IOUtils.toByteArray(file.getInputStream())));
            boolean success = hfileService.saveFile(smallFile);
            if (success) {
                result.put("data", "/show/" + namespace + "/" + smallFile.getId());
                result.put("file_name",file.getOriginalFilename());
            } else {
                code = 500;
                message = "保存文件失败";
            }
            //存入数据库
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(smallFile.getId());
            fileInfo.setName(namespace);
            fileInfo.setFileName(smallFile.getName());
            fileInfo.setContentType(file.getContentType());
            fileInfo.setSize(file.getSize());
            if (file.getContentType().equals("image/jpeg")) {
                fileInfo.setIsImg(true);
            } else {
                fileInfo.setIsImg(false);
            }
            fileInfo.setSource("HBASE");
            fileInfo.setPath(url + namespace + "/" + smallFile.getId());
            fileInfo.setUrl(url + namespace + "/" + smallFile.getId());
            hfileService.save(fileInfo);
        } else {
            code = 500;
            message = "请上传文件";
        }
        result.put("code", code);
        result.put("message", message);
        return result;
    }

    /**
     * @param req
     * @return
     */
    @LogAnnotation(module = "file-center", recordRequestParam = false)
    @ApiOperation(value = "上传文件", notes = "支持多个文件批量上传")
    @RequestMapping(value = "/files/upload2", method = RequestMethod.POST, produces = {"application/json"})
    public Map<String, Object> upload2(HttpServletRequest req) {
        HashMap<String, Object> result = new HashMap<>();
        int code = HttpStatus.SC_OK;
        String message = "Upload file success";
        List<String> ids = new ArrayList<>();
        try {
            Collection<Part> parts = req.getParts();
            String nameSpace = req.getHeader("namespace");
            if (nameSpace == null || nameSpace.isEmpty()) {
                nameSpace = "wudi";
            }
            List<SmallFile> smallFiles = new ArrayList<>();
            for (Part part : parts) {
                SmallFile smallFile = new SmallFile();
                smallFile.setName(part.getSubmittedFileName());
                smallFile.setContentType(part.getContentType());
                smallFile.setNameSpace(nameSpace);
                try (InputStream is = part.getInputStream()) {
                    smallFile.setContent(new Binary(IOUtils.toByteArray(is)));
                }
                logger.debug("smallFile=" + smallFile.toString());
                smallFiles.add(smallFile);
            }
            if (smallFiles.size() == 0) {
                code = HttpStatus.SC_BAD_REQUEST;
                message = "Not Receive a file";
            } else {
                boolean success = hfileService.saveFiles(smallFiles);
                if (!success) {
                    code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
                    message = "Upload file fail";
                } else {
                    for (SmallFile file : smallFiles) {
                        ids.add("/show/" + nameSpace + "/" + file.getId());
                    }
                }
            }
        } catch (Exception e) {
            code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
            message = "Upload file fail";
            logger.error("Upload occur {} .", e.getMessage(), e);
        }
        result.put("code", code);
        result.put("data", ids);
        result.put("message", message);
        return result;
    }

    /**
     *
     * @param namespace 命名空间
     * @param id        唯一标识
     * @param endSuffix 后缀
     * @param downAddress 下载地址
     * @param response 输出流
     */
    @ApiOperation(value = "下载文件", notes = "根据文件在Hbase中的唯一ID下载")
    @RequestMapping(value = "/show/{namespace}/{id}", method = RequestMethod.GET)
    public void show(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "id") String id,
                     @RequestParam(required = false) String endSuffix, @RequestParam(required = false) String downAddress,
                     HttpServletResponse response) throws UnsupportedEncodingException {
        Optional<SmallFile> optional = hfileService.getFileById(namespace, id);
        if (optional.isPresent()) {
            SmallFile smallFile = optional.get();
            logger.info(smallFile.toString());
            byte[] content = smallFile.getContent().getData();
            response.setContentType(smallFile.getContentType());
            logger.info("获取到的文件名称为："+smallFile.getName());
            if (!smallFile.getContentType().contains("image")){
                response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(smallFile.getName(), "utf-8"));
            }
            //如果下载地址指定
            if (StringUtils.isNotEmpty(endSuffix) && StringUtils.isNotEmpty(downAddress)) {
                String fileName=id+endSuffix;
                //下载文件
                downAddressFile(response,content,downAddress,fileName);
            }
            try {
                response.getOutputStream().write(content);
                response.flushBuffer();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
    }

    @ApiOperation(value = "获取文件的二进制流", notes = "根据文件在Hbase中的唯一ID下载")
    @RequestMapping(value = "/downFileByByte/{namespace}/{id}", method = RequestMethod.GET)
    public byte[] downFileByByte(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "id") String id) {
        byte[] content=null;
        Optional<SmallFile> optional = hfileService.getFileById(namespace, id);
        if (optional.isPresent()) {
            SmallFile smallFile = optional.get();
            logger.info(smallFile.toString());
            content= smallFile.getContent().getData();
        }
        return content;
    }

    /**
     * 下载文件到本地
     * @param response
     * @param content
     * @param fileName
     */
    public void downAddressFile(HttpServletResponse response,byte[] content,String downAddress,String fileName){
        response.setHeader("Content-Disposition", "attachment;fileName=" +fileName);
        if (!downAddress.endsWith("/")){
            downAddress+="/";
        }
        //获取要下载的文件的绝对路径
        String realPath = downAddress + fileName;
        File file = new File(realPath);
        if (!file.exists()) {
            boolean flag = false;
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                logger.info("创建文件失败。");
            }
            logger.info("新建文件状态：{}", flag);
        }
        //创建BufferedOutputStream流
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(content);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    @ApiOperation(value = "下载文件", notes = "根据文件在Hbase中的唯一ID下载")
    @RequestMapping(value = "/list/{namespace}", method = RequestMethod.GET)
    public void list(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "id") String id, HttpServletResponse response, HttpServletRequest request) {
        Optional<SmallFile> optional = hfileService.getFileById(namespace, id);
        if (optional.isPresent()) {
            SmallFile smallFile = optional.get();
            if (smallFile != null) {
                logger.info(smallFile.toString());
                String fileName = smallFile.getName();
                String agent = request.getHeader("User-agent");

                byte[] content = smallFile.getContent().getData();
                try {
                    if (agent.indexOf("Firefox") != -1) {
                        response.addHeader("content-Disposition", "attachment;fileName==?UTF-8?B?" + (Base64.getEncoder().encodeToString(fileName.getBytes("utf-8"))) + "?=");
                    } else if (agent.indexOf("Edge") != -1) {
                        response.addHeader("content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
                    }
                    response.setContentType(smallFile.getContentType());
                    response.getOutputStream().write(content);
                    response.flushBuffer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * @param namespace
     * @param name
     * @param response
     */
    @ApiOperation(value = "下载文件", notes = "根据文件名下载")
    @RequestMapping(value = "showByName")
    public void showByName(@RequestParam(value = "namespace") String namespace,
                           @RequestParam(value = "name") String name,
                           HttpServletResponse response) {
        Optional<SmallFile> optional = hfileService.getFileByName(namespace, name);
        if (optional.isPresent()) {
            SmallFile smallFile = optional.get();
            byte[] content = smallFile.getContent().getData();
            try {
                response.setContentType(smallFile.getContentType());
                response.getOutputStream().write(content);
                response.flushBuffer();
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
