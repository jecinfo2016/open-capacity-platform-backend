package com.open.capacity.user.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.common.annotation.ApiIdempotent;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.constant.UserType;
import com.open.capacity.common.exception.controller.ControllerException;
import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysRole;
import com.open.capacity.common.model.SysUser;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.util.ValidatorUtil;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.user.model.SysUserExcel;
import com.open.capacity.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 *用户
 */
@Slf4j
@RestController
@Api(tags = "USER API")
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${file.upload.url}")
    public String urls;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 当前登录用户 LoginAppUser
     *
     * @return
     * @throws ControllerException 
     * @throws JsonProcessingException 
     */
    @ApiOperation(value = "根据access_token当前登录用户")
    @GetMapping("/users/current")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public LoginAppUser getLoginAppUser() throws ControllerException   {
		
		try {
			LoginAppUser loginUser = SysUserUtil.getLoginAppUser();
			return loginUser ;
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
        
    }

    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    @ApiOperation(value = "根据用户名查询手机号")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public LoginAppUser findByMobile(String mobile) throws ControllerException {
        try {
			return sysUserService.findByMobile(mobile);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
    }

    @PreAuthorize("hasAuthority('user:get/users/{id}')")
    @GetMapping("/findUserById/{id}")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public SysUser findUserById(@PathVariable Long id) {
        return sysUserService.findById(id);
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id
     * @param newPassword
     * @throws ControllerException 
     */
    @PreAuthorize("hasAnyAuthority('user:post/users/{id}/resetPassword')")
    @PostMapping(value = "/resetPassword/{id}/password", params = {"newPassword"})
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result resetPassword(@PathVariable Long id, String newPassword) {
        return sysUserService.resetPassword(id, null, newPassword);
    }

    /**
     * 管理后台修改用户
     *
     * @param sysUser
     * @throws JsonProcessingException 
     */
    @PreAuthorize("hasAuthority('user:put/users/me')")
    @PutMapping("/updateSysUser")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public void updateSysUser(@RequestBody SysUser sysUser) throws ControllerException {
        try {
			sysUserService.updateSysUser(sysUser);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
    }

    /**
     * 管理后台给用户分配角色
     *
     * @param id
     * @param roleIds
     * @throws JsonProcessingException 
     */
    @PreAuthorize("hasAuthority('user:post/users/{id}/roles')")
    @PostMapping("/setRoleToUser/{id}/roles")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) throws JsonProcessingException {
        sysUserService.setRoleToUser(id, roleIds);
    }

    /**
     * 获取用户的角色
     *
     * @param
     * @return
     * @throws ControllerException 
     */
    @PreAuthorize("hasAnyAuthority('user:get/users/{id}/roles')")
    @GetMapping("/findRolesByUserId/{id}/roles")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Set<SysRole> findRolesByUserId(@PathVariable Long id) {
        return sysUserService.findRolesByUserId(id);
    }


    /**
     * 用户查询
     * @param params
     * @return
     * @throws ControllerException 
     */
    @PreAuthorize("hasAuthority('user:get/users/{id}')")
    @ApiOperation(value = "用户查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/findUsers")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public PageResult<SysUser> findUsers( @RequestParam Map<String, Object> params) throws JsonProcessingException {
        return sysUserService.findUsers(params);
    }

    /**
     * 修改自己的个人信息
     *
     * @param sysUser
     * @return
     * @throws ControllerException 
     */
    @PutMapping("/me")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    @PreAuthorize("hasAnyAuthority('user:put/users/me','user:post/users/saveOrUpdate')")
    public Result updateMe(@RequestBody SysUser sysUser) throws ControllerException {
        try {
			SysUser user = sysUserService.updateSysUser(sysUser);

			return Result.succeed(user,"操作成功");
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
    }

    /**
     * 修改密码
     *
     * @param sysUser
     * @throws ControllerException 
     */
    @PutMapping(value = "/updatePassword")
    @PreAuthorize("hasAuthority('user:put/users/password')")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result updatePassword(@RequestBody SysUser sysUser) throws ControllerException {
        try {
			if (StringUtils.isBlank(sysUser.getOldPassword())) {
			    throw new IllegalArgumentException("旧密码不能为空");
			}
			if (StringUtils.isBlank(sysUser.getNewPassword())) {
			    throw new IllegalArgumentException("新密码不能为空");
			}

			if (sysUser.getId() == 1L){
			    return Result.failed("超级管理员不给予修改");
			}

			return sysUserService.updatePassword(sysUser.getId(), sysUser.getOldPassword(), sysUser.getNewPassword());
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
    }

    /**
     *  修改用户状态
     * @param params
     * @return
     * @author gitgeek
     * @throws ControllerException 
     */
    @ApiOperation(value = "修改用户状态")
    @GetMapping("/updateEnabled")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "enabled",value = "是否启用", required = true, dataType = "Boolean")
    })
    @LogAnnotation(module="user-center",recordRequestParam=false)
    @PreAuthorize("hasAnyAuthority('user:get/users/updateEnabled' ,'user:put/users/me')")
    public Result updateEnabled(@RequestParam Map<String, Object> params) throws ControllerException{
        try {
			Long id = MapUtils.getLong(params, "id");
			if (id == 1L){
			    return Result.failed("超级管理员不给予修改");
			}
			return sysUserService.updateEnabled(params);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
    }

    /**
     * 管理后台，给用户重置密码
     * @param id
     * @author gitgeek
     * @throws ControllerException 
     */
    @PreAuthorize("hasAuthority('user:post/users/{id}/resetPassword' )")
    @PostMapping(value = "/{id}/resetPassword")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result resetPassword(@PathVariable Long id) {
        if (id == 1L){
            return Result.failed("超级管理员不给予修改");
        }
        sysUserService.updatePassword(id, null, "123456");
        return Result.succeed(null,"重置成功");
    }


    /**
     * 新增or更新
     * @param sysUser
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('user:post/users/saveOrUpdate')")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result saveOrUpdate(@RequestBody SysUser sysUser) {
        String username = sysUser.getUsername();
        if (StringUtils.isBlank(username)) {
            //throw new IllegalArgumentException("用户名不能为空");
            return Result.failed("用户名不能为空");
        }

        // 防止用手机号直接当用户名，手机号要发短信验证
        if (ValidatorUtil.checkPhone(username)) {
            //throw new IllegalArgumentException("用户名要包含英文字符");
            return Result.failed("用户名要包含英文字符");
        }

        // 防止用邮箱直接当用户名，邮箱也要发送验证（暂未开发）
        if (username.contains("@")) {
            //throw new IllegalArgumentException("用户名不能包含@");
            return Result.failed("用户名不能包含@");
        }

        if (username.contains("|")) {
            //throw new IllegalArgumentException("用户名不能包含|字符");
            return Result.failed("用户名不能包含|字符");
        }

        if (StringUtils.isBlank(sysUser.getNickname())) {
            sysUser.setNickname(username);
        }

        if (StringUtils.isBlank(sysUser.getType())) {
            sysUser.setType(UserType.BACKEND.name());
        }

        if (!StringUtils.isBlank(sysUser.getPhone())) {
            // 防止用手机号直接当用户名，手机号要发短信验证
            if (!ValidatorUtil.checkPhone(sysUser.getPhone())) {
                return Result.failed("手机号格式不正确");
            }
        }
        return sysUserService.saveOrUpdate(sysUser);
    }

    /**
     * 导出数据
     * @return
     * @throws ControllerException 
     */
    @PostMapping("/users/exportUser")
    @PreAuthorize("hasAuthority('user:post/users/exportUser')")
    public void exportUser(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        try {
			List<SysUserExcel> result = sysUserService.findAllUsers(params);

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=myExcel.xls");
			@Cleanup OutputStream ouputStream = null;
			Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户导出","用户"),
			        SysUserExcel.class, result );
			    ouputStream = response.getOutputStream();
			    workbook.write(ouputStream);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		} catch (IOException e) {
			throw new ControllerException(e);
		}
        
    }


    /**
     * 测试幂等接口
     * @param sysUser
     * @return
     * @throws ControllerException 
     */
    @PostMapping("/users/save")
    @ApiIdempotent
    public Result save(@RequestBody SysUser sysUser) throws ControllerException {
        try {
			return  sysUserService.saveOrUpdate(sysUser);
		} catch (ServiceException e) {
			log.error("执行" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
			throw new ControllerException(e);
		}
    }

    /**
     * 上传头像
     * @return
     */
    @RequestMapping(value = "/headImg/upload", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public Result TransferFile(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        String imgUrl = null;
        if(!file.isEmpty()){
            String fileName=file.getOriginalFilename();
            ByteArrayResource is = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
        headers.setContentType(type);
        //设置请求体
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", is);
        form.add("filename",fileName);

        MultiValueMap request = new LinkedMultiValueMap(1);
            request.add("file", is);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
            RestTemplate restTemplate = new RestTemplate();
            Map map = restTemplate.postForObject(urls+"/files/upload", files, Map.class);
            imgUrl = (String) map.get("data");
        }
        try {
            LoginAppUser user = SysUserUtil.getLoginAppUser();
            Long id = user.getId();
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setHeadImgUrl(urls+imgUrl);
            sysUserService.updateSysUser(sysUser);
            return Result.succeed(sysUser,"操作成功");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
