package com.example.order_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrderGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderGatewayApplication.class, args);
	}
}
