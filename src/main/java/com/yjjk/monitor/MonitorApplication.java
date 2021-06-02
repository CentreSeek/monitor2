package com.yjjk.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.yjjk")
@MapperScan(basePackages = "com.yjjk.monitor.mapper")
public class MonitorApplication {
	public static void main(String[] args) {
    	SpringApplication.run(MonitorApplication.class, args);
	}

}
