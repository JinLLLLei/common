package com.snfq.files.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.snfq.files.controller.FilesResourceDefinition;

@FeignClient(value="files-service", path="/files") 
interface FilesResourceClient extends FilesResourceDefinition {
	
}
