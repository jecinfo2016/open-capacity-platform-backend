package com.open.capacity.oss.dao;

import com.open.capacity.oss.model.SmallFile;

import java.util.List;

/**
 * @BelongsProject: servlet-photo-storage-system
 * @BelongsPackage: com.sinorail.dao
 * @Author: shiyu
 * @CreateTime: 2019-05-05
 */
public interface SmallFileDao {

    boolean save(SmallFile smallFile);

    boolean saveFiles(List<SmallFile> smallFiles);

    void delete(String namespace, String id);

    SmallFile find(String namespace, String id);

}
