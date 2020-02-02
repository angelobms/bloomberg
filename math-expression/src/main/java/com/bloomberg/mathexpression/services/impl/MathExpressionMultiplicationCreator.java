package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.services.MathExpressionCreator;

public class MathExpressionMultiplicationCreator extends MathExpressionCreator {

    @Override
    protected String getOperationName() {
        return "times";
    }

    @Override
    protected String getOperationResultName() {
        return "product";
    }
}
