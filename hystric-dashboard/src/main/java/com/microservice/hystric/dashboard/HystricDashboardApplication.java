package com.microservice.hystric.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystricDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystricDashboardApplication.class, args);
	}

}
