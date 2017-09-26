package com.sanchezjm.tuto.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientsApplication.class, args);
	}
}
