package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.services.MathExpressionCreator;

public class MathExpressionAddictionCreator extends MathExpressionCreator {

    @Override
    protected String getOperationName() {
        return "plus";
    }

    @Override
    protected String getOperationResultName() {
        return "sum";
    }
}
