package com.open.capacity.display.service.impl;

import com.open.capacity.display.dao.IDisplayDao;
import com.open.capacity.display.entity.DisplayBaseInfo;
import com.open.capacity.display.exception.CustomException;
import com.open.capacity.display.service.IDisplayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 * @Classname DisplayServiceImpl
 * @Date 2020/9/11 11:54
 */
@Service
@Slf4j
public class DisplayServiceImpl implements IDisplayService {

    @Autowired
    IDisplayDao displayDao;

    @Override
    public List<String> queryDatabases() {
        List<String> list = null;
        try {
            list = displayDao.queryDatabases();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("查询数据库名称异常");
        }
        return list;
    }

    @Override
    public List<Map> queryTablesByDbname(String dbName) {
        List<Map> list = null;
        try {
            list = displayDao.queryTablesByDbname(dbName);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("根据数据库名称查询表集合异常");
        }
        return list;
    }

    @Override
    public List<Map> queryColumnsByTableName(String dbName, String tableName) {
        List<Map> list = null;
        try {
            list = displayDao.queryColumnsByTableName(dbName, tableName);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("根据表名称查询字段集合异常");
        }
        return list;
    }

    @Override
    public int saveOrUpdateDisplayInfo(DisplayBaseInfo displayBaseInfo) {
        int row = 0;
        try {
            //修改
            if (displayBaseInfo.getId() != null) {
                row = displayDao.updateDisplayInfo(displayBaseInfo);
            } else {
                row = displayDao.saveDisplayInfo(displayBaseInfo);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("保存、修改统计信息异常");
        }
        return row;
    }

    @Override
    public int deleteDisplayInfo(Integer id) {
        int row = 0;
        try {
            row = displayDao.deleteDisplayInfo(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("删除统计信息异常");
        }
        return row;
    }

    @Override
    public List<DisplayBaseInfo> queryDisplayInfo(DisplayBaseInfo displayBaseInfo) {
        List<DisplayBaseInfo> list;
        try {
            list = displayDao.queryDisplayInfo(displayBaseInfo);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("查询展示列表异常");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> queryDataList(String databaseName, String tableName, String columnName, String mode) {
        List<Map<String, Object>> list=null;
        try {
            if(StringUtils.isEmpty(mode) || "none".equals(mode)){
                list = displayDao.queryDataList(databaseName,tableName,columnName);
            }else {
                list = displayDao.queryDataListByGroup(databaseName,tableName,columnName,mode);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("从数据库查询数据异常");
        }
        return list;
    }
}
