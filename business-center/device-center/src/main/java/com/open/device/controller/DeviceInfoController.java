package com.open.device.controller;

import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.ExcelUtil;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.device.model.*;
import com.open.device.service.DeviceInfoService;
import com.open.device.util.DateUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;


/**
 * 设备信息
 *
 * @author
 * @email
 * @date 2020-05-08 10:42:41
 */
@RestController
@RequestMapping("deviceinfo")
@Api(tags = "设备信息")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;


    /**
     * 查询设备信息列表
     */
    @ApiOperation(value = "查询设备信息列表")
    @PostMapping("/deviceInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('deviceinfo:get/DeviceInfos')")
    public PageResult deviceInfoList(@RequestParam Map<String, Object> params) {
        return deviceInfoService.findAll(params);
    }

    /**
     * 根据appid权限，查询设备基础信息列表接口
     */
    @ApiOperation(value = "根据appid权限，查询设备基础信息列表接口")
    @PostMapping("/deviceBasicList/{appid}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('deviceinfo:get/DeviceBasics/{appid}')")
    public PageResult deviceBasicList(@RequestParam Map<String, Object> params, @PathVariable(name = "appid") String appid) {
        return deviceInfoService.findByDeviceBasic(appid, params);
    }

    /**
     * 设备状态信息列表
     */
    @ApiOperation(value = "根据appid权限，查询设备状态信息列表接口")
    @PostMapping("/deviceStatusList/{appid}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('deviceinfo:get/DeviceStatus/{appid}')")
    public PageResult deviceStatusList(@RequestParam Map<String, Object> params, @PathVariable(name = "appid") String appid) {
        deviceInfoService.findByDeviceStatus(appid, params);
        return deviceInfoService.findByDeviceStatus(appid, params);
    }

    /**
     * 根据deviceSn查询基础信息
     *deviceapp:get/deviceapp/{appId}/deviceapp
     * @param deviceSn
     * @return
     */

    @ApiOperation(value = "根据appid权限和deviceSn查询基础信息接口")
    @PostMapping("/deviceBasicBySn/{deviceSn}/{appid}")
    @PreAuthorize("hasAuthority('deviceinfo:get/DeviceBasics/{deviceSn}/{appid}')")
    public DeviceInfo findDeviceBasicBySn(@ApiParam(value = "根据deviceSn查询基础信息参数", required = true) @PathVariable(name = "deviceSn") String deviceSn, @PathVariable(name = "appid") String appid) {
        return deviceInfoService.findDeviceBySn(appid, deviceSn);
    }

    /**
     * 根据deviceSn查询状态信息
     *
     * @param deviceSn
     * @return
     */
    @ApiOperation(value = "根据appid权限和deviceSn查询状态信息接口")
    @PostMapping("/deviceStatusBySn/{deviceSn}/{appid}")
    @PreAuthorize("hasAuthority('deviceinfo:get/DeviceStatus/{deviceSn}/{appid}')")
    public DeviceInfo findDeviceStatusBySn(@ApiParam(value = "根据deviceSn查询状态信息参数", required = true) @PathVariable(name = "deviceSn") String deviceSn, @PathVariable(name = "appid") String appid) {
        return deviceInfoService.findDeviceStatusBySn(appid, deviceSn);
    }

    /**
     * 保存设备信息
     */
    @ApiOperation(value = "保存设备信息")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('deviceinfo:post/deviceInfos')")
    @LogAnnotation(module = "device-center",recordRequestParam = false)
    public Result save(DeviceInfo deviceInfo) {
        String deviceSn = deviceInfo.getDeviceSn();
        DeviceInfo bySn = deviceInfoService.findBySn(deviceSn);
        if (bySn!=null){
            return Result.failed("设备编码不能重复");
        }
        LoginAppUser user = SysUserUtil.getLoginAppUser();
        String username = user.getUsername();
        deviceInfo.setCreateBy(username);
        deviceInfoService.save(deviceInfo);
        return Result.succeed("保存成功");
    }

    /**
     * 点位信息
     */
    @ApiOperation(value = "地图点位信息")
    @PostMapping("/Point")
    @PreAuthorize("hasAnyAuthority('deviceinfo:get/PointDatas')")
     public Point PointData() {

        Point point = new Point();
        List<Features> featlist = new ArrayList<>();
        List<DeviceInfo> pointData = deviceInfoService.findPointData();

        pointData.forEach(Info->{

            DeviceInstallInfo deviceInstallInfo = Info.getDeviceInstallInfo();
            Integer devceTypeId = Info.getDevceTypeId();
            if (devceTypeId==0){
                HashMap<String, String> map = new HashMap<>();
                map.put("压力计","0.95Mpa");
                Info.setDeviceValue(map);
            }
            if (devceTypeId==1){
                HashMap<String, String> map = new HashMap<>();
                map.put("流量计","1086m³/h");
                Info.setDeviceValue(map);
            }
            if (devceTypeId==2){
                HashMap<String, String> map = new HashMap<>();
                map.put("水表","686173.0");
                Info.setDeviceValue(map);
            }
            if (devceTypeId==3){
                HashMap<String, String> map = new HashMap<>();
                map.put("大用户水表","686173.0");
                Info.setDeviceValue(map);
            }
            if (devceTypeId==4){
                HashMap<String, String> map = new HashMap<>();
                map.put("消火栓","1.95Mpa");
                Info.setDeviceValue(map);
            }
            if (devceTypeId==5){
                HashMap<String, String> map = new HashMap<>();
                map.put("浊度","0.5NTU");
                map.put("余氯","0.4");
                Info.setDeviceValue(map);
            }
            if(deviceInstallInfo!=null){
                BigDecimal latitude = deviceInstallInfo.getLatitude();//经度
                BigDecimal longitude = deviceInstallInfo.getLongitude();//纬度
                BigDecimal[] coordinates = {latitude,longitude};//拼接经纬度

                Geometry geometry = new Geometry();
                geometry.setCoordinates(coordinates);
                geometry.setType("Point");
                Features features = new Features();
                features.setGeometry(geometry);
                features.setProperties(Info);
                featlist.add(features);
            }
        });
            point.setFeatures(featlist);

        return point;
    }


    /**
     * 修改设备信息
     */
    @ApiOperation(value = "修改设备信息")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('deviceinfo:put/deviceInfos')")
    @LogAnnotation(module = "device-center",recordRequestParam = false)
    public Result update(DeviceInfo deviceInfo) {
        LoginAppUser user = SysUserUtil.getLoginAppUser();
        String username = user.getUsername();
        deviceInfo.setUpdateBy(username);
        deviceInfoService.update(deviceInfo);
        return Result.succeed("修改成功");
    }

    /**
     * 批量修改设备离线
     */
    @ApiOperation(value = "批量修改设备离线")
    @PostMapping("/modifyOnline")
    @LogAnnotation(module = "device-center",recordRequestParam = false)
    public Result modifyOnline(@RequestParam List<Integer> idList) {
        deviceInfoService.modifyOnline(idList);
        return Result.succeed("修改成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除设备信息")
    @PostMapping("/deleteDeviceInfoById{id}")
    @PreAuthorize("hasAnyAuthority('deviceinfo:delete/deviceInfos/{id}')")
    @LogAnnotation(module = "device-center",recordRequestParam = false)
    public Result deleteDeviceInfoById(@ApiParam(value = "根据id删除", required = true)  Long id) {
        deviceInfoService.delete(id);
        return Result.succeed("删除成功");
    }


    /**
     * 数据导出
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "数据导出")
    @PostMapping("/export")
    public HttpServletResponse exportExcel(HttpServletResponse response,Map<String, Object> params) {

        PageResult<DeviceInfo> result = deviceInfoService.findAll(params);
        List<DeviceInfo> list = result.getData();
        //获取工具类
        ExcelUtil<DeviceInfo> util = new ExcelUtil<>(DeviceInfo.class);
        try {
            String path = util.getAbsoluteFile(util.exportExcel(list, "设备信息列表"));

            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[1024*8];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }





    List<String> excelname = Arrays.asList(".xls", ".xlsx");
    /**
     * 数据导入
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "数据导入")
    @PostMapping("/upLoadExcel")
    @LogAnnotation(module = "device-center",recordRequestParam = false)
    public String upLoadExcel(@RequestParam("file") MultipartFile file)  {
        ExcelUtil<DeviceInfo> util = new ExcelUtil<>(DeviceInfo.class);
        //截取后缀名
        if (file == null) {
            return "文件为空";
        }
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!excelname.contains(fileType)) {
            return "只能上传excel格式的文件";
        }
        try {
            //file文件转化成inputStream
            InputStream inputStream = file.getInputStream();
            //调用工具类
            List<DeviceInfo> list = util.importExcel(inputStream);
            //获取内容
            for (DeviceInfo info : list){
                try {
                    //GenerationDate日期转换
                    String generationDate = DateUtils.formt(info.getGenerationDate());
                    String createTime = DateUtils.formt(info.getCreateTime());
                    String updateTime = DateUtils.formt(info.getUpdateTime());
                    info.setGenerationDate(generationDate);
                    info.setCreateTime(createTime);
                    info.setUpdateTime(updateTime);
                    //如果数据不存在就插入
                    DeviceInfo bySn = deviceInfoService.findBySn(info.getDeviceSn());
                    if (bySn==null){
                        deviceInfoService.save(info);
                    }
                    //修改数据
                    deviceInfoService.update(info);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "导入数据失败";
        }
        return "导入数据成功";
    }




}
