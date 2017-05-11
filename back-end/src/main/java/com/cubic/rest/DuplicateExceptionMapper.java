package com.cubic.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.cubic.exception.DuplicateException;
import com.cubic.vo.ErrorResponse;

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class DuplicateExceptionMapper implements ExceptionMapper<DuplicateException> {

	public Response toResponse(final DuplicateException exception) {
		ErrorResponse errResp = new ErrorResponse();
		errResp.setErrorCode("ERR-VAL-409");
		errResp.setErrorDesc(exception.getMessage());
		return Response.serverError().entity(errResp).build();
	}
}
