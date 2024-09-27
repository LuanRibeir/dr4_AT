package com.luanribeiro.dr4_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Dr41Application {

	public static void main(String[] args) {
		SpringApplication.run(Dr41Application.class, args);
	}

}
