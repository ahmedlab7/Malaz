package com.cubic.rest.resource.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import com.cubic.rest.service.ErrorEntity;
import com.cubic.rest.service.exception.InvalidDataException;

@Service
@Provider
public class InvalidDataExceptionHandler implements ExceptionMapper<InvalidDataException> {

	public InvalidDataExceptionHandler() {
	}

	@Override
	public Response toResponse(final InvalidDataException exception) {
		final ErrorEntity entity = ErrorEntity.builder().errorCode("ERR-100").errorDesc(exception.getMessage()).build();
		return Response.status(510).entity(entity).build();
	}

}
