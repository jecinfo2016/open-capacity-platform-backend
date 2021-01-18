package com.open.capacity.epanetgis.util;

import com.open.capacity.epanetgis.entity.Coordinates;
import com.open.capacity.epanetgis.entity.Gps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map 地图工具类
 */
@Slf4j
public class MapUtil {

    //模型转换器
    private Convertor convertor = null;

    /**
     * 测量两点的距离
     *
     * @param fromPoint
     * @param toPoint
     * @return 返回距离(单位是米)
     */
    public static double measureDistance(Gps fromPoint, Gps toPoint) {
        double EARTH_RADIUS = 6378137;
        double distance = 0;
        double startLongitude = fromPoint.getWgLon();
        double startLatitude = fromPoint.getWgLat();
        double endLongitude = toPoint.getWgLon();
        double endLatitude = toPoint.getWgLat();
        double radLatitude1 = startLatitude * Math.PI / 180.0;
        double radLatitude2 = endLatitude * Math.PI / 180.0;
        double a = Math.abs(radLatitude1 - radLatitude2);
        double b = Math.abs(startLongitude * Math.PI / 180.0 - endLongitude * Math.PI / 180.0);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLatitude1) * Math.cos(radLatitude2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        distance = Math.round(s * 10000) / 10000;
        return distance;
    }


    /**
     * 管网模型-高德地图偏移量计算
     *
     * @return
     */
    public double[] searchOffset(double[] nodeFromPoint, double[] viewFromPoint, double[] nodeToPoint, double[] viewToPoint) {
        //返回报文定义
        double [] res=new double[]{-1,-1};
        //起始点
        double nodeFromPointX = nodeFromPoint[0]; // 模型起始点_横坐标
        double nodeFromPointY = nodeFromPoint[1]; // 模型起始点_纵坐标
        double viewFromPointX = viewFromPoint[0]; // 高德起始点_横坐标
        double viewFromPointY = viewFromPoint[1]; // 高德起始点_纵坐标

        //目标点
        double nodeToPointX = nodeToPoint[0];     // 模型目标点_横坐标
        double nodeToPointY = nodeToPoint[1];     // 模型目标点_纵坐标
        double viewToPointX = viewToPoint[0];     // 高德目标点_横坐标
        double viewToPointY = viewToPoint[1];     // 高德目标点_纵坐标

        //计算高德地图-起始点与目标点的距离
        double distance = MapUtil.measureDistance(new Gps(viewFromPointX, viewFromPointY), new Gps(viewToPointX, viewToPointY));
        log.info("预期距离：" + distance);
        //转换工具
        convertor = new Convertor(1, 120, 0, 0, 0);

        //定义偏移量
        double eastOffset = 0.0;
        double northOffset = 0.0;
        double converK0 = 1; //转换因子
        double step = 1000;
        double[] result_from;
        double[] result_to;
        int count = 0;
        int count_k0 = 0;
        double diffDistance = 1;
        int maxIt = 1000000;
        double stepMutiEst = 1;
        double stepMutiNor = 1;
        double acceptDiff = 0.000001;
        double diffX = 1;
        double diffY = 1;
        while (true) {
            count_k0++;
            result_from = convertor.GKgetJW(nodeFromPointX, nodeFromPointY);
            result_to = convertor.GKgetJW(nodeToPointX, nodeToPointY);
            double distance1 = MapUtil.measureDistance(new Gps(result_from[0], result_from[1]), new Gps(result_to[0], result_to[1]));
            if (Math.abs(distance - distance1) < diffDistance) {
                log.info("计算【比例因子】成功,k0=" + convertor.getK0());
                break;
            } else {
                converK0 = converK0 * distance1 / distance;
                convertor.setK0(converK0);
            }
            if (count_k0 > maxIt) {
                log.info("计算【比例因子】次数超出预期阈值，总次数=" + count_k0);
                break;
            }
        }

        //将高德起始点转为84坐标
//        Gps gps = PositionUtil.gcj_To_Gps84(viewFromPointX, viewFromPointY);
//        viewFromPointX = gps.getWgLon();
//        viewFromPointY = gps.getWgLat();

        //循环求偏移量
        while (true) {
            northOffset = northOffset + step * stepMutiNor;
            convertor.setFN(northOffset);
            eastOffset = eastOffset + step * stepMutiEst;
            convertor.setFE(eastOffset);
            result_from = convertor.GKgetJW(nodeFromPointX, nodeFromPointY);
            double absX = result_from[0] - viewFromPointX;
            double absY = result_from[1] - viewFromPointY;
            count++;
            if (Math.abs(absX) < acceptDiff && Math.abs(absY) < acceptDiff) {
                log.info("计算偏移量成功，eastOffset=" + eastOffset + "  |  northOffset=" + northOffset);
                res[0]=eastOffset;
                res[1]=northOffset;
                break;
            }
            if (Math.abs(absX) > acceptDiff) {
                if (Math.abs(absX) > 1) {
                    stepMutiEst = 1 * absX / Math.abs(absX);
                } else {
                    stepMutiEst = absX * diffX * (Math.random());
                }
            }
            if (Math.abs(absY) > acceptDiff) {
                stepMutiNor = absY * diffY * Math.random();
            }

            if (count >= maxIt) {
                log.info("计算【偏移量】次数，超出预期阈值~");
                break;
            }
        }
        return res;
    }


    /**
     * Epnaet坐标-转高德坐标 [批处理方式]
     */
    public List<double[]> calculateCoordinate(List<Coordinates> list, double eastOffset, double northOffset) {
        List<double[]> resList = new ArrayList<>();
        if (convertor == null) {
            convertor = new Convertor(1, 120, 0, eastOffset, northOffset);
        } else {
            convertor.setFE(eastOffset);
            convertor.setFN(northOffset);
        }
        for (Coordinates geo : list) {
            double[] doubles = convertor.GKgetJW(geo.getXCoord(), geo.getYCoord());
            resList.add(doubles);
        }
        return resList;
    }


    /**
     * Epnaet坐标-转高德坐标 [单点处理方式]
     */
    public double[] calculateCoordinate(Coordinates geo, double eastOffset, double northOffset) {
        double[] res = new double[2];
        if (convertor == null) {
            convertor = new Convertor(1, 120, 0, eastOffset, northOffset);
        } else {
            convertor.setFE(eastOffset);
            convertor.setFN(northOffset);
        }
        res = convertor.GKgetJW(geo.getXCoord(), geo.getYCoord());
        return res;
    }
}
