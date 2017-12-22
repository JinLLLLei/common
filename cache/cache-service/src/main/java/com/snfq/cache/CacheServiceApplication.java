package com.snfq.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.snfq.base.exception.annotation.EnableExceptionPropagation;
import com.snfq.base.log.annotation.EnableLogs;


@SpringBootApplication
@EnableDiscoveryClient
@EnableExceptionPropagation
@EnableLogs
public class CacheServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CacheServiceApplication.class, args);
	}
}
