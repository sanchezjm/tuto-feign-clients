package com.sanchezjm.tuto.feign.clients;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sanchezjm.tuto.feign.dto.Post;

@FeignClient(name="posts", url="https://jsonplaceholder.typicode.com")
public interface PostClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
	List<Post> getAll();
	
}
