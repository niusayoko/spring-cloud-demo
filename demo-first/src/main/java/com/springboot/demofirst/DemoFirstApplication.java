package com.springboot.demofirst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.springboot.demofirst.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DemoFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFirstApplication.class, args);
	}

}
