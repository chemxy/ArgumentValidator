package argVal.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {

    private String expression;
    private String assignedExpression;
    private Set<Symbol> symbolList;


    public Expression(String expression) {
        super();
        this.expression = expression.trim().replaceAll("\\s+", "").replaceAll("->", ">").replaceAll("v", "|");
        this.assignedExpression = this.expression;
        this.symbolList = new HashSet<>();

        for (char c : this.expression.toCharArray()) {

            if (Character.isLetter(c)) {

                Symbol symbol = new Symbol(c);

                if (!symbolList.contains(symbol)) {
                    symbolList.add(symbol);
                }
            }
        }
    }

    public String getExpression() {
        return this.expression;
    }

    public String getAssignedExpression() {
        return this.assignedExpression;
    }

    public Set<Symbol> getSymbolList() {
        return symbolList;
    }

    public void assign(Symbol symbol, boolean value) {
        this.assignedExpression = this.assignedExpression.replaceAll(symbol.getName(), value ? "T" : "F");
    }

    public void assign(String symbol, boolean value) {
        this.assignedExpression = this.assignedExpression.replaceAll(symbol, value ? "T" : "F");
    }

    public void restore() {
        this.assignedExpression = this.expression;
    }

    public boolean validate() {

        Pattern r = Pattern.compile("[()TF>&~|]*");

        Matcher m = r.matcher(this.assignedExpression);

        return m.matches();
    }

    public boolean calculate() {
        if (validate()) {
            return new BoolExpression(this.assignedExpression).calculate();
        }
        throw new RuntimeException(String.format("Expression: %s is not validated", this.expression));
    }

    @Override
    public String toString() {
        return this.expression;
    }
}
