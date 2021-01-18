package com.open.capacity.user.controller;

import com.open.capacity.common.exception.controller.ControllerException;
import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysDept;
import com.open.capacity.common.model.SysUser;
import com.open.capacity.common.web.Result;
import com.open.capacity.user.model.Department;
import com.open.capacity.user.service.SysTreeService;
import com.open.capacity.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "DEPT API")
@RequestMapping("/depts")
public class SysDeptController {

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 组织架构
     * @return
     * @throws ControllerException
     */
    @GetMapping(value = "/deptList")
    @ApiOperation(value = "部门查询")
    public Department deptList() throws ControllerException {
        List<SysDept> deptById = sysTreeService.getDeptById();
        //公司
        Department company = new Department();
        //一级部门
        List<SysDept> allDept = sysTreeService.getAllDept();
        allDept.forEach(depts->{
            Department departs = new Department();
            departs.setDeptName(depts.getName());
            departs.setId(depts.getId());
            //二级部门
            List<SysDept> deptByParents = sysTreeService.getDeptByParentId(depts.getId());
            deptByParents.forEach(dept->{
                Department depart = new Department();
                depart.setDeptName(dept.getName());
                depart.setId(dept.getId());
                departs.add(depart);
                //三级部门
                List<SysDept> deptByParent = sysTreeService.getDeptByParentId(dept.getId());
                if (deptByParent!=null){
                    deptByParent.forEach(d->{
                        Department a = new Department();
                        a.setDeptName(d.getName());
                        a.setId(d.getId());
                        depart.add(a);
                    });
                }
            });
            deptById.forEach(a->{
                company.setDeptName(a.getName());
                company.add(departs);
            });
        });
        return company;
    }

    /**
     * 根据部门id查询用户
     * @param id
     * @return
     * @throws ControllerException
     */
    @PostMapping("/getUsers")
    @ApiOperation(value = "根据部门id查询用户")
    public List<SysUser> getUser(long id) throws ControllerException {

        return sysUserService.selectByDeptId(id);
    }


    /**
     * 添加或更新
     * @param sysDept
     * @return
     * @throws ServiceException
     */
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加或更新")
    public Result saveOrUpdate(SysDept sysDept) throws ServiceException {
        return sysTreeService.saveOrUpdate(sysDept);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @PostMapping("/delete")
    @ApiOperation(value = "根据id删除")
    public Result delete(Long id) {
        return sysTreeService.delete(id);
    }
}
