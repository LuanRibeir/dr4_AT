package com.luanribeiro.dr4_eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Dr4EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dr4EurekaServerApplication.class, args);
	}

}
