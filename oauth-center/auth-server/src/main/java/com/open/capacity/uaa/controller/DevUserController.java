package com.open.capacity.uaa.controller;

import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.uaa.dto.SysClientDto;
import com.open.capacity.uaa.model.*;
import com.open.capacity.uaa.service.DevUserService;
import com.open.capacity.uaa.service.SysClientService;
import com.open.capacity.uaa.service.SysServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 开发者账号相关接口
 *
 * @author lisq
 */
@RestController
@Api(tags = "DEVUSER API")
@RequestMapping("/devusers")
public class DevUserController {
    @Autowired
    private DevUserService devUserService;
    @Autowired
    private SysClientService sysClientService;
    @Autowired
    private SysServiceService sysServiceService;

    @PostMapping("/save")
    @ApiOperation(value = "保存用户")
    public Result save(@RequestBody DevUser devUser) {
        return devUserService.saveOrUpdate(devUser);
    }

    @PostMapping("/getSelf")
    @ApiOperation(value = "查看当前用户")
    public Result test() {
        Result result = new Result();
        try {
            DevUser devUser = devUserService.getSelf();
            result = Result.succeed("操作成功");
            result.setDatas(devUser);
        } catch (Exception e) {
            result = Result.failed(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户")
    public Result update(@RequestBody DevUser devUser) {return devUserService.saveOrUpdate(devUser);}

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改用户密码")
    public Result updatePassword(@RequestBody Map params) {
        return devUserService.updatePassword(params);
    }

    @PostMapping("/saveGroup")
    @ApiOperation(value = "保存组")
    public Result saveGroup(@RequestBody DevGroup devGroup) {
        return devUserService.saveGroup(devGroup);
    }

    @PostMapping("/updateGroup")
    @ApiOperation(value = "修改组")
    public Result updateGroup(@RequestBody DevGroup devGroup) {
        return devUserService.updateGroup(devGroup);
    }

    @GetMapping("/groups")
    @ApiOperation(value = "组列表")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public PageResult<DevGroup> grouplist(@RequestParam Map<String, Object> params) {
        return devUserService.grouplist(params);
    }

    @PostMapping("/group/delete/{id}")
    @ApiOperation(value = "删除应用组")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public Result deleteGroup(@PathVariable Integer id) {
        return devUserService.deleteGroup(id);
    }

    @GetMapping("/clients")
    @ApiOperation(value = "应用列表")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public PageResult<SysClient> listRoles(@RequestParam Map<String, Object> params) {
        return devUserService.listRoles(params) ;
    }

    @PostMapping("/client/saveOrUpdate")
    @ApiOperation(value = "保存或者修改应用")
    public Result clientSaveOrUpdate(@RequestBody SysClientDto clientDto, @RequestParam(required = false) Integer groupId){
        return  devUserService.clientSaveOrUpdate(clientDto, groupId);
    }

    @PostMapping("/client/delete/{id}")
    @ApiOperation(value = "删除应用")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public Result delete(@PathVariable Long id) {
        return devUserService.deleteClient(id);
    }

    @ApiOperation(value = "根据clientId获取对应的服务")
    @GetMapping("/client/{clientId}/services")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public List<Map<String, Object>> findServicesByclientId(@PathVariable Long clientId) {
        Set<Long> clientIds = new HashSet<Long>();
        //初始化应用
        clientIds.add(clientId);
        List<SysService> clientService = sysServiceService.findByClient(clientIds);
        List<SysService> allService = devUserService.findServiceAll();
        List<Map<String, Object>> authTrees = new ArrayList<>();
        Map<Long,SysService> clientServiceMap = clientService.stream().collect(Collectors.toMap(SysService::getId, SysService->SysService));
        for (SysService sysService: allService) {
            Map<String, Object> authTree = new LinkedHashMap<>();
            authTree.put("id",sysService.getId());
            authTree.put("name",sysService.getName());
            authTree.put("pId",sysService.getParentId());
            authTree.put("open",true);
            authTree.put("checked", false);
            if (clientServiceMap.get(sysService.getId())!=null){
                authTree.put("checked", true);
            }
            authTrees.add(authTree);
        }
//        List<Map<String, Object>> results = new LinkedList<>();
//        Map<String, Integer> map = new LinkedHashMap<>();
//        for(Map<String, Object> auth : authTrees) {
//            if(auth.get("pId").toString().equals("-1")) {
//                results.add(auth);
//                map.put(auth.get("id").toString(), results.size() - 1);
//            } else {
//                Map<String, Object> result = results.get(map.get(auth.get("pId").toString()));
//                List<Map<String, Object>> list = (List) result.get("childs");
//                if (list == null) {
//                    list = new ArrayList<>();
//                }
//                list.add(auth);
//                result.put("childs", list);
//            }
//        }
        return  authTrees;//results;
    }

    @PostMapping("/client/granted")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public Result setMenuToClient(@RequestBody SysClientDto clientDto) {
        sysServiceService.setMenuToClient(clientDto.getId(), clientDto.getServiceIds());
        return Result.succeed("操作成功");
    }

    /**
     * 移除access_token和refresh_token
     *
     * @param access_token
     */
//    @ApiOperation(value = "移除token")
//    @PostMapping(value = "/remove/token", params = "access_token")
//    @LogAnnotation(module = "auth-server", recordRequestParam = false)
//    public Result removeToken(String access_token) {
//        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
//        if (accessToken != null) {
//            // 移除access_token
//            tokenStore.removeAccessToken(accessToken);
//
//            // 移除refresh_token
//            if (accessToken.getRefreshToken() != null) {
//                tokenStore.removeRefreshToken(accessToken.getRefreshToken());
//            }
//        }
//        return Result.succeed("登出成功");
//    }
}
