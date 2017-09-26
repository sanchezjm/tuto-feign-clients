package com.sanchezjm.tuto.feign;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sanchezjm.tuto.feign.hystrix.dummy.local.HelloWorldFallBackClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {FeignClientsApplication.class, HystrixFeignClientsIntegrationTest.Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT, value = {
		"spring.application.name=identity-service", "feign.hystrix.enabled=true",
		"feign.okhttp.enabled=false" , "hystrix.shareSecurityContext=true"})
@DirtiesContext
@ComponentScan(basePackages="com.sanchezjm.tuto.hystrix.dummy.local")
public class HystrixFeignClientsIntegrationTest extends FeignClientsIntegrationTest{

	@Autowired
	protected HelloWorldFallBackClient helloWorldFallBackClient;
	
	@Test
	public void shouldThrowIllegalArgumentException(){
		Assert.assertEquals("Bye dummy!", helloWorldFallBackClient.sayHi("dummy"));
	}
	
}
