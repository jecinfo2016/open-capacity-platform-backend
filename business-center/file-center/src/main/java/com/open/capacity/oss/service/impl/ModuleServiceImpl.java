package com.open.capacity.oss.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.oss.dao.ModuleDao;
import com.open.capacity.oss.model.ModuleInfo;
import com.open.capacity.oss.service.HFileService;
import com.open.capacity.oss.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleDao moduleDao;
    @Autowired
    HFileService hfileService;

    private Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);

    @Override
    public PageResult<ModuleInfo> findAll(ModuleInfo module) {

        if (null != module.getPage() && null != module.getLimit())
            PageHelper.startPage(module.getPage(), module.getLimit(), true);

        List<ModuleInfo> all = moduleDao.findAll(module);
        PageInfo<ModuleInfo> pageInfo = new PageInfo(all);
        return PageResult.<ModuleInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public ModuleInfo getById(Integer Id) {
        return moduleDao.getById(Id);
    }

    @Override
    public int update(ModuleInfo module) {
        return moduleDao.update(module);
    }

    @Override
    public int updateFile(ModuleInfo module) {
        return moduleDao.updateFile(module);
    }

    @Override
    public int save(ModuleInfo module) {

        LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
        //发送人
        if (null != loginAppUser) {
            module.setCreater(loginAppUser.getUsername());
        } else{
            //默认是管理员
            module.setCreater("admin");
        }
        return moduleDao.save(module);
    }

    @Override
    public int delete(Integer Id) {

        ModuleInfo moduleInfo = moduleDao.getById(Id);
        String showUrl = moduleInfo.getShowUrl();

        try {
            if (showUrl.contains("/")){
                String HId = showUrl.substring(showUrl.lastIndexOf("/")+1);
                String HNameStr = showUrl.substring(showUrl.indexOf("/"), showUrl.lastIndexOf("/"));
                String HName = HNameStr.substring(HNameStr.lastIndexOf("/") + 1);

                //删除hbase文档和文档记录
                hfileService.removeFile(HName, HId);
                hfileService.delete(HId);
            }
        } catch (Exception e) {
            logger.error("hbase delete exception ：", e.getMessage(), e);
        }

        return moduleDao.delete(Id);
    }
}
