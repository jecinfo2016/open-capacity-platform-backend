package com.open.capacity.uaa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.constant.UaaConstant;
import com.open.capacity.common.web.Result;
import com.open.capacity.uaa.dao.*;
import com.open.capacity.uaa.dto.SysClientDto;
import com.open.capacity.uaa.model.*;
import com.open.capacity.uaa.service.DevUserService;
import com.open.capacity.uaa.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class DevUserServiceImpl implements DevUserService {
    @Autowired
    private DevUserDao devUserDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysClientDao sysClientDao;
    @Autowired
    private DevUserClientDao devUserClientDao;
    @Autowired
    private SysClientServiceDao sysClientServiceDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    @Override
    public Result saveOrUpdate(DevUser devUser) {
        if (devUser.getId() != null) {
            devUser.setUpdateTime(sd.format(new Date()));
            devUserDao.update(devUser);
        } else {
            devUser.setPassword(passwordEncoder.encode(devUser.getPassword()));
            DevUser r = devUserDao.selectByUsername(devUser.getUsername());
            if (r != null) {
                return Result.failed(devUser.getUsername() + "已存在");
            }
            devUser.setCreateTime(sd.format(new Date()));
            devUser.setUpdateTime(devUser.getCreateTime());
            devUserDao.insert(devUser);
        }
        return Result.succeed("操作成功");
    }

    @Override
    public Long checkUser(String username, String password) {
        DevUser user = devUserDao.selectByUsername(username);
        if (user != null) {
            boolean isPass = passwordEncoder.matches(password, user.getPassword());
            if (!isPass) {
                return -1L;
            }
        } else {
            return -2L;
        }
        return user.getId();
    }

    @Override
    public DevUser getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
        authentication = oAuth2Auth.getUserAuthentication();
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        LoginAppUser loginAppUser = (LoginAppUser) authenticationToken.getPrincipal();
        DevUser devUser = devUserDao.selectByUsername(loginAppUser.getUsername());
        return devUser;
    }

    @Override
    public Result clientSaveOrUpdate(SysClientDto clientDto, Integer groupId) {
        if (clientDto.getClientSecretStr() != null) {
            clientDto.setClientSecret(passwordEncoder.encode(clientDto.getClientSecretStr()));
        }

        if (clientDto.getId() != null) {// 修改
            sysClientDao.update(clientDto);
        } else {// 新增
            //自动生成appSecret
            if (groupId == null) {
                return Result.failed("用户组id为空");
            }
            clientDto.setClientSecretStr(AppUtils.getAppId());
            clientDto.setClientSecret(AppUtils.getAppSecret(clientDto.getClientSecretStr()));
            SysClient r = sysClientDao.getClient(clientDto.getClientId());
            if (r != null) {
                return Result.failed(clientDto.getClientId() + "已存在");
            }
            sysClientDao.save(clientDto);
            devUserClientDao.insert(groupId, clientDto.getId().intValue());
        }
        return Result.succeed("操作成功");
    }

    @Override
    public List<SysService> findServiceAll() {
        return devUserDao.findServiceAll();
    }

    @Override
    public PageResult<SysClient> listRoles(Map<String, Object> params) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        List<SysClient> list = devUserDao.findClientList(params);
        PageInfo<SysClient> pageInfo = new PageInfo<>(list);
        return PageResult.<SysClient>builder().data(pageInfo.getList()).resp_code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    @Transactional
    public Result deleteClient(Long id) {
        sysClientDao.delete(id);
        devUserClientDao.deleteByClientId(id.intValue());
        sysClientServiceDao.delete(id, null);
        redisTemplate.boundHashOps(UaaConstant.CACHE_CLIENT_KEY).delete(id);
//        log.debug("删除应用id:{}", id);
        return Result.succeed("删除应用id:" + id );
    }

    @Override
    public Result updatePassword(Map params) {
        DevUser old = getSelf();
        String oldPassword = devUserDao.findById(old.getId()).getPassword();
        if (passwordEncoder.matches(params.get("oldPassword").toString(), oldPassword)) {
            DevUser devUser = new DevUser();
            devUser.setId(old.getId());
            devUser.setPassword(passwordEncoder.encode(params.get("password").toString()));
            devUser.setUpdateTime(sd.format(new Date()));
            devUserDao.updatePassword(devUser);
        } else {
            return Result.failed("原密码错误");
        }
        return Result.succeed("密码修改成功");
    }

    @Override
    public Result saveGroup(DevGroup devGroup) {
        DevGroup group = devUserDao.selectByName(devGroup);
        if (group != null) {
            return Result.failed("name已存在");
        } else {
            devGroup.setUserId(getSelf().getId().intValue());
            devUserDao.insertGroup(devGroup);
        }
        return Result.succeed("操作成功");
    }

    @Override
    public Result updateGroup(DevGroup devGroup) {
        devUserDao.updateGroup(devGroup);
        return Result.succeed("操作成功");
    }

    @Override
    public PageResult<DevGroup> grouplist(Map<String, Object> params) {
        PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        params.put("userId", getSelf().getId());
        List<DevGroup> list = devUserDao.findGroup(params);
        PageInfo<DevGroup> pageInfo = new PageInfo<>(list);
        return PageResult.<DevGroup>builder().data(pageInfo.getList()).resp_code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public Result deleteGroup(Integer id) {
        devUserDao.deleteGroup(id);
        List<Long> clientIds = devUserClientDao.getClientIds(id);
        for (Long clientId : clientIds) {
            deleteClient(clientId);
        }
        return Result.succeed("删除应用组:" + id);
    }

}
