package argVal.model.calculator;

import argVal.model.Operator;

import java.util.Stack;

/**
 * reference: http://www2.lawrence.edu/fast/GREGGJ/CMSC150/073Calculator/Calculator.html
 */
public class BoolExpCalculator {

    private Stack<String> operatorStack;
    private Stack<Boolean> symbolStack;

    public BoolExpCalculator() {
        this.operatorStack = new Stack<>();
        this.symbolStack = new Stack<>();
    }

    private boolean isOperator(String s) {

        return s.equals("&") || s.equals("|") || s.equals("~") || s.equals(">");
    }

    private int getPrecedence(String s) {
        if (s.equals("&") || s.equals("|") || s.equals(">")) {
            return 1;
        }
        if (s.equals("~")) {
            return 2;
        }
        return 0;
    }

    private void processOperator(String token) {

        if(token.equals("~")){
            if (symbolStack.empty()) {
                throw new RuntimeException("Error: Invalid expression. Check the expression.");
            }
            symbolStack.push(!symbolStack.pop());
            return;
        }

        Boolean a, b;
        if (symbolStack.empty()) {
           throw new RuntimeException("Error: Invalid expression. Check the expression.");
        } else {
            b =  symbolStack.pop();
        }
        if (symbolStack.empty()) {
            throw new RuntimeException("Error: Invalid expression. Check the expression.");
        } else {
            a =  symbolStack.pop();
        }

        if (token.equals("|")) {
            symbolStack.push(a || b);
        } else if (token.equals("&")) {
            symbolStack.push(a && b);
        } else if (token.equals(">")) {
            symbolStack.push(Operator.imply(a , b));
        } else {
            throw new RuntimeException(String.format("Error: Invalid Operator: %s. Check the expression.", token));
        }

    }

    public boolean calculate(String expression) {

        //Debugger.log("input: " + input);

        // Main loop - process all input tokens
        for (int i = 0; i < expression.length(); i++) {
            String token = Character.toString(expression.charAt(i));

            //Debugger.log("token: " + token);
            if (token.equals("T")){
                //Debugger.log("parse token to true");
                symbolStack.push(Boolean.parseBoolean("true"));
            }
            else if(token.equals("F")) {
                //Debugger.log("parse token to false");
                symbolStack.push(Boolean.parseBoolean("false"));
            }
            else if (isOperator(token)) {
                //Debugger.log("token is operator");
                if (operatorStack.empty() || getPrecedence(token) > getPrecedence(operatorStack.peek())) {
                    operatorStack.push(token);
                } else {
                    while (!operatorStack.empty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                        processOperator(operatorStack.pop());
                    }
                    operatorStack.push(token);
                }
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                    processOperator(operatorStack.pop());
                }
                if (!operatorStack.empty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                } else {
                    throw new RuntimeException("unbalanced parenthesis.");
                }
            }

        }

        // Empty out the operator stack at the end of the input
        while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
            processOperator(operatorStack.pop());
        }

        Boolean result = symbolStack.pop();

        if (!operatorStack.empty() || !symbolStack.empty()) {
            throw new RuntimeException("Expression error.");
        } else {
           return result;
        }

    }

}
