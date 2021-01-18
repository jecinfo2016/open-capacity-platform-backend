package com.open.capacity.video.service;

import org.springframework.web.multipart.MultipartFile;

import java.rmi.ServerException;

/**
 * 采集
 *
 * @author donglh
 * @date 2020/7/14
 */
public interface CollectorService {

    /**
     * 对视频进行处理
     *
     * @param file 视频流
     * @return 生成的taskId
     */
    String videoCollector(MultipartFile file) throws ServerException;
}
