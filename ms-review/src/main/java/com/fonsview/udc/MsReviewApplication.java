package com.fonsview.udc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class MsReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReviewApplication.class, args);
	}

}
