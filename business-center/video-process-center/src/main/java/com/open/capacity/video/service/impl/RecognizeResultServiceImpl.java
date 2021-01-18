package com.open.capacity.video.service.impl;

import com.open.capacity.video.dao.RecognizeResultDao;
import com.open.capacity.video.entity.RecognizeResult;
import com.open.capacity.video.service.RecognizeResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 单帧处理结果 service
 *
 * @author donglh
 * @date 2020/7/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RecognizeResultServiceImpl implements RecognizeResultService, Serializable {

    private final RecognizeResultDao recognizeResultDao;

    @Override
    public void saveOne(RecognizeResult result) {
        recognizeResultDao.saveOne(result);
    }
}
