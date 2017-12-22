package com.snfq.cache.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.snfq.cache.controller.LockResourceDefinition;

@FeignClient(value="cache-service", path="/locks")
interface LockResourceClient extends LockResourceDefinition {
	
}
