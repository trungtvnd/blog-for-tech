package com.trungtv.blogauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BlogAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAuthApplication.class, args);
	}

}
