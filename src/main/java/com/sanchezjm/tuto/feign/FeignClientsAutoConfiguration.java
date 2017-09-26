package com.sanchezjm.tuto.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sanchezjm.tuto.feign.handler.CustomFeignErrorDecoder;

@Configuration
public class FeignClientsAutoConfiguration {

	@Bean
	public CustomFeignErrorDecoder customFeignErrorDecoder(){
		return new CustomFeignErrorDecoder();
	}
	
}
