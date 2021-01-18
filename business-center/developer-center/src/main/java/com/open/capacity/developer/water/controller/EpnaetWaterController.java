package com.open.capacity.developer.water.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.capacity.common.web.Result;
import com.open.capacity.developer.water.entity.WaterBasicModel;
import com.open.capacity.developer.water.service.IWaterService;
import com.open.capacity.developer.water.util.RestTemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
@RestController
@RequestMapping("/epnaet-water")
@Api(tags = "水利模型-API")
@Slf4j
public class EpnaetWaterController {

    @Qualifier("MyRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IWaterService waterService;

    @Value("${internal.fileCenter.address}")
    public String fileCenterAddress;

    @Value("${internal.fileCenter.nameSpace}")
    public String nameSpace;

    @Value("${internal.cache.address}")
    public String cacheAddress;

    @RequestMapping("/uploadEnactFile")
    @ApiOperation("水利模型文件上传")
    @ApiImplicitParam(name = "file", value = "水利模型文件", required = true, dataType = "file", paramType = "form")
    public Result uploadEnactFile(@RequestParam("file") MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf('.'));
            // 此处限制所有的水利模型文件都为inp 结尾
            if (!".inp".equals(suffixName)) {
                return Result.failed("请上传后缀名为[.inp]的水利模型文件");
            } else {
                // 得到响应体
                Map map = null;
                try {
                    ByteArrayResource is =
                            new ByteArrayResource(file.getBytes()) {
                                @Override
                                public String getFilename() {
                                    return file.getOriginalFilename();
                                }
                            };
                    // 设置请求头
                    HttpHeaders headers = new HttpHeaders();
                    MediaType type = MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
                    headers.setContentType(type);
                    // 设置请求体
                    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
                    form.add("file", is);
                    form.add("namespace", nameSpace);
                    // 构建http请求体
                    HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
                    map = restTemplate.postForObject(fileCenterAddress + "/files/upload", files, Map.class);
                } catch (Exception e) {
                    log.error("调用文件中心-文件上传服务异常。");
                    log.error(e.getMessage());
                    return Result.failed("文件上传服务异常，请重试");
                }
                // 上传成功
                if ("0".equals(String.valueOf(map.get("code")))) {
                    return Result.succeed(fileCenterAddress + map.get("data"), "上传水利模型文件成功");
                } else {
                    return Result.failed(String.valueOf(map.get("message")));
                }
            }
        } else {
            return Result.failed("请上传水利模型文件");
        }
    }

    /**
     * 水利模型文件解析并保存
     *
     * @param waterBasicModel
     * @return
     */
    @RequestMapping("/translateEnactInfo")
    @ApiOperation("水利模型文件解析")
    public Result translateEnactInfo(@RequestBody @Valid WaterBasicModel waterBasicModel) {
        log.info("进入水利模型文件解析控制器...");
        if (waterService.insertWaterModel(waterBasicModel) > 0) {
            log.info("保存水利模型基础信息成功...");
            return Result.succeed("保存水利模型信息成功");
        } else {
            log.info("保存水利模型基础信息失败...");
            return Result.failed("保存水利模型信息失败");
        }
    }

    @RequestMapping("/findWaterModelInfo")
    @ApiOperation("查询水利模型文件信息列表")
    @ApiImplicitParam(name = "clientId", value = "应用id", required = true)
    public Result findWaterBasicModelInfo(Integer clientId) {
        log.info("进入查询水利模型基础信息控制器...");
        if (clientId == null) {
            log.info("请求参数clientId不能为空");
            return Result.failed("请求参数不能为空");
        }
        List<Map<String, Object>> list = waterService.quertWaterBasicInfo(clientId);
        log.info("根据clientId={}查询出的水利模型基础信息列表：{}", clientId, list);
        return Result.succeed(list, "查询成功");
    }

    /**
     * 查询高德地图点位、线段信息集合
     *
     * @param modelId ：模型ID
     * @return
     */
    @RequestMapping("/findGuidesInfo")
    @ApiOperation("查询高德地图点位、管线信息")
    @ApiImplicitParam(name = "modelId", value = "模型id", required = true)
    public Result findGuidesInfo(@RequestParam Integer modelId) {
        if (modelId == null) {
            log.info("请求参数modelId不能为空");
            return Result.failed("请求参数不能为空");
        }
        return Result.succeed(waterService.queryWaterGuides(modelId), "查询成功");
    }

    //漏损算法接口地址
    private static final String LEAKURL = "";

    /**
     * 漏损算法
     * （暂时不集成）
     */
    @RequestMapping("/leakageCalc")
    @ApiOperation("漏损算法")
    @ApiImplicitParam(name = "params", value = "参数", required = true)
    public Result leakageCalc(@RequestParam Map<String, Object> params) {

        String jsonParams = JSON.toJSONString(params);
        String s = RestTemplateUtil.postWithToken(LEAKURL, jsonParams);
        log.info("调用漏损算法成功");
        return returnResult(s);
    }

    private Result returnResult(String request) {

        JSONObject jsonObject = JSONObject.parseObject(request);
        if (jsonObject.getString("").equals("0")) {
            return Result.succeed(jsonObject.get("data"), jsonObject.getString("remark"));
        } else {
            return Result.failed(jsonObject.getString("remark"));
        }
    }

    @RequestMapping("/deleteBasciWaterInfo")
    @ApiOperation("删除水利模型基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模型ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "fileAddress", value = "模型文件地址", required = true, dataType = "String"),
    })
    public Result deleteBasciWaterInfo(@RequestParam Integer modelId,@RequestParam String fileAddress){
        if(modelId==null || StringUtils.isEmpty(fileAddress)){
            return Result.failed("请传输正确的参数。");
        }
        if (waterService.deleteWaterBasciInfo(modelId,fileAddress)>0){
            return Result.succeed("删除成功");
        }else{
            return Result.failed("删除失败,此记录不存在");
        }
    }

    @RequestMapping("/updateBasciWaterInfo")
    @ApiOperation("修改水利模型基础信息")
    public Result updateBasciWaterInfo(@RequestBody @Valid WaterBasicModel waterBasicModel){
        if (waterBasicModel.getId()==null){
            return Result.failed("参数:[id]必传！");
        }
        if (waterService.updateWaterBasicInfo(waterBasicModel)){
            return Result.succeed("更新成功");
        }else {
            return Result.failed("更新失败");
        }
    }

}
