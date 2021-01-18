package com.open.capacity.controller;


import com.open.capacity.service.GisConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
@RestController
@RequestMapping("gisconfig")
@Api(tags = "获取高德地图key")
public class GisConfigController {

    @Autowired
    private GisConfigService gisConfigService;

    /**
     * 获取高德地图key
     *
     * @param appId
     * @return
     */
    @ApiOperation(value = "根据appId获取高德地图key接口")
    @PostMapping("/getAmapKey/{appId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('gisconfig:post/AmapKey/{appId}')")
    public String getAmapKey(@ApiParam(value = "根据appId获取key", required = true) @PathVariable(name = "appId") Integer appId) {

        return gisConfigService.getAmapKey(appId);
    }

    /**
     * 点要素信息
     */
    @ApiOperation(value = "地图点位信息")
    @PostMapping("/Point")
    @PreAuthorize("hasAnyAuthority('gisconfig:post/PointDatas')")
    public ResponseEntity<String> PointData() {
        return this.gisConfigService.queryPoint();
    }


    /**
     * 面要素信息
     */
    @ApiOperation(value = "地图面要素信息")
    @PostMapping("/Polygon")
    @PreAuthorize("hasAnyAuthority('gisconfig:post/Polygon')")
    public ResponseEntity<String> PolygonData() {
        return this.gisConfigService.queryPolygon();
    }

    /**
     * 线要素信息
     */
    @ApiOperation(value = "地图线要素信息")
    @PostMapping("/LineString")
    @PreAuthorize("hasAnyAuthority('gisconfig:post/LineString')")
    public ResponseEntity<String> LineStringData() {
        return this.gisConfigService.queryLineString();
    }
}
