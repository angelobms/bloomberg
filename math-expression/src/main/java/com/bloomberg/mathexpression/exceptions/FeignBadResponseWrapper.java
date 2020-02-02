package com.bloomberg.mathexpression.exceptions;

import com.bloomberg.mathexpression.web.errors.ErrorMessage;
import com.bloomberg.mathexpression.web.errors.ErrorMessages;
import feign.FeignException;
import feign.Request;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
public class FeignBadResponseWrapper extends FeignException.BadRequest {

    private final int status;

    private final HttpHeaders headers;

    private final String body;

    private ErrorMessage[] errors;

    public FeignBadResponseWrapper(int status, Request request, HttpHeaders headers, String body) {
        super("Bad request", request, body.getBytes());
        this.status = status;
        this.headers = headers;
        this.body = body;
        this.errors = new ErrorMessage[0];
    }

    public FeignBadResponseWrapper(int status, Request request, HttpHeaders headers, String body, ErrorMessages errors) {
        this(status, request, headers, body);
        if (errors != null) {
            this.errors = errors.getErrors().toArray(new ErrorMessage[0]);
        } else {
            this.errors = new ErrorMessage[0];
        }
    }
}
