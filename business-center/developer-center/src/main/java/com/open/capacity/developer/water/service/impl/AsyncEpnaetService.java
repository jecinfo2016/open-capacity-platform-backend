package com.open.capacity.developer.water.service.impl;

import com.open.capacity.developer.water.dao.IWaterDao;
import com.open.capacity.developer.water.entity.EpnaetPipes;
import com.open.capacity.developer.water.entity.EpnaetPoint;
import com.open.capacity.developer.water.entity.WaterBasicModel;
import com.open.capacity.epanetgis.entity.Coordinates;
import com.open.capacity.epanetgis.entity.Pipes;
import com.open.capacity.epanetgis.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author DUJIN
 * @Classname AsyncEpnaetService
 * @Description 保存模型点位信息
 * @Date 2020/8/26 11:13
 */
@Service
@Slf4j
public class AsyncEpnaetService {

    @Autowired
    IWaterDao epanetDao;

    @Autowired
    MapUtil mapUtil;

    /**
     * 保存模型点位信息
     *
     * @param coordinatesList
     */
    public void saveCoordinateInfo(List<Coordinates> coordinatesList, Integer modelId, double eastOffset, double northOffset) {
        if (coordinatesList != null && !coordinatesList.isEmpty()) {
            //循环遍历-保存模型点位数据
            int count = 0;
            for (Coordinates coordinate : coordinatesList) {
                //转化数据
                double[] doubles = mapUtil.calculateCoordinate(coordinate, eastOffset, northOffset);
                //插入/更新-数据库
                EpnaetPoint ep = new EpnaetPoint();
                ep.setModelId(modelId);
                ep.setNodeId(coordinate.getNodeId());
                ep.setNodeX(coordinate.getXCoord());
                ep.setNodeY(coordinate.getYCoord());
                ep.setLongitude(doubles[0]);
                ep.setLatitude(doubles[1]);
                ep.setCreateTime(new Date());
                List<EpnaetPoint> epnaetPoints = epanetDao.queryEpnaetPoint(coordinate.getNodeId(), modelId);
                if (epnaetPoints != null && !epnaetPoints.isEmpty()) {
                    log.info("该条数据已存在,modelId:{},nodeId:{}", modelId, coordinate.getNodeId());
                } else {
                    if (epanetDao.insertEpanetPoint(ep) > 0) {
                        log.info("保存模型点位信息成功,id={}", ep.getId());
                        count++;
                    }
                }
            }
            log.info("保存点位信息：{}个", count);
        }
    }

    /**
     * 保存模型管线信息
     *
     * @param pipesList
     */
    public void savePipesInfo(List<Pipes> pipesList, Integer modelId) {
        if (pipesList != null && !pipesList.isEmpty()) {
            //循环遍历-保存模型管线数据
            int count = 0;
            for (Pipes pipes : pipesList) {
                EpnaetPipes pv = new EpnaetPipes();
                pv.setModelId(modelId);
                pv.setPipesId(pipes.getPipesId());
                pv.setNode1Id(pipes.getNode1Id());
                pv.setNode2Id(pipes.getNode2Id());
                pv.setLength(pipes.getLength());
                pv.setDiameter(pipes.getDiameter());
                pv.setRoughness(pipes.getRoughness());
                pv.setMinorLoss(pipes.getMinorLoss());
                pv.setCreateTime(new Date());
                List<EpnaetPipes> epnaetPipes = epanetDao.queryEpnaetPipes(pipes.getPipesId(), modelId);
                if (epnaetPipes != null && !epnaetPipes.isEmpty()) {
                    log.info("该条管线数据已存在,modelId:{},pipesId:{}", modelId, pipes.getPipesId());
                } else {
                    if (epanetDao.insertEpnaetPipes(pv) > 0) {
                        log.info("成功插入一条数据,id={}", pv.getId());
                        count++;
                    }
                }
            }
            log.info("保存模型管线信息：{}个", count);
        }
    }

    /**
     * 计算偏移量
     *
     * @param basicModel
     * @return
     */
    public double[] searchOffset(WaterBasicModel basicModel) {
        double[] nodeFromPoint = converStringToDouble(basicModel.getNode1().split(","));
        double[] nodeToPoint = converStringToDouble(basicModel.getNode2().split(","));
        double[] viewFromPoint = converStringToDouble(basicModel.getGaude1().split(","));
        double[] viewToPoint = converStringToDouble(basicModel.getGaude2().split(","));
        return mapUtil.searchOffset(nodeFromPoint, viewFromPoint, nodeToPoint, viewToPoint);
    }

    /**
     * 将string类型的数组转为double
     *
     * @return
     */
    public double[] converStringToDouble(String[] oldArray) {
        double[] newArray = new double[2];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = Double.parseDouble(oldArray[i]);
        }
        return newArray;
    }

    /**
     * 更新模型点位经纬度
     *
     * @param eastOffset
     * @param northOffset
     */
    public int updateCoordinateInfo(Integer modelId, double eastOffset, double northOffset) {
        //查询出该模型下所有的点
        List<EpnaetPoint> list = epanetDao.queryEpnaetPoint(null, modelId);
        int count=0;
        Coordinates coordinate = null;
        if (list != null && !list.isEmpty()) {
            for (EpnaetPoint point : list) {
                coordinate = new Coordinates();
                coordinate.setXCoord(point.getNodeX());
                coordinate.setYCoord(point.getNodeY());
                double[] coorArray = mapUtil.calculateCoordinate(coordinate, eastOffset, northOffset);
                if (epanetDao.updatePointInfo(point.getId(), coorArray[0], coorArray[1])>0){
                    log.info("点位经纬度更新成功,id：{}",point.getId());
                    count++;
                }
            }
        }
        log.info("更新点位数量：{}",count);
        return count;
    }
}
