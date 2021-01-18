package com.open.capacity.oss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.open.capacity.common.port.PortApplicationEnvironmentPreparedEventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 文件中心
*/
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class FileCenterApp {

	public static void main(String[] args) {
		// 固定端口
		// SpringApplication.run(FileCenterApp.class, args);

		// 随机端口启动
		SpringApplication app = new SpringApplication(FileCenterApp.class);
		app.addListeners(new PortApplicationEnvironmentPreparedEventListener());
		app.run(args);

	}

}