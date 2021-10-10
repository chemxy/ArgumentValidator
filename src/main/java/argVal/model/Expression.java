package argVal.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {

    private final String expression;
    private String assignedExpression;
    private final Set<String> symbolList;

    public Expression(String expression) {

        super();
        this.expression = expression.trim().replaceAll("\\s+", "").replaceAll("->", ">").replaceAll("v", "|");
        this.assignedExpression = this.expression;
        this.symbolList = new HashSet<>();

        for (char c : this.expression.toCharArray()) // for each char in the expression string
            if (Character.isLetter(c))  // if the char is a symbol
                symbolList.add(Character.toString(c)); // add the symbol to the set
    }

    public String getExpression() {
        return this.expression;
    }

    public String getAssignedExpression() {
        return this.assignedExpression;
    }

    public Set<String> getSymbolList() {
        return symbolList;
    }

    /**
     * Assign a symbol with a truth value.
     * @param symbol
     * @param value
     */
    public void assign(String symbol, boolean value) {
        this.assignedExpression = this.assignedExpression.replaceAll(symbol, value ? "T" : "F");
    }

    /**
     * Restore to the original expression.
     */
    public void restore() {
        this.assignedExpression = this.expression;
    }

    /**
     * Determine if this is a valid expression.
     * That is, check if all symbols are replaced with a truth value.
     * @return
     */
    public boolean validate() {

        Pattern r = Pattern.compile("[()TF>&~|]*");

        Matcher m = r.matcher(this.assignedExpression);

        return m.matches();
    }

    /**
     * Calculate the truthe value of the expression if the expression is valid.
     * @return the truth value of the expression.
     */
    public boolean calculate() {

        if (validate()) // if this expression is a valid expression, do the calculation
            return new BoolExpression(this.assignedExpression).calculate();

        throw new RuntimeException(String.format("Expression: %s is not valid", this.expression));
    }

    @Override
    public String toString() {
        return this.expression;
    }
}
