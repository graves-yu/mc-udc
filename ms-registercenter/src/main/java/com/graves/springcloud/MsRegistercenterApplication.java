package com.graves.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsRegistercenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRegistercenterApplication.class, args);
	}

}
