package com.open.capacity.video.service;

import com.open.capacity.video.entity.RecognizeResult;

/**
 * 单帧识别结果service
 *
 * @author donglh
 * @date 2020/7/20
 */
public interface RecognizeResultService {

    /**
     * 保存一条数据
     *
     * @param result 单帧处理结果
     */
    void saveOne(RecognizeResult result);

}
