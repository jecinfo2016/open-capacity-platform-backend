package com.open.capacity.workflow.controller;

import com.open.capacity.workflow.constant.AjaxResult;
import com.open.capacity.workflow.constant.BaseController;
import com.open.capacity.workflow.constant.TableDataInfo;
import com.open.capacity.workflow.domain.WorkflowLeave;
import com.open.capacity.workflow.service.IWorkflowLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请假Controller
 *
 * @author danny
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/workflow/leave")
@CrossOrigin(origins = "*")
public class WorkflowLeaveController extends BaseController {
    @Autowired
    private IWorkflowLeaveService workflowLeaveService;

    /**
     * 查询请假列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WorkflowLeave workflowLeave) {
        startPage();
//        workflowLeave.setCreateBy(SecurityUtils.getUsername());
        List<WorkflowLeave> list = workflowLeaveService.selectWorkflowLeaveAndTaskNameList(workflowLeave);
        return getDataTable(list);
    }
//    /**
//     * 查询请假列表
//     */
//    @GetMapping("/listAll")
//    public TableDataInfo listAll(WorkflowLeave workflowLeave) {
//        startPage();
//        List<WorkflowLeave> list = workflowLeaveService.selectWorkflowLeaveList(workflowLeave);
//        return getDataTable(list);
//    }

//    /**
//     * 导出请假列表
//     */
//    @GetMapping("/export")
//    public AjaxResult export(WorkflowLeave workflowLeave) {
//        List<WorkflowLeave> list = workflowLeaveService.selectWorkflowLeaveList(workflowLeave);
//        ExcelUtil<WorkflowLeave> util = new ExcelUtil<WorkflowLeave>(WorkflowLeave.class);
//        return util.exportExcel(list, "leave");
//    }

    /**
     * 获取请假详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(workflowLeaveService.selectWorkflowLeaveById(id));
    }   /**
     * 获取请假详细信息
     */
    @GetMapping(value = "ByInstanceId/{instanceId}")
    public AjaxResult getInfoByInstanceId(@PathVariable("instanceId") String instanceId) {
        return AjaxResult.success(workflowLeaveService.selectWorkflowLeaveByInstanceId(instanceId));
    }

//    /**
//     * 新增请假
//     */
//    @PostMapping
//    public AjaxResult add(@RequestBody WorkflowLeave workflowLeave) {
//        return toAjax(workflowLeaveService.insertWorkflowLeave(workflowLeave));
//    }
//
//    /**
//     * 修改请假
//     */
//    @PutMapping
//    public AjaxResult edit(@RequestBody WorkflowLeave workflowLeave) {
//        return toAjax(workflowLeaveService.insertWorkflowLeave(workflowLeave));
//    }

    /**
     * 删除请假
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowLeaveService.deleteWorkflowLeaveByIds(ids));
    }
}
