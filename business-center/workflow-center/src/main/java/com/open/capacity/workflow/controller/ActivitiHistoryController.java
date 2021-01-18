package com.open.capacity.workflow.controller;


import com.open.capacity.workflow.constant.AjaxResult;
import com.open.capacity.workflow.domain.dto.ActivitiHighLineDTO;
import com.open.capacity.workflow.service.IActivitiHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/activitiHistory")
@CrossOrigin(origins = "*")
public class ActivitiHistoryController {

    @Autowired
    private IActivitiHistoryService activitiHistoryService;

    //流程图高亮
    @GetMapping("/gethighLine")
    public AjaxResult gethighLine(@RequestParam("instanceId") String instanceId) {

        ActivitiHighLineDTO activitiHighLineDTO = activitiHistoryService.gethighLine(instanceId);
        return AjaxResult.success(activitiHighLineDTO);


    }


}
