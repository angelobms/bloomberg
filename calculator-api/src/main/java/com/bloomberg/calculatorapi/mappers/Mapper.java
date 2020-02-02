package com.bloomberg.calculatorapi.mappers;

import com.bloomberg.calculatorapi.domain.DTO;
import com.bloomberg.calculatorapi.web.requests.Request;
import com.bloomberg.calculatorapi.web.responses.Response;

public interface Mapper<D extends DTO, RQ extends Request, RS extends Response> {

    D toDto(RQ request);

    RS toResponse(D dto);
}
