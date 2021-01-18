package com.open.capacity.workflow.service;


import com.open.capacity.workflow.domain.dto.ActivitiHighLineDTO;

public interface IActivitiHistoryService {
    public ActivitiHighLineDTO gethighLine(String instanceId);
}
