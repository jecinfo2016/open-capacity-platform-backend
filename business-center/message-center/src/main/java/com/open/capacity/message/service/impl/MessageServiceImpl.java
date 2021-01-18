package com.open.capacity.message.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.message.dao.MessageDao;
import com.open.capacity.message.model.Message;
import com.open.capacity.message.service.MessageService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public int save(Message message) {
        return messageDao.save(message);
    }

    @Override
    public int update(Message message) {
        return messageDao.update(message);
    }

    @Override
    public int delete(Long id) {
        return messageDao.delete(id);
    }

    @Override
    public PageResult<Message> findAll(Message message) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (null != message.getPage() && null != message.getLimit())
            PageHelper.startPage(message.getPage(), message.getLimit(), true);
        LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
        if (null != loginAppUser) {
            message.setSendUser(loginAppUser.getId().intValue());
        } else {
            message.setSendUser(1);
        }
        List<Message> list = messageDao.findAll(message);
        //对list进行排序

        PageInfo<Message> pageInfo = new PageInfo(list);
        return PageResult.<Message>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }


    @Override
    public int countByType(Integer type) {
        int result = 0;
        LoginAppUser user = SysUserUtil.getLoginAppUser();
        Message message = new Message();
        message.setType(type);
        if (null != user) {
            message.setSendUser(user.getId().intValue());
        } else {
            message.setSendUser(1);
        }
        result = messageDao.countByType(message);
        return result;
    }

    @Override
    public List<Message> findAppName() {
        return messageDao.findAppName();
    }
}
