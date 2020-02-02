package com.bloomberg.mathexpression.mappers;

import com.bloomberg.mathexpression.domain.Operation;
import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.web.requests.CalculatorRequest;
import com.bloomberg.mathexpression.web.responses.CalculatorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    private ModelMapper mapper;

    @Autowired
    public OperationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Operation toDto(CalculatorRequest request) {

        return mapper.map(request, Operation.class);
    }

    public CalculatorResponse toResponse(Result dto) {

        return mapper.map(dto, CalculatorResponse.class);
    }
}
