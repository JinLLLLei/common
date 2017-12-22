package com.snfq.cache.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.snfq.cache.controller.CaptchaResourceDefinition;

@FeignClient(value="cache-service", path="/captchas")
interface CaptchaResourceFeignClient extends CaptchaResourceDefinition {
	
}
