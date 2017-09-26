package com.sanchezjm.tuto.feign.hystrix.dummy.local;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class HelloWorldFooClient implements HelloWorldFallBackClient {

	public String sayHi(String name){
		return "Bye "+ name + "!";
	}
	
}
