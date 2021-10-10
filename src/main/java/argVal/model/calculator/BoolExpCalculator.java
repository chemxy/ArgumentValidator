package argVal.model.calculator;

import argVal.model.operation.Operation;

import java.util.Stack;

/**
 * reference: http://www2.lawrence.edu/fast/GREGGJ/CMSC150/073Calculator/Calculator.html
 */
public class BoolExpCalculator {

    private Stack<String> operatorStack;
    private Stack<Boolean> valueStack;

    public BoolExpCalculator() {
        this.operatorStack = new Stack<>();
        this.valueStack = new Stack<>();
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
        Boolean a, b;
        if(token.equals("~")){
            if (valueStack.empty()) {
                throw new RuntimeException("Expression error.");
            } else {
                b = valueStack.peek();
                valueStack.pop();
            }
            valueStack.push(!b);
            return;
        }

        if (valueStack.empty()) {
           throw new RuntimeException("Expression error.");
        } else {
            b = valueStack.peek();
            valueStack.pop();
        }
        if (valueStack.empty()) {
            throw new RuntimeException("Expression error.");
        } else {
            a = valueStack.peek();
            valueStack.pop();
        }
        Boolean r = null;
        if (token.equals("|")) {
            r = a || b;
        } else if (token.equals("&")) {
            r = a && b;
        } else if (token.equals("->") || token.equals(">")) {
            r = Operation.imply(a , b);
        } else {
            throw new RuntimeException("Oprator error.");
        }
        valueStack.push(r);
    }

    public boolean calculate(String input) {

        //Debugger.log("input: " + input);

        String[] tokens = input.split(" ");

        String inputTrimmed = input.trim().replaceAll("\\s+", "").replaceAll("->", ">");

        // Main loop - process all input tokens

        for (int i = 0; i < inputTrimmed.length(); i++) {
            String token = Character.toString(inputTrimmed.charAt(i));

            //Debugger.log("token: " + token);
            if (token.equals("T")){
                //Debugger.log("parse token to true");
                Boolean value = Boolean.parseBoolean("true");
                valueStack.push(value);
            }
            else if(token.equals("F")) {
                //Debugger.log("parse token to false");
                Boolean value = Boolean.parseBoolean("false");
                valueStack.push(value);
            }
            else if (isOperator(token)) {
                //Debugger.log("token is operator");
                if (operatorStack.empty() || getPrecedence(token) > getPrecedence(operatorStack.peek())) {
                    operatorStack.push(token);
                } else {
                    while (!operatorStack.empty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                        String toProcess = operatorStack.peek();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(token);
                }
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                    String toProcess = operatorStack.peek();
                    operatorStack.pop();
                    processOperator(toProcess);
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
            String toProcess = operatorStack.peek();
            operatorStack.pop();
            processOperator(toProcess);
        }
        // Print the result if no error has been seen.

        Boolean result = valueStack.peek();
        valueStack.pop();
        if (!operatorStack.empty() || !valueStack.empty()) {
            throw new RuntimeException("Expression error.");
        } else {
           return result;
        }

    }

}
