import argVal.model.Argument;
import argVal.model.Expression;
import argVal.model.Symbol;
import argVal.model.calculator.BoolExpCalculator;
import org.junit.Test;

public class ArgValidatorTest {

    @Test
    public void testCal() {

        //        String s = "~ ( T &  ~ T )";
        Expression exp = new Expression("  T -> ~T ");

        System.out.println("Expression: " + exp);

        System.out.println("Result: " + exp.calculate());

    }

    @Test
    public void testBoolExpression() {

        Expression exp = new Expression("(A v B) -> ~(B v C)");
        System.out.println(exp.getSymbolList());
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        exp.assign("B", false);
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        exp.assign("A", true);
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        exp.assign("C", true);
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        System.out.println("Result: " + exp.calculate());

    }

    @Test
    public void testAss5Q1() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("(A & B) -> ~C"));
        arg.addPremise(new Expression("~(A v B) -> ~(B v C)"));
        arg.setConclusion(new Expression("~(A & (~B & C))"));
        arg.printArgument();

        arg.printTruthTable();


    }

    @Test
    public void testAss5Q2() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("(R v S) -> ~(U & V)"));
        arg.addPremise(new Expression("~(U & R) -> ~V"));
        arg.addPremise(new Expression("~(R v S) v V"));
        arg.setConclusion(new Expression("(V v R) -> ~(S & U)"));
        arg.printArgument();

        arg.printTruthTable();


    }

    @Test
    public void testAss5Q3() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("S -> (N v H)"));
        arg.addPremise(new Expression("S v ~N"));
        arg.setConclusion(new Expression("S -> H"));
        arg.printArgument();

        arg.printTruthTable();


    }

    @Test
    public void testAss5Q4() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("A->(B&C)"));
        arg.addPremise(new Expression("~B"));
        arg.setConclusion(new Expression("~A"));
        arg.printArgument();

        arg.printTruthTable();


    }


    @Test
    public void testAss5Q5() {

        Argument arg = new Argument();

        arg.addPremise(new Expression("(P & V) -> S"));
        arg.addPremise(new Expression("(P -> V) & (V -> P)"));
        arg.setConclusion(new Expression("~S & (V v P)"));
        arg.printArgument();

        arg.printTruthTable();
        
    }

    @Test
    public void testAss5Q6() {

        Argument arg = new Argument();

        arg.addPremise(new Expression("(M | ~E) ->D"));
        arg.addPremise(new Expression("~E -> ~M"));
        arg.addPremise(new Expression("M -> ~E"));
        arg.setConclusion(new Expression("D"));
        arg.printArgument();

        arg.printTruthTable();

    }

    @Test
    public void testAss5Q8() {

        Argument arg = new Argument();

        arg.addPremise(new Expression("A v (B & C)"));
        arg.addPremise(new Expression("~(D -> B)"));
        arg.addPremise(new Expression("~B -> A"));
        arg.setConclusion(new Expression("C & ~A"));
        arg.printArgument();

        arg.printTruthTable();

    }


    // Assignment 6
    @Test
    public void testAss6Q1() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("(K & ~C) -> ~(P & R)"));
        arg.addPremise(new Expression("J -> (K & P)"));
        arg.addPremise(new Expression("A -> (P & R)"));
        arg.setConclusion(new Expression("(A & J) -> C"));
        arg.printArgument();

        arg.printTruthTable();


    }

}
