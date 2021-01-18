package com.open.capacity.user.controller;

import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 *用户
 */
@Slf4j
@RestController
@Api(tags = "LOGIN API")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/users-anon/login", params = "username")
    @ApiOperation(value = "根据用户名查询用户")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public LoginAppUser findByUsername(String username) {
        return sysUserService.findByUsername(username);
    }


}
