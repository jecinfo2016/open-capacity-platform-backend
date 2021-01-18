package com.open.capacity.user.service;

import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysDept;
import com.open.capacity.common.model.SysUser;
import com.open.capacity.common.web.Result;

import java.util.List;

public interface SysTreeService {


    List<SysDept> getAllDept()  throws ServiceException;


    List<SysDept> getDeptByParentId(Long id);

    List<SysDept> getDeptById();

    /**
     * 更新
     * @param sysDept
     * @return
     */
    Result saveOrUpdate(SysDept sysDept)  throws ServiceException;

    /**
     * 根据id删除
     * @param id
     */
    Result delete(Long id);
}
