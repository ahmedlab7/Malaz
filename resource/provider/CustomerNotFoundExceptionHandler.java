package com.cubic.rest.resource.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import com.cubic.rest.service.ErrorEntity;
import com.cubic.rest.service.exception.CustomerNotFoundException;

@Service
@Provider
public class CustomerNotFoundExceptionHandler implements ExceptionMapper<CustomerNotFoundException> {

	@Override
	public Response toResponse(final CustomerNotFoundException exception) {
		final ErrorEntity entity = ErrorEntity.builder().errorCode("ERR-200")
				.errorDesc("Customer not found in our system").build();
		return Response.status(Status.NOT_FOUND).entity(entity).build();
	}

}
