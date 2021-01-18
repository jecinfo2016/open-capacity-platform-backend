package com.open.capacity.oss.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.oss.dao.FileDao;
import com.open.capacity.oss.dao.SmallFileDao;
import com.open.capacity.oss.model.FileInfo;
import com.open.capacity.oss.model.SmallFile;
import com.open.capacity.oss.service.HFileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: com.sinorail.service.impl
 * @Author: shiyu
 * @CreateTime: 2019-05-05
 */
@Service
public class HFileServicesImpl implements HFileService {

    @Autowired
    private SmallFileDao smallFileDao;

    @Autowired
    private FileDao fileDao;

    @Transactional
    @Override
    public boolean saveFile(SmallFile smallFile) {
        //Id生成策略
        //UUID
        //时间戳+自增长的编号
        smallFile.setId(UUID.randomUUID().toString());
        smallFile.setUploadDate(LocalDateTime.now());
        smallFile.setMd5(DigestUtils.md5Hex(smallFile.getContent().getData()));
        return smallFileDao.save(smallFile);
    }

    @Transactional
    @Override
    public boolean saveFiles(List<SmallFile> smallFiles) {

        for (SmallFile smallFile : smallFiles) {
            smallFile.setId(UUID.randomUUID().toString());
            smallFile.setUploadDate(LocalDateTime.now());
            smallFile.setMd5(DigestUtils.md5Hex(smallFile.getContent().getData()));
        }
        return smallFileDao.saveFiles(smallFiles);
    }

    @Transactional
    @Override
    public void removeFile(String namespace, String id) {
        smallFileDao.delete(namespace, id);
    }

    @Transactional
    @Override
    public void removeFileByName(String namespace, String name) {
        String rowKey = DigestUtils.md5Hex(name);
        removeFile(namespace,rowKey);
    }

    @Override
    public Optional<SmallFile> getFileById(String namespace, String id) {
        return Optional.ofNullable(smallFileDao.find(namespace, id));
    }

    @Override
    public Optional<SmallFile> getFileByName(String namespace, String name) {
        String rowKey = DigestUtils.md5Hex(name);
        return getFileById(namespace, rowKey);
    }

    @Override
    public PageResult<FileInfo> findList(Map<String, Object> params) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<FileInfo> list = fileDao.findList(params);
        PageInfo<FileInfo> pageInfo = new PageInfo<>(list);
        return PageResult.<FileInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public int save(FileInfo fileInfo) {
        return fileDao.save(fileInfo);
    }

    @Override
    public int delete(String id) {
        return fileDao.delete(id);
    }
}