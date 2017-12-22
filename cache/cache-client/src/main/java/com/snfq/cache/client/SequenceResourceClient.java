package com.snfq.cache.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.snfq.cache.controller.SequenceResourceDefinition;

@FeignClient(value="cache-service", path="/sequences")
public interface SequenceResourceClient extends SequenceResourceDefinition {

}
