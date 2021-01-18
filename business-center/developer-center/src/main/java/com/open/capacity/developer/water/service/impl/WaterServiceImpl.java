package com.open.capacity.developer.water.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.developer.exception.MyException;
import com.open.capacity.developer.util.FileUtil;
import com.open.capacity.developer.water.dao.IWaterDao;
import com.open.capacity.developer.water.entity.*;
import com.open.capacity.developer.water.service.IWaterService;
import com.open.capacity.epanetgis.entity.Coordinates;
import com.open.capacity.epanetgis.entity.Pipes;
import com.open.capacity.epanetgis.util.EpanetFileReadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.*;

/**
 * @author DUJIN
 */
@Slf4j
@Service
public class WaterServiceImpl implements IWaterService {

    @Autowired
    IWaterDao epanetDao;

    @Autowired
    @Qualifier("MyRestTemplate")
    private RestTemplate restTemplate;

    @Value("${internal.fileCenter.address}")
    public String fileCenterAddress;

    @Value("${internal.fileCenter.nameSpace}")
    public String nameSpace;

    @Value("${internal.cache.address}")
    public String cacheAddress;

    @Autowired
    AsyncEpnaetService asyncEpnaetService;

    @Autowired
    EpanetFileReadUtil epanetFileReadUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertWaterModel(WaterBasicModel basicModel) throws MyException {
        int row = 0;
        try {
            row = epanetDao.insertBasicModel(basicModel);
            if (row > 0) {
                Integer modelId = basicModel.getId();
                log.info("保存水利模型基础信息成功,modelId:{}", modelId);
                log.info("开始计算偏移量...");
                double[] res = asyncEpnaetService.searchOffset(basicModel);
                log.info("偏移量计算结果：eastOffset：{},northOffset:{}", res[0], res[1]);
                if (res[0] != -1 && res[1] != -1) {
                    byte[] fileBytes = downloadEpnaetFile(basicModel);
                    //下载文件
                    String fileName = UUID.randomUUID() + ".inp";
                    FileUtil.getFile(fileBytes, cacheAddress, fileName);
                    File file = new File(cacheAddress + fileName);
                    //模型点集合
                    List<Coordinates> coordinatesList = EpanetFileReadUtil.readCoordinates(file);
                    log.info("coordinatesList:{}", coordinatesList);
                    //管网集合
                    List<Pipes> pipesList = EpanetFileReadUtil.readPipes(file);
                    log.info("pipesList:{}", pipesList);
                    //保存点位信息
                    asyncEpnaetService.saveCoordinateInfo(coordinatesList, modelId, res[0], res[1]);
                    //保存管线信息
                    asyncEpnaetService.savePipesInfo(pipesList, modelId);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException("保存水利模型基础信息异常");
        }
        return row;
    }

    /**
     * 下载模型文件
     *
     * @param basicModel
     */
    public byte[] downloadEpnaetFile(WaterBasicModel basicModel) {
        String enactAddress = basicModel.getEpnaetAddress().replace("show", "downFileByByte");
        //下载文件
        byte[] fileByte = null;
        try {
            fileByte = restTemplate.getForObject(enactAddress, byte[].class);
        } catch (RestClientException e) {
            log.info("下载模型文件失败");
            throw new MyException("下载模型文件");
        }
        return fileByte;
    }

    @Override
    public List<Map<String, Object>> quertWaterBasicInfo(Integer clientId) {
        return epanetDao.quertWaterBasicInfo(clientId);
    }

    @Override
    public JSONObject queryWaterGuides(Integer modelId) {
        JSONObject result = new JSONObject();
        List<EpnaetPoint> points = epanetDao.queryEpnaetPoint(null, modelId);
        List<EpnaetPipes> pipes = epanetDao.queryEpnaetPipes(null, modelId);
        //管线实体数据返回
        Geo geoLineString = new Geo();
        List<Feature> lineFeatures = new ArrayList<>();
        geoLineString.setFeatures(lineFeatures);
        for (EpnaetPipes pipe : pipes) {
            Feature feature = new Feature();
            LineString geometry = new LineString();
            //高德点位坐标
            Integer node1Id = pipe.getNode1Id();
            Integer node2Id = pipe.getNode2Id();
            EpnaetPoint point1 = points.stream().filter(o -> o.getNodeId().equals(node1Id)).findAny().orElse(null);
            EpnaetPoint point2 = points.stream().filter(o -> o.getNodeId().equals(node2Id)).findAny().orElse(null);
            if (point1 == null || point2 == null) {
                log.info("模型点位、管线数据异常");
                return null;
            }
            geometry.setCoordinates(Arrays.asList(new double[]{point1.getLongitude(), point1.getLatitude()}, new double[]{point2.getLongitude(), point2.getLatitude()}));
            feature.setGeometry(geometry);
            lineFeatures.add(feature);
        }
        List<Map<String, Object>> list = epanetDao.queryDeviceMark(modelId);
        //设备点位实体数据返回
        Geo devicePoint = new Geo();
        List<Feature> pointFeatures = new ArrayList<>();
        devicePoint.setFeatures(pointFeatures);
        for (Map<String, Object> map : list) {
            Feature feature = new Feature();
            Point geometry = new Point();
            geometry.setCoordinates(new double[]{Double.parseDouble(String.valueOf(map.get("longitude"))), Double.parseDouble(String.valueOf(map.get("latitude")))});
            feature.setGeometry(geometry);
            Map<String, Object> properties = new HashMap<>();
            properties.put("typeName", map.get("type_name"));
            properties.put("deviceName", map.get("device_name"));
            properties.put("deviceSn", map.get("device_sn"));
            feature.setProperties(properties);
            pointFeatures.add(feature);
        }
        result.put("deviceMark", devicePoint);
        result.put("lineString", geoLineString);
        return result;
    }

    @Override
    public int deleteWaterBasciInfo(Integer modelId, String downloadAddress) {
        int row = epanetDao.deleteWaterBasciInfo(modelId);
        if (row > 0) {
            deleteHabaseFile(downloadAddress);
        }
        return row;
    }

    /**
     * 异步删除habase模型文件
     *
     * @param downloadAddress
     */
    public void deleteHabaseFile(String downloadAddress) {
        try {
            String fileId = downloadAddress.substring(downloadAddress.lastIndexOf('/') + 1);
            Map param = new HashMap(8);
            param.put("namespace", nameSpace);
            param.put("id", fileId);
            Map res = restTemplate.postForObject(fileCenterAddress + "/delete", param, Map.class);
            if ("200".equals(String.valueOf(res.get("code")))) {
                log.info("从文件中心删除模型文件成功，id：{}", fileId);
            } else {
                log.info("从文件中心删除模型文件失败，id：{}", fileId);
                log.info("message:{}", res.get("message"));
            }
        } catch (Exception e) {
            log.info("删除水利模型基础信息异常");
        }
    }

    @Override
    public boolean updateWaterBasicInfo(WaterBasicModel waterBasicModel) {
        boolean flag = false;
        if (epanetDao.updateWaterInfo(waterBasicModel) > 0) {
            double[] offset = asyncEpnaetService.searchOffset(waterBasicModel);
            log.info("偏移量：eastOffset：{},northOffset:{}", offset[0], offset[1]);
            if (offset[0] != -1 && offset[1] != -1) {
                //更新模型经纬度
                if (asyncEpnaetService.updateCoordinateInfo(waterBasicModel.getId(), offset[0], offset[1]) > 0) {
                    flag = true;
                }
            }
        }
        return flag;
    }

}
