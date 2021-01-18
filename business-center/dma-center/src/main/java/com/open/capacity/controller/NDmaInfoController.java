package com.open.capacity.controller;


import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.entity.Features;
import com.open.capacity.entity.Geometry;
import com.open.capacity.entity.NDmaInfo;
import com.open.capacity.entity.Polygon;
import com.open.capacity.service.NDmaInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */
@RestController
@RequestMapping("ndmainfo")
@Api(tags = "dam分区")
public class NDmaInfoController {

    @Autowired
    private NDmaInfoService nDmaInfoService;

    /**
     * 查询dam分区列表
     */
    @ApiOperation(value = "查询dam分区列表")
    @PostMapping("/ nDmaInfoList")
    @PreAuthorize("hasAnyAuthority('ndmainfo:get/nDmaInfos')")
    public PageResult  nDmaInfoList(@RequestParam Map<String, Object> params){
        PageResult pageResult = nDmaInfoService.findAll(params);
        return pageResult;
    }


    /**
     * 保存dam分区
     */
    @ApiOperation(value = "保存dam分区")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ndmainfo:post/nDmaInfo')")
    public Result save(@RequestBody NDmaInfo nDmaInfo){
			nDmaInfoService.save(nDmaInfo);

        return Result.succeed("保存成功");
    }

    /**
     * 修改dam分区
     */
    @ApiOperation(value = "修改dam分区")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ndmainfo:put/nDmaInfo')")
    public Result update(@RequestBody NDmaInfo nDmaInfo){
			nDmaInfoService.update(nDmaInfo);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除dam分区
     */
    @ApiOperation(value = "根据id删除dam分区")
    @PostMapping("/deleteDmaInfoById/{id}")
    @PreAuthorize("hasAnyAuthority('ndmainfo:delete/nDmaInfo/{id}')")
    public Result deleteDmaInfoById(@ApiParam(value = "根据id删除dam分区", required = true) @PathVariable Long  id){
			nDmaInfoService.delete(id);
        return Result.succeed("删除成功");
    }



    /**
     * 面要素信息
     */
    @ApiOperation(value = "面要素信息")
    @PostMapping("/Polygon")
    @PreAuthorize("hasAnyAuthority('deviceinfo:post/PointDatas')")
    public Polygon PointData() {

        HashMap<String, Object> params = new HashMap<>();
        Polygon polygon = new Polygon();
        List<Features> featlist = new ArrayList<>();
        //获取线数据
        List<NDmaInfo> polygonList = nDmaInfoService.findAll(params).getData();
        //遍历线数据
        polygonList.forEach(Info->{
            Geometry geometry = new Geometry();
            geometry.setType("Polygon");
            Features features = new Features();
            //获取nodes
            String nodes = Info.getNodes();
            ArrayList<BigDecimal[]> list = new ArrayList<>();
            ArrayList<List> alist = new ArrayList<>();
            //如果nodes不为空，进行切割
            if (nodes!=null){
                //切割nodes
                String replace = nodes.replaceAll("\\\",\\\"","|").replace("\"","").replace("[","").replace("]","");
                String[] split = replace.split("\\|");
                //遍历切割后的数组
                for (String str : split){
                    //按照逗号切割
                    String[] split1 = str.split("\\,");
                    //获取经纬度
                    BigDecimal longitude = new BigDecimal(split1[0]);
                    BigDecimal latitude = new BigDecimal(split1[1]);
                    //储存经纬度
                    BigDecimal[] a = {longitude,latitude};
                    //添加到集合
                    list.add(a);

                }
                alist.add(list);
            }

            geometry.setCoordinates(alist);
            features.setProperties(Info);
            features.setGeometry(geometry);
            featlist.add(features);
        });
        polygon.setFeatures(featlist);
        return polygon;
    }



}
