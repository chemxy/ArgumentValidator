package argVal.model;

import argVal.model.calculator.BoolExpCalculator;

public class BoolExpression {

    private String expression;
    private boolean value;

    public BoolExpression(String expression) {

        this.expression = expression.trim().replaceAll("\\s+", "").replaceAll("->", ">");
    }

    public String getExpression() {
        return expression;
    }

    public boolean getValue() {
        return value;
    }

    public boolean calculate() {

        BoolExpCalculator calc = new BoolExpCalculator();

        this.value = calc.calculate(this.expression);

        return this.value;
    }

}
