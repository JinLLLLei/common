package com.snfq.files.client;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients
@Configuration
@ComponentScan
public class FilesResourceClientConfiguration {

}
