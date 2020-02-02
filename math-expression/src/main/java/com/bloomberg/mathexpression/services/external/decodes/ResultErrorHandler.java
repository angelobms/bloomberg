package com.bloomberg.mathexpression.services.external.decodes;

import com.bloomberg.mathexpression.exceptions.FeignBadResponseWrapper;
import com.bloomberg.mathexpression.web.errors.ErrorMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResultErrorHandler implements ErrorDecoder {

    private static Logger LOGGER = LoggerFactory.getLogger(ResultErrorHandler.class);

    private ObjectMapper objectMapper;

    @Autowired
    public ResultErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        LOGGER.info("Decoding Calculator API response error...");
        int status = response.status();

        if (status == 504) {
            String message = "";
            throw new RetryableException(status, message, Request.HttpMethod.PATCH, null, response.request());
        }

        String body = "Bad request";
        String json = "{}";

        try {
            json = IOUtils.toString(response.body().asReader());
            LOGGER.debug("JSON not sanitized:\n{}", json);
            json = sanatizeJson(json);
            LOGGER.debug("JSON sanitized:\n{}", json);
        } catch (Exception e) {
            LOGGER.error("Error Decoding Calculator API response error.", e);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, Collection<String>> headers = new HashMap<>();
        Request request = Request.create(Request.HttpMethod.PUT, "", headers, Request.Body.empty());
        try {
            LOGGER.debug("Deserializing json...");
            ErrorMessages errors = objectMapper.readValue(json, ErrorMessages.class);
            LOGGER.debug("Calculator API response error decoded.");
            return new FeignBadResponseWrapper(status, request, httpHeaders, body, errors);
        } catch (JsonProcessingException e) {
            return new FeignBadResponseWrapper(status, request, httpHeaders, body);
        }
    }

    private String sanatizeJson(String json) {
        json = json.replaceAll("\\{\"messageErrorApi\":", "");
        json = json.substring(0, json.length()-1);
        return json;
    }
}
