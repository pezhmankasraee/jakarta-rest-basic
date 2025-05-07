package com.pezhmankasraee.jakartarestbasic.exception;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        JsonArrayBuilder errors = Json.createArrayBuilder();
        exception.getConstraintViolations().forEach(violation -> {
            errors.add(Json.createObjectBuilder()
                    .add("field", violation.getPropertyPath().toString())
                    .add("message", violation.getMessage()));
        });
        JsonObject errorResponse = Json.createObjectBuilder()
                .add("errors", errors)
                .build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type("application/json")
                .build();
    }
}
