package com.bloomberg.mathexpression.services.external.decodes;

import com.bloomberg.mathexpression.domain.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
public class ResultDecoder implements Decoder {

    private static Logger LOGGER = LoggerFactory.getLogger(ResultDecoder.class);

    private ObjectMapper objectMapper;

    @Autowired
    public ResultDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object decode(Response response, Type type) throws FeignException {
        LOGGER.info("Decoding Calculator API response...");
        return extractObject(response.body());
    }

    private Result extractObject(Response.Body body) {
        Result result = null;
        try {
            String json = IOUtils.toString(body.asReader());
            LOGGER.debug("JSON not sanitized:\n{}", json);
            json = sanatizeJson(json);
            LOGGER.debug("JSON sanitized:\n{}", json);

            LOGGER.debug("Deserializing json...");
            result = objectMapper.readValue(json, Result.class);
            LOGGER.info("Calculator API response decoded.");
        } catch (IOException e) {
            LOGGER.error("Error Decoding Calculator API response.", e);
        }
        return result;
    }

    private String sanatizeJson(String json) {
        json = json.replaceAll("\\{\"calculator_response\":", "");
        json = json.substring(0, json.length()-1);
        return json;
    }
}
