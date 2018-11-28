package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProviderApplicationB {

	public static void main(String[] args) {
		SpringApplication.run(CloudProviderApplicationB.class, args);
	}
}
