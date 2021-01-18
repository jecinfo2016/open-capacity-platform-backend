package com.open.capacity.developer.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author DUJIN
 * @Classname FileUtil
 * @Date 2020/8/27 11:11
 */
@Slf4j
public class FileUtil {

    /**
     * 根据byte数组，生成文件
     * filePath  文件路径
     * fileName  文件名称（需要带后缀，如*.jpg、*.java、*.xml）
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        File dir = new File(filePath);
        //判断文件目录是否存在
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        File file = new File(filePath + File.separator + fileName);
        try (FileOutputStream fos = new FileOutputStream(file); BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(bfile);
        } catch (Exception e) {
           log.error(e.getMessage());
        }
    }
}
