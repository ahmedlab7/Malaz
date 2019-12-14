package com.cubic.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.rest.service.CustomerData;
import com.cubic.rest.service.CustomerSearchResult;
import com.cubic.rest.service.CustomerService;
import com.cubic.rest.service.HeaderParameter;

@Service
@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

	@Autowired
	private CustomerService cs;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response createCustomer(final CustomerData customer) {
		final CustomerData result = cs.create(customer);
		return Response.ok().entity(result).build();
	}

	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findCustomer(@PathParam("customerId") final String id) {
		final CustomerData result = cs.get(id);
		return Response.ok().entity(result).build();
	}

	@PUT
	public Response modifyCustomer(final CustomerData customer) {
		cs.modify(customer);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{customerId}")
	public Response removeCustomer(@PathParam("customerId") final String id) {
		cs.remove(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/search")
	public Response search(@QueryParam("name") final String name) {
		final CustomerSearchResult result = cs.search(name);
		return Response.ok().entity(result).build();
	}

	@GET
	@Path("/matrix")
	public Response searchByMatrix(@MatrixParam("name") final String name) {
		return search(name);
	}

	@GET
	@Path("/hdr")
	public Response headerParameters(@HeaderParam("auth-key") final String authKey,
			@HeaderParam("credential") final String credential, @HeaderParam("version") final String version) {
		final HeaderParameter result = HeaderParameter.builder().authKey(authKey).credential(credential)
				.version(version).build();
		return Response.ok().entity(result).build();
	}

	@GET
	@Path("/ctx")
	public Response ctxParameters(@Context final HttpHeaders header) {
		final String authKey = header.getHeaderString("auth-key");
		final String credential = header.getHeaderString("credential");
		final String version = header.getHeaderString("version");
		return headerParameters(authKey, credential, version);
	}

}
