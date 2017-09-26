package com.sanchezjm.tuto.feign.interceptor;


import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityFeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHENTICATION_HEADER = "my-security-header";

	@Override
    public void apply(RequestTemplate template) {
        propagateAuthorizationHeader(template);
	}

	private void propagateAuthorizationHeader(RequestTemplate template) {
		if (template.headers().containsKey(AUTHENTICATION_HEADER)) {
            log.trace("the authorization {} token has been already set", AUTHENTICATION_HEADER);
        } else {
        	log.trace("setting the authorization token {}", AUTHENTICATION_HEADER);
            template.header(AUTHENTICATION_HEADER, SecurityContextHolder.getContext().getAuthentication().getName());
        }
	}
}
