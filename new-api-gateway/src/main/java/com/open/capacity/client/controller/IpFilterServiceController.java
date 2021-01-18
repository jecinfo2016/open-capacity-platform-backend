package com.open.capacity.client.controller;

import com.open.capacity.client.entity.IpFilter;
import com.open.capacity.client.service.IpFilterService;
import com.open.capacity.common.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

@RestController
@RequestMapping("/ipfilter")
public class IpFilterServiceController {


    @Autowired
    private IpFilterService ipFilterService;

    //获取全部数据
    @GetMapping("/findAll")
    public List<IpFilter> findAll(){
        return ipFilterService.findAll();
    }

    @GetMapping("/get")
    public List<Object> get(){
        return ipFilterService.test();
    }

    @PostMapping("/add")
    public Result add(@RequestBody IpFilter ipFilter){

        ipFilterService.insert(ipFilter);
        return Result.succeed("添加成功！");
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long  id){

        ipFilterService.deleteByPrimaryKey(id);
        return Result.succeed("删除成功！");
    }

    @GetMapping("/getIp")
    public Result getIp(ServerWebExchange exchange){

        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        String s = path.toString();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        InetAddress address = remoteAddress.getAddress();
        String hostAddress = address.getHostAddress();
        String ip = request.getRemoteAddress().getAddress().getHostAddress();
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
//            if(ip.indexOf(",")>0){
//                ip = ip.substring(0,ip.indexOf(","));
//            }
//        }
        return Result.succeed(ip);
    }
}
