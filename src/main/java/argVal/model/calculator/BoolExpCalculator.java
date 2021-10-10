package argVal.model.calculator;

import argVal.model.Operator;

import java.util.Stack;

/**
 * reference: http://www2.lawrence.edu/fast/GREGGJ/CMSC150/073Calculator/Calculator.html
 */
public class BoolExpCalculator {

    private final Stack<String> operatorStack;
    private final Stack<Boolean> symbolStack;

    public BoolExpCalculator() {

        this.operatorStack = new Stack<>();
        this.symbolStack = new Stack<>();
    }

    private boolean isOperator(String s) {

        return s.equals("&") || s.equals("|") || s.equals("~") || s.equals(">");
    }

    private int getPrecedence(String s) {

        if (s.equals("&") || s.equals("|") || s.equals(">"))
            return 1;

        if (s.equals("~"))
            return 2;

        return 0;
    }

    private void calculateToken(String token) {

        if(token.equals("~")){ // if the operator is NOT. do the calculation
            if (symbolStack.empty())
                throw new RuntimeException("Error: Invalid expression. Check the expression.");

            symbolStack.push(!symbolStack.pop());

            return;
        }

        // get two values from stack
        Boolean a, b;
        if (symbolStack.empty())
            throw new RuntimeException("Error: Invalid expression. Check the expression.");
        else
            a = symbolStack.pop();

        if (symbolStack.empty())
            throw new RuntimeException("Error: Invalid expression. Check the expression.");
        else
            b = symbolStack.pop();

        // get an operator from stack and do the calculation
        switch (token) {
            case "|":
                symbolStack.push(a || b);
                break;
            case "&":
                symbolStack.push(a && b);
                break;
            case ">":
                symbolStack.push(Operator.imply(a, b));
                break;
            default:
                throw new RuntimeException(String.format("Error: Invalid Operator: %s. Check the expression.", token));
        }


    }

    public boolean calculate(String expression) {

        //Debugger.log("input: " + input);

        // Main loop - parse and calculate every token
        for (int i = 0; i < expression.length(); i++) {
            String token = Character.toString(expression.charAt(i));

            //Debugger.log("token: " + token);
            if (token.equals("T")){
                //Debugger.log("parse token to true");
                symbolStack.push(true); // push a TRUE value to stack
            }
            else if(token.equals("F")) {
                //Debugger.log("parse token to false");
                symbolStack.push(false); // push a FALSE value to stack
            }
            else if (isOperator(token)) {
                //Debugger.log("token is operator");
                if (!operatorStack.empty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {

                    while (!operatorStack.empty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {

                        calculateToken(operatorStack.pop());
                    }

                }
                operatorStack.push(token);
            } else if (token.equals("(")) {

                operatorStack.push(token);

            } else if (token.equals(")")) {

                while (!operatorStack.empty() && isOperator(operatorStack.peek())) {

                    calculateToken(operatorStack.pop());
                }

                if (!operatorStack.empty() && operatorStack.peek().equals("(")) {

                    operatorStack.pop();
                } else {

                    throw new RuntimeException("Error: Unbalanced parenthesis. Check the expression.");
                }
            }

        }

        // Empty out the operator stack at the end of the input
        while (!operatorStack.empty() && isOperator(operatorStack.peek())) {

            calculateToken(operatorStack.pop());
        }

        Boolean result = symbolStack.pop();

        if (!operatorStack.empty() || !symbolStack.empty())
            throw new RuntimeException("Expression error.");
        else
           return result;

    }

}
