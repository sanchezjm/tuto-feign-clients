package com.sanchezjm.tuto.feign;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.sanchezjm.tuto.feign.dummy.HelloWorld;
import com.sanchezjm.tuto.feign.dummy.remote.HelloWorldClient;
import com.sanchezjm.tuto.feign.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {FeignClientsApplication.class, FeignClientsIntegrationTest.Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT, value = {
		"spring.application.name=hello-service", "feign.hystrix.enabled=true",
		"feign.okhttp.enabled=false" , "hystrix.shareSecurityContext=true"})
@DirtiesContext
public class FeignClientsIntegrationTest {

	@Value("${local.server.port}")
	private int port = 0;
	
	@Autowired
	protected HelloWorldClient helloWorldClient;
	
	@Configuration
	@EnableAutoConfiguration
	@RestController
	@EnableFeignClients(clients = { HelloWorldClient.class })
	@RibbonClient(name = "localapp", configuration = LocalRibbonClientConfiguration.class)
	protected static class Application implements HelloWorld {

		@Override
		@RequestMapping(value="/hello/{name}")
		public String sayHi(@PathVariable("name") String name) {
			if ("dummy".equals(name)){
				throw new IllegalArgumentException("Dummies not supported!");
			}
			if ("nil".equals(name)){
				throw new BusinessException("Nil not supported!");
			}			
			return "Hi " + name + "!";
		}

		@RequestMapping(value="/not_found")
		public String notFound() {
			return "";
		}
		
	}

	@Configuration
	static class LocalRibbonClientConfiguration {

		@Value("${local.server.port}")
		private int port = 0;

		@Bean
		public ServerList<Server> ribbonServerList() {
			return new StaticServerList<>(new Server("localhost", this.port));
		}
	}
	
    @Test
	public void shouldSayHi(){
		Assert.assertEquals("Hi all!", helloWorldClient.sayHi("all"));
	}

	@Test
	public void shouldThrowIllegalArgumentException(){
		try {
			final String message = helloWorldClient.sayHi("dummy");
			Assert.fail("Must throws an Exception");
		}
		catch(HystrixRuntimeException e) {
			Assert.assertEquals("Dummies not supported!", e.getCause().getMessage());
		}
	}
	
}
