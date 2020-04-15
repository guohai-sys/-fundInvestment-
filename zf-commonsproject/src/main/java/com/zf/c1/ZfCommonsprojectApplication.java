package com.zf.c1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@EnableDiscoveryClient
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class ZfCommonsprojectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ZfCommonsprojectApplication.class, args);
	}

}
