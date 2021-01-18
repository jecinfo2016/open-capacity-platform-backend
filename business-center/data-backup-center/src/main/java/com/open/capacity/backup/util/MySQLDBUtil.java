package com.open.capacity.backup.util;
/**
 * @author Jk
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * ---------------------------
 * MySQL备份与还原工具类
 * ---------------------------
 *
 * @author DUJIN
 */
@Slf4j
@Component
public class MySQLDBUtil {

    @Value("${mysql.path}")
    private String mysqlPath;

    private MySQLDBUtil() {
    }

    /**
     * 备份数据库所有表数据
     *
     * @param host         IP地址
     * @param username     数据库的用户名
     * @param password     数据库的密码
     * @param savePath     备份文件的地址
     * @param fileName     备份文件名称
     * @param databaseName 需要备份的数据库的名称
     * @return 备份成功返回true, 否则返回false
     */
    public boolean backup(String host, String port, String username, String password, String savePath, String fileName, String databaseName) {
        File saveFile = new File(savePath);
        // 如果目录不存在
        if (!saveFile.exists()) {
            // 创建文件夹
            saveFile.mkdirs();
        }
        if (!savePath.endsWith("/")) {
            savePath = savePath + "/";
        }

        //拼接命令行的命令
        // mysqldump -u用户名 -p密码 -hIP地址 -P端口号 --default-character-set=utf8  -B 数据库名 -r文件地址+文件名称
        StringBuilder cmd = new StringBuilder();
        cmd.append(mysqlPath)
                .append("/mysqldump")
                .append(" -u").append(username)
                .append(" -p").append(password)
                .append(" -h").append(host)
                .append(" -P").append(port)
                .append(" --default-character-set=utf8 ")
                .append(" -B ").append(databaseName)
                .append(" -r").append(savePath + fileName);
        try {
            //调用外部执行exe文件的javaAPI
            Process process = Runtime.getRuntime().exec(getCommand(cmd.toString()));
            // 0 表示线程正常终止
            if (process.waitFor() == 0) {
                log.info("数据备份成功，备份路径为:" + savePath);
                return true;
            }
        } catch (IOException | InterruptedException e) {
            log.error("MySQL数据库进行备份，备份命令执行错误！" + e.getMessage());
        }
        return false;
    }

    /**
     * 将备份的数据库sql文件导入到指定数据库
     *
     * @param filePath     数据库备份的sql文件路径
     * @param host         IP地址
     * @param databaseName 数据库名称
     * @param username     用户名
     * @param password     密码
     * @return 数据导入成功为true, 否则为false
     */
    public boolean recover(String host, String port, String username, String password, String filePath, String fileName, String databaseName) {
        //拼接命令行的命令
        // mysql -u用户名 -p密码 -hIP地址 -P端口号 --default-character-set=utf8  -B 数据库名 < 文件地址+文件名称
        //mysql -uroot -proot -h192.268.1.123 -P3306 --default-character-set=utf8  -B testdb < ./conf_sql/recover_db/conf.sql
        StringBuilder cmd = new StringBuilder();
        cmd.append("mysql")
                .append(" -u").append(username)
                .append(" -p").append(password)
                .append(" -h").append(host)
                .append(" -P").append(port)
                .append(" --default-character-set=utf8 ")
                .append(" -B ").append(databaseName)
                .append(" < ").append(filePath + fileName);
        try {
            Process process = Runtime.getRuntime().exec(getCommand(cmd.toString()));
            // 0 表示线程正常终止
            if (process.waitFor() == 0) {
                log.info("数据已从 " + filePath + " 导入到数据库中");
                return true;
            }
        } catch (IOException | InterruptedException e) {
            log.error("Mysql数据导入数据库发生异常" + e.getMessage());
        }
        return false;
    }

    /**
     * 根据操作系统得到执行语句
     *
     * @param command
     * @return
     */
    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if (os.toLowerCase().startsWith("win")) {
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = {shell, c, command};
        log.info("...数据库备份过程中,执行的sql命令为：" + Arrays.toString(cmd));
        return cmd;
    }
}
