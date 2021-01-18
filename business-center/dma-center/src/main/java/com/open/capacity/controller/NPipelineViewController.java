package com.open.capacity.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.entity.Features;
import com.open.capacity.entity.Geometry;
import com.open.capacity.entity.LineString;
import com.open.capacity.entity.NPipelineView;
import com.open.capacity.service.NPipelineViewService;
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
 * 管线显示
 *
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */
@RestController
@RequestMapping("npipelineview")
@Api(tags = "管线显示")
public class NPipelineViewController {

    @Autowired
    private NPipelineViewService nPipelineViewService;

    /**
     * 查询管线显示列表
     */
    @ApiOperation(value = "查询管线显示列表")
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('npipelineview:get/npipeLineViews')")
    public PageResult list(@RequestParam Map<String, Object> params){
        PageResult pageResult = nPipelineViewService.findAll(params);
        return pageResult;
    }


    /**
     * 保存管线
     */
    @ApiOperation(value = "保存管线")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('npipelineview:post/npipeLineView')")
    public Result save(@RequestBody NPipelineView nPipelineView){
			nPipelineViewService.save(nPipelineView);

        return Result.succeed("保存成功");
    }

    /**
     * 修改管线
     */
    @ApiOperation(value = "修改管线")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('npipelineview:put/npipeLineView')")
    public Result update(@RequestBody NPipelineView nPipelineView){
			nPipelineViewService.update(nPipelineView);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除
     */
    @ApiOperation(value = "根据id删除管线")
    @PostMapping("/deleteLineViewById/{id}")
    @PreAuthorize("hasAnyAuthority('npipelineview:delete/npipeLineView/{id}')")
    public Result deleteLineViewById(@ApiParam(value = "根据id删除管线", required = true) @PathVariable Long  id){
			nPipelineViewService.delete(id);
        return Result.succeed("删除成功");
    }

    /**
     * 地图管线信息
     */
    @ApiOperation(value = "地图管线信息")
    @PostMapping("/LineString")
    @PreAuthorize("hasAnyAuthority('npipelineview:post/LineString')")
    public LineString PointData() {

        HashMap<String, Object> params = new HashMap<>();
        LineString lineString = new LineString();
        List<Features> featlist = new ArrayList<>();

        //获取管线条数
        int i = nPipelineViewService.PipelineCount();
        //遍历
        for (int pipeid = 1; pipeid<=i; pipeid++ ){
            Geometry geometry = new Geometry();
            geometry.setType("LineString");
            Features features = new Features();
            ArrayList<BigDecimal[]> list = new ArrayList<>();
            ArrayList<List> alist = new ArrayList<>();
            //一条管线包含的点信息
            List<NPipelineView> properties = nPipelineViewService.findPipelineBypipeId(pipeid);
            properties.forEach(a->{
                BigDecimal latitude = a.getLatitude();
                BigDecimal longitude = a.getLongitude();
                if (latitude!=null&&longitude!=null){
                    BigDecimal[] coordinates = {longitude,latitude};//拼接经纬度
                    list.add(coordinates);
                }
                //存管线起始位置
                if (a.getParentNode()==0){
                    features.setProperties(a);
                }
            });
            alist.add(list);
            geometry.setCoordinates(list);//

           features.setGeometry(geometry);
            featlist.add(features);

        }
        lineString.setFeatures(featlist);
        return lineString;
    }
}
