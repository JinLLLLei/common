package com.snfq.cache.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.snfq.cache.controller.CacheResourceDefinition;


@FeignClient(value="cache-service", path="/caches", configuration=CacheResourceClientConfiguration.class)
interface CacheResourceClient extends CacheResourceDefinition {
    
}
