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

    public Boolean[] calculateRow() {

        List<Boolean> result = new ArrayList<>();

        for (Expression p : this.premiseList){
            result.add(p.calculate());
        }

        result.add(this.conclusion.calculate());
        return result.toArray(new Boolean[0]);
    }

    public void printTruthTable() {

        //get all symbols and sort in alphabetic order
        getAllSymbols();

        List<String> symbolList = new ArrayList<>(symbolSet);

        char[] symbolCharArray = String.join("", symbolList).toCharArray();
        Arrays.sort(symbolCharArray);

        symbolList.clear();

        for (char c : symbolCharArray)
            symbolList.add(Character.toString(c));

        Debugger.log(symbolList.toString());

        //print truth table
        int colSize = symbolList.size();
        int rowSize = (int) Math.pow(2, symbolList.size());
        boolean flipIndicator = false; // initial value is True.
        int sectionSize = rowSize / 2; // initial the size of the first.

        Debugger.log("row size: " + rowSize);
        Debugger.log("col size: " + colSize);
        Debugger.log("section size: " + sectionSize);

        Boolean[][] truthValues = new Boolean[rowSize][colSize];
        Boolean[][] resultValues = new Boolean[rowSize][premiseList.size() + 1]; // size = num of premises + conclusion

        // generate truth value table
        for (int col = 0; col < colSize; col++) {
            for (int row = 0; row < rowSize; row++) {

                if (row % sectionSize == 0) //flip the truth value indicator per section
                    flipIndicator = !flipIndicator;

                truthValues[row][col] = flipIndicator; // set the truth value
            }

            flipIndicator = false; // reset indicator for each col.
            sectionSize = sectionSize / 2; // re-calculate the size of the section for that col.
        }

        //print out truth table header
        System.out.print(String.format("Truth Table:\n%5s\t",""));
        for (int i = 0; i < symbolList.size(); i++) {
            System.out.print(String.format("%6s\t", symbolList.get(i)));
        }
        for (int i = 0; i < premiseList.size(); i++) {
            System.out.print(String.format("%15s\t", premiseList.get(i)));
        }

        System.out.println(String.format("%15s\t", this.conclusion));

        //print out truth table body
        for (int row = 0; row < rowSize; row++) {

            restore();

            System.out.print(String.format("%5s:\t", row + 1));

            for (int col = 0; col < colSize; col++) {

                System.out.print(String.format("%6s\t", truthValues[row][col]));

                assign(symbolList.get(col), truthValues[row][col]);

            }

            Boolean[] resultRow = calculateRow();

            for (int i = 0; i < resultRow.length; i++){
                System.out.print(String.format("%15s\t", resultRow[i]));
            }

            System.out.println();

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
