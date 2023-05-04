package com.trungtv.blogdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer	// This annotation is used to enable the Eureka server.
public class BlogDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogDiscoveryApplication.class, args);
	}

}
