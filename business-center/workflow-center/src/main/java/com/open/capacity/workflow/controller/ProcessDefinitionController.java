package com.open.capacity.workflow.controller;


import com.open.capacity.workflow.constant.*;
import com.open.capacity.workflow.domain.dto.ProcessDefinitionDTO;
import com.open.capacity.workflow.service.IProcessDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 汇讯数码科技(深圳)有限公司
 * 创建日期:2020/10/22-15:16
 * 版本   开发者     日期
 * 1.0    Danny    2020/10/22
 * @author JK
 */
@RestController
@RequestMapping("/processDefinition")
@CrossOrigin(origins = "*")
public class ProcessDefinitionController extends BaseController {

    @Autowired
    private IProcessDefinitionService processDefinitionService;


    /**
     * 获取流程定义集合
     *
     * @param processDefinition
     * @return
     */
    @GetMapping(value = "/list")
    public TableDataInfo list(ProcessDefinitionDTO processDefinition) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return getDataTable(processDefinitionService.selectProcessDefinitionList(processDefinition, pageDomain));

    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/getDefinitions/{instanceId}")
    public AjaxResult getDefinitionsByInstanceId(@PathVariable("instanceId") String instanceId){
        return AjaxResult.success(processDefinitionService.getDefinitionsByInstanceId(instanceId));
    }

    /**
     * 删除流程定义
     *
     * @param deploymentId
     * @return
     */
    @DeleteMapping(value = "/remove/{deploymentId}")
    public AjaxResult delDefinition(@PathVariable("deploymentId") String deploymentId) {
        return toAjax(processDefinitionService.deleteProcessDefinitionById(deploymentId));
    }

    /**
     * 上传并部署流程定义
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/uploadStreamAndDeployment")
    public AjaxResult uploadStreamAndDeployment(@RequestParam("file") MultipartFile file) throws IOException {
        processDefinitionService.uploadStreamAndDeployment(file);
        return AjaxResult.success();

    }

    /**
     * 启动挂起流程流程定义
     *
     * @param processDefinition
     * @return
     */
    @PostMapping("/suspendOrActiveApply")
    @ResponseBody
    public AjaxResult suspendOrActiveApply(@RequestBody ProcessDefinitionDTO processDefinition) {
        processDefinitionService.suspendOrActiveApply(processDefinition.getId(), processDefinition.getSuspendState());
        return AjaxResult.success();
    }

//    /**
//     * 上传流程流程定义
//     *
//     * @param multipartFile
//     * @return
//     * @throws IOException
//     */
//    @PostMapping(value = "/upload")
//    public AjaxResult upload(@RequestParam("processFile") MultipartFile multipartFile) throws IOException {
//
//        if (!multipartFile.isEmpty()) {
//            String fileName = processDefinitionService.upload(multipartFile);
//            return AjaxResult.success("操作成功", fileName);
//
//        }
//        return AjaxResult.error("不允许上传空文件！");
//    }


    /**
     * 通过stringBPMN添加流程定义
     *
     * @param stringBPMN
     * @return
     */
    @PostMapping(value = "/addDeploymentByString")
    public AjaxResult addDeploymentByString(@RequestParam("stringBPMN") String stringBPMN) {
        processDefinitionService.addDeploymentByString(stringBPMN);
        return AjaxResult.success();

    }


    /**
     * 获取流程定义XML
     *
     * @param response
     * @param deploymentId
     * @param resourceName
     */
    @GetMapping(value = "/getDefinitionXML")
    public void getProcessDefineXML(HttpServletResponse response,
                                    @RequestParam("deploymentId") String deploymentId,
                                    @RequestParam("resourceName") String resourceName) throws IOException {

        processDefinitionService.getProcessDefineXML(response, deploymentId, resourceName);
    }


}
