package com.bloomberg.mathexpression.services;

import com.bloomberg.mathexpression.domain.Operation;
import com.bloomberg.mathexpression.domain.Result;

public interface MathExpressionService {

    Result calc(Operation operation);
}
