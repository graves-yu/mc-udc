package com.fonsvew.udc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReminderApplication.class, args);
	}

}
