package com.open.capacity.activiticenter.service;

import java.util.HashMap;

public interface ProcessService {

    HashMap<String, Object> startProcessInstance(String key);

    HashMap<String, Object> processList();

    HashMap<String, Object> viewProcess(String Id);
}
