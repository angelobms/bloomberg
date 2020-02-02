package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.services.MathExpressionCreator;

public class MathExpressionSubtractionCreator extends MathExpressionCreator {

    @Override
    protected String getOperationName() {
        return "minus";
    }

    @Override
    protected String getOperationResultName() {
        return "difference";
    }
}
