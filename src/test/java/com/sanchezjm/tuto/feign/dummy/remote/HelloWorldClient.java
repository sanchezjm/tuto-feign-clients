package com.sanchezjm.tuto.feign.dummy.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanchezjm.tuto.feign.dummy.HelloWorld;

@FeignClient(name = "localapp")
public interface HelloWorldClient extends HelloWorld {

	@RequestMapping(value="/hello/{name}")
	String sayHi(@PathVariable("name") String name);
	
}
