package com.cubic.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloWorld {

	@GET
	public String welcome() {
		return "Welcome to Rest Webservices";
	}
	
}
