package com.sanchezjm.tuto.feign.hystrix.dummy.local;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanchezjm.tuto.feign.dummy.HelloWorld;

@FeignClient(name = "localapp", fallback= HelloWorldFooClient.class)
public interface HelloWorldFallBackClient extends HelloWorld {

	@RequestMapping(value="/hello/{name}")
	String sayHi(@PathVariable("name") String name);
	
}
