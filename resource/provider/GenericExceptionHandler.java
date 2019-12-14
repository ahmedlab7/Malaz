package com.cubic.rest.resource.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import com.cubic.rest.service.ErrorEntity;

@Service
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GenericExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(final Exception exception) {
		final ErrorEntity entity = ErrorEntity.builder().errorCode("ERR-500").errorDesc(exception.getMessage()).build();
		return Response.serverError().entity(entity).build();
	}

}
