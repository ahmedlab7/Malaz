package com.cubic.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.cubic.rest.resource.CustomerResource;
import com.cubic.rest.resource.HelloWorld;

@Configuration
public class RestConfig extends ResourceConfig {

	public RestConfig() {
		this.register(HelloWorld.class);
		this.register(CustomerResource.class);
		this.packages("com.cubic.rest.resource.provider");
	}

}
