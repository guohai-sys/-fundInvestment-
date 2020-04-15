package com.zf.f1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.zf.f1.mapper")
@EnableDiscoveryClient
public class ZfFundbuysaleprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZfFundbuysaleprojectApplication.class, args);
	}

}
