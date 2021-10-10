package argVal.model;

import argVal.logger.Debugger;

import java.util.*;

public class Argument {

    private final ArrayList<Expression> premiseList;
    private Expression conclusion;
    private final Set<String> symbolSet;

    public Argument() {
        this.premiseList = new ArrayList<>();
        this.conclusion = null;
        this.symbolSet = new HashSet<>();
    }

    public ArrayList<Expression> getPremiseList() {
        return premiseList;
    }

    public void addPremise(Expression p) {
        this.premiseList.add(p);
    }

    public Expression getConclusion() {
        return conclusion;
    }

    public void setConclusion(Expression c) {
        this.conclusion = c;
    }

    public void assign(String symbol, boolean value) {
        for (Expression p : this.premiseList)
            p.assign(symbol, value);

        this.conclusion.assign(symbol, value);
    }

    public void printArgument() {
        System.out.println(this.toString(false));
    }

    public void printAssignedArgument() {
        System.out.println(this.toString(true));
    }

    public void getAllSymbols() {
        for (Expression p : this.premiseList)
            this.symbolSet.addAll(p.getSymbolList());

        this.symbolSet.addAll(conclusion.getSymbolList());
    }

    public Set<String> getSymbolSet() {
        getAllSymbols();
        return symbolSet;
    }

    public void restore() {
        for (Expression p : this.premiseList)
            p.restore();

        this.conclusion.restore();
    }

    public void calculate() {

        for (Expression p : this.premiseList)
            System.out.print(p.calculate() + ", ");


        System.out.println("| " + this.conclusion.calculate());
    }


    public void printTruthTable() {
        getAllSymbols();
        List<String> symbolList = new ArrayList<>();
        for (String s : symbolSet)
            symbolList.add(s);


        char[] symbolCharArray = String.join("", symbolList).toCharArray();
        Arrays.sort(symbolCharArray);

        symbolList.clear();

        for (char c : symbolCharArray)
            symbolList.add(Character.toString(c));

//       Debugger.log(symbolList.toString());

        if (symbolList.size() == 2) {
            restore();
            System.out.print("line 1: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            calculate();

            restore();
            System.out.print("line 2: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            calculate();

            restore();
            System.out.print("line 3: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            calculate();

            restore();
            System.out.print("line 4: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            calculate();

        } else if (symbolList.size() == 3) {

            restore();
            System.out.print("line 1: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), true);
            calculate();

            restore();
            System.out.print("line 2: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), false);
            calculate();

            restore();
            System.out.print("line 3: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), true);
            calculate();

            restore();
            System.out.print("line 4: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), false);
            calculate();

            restore();
            System.out.print("line 5: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), true);
            calculate();

            restore();
            System.out.print("line 6: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), false);
            calculate();

            restore();
            System.out.print("line 7: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), true);
            calculate();

            restore();
            System.out.print("line 8: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), false);
            calculate();

        } else if (symbolList.size() == 4) {

            restore();
            System.out.print("line 1: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 2: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 3: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 4: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 5: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 6: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 7: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 8: ");
            assign(symbolList.get(0), true);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 9: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 10: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 11: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 12: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), true);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 13: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.print("line 14: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), true);
            assign(symbolList.get(3), false);
            calculate();

            restore();
            System.out.print("line 15: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), true);
            calculate();

            restore();
            System.out.println("line 16: ");
            assign(symbolList.get(0), false);
            assign(symbolList.get(1), false);
            assign(symbolList.get(2), false);
            assign(symbolList.get(3), false);
            calculate();
        }

    }

    public String toString(boolean option) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        if (!option) {

            for (Expression p : this.premiseList) {
                sb.append(p.getExpression()).append("\n");
            }

            sb.append("-------------------------------------\n");
            if (this.conclusion != null)
                sb.append(this.conclusion.getExpression());

        } else {
            for (Expression p : this.premiseList) {
                sb.append(p.getAssignedExpression()).append("\n");
            }

            sb.append("-------------------------------------\n");
            if (this.conclusion != null)
                sb.append(this.conclusion.getAssignedExpression());
        }

        sb.append("\n");
        return sb.toString();

    }
}
