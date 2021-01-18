package com.open.capacity.epanetgis.util;

import com.open.capacity.epanetgis.entity.Coordinates;
import com.open.capacity.epanetgis.entity.Pipes;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取工具类
 */
@Slf4j
public class EpanetFileReadUtil {

    /**
     * 以行为单位,读取点位信息
     */
    public static List<Coordinates> readCoordinates(File file) {
        List<Coordinates> res = null;
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;
        boolean readFlag = false;
        if (!file.exists()) {
            log.info("文件不存在~");
            return null;
        }
        try {
            log.info("开始以行为单位，读取模型点位数据...");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.startsWith("[VERTICES]")) {
                    readFlag = false;
                }

                if (readFlag) {
                    list.add(tempString);
                }

                if (tempString.startsWith("[COORDINATES]")) {
                    readFlag = true;
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (list != null && !list.isEmpty()) {
            list.remove(0);
            res = converCoordinates(list);
        }
        return res;
    }

    /**
     * 读取管线信息
     *
     * @param file
     * @return
     */
    public static List<Pipes> readPipes(File file) {
        List<Pipes> res = null;
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;
        boolean readFlag = false;
        if (!file.exists()) {
            log.info("文件不存在~");
            return null;
        }
        try {
            log.info("开始以行为单位，读取模型管线数据...");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.startsWith("[PUMPS]")) {
                    readFlag = false;
                }

                if (readFlag) {
                    list.add(tempString);
                }

                if (tempString.startsWith("[PIPES]")) {
                    readFlag = true;
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (list != null && !list.isEmpty()) {
            list.remove(0);
            res = converPipes(list);
        }
        return res;
    }

    /**
     * 将字符串数组转为模型点类集合
     *
     * @return
     */
    public static List<Coordinates> converCoordinates(List<String> paramsList) {
        List<Coordinates> list = new ArrayList<>();
        Coordinates coordinates = null;
        if (paramsList != null && !paramsList.isEmpty()) {
            for (String lineStr : paramsList) {
                if (!"".equals(lineStr)) {
                    coordinates = new Coordinates();
                    String[] array = lineStr.trim().split("\\s+");
                    coordinates.setNodeId(Integer.parseInt(array[0]));
                    coordinates.setXCoord(Double.parseDouble(array[1]));
                    coordinates.setYCoord(Double.parseDouble(array[2]));
                    list.add(coordinates);
                }
            }
        }
        return list;
    }

    /**
     * 将字符串数组转为模型管线类集合
     *
     * @return
     */
    public static List<Pipes> converPipes(List<String> paramsList) {
        List<Pipes> list = new ArrayList<>();
        Pipes pipes = null;
        if (paramsList != null && !paramsList.isEmpty()) {
            for (String lineStr : paramsList) {
                if (!"".equals(lineStr)) {
                    pipes = new Pipes();
                    String[] array = lineStr.trim().split("\\s+");
                    pipes.setPipesId(Integer.parseInt(array[0]));
                    pipes.setNode1Id(Integer.parseInt(array[1]));
                    pipes.setNode2Id(Integer.parseInt(array[2]));
                    pipes.setLength(Double.parseDouble(array[3]));
                    pipes.setDiameter(Double.parseDouble(array[4]));
                    pipes.setRoughness(Double.parseDouble(array[5]));
                    pipes.setMinorLoss(Double.parseDouble(array[6]));
                    list.add(pipes);
                }
            }
        }
        return list;
    }
}
