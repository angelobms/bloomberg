package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.services.MathExpressionCreator;

public class MathExpressionDivisionCreator extends MathExpressionCreator {

    @Override
    protected String getOperationName() {
        return "over";
    }

    @Override
    protected String getOperationResultName() {
        return "quotient";
    }
}
