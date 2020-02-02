package com.bloomberg.mathexpression.services.external;

import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.services.external.requests.CalculatorServiceProxyRequest;

public interface CalculatorServiceClient {

    Result calc(CalculatorServiceProxyRequest request);
}
