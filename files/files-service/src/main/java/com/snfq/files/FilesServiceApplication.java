package com.snfq.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.snfq.base.log.annotation.EnableLogs;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
@EnableLogs
public class FilesServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FilesServiceApplication.class, args);
	}
}
