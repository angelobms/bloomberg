package com.bloomberg.mathexpression.services.external;

import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.services.external.requests.CalculatorServiceProxyRequest;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "calculator-api", url = "http://calculator-api:8080")
public interface CalculatorServiceProxy {
    @RequestLine("PATCH")
    @Headers("Content-Type: application/json")
    @RequestMapping(value = "/api/v1/calculators", method = RequestMethod.PATCH)
    Result calc(@Valid @RequestBody CalculatorServiceProxyRequest request);
}
