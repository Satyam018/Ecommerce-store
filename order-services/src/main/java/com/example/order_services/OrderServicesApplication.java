package com.example.order_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderServicesApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderServicesApplication.class, args);
	}

}
