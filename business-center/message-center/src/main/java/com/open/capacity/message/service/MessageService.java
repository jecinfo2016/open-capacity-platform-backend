package com.open.capacity.message.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.message.model.Message;

import java.util.List;

public interface MessageService {

    int save(Message message);

    int update(Message message);

    int delete(Long id);

    PageResult<Message> findAll(Message message);

    int countByType(Integer type);

    /**
     * 查询应用名称
     */
    List<Message> findAppName();
}
