package com.newchieve.crm.config;

import com.newchieve.component.exception.FeignErrorDecoder;
import feign.codec.ErrorDecoder;
import feign.slf4j.Slf4jLogger;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.newchieve")
public class FeignConfig {

	@Bean
	public feign.Logger logger(){
		return new Slf4jLogger();
	}

	@Bean
	public ErrorDecoder feignDecoder() {
		return new FeignErrorDecoder();
	}
}
