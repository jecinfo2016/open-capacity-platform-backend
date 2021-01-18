package com.open.capacity.uaa.service.impl;

import com.open.capacity.uaa.dao.DevUserDao;
import com.open.capacity.uaa.model.DevUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.StringUtil;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.uaa.feign.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignClient  userFeignClient;
    @Autowired
    private DevUserDao devUserDao;

    @Override
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginAppUser loginAppUser = null;
        if(username.startsWith("/devuser/")) {
            loginAppUser = new LoginAppUser();
            username = username.substring(9);
            DevUser user = devUserDao.selectByUsername(username);
            BeanUtils.copyProperties(user, loginAppUser);
            loginAppUser.setEnabled(true);
            loginAppUser.setNickname(user.getRealname());
            return loginAppUser;
        }

        if (StringUtil.isPhone(username)){
            loginAppUser = userFeignClient.findByMobile(username);
        }else {
            //      后续考虑集成spring socail,支持多种类型登录
            loginAppUser = userFeignClient.findByUsername(username);   			  //方式1  feign调用       对外feign resttemplate
//        loginAppUser = userLoginGrpc.findByUsername(username);		  //方式2  gprc调用		对内grpc dubbo
        }

        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }

        return loginAppUser;
    }



}
