package com.bloomberg.mathexpression.services.external;

import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.exceptions.ServiceUnavailableException;
import com.bloomberg.mathexpression.services.external.decodes.ResultDecoder;
import com.bloomberg.mathexpression.services.external.decodes.ResultErrorHandler;
import com.bloomberg.mathexpression.services.external.requests.CalculatorServiceProxyRequest;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import feign.Feign;
import feign.RetryableException;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceClientImpl implements CalculatorServiceClient {

    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorServiceClientImpl.class);

    private EurekaClient eurekaClient;

    private ResultDecoder resultDecoder;

    private ResultErrorHandler resultErrorDecoder;

    private CalculatorServiceProxy serviceProxy;

    private String calculatorApiId;

    private String calculatorApiUrl;

    @Autowired
    public CalculatorServiceClientImpl(EurekaClient eurekaClient, ResultDecoder resultDecoder, ResultErrorHandler resultErrorDecoder) {
        this.eurekaClient = eurekaClient;
        this.resultDecoder = resultDecoder;
        this.resultErrorDecoder = resultErrorDecoder;
    }

    @Override
    public Result calc(CalculatorServiceProxyRequest request) {
        Result result;

        try {
            LOGGER.info("Getting Calculator API instance from discovery service...");
            this.serviceProxy = Feign.builder()
                    .client(new ApacheHttpClient())
                    .encoder(new JacksonEncoder())
                    .decoder(resultDecoder)
                    .errorDecoder(resultErrorDecoder)
                    .target(CalculatorServiceProxy.class, getCalculatorApiUrl());

            LOGGER.info("Calling Calculator API to perform the operation...");

            result = serviceProxy.calc(request);
        } catch (RetryableException e) {
            LOGGER.error("Calculator API unavailable.", e);
            throw new ServiceUnavailableException(e);
        }
        return result;
    }

    private String getCalculatorApiUrl() {
        Application application = eurekaClient.getApplication(calculatorApiId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = String.format("http://%s:%s/%s",
                instanceInfo.getIPAddr(), instanceInfo.getPort(), calculatorApiUrl);
        LOGGER.info("Calculator API URL: {}", url);
        return url;
    }

    @Value("${external.client.calculator-api.id}")
    public void setCalculatorApiId(String calculatorApiId) {
        this.calculatorApiId = calculatorApiId;
    }

    @Value("${external.client.calculator-api.context-uri}")
    public void setCalculatorApiUrl(String calculatorApiUrl) {
        this.calculatorApiUrl = calculatorApiUrl;
    }
}
