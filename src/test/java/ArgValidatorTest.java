import argVal.model.Argument;
import argVal.model.Expression;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ArgValidatorTest {

    @Rule
    public TestName name = new TestName();

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

        arg.generateTruthTable();


    }

    @Test
    public void testAss5Q2() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("(R v S) -> ~(U & V)"));
        arg.addPremise(new Expression("~(U & R) -> ~V"));
        arg.addPremise(new Expression("~(R v S) v V"));
        arg.setConclusion(new Expression("(V v R) -> ~(S & U)"));
        arg.printArgument();

        arg.generateTruthTable();


    }

    @Test
    public void testAss5Q3() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("S -> (N v H)"));
        arg.addPremise(new Expression("S v ~N"));
        arg.setConclusion(new Expression("S -> H"));
        arg.printArgument();

        arg.generateTruthTable();


    }

    @Test
    public void testAss5Q4() {
        
        Argument arg = new Argument();

        arg.addPremise(new Expression("A->(B&C)"));
        arg.addPremise(new Expression("~B"));
        arg.setConclusion(new Expression("~A"));
        arg.printArgument();

        arg.generateTruthTable();


    }


    @Test
    public void testAss5Q5() {

        Argument arg = new Argument();

        arg.addPremise(new Expression("(P & V) -> S"));
        arg.addPremise(new Expression("(P -> V) & (V -> P)"));
        arg.setConclusion(new Expression("~S & (V v P)"));
        arg.printArgument();

        arg.generateTruthTable();
        
    }

    @Test
    public void testAss5Q6() {

        Argument arg = new Argument();

        arg.addPremise(new Expression("(M | ~E) ->D"));
        arg.addPremise(new Expression("~E -> ~M"));
        arg.addPremise(new Expression("M -> ~E"));
        arg.setConclusion(new Expression("D"));
        arg.printArgument();

        arg.generateTruthTable();

    }

    @Test
    public void testAss5Q8() {

        Argument arg = new Argument();

        arg.addPremise(new Expression("A v (B & C)"));
        arg.addPremise(new Expression("~(D -> B)"));
        arg.addPremise(new Expression("~B -> A"));
        arg.setConclusion(new Expression("C & ~A"));
        arg.printArgument();

        arg.generateTruthTable();

    }


    // Assignment 6
    @Test
    public void testA6Q1() {
        System.out.println("Test Method Name: " + name.getMethodName());

        Argument arg = new Argument();

        arg.addPremise(new Expression("(K & ~C) -> ~(P & R)"));
        arg.addPremise(new Expression("J -> (K & P)"));
        arg.addPremise(new Expression("A -> (P & R)"));
        arg.setConclusion(new Expression("(A & J) -> C"));
        arg.printArgument();

        int isValid = arg.generateTruthTable();
        System.out.println("is valid argument: " + isValid);
    }

    @Test
    public void testA6Q2() {
        System.out.println("Test Method Name: " + name.getMethodName());

        Argument arg = new Argument();

        arg.addPremise(new Expression("E -> J"));
        arg.addPremise(new Expression("B -> Q"));
        arg.addPremise(new Expression("D -> (J & ~Q)"));
        arg.setConclusion(new Expression("(E & B) -> D"));
        arg.printArgument();

        int isValid = arg.generateTruthTable();
        System.out.println("is valid argument: " + isValid);
    }

    @Test
    public void testA6Q3() {
        System.out.println("Test Method Name: " + name.getMethodName());

        Argument arg = new Argument();

        arg.addPremise(new Expression("~S -> ~(Q v G)"));
        arg.addPremise(new Expression("(Q v S) & (G v ~N)"));
        arg.addPremise(new Expression("(N v ~S) -> L"));
        arg.setConclusion(new Expression("S & ~N"));
        arg.printArgument();

        int isValid = arg.generateTruthTable();
        System.out.println("is valid argument: " + isValid);
    }

    @Test
    public void testA6Q4() {
        System.out.println("Test Method Name: " + name.getMethodName());

        Argument arg = new Argument();

        arg.addPremise(new Expression("R -> (M v ~C)"));
        arg.addPremise(new Expression("(P v U) -> C"));
        arg.addPremise(new Expression("M -> ~P"));
        arg.addPremise(new Expression("R -> U"));
        arg.setConclusion(new Expression(" (M & U) v ~R"));
        arg.printArgument();

        int isValid = arg.generateTruthTable();
        System.out.println("is valid argument: " + isValid);
    }

    @Test
    public void testA6Q5() {
        System.out.println("Test Method Name: " + name.getMethodName());

        Argument arg = new Argument();

        arg.addPremise(new Expression("(E & ~R) -> J"));
        arg.addPremise(new Expression("~J & E"));
        arg.addPremise(new Expression("R -> (J v M)"));
        arg.setConclusion(new Expression("~ (E v M)"));
        arg.printArgument();

        int isValid = arg.generateTruthTable();
        System.out.println("is valid argument: " + isValid);
    }

    @Test
    public void testA6Q6() {
        System.out.println("Test Method Name: " + name.getMethodName());

        Argument arg = new Argument();

        arg.addPremise(new Expression("~S | (B &E)"));
        arg.addPremise(new Expression("~S->M"));
        arg.addPremise(new Expression("E->(H|D)"));
        arg.addPremise(new Expression("~M&~D"));
        arg.setConclusion(new Expression("H"));
        arg.printArgument();

        int isValid = arg.generateTruthTable();
        System.out.println("is valid argument: " + isValid);
    }

}
