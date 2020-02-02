package com.bloomberg.calculatorapi.mappers;

import com.bloomberg.calculatorapi.domain.Operation;
import com.bloomberg.calculatorapi.web.requests.CalculatorRequest;
import com.bloomberg.calculatorapi.web.responses.CalculatorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper implements Mapper<Operation, CalculatorRequest, CalculatorResponse> {

    private ModelMapper mapper;

    @Autowired
    public OperationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Operation toDto(CalculatorRequest request) {

        return mapper.map(request, Operation.class);
    }

    @Override
    public CalculatorResponse toResponse(Operation dto) {

        return mapper.map(dto, CalculatorResponse.class);
    }
}
