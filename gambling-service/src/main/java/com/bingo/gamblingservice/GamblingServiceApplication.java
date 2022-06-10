package com.bingo.gamblingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GamblingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamblingServiceApplication.class, args);
	}

}
