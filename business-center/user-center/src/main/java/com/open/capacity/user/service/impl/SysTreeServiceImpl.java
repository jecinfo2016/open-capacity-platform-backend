package com.open.capacity.user.service.impl;

import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysDept;
import com.open.capacity.common.model.SysUser;
import com.open.capacity.common.web.Result;
import com.open.capacity.user.dao.SysDeptDao;
import com.open.capacity.user.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SysTreeServiceImpl implements SysTreeService {


    @Autowired
    private SysDeptDao SysDeptDao;


    @Override
    public List<SysDept> getAllDept() throws ServiceException {
        return SysDeptDao.getAllDept();
    }

    @Override
    public List<SysDept> getDeptByParentId(Long id) {
        return SysDeptDao.getDeptByParentId(id);
    }

    @Override
    public List<SysDept> getDeptById() {
        return SysDeptDao.getDeptById();
    }

    /**
     * 添加或更新
     * @param sysDept
     * @return
     * @throws ServiceException
     */
    @Override
    public Result saveOrUpdate(SysDept sysDept) throws ServiceException {
        int i = 0;
        if (sysDept.getId()!=null) {
             i = SysDeptDao.update(sysDept);
        }else {
             i = SysDeptDao.save(sysDept);
        }
             return i > 0 ? Result.succeed(sysDept, "操作成功") : Result.failed("操作失败");
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public Result delete(Long id) {
        return SysDeptDao.delete(id)==1? Result.succeed("操作成功") : Result.failed("操作失败");
    }
}
