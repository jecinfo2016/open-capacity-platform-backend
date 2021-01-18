package com.open.capacity.oss.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.oss.model.ModuleInfo;

import java.util.List;

public interface ModuleService {

    PageResult<ModuleInfo> findAll(ModuleInfo module);

    ModuleInfo getById(Integer Id);

    int update(ModuleInfo module);

    int updateFile(ModuleInfo module);

    int save(ModuleInfo module);

    int delete(Integer Id);
}
