import argVal.model.Argument;
import argVal.model.Expression;
import argVal.model.Symbol;
import argVal.model.calculator.BoolExpCalculator;
import org.junit.Test;

public class ArgValidatorTest {


    //    @Test
    public void testCal() {

        //        String s = "~ ( T &  ~ T )";
        String s = "  (T|F)-> (T&F ) ";

        System.out.println("Expression: " + s);

        BoolExpCalculator calc = new BoolExpCalculator();
        System.out.println("Result: " + calc.calculate(s));

    }

    //    @Test
    public void testBoolExpression() {

        Expression exp = new Expression("(A v B) -> ~(B v C)");
        System.out.println(exp.getSymbolList());
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        exp.assign(new Symbol("B"), false);
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        exp.assign(new Symbol("A"), true);
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        exp.assign(new Symbol("C"), true);
        System.out.println("validate argument: " + exp.getAssignedExpression());
        System.out.println(exp.validate());

        System.out.println("Result: " + exp.calculate());

    }

    //    @Test
    public void testAss5Q1() {
        System.out.println("===in test arg===\n\n");
        Argument arg = new Argument();

        arg.addPremise(new Expression("(A & B) -> ~C"));
        arg.addPremise(new Expression("~(A v B) -> ~(B v C)"));
        arg.setConclusion(new Expression("~(A & (~B & C))"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }

    //    @Test
    public void testAss5Q2() {
        System.out.println("===in test arg===\n\n");
        Argument arg = new Argument();

        arg.addPremise(new Expression("(R v S) -> ~(U & V)"));
        arg.addPremise(new Expression("~(U & R) -> ~V"));
        arg.addPremise(new Expression("~(R v S) v V"));
        arg.setConclusion(new Expression("(V v R) -> ~(S & U)"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }

    //    @Test
    public void testAss5Q3() {
        System.out.println("===in test arg===\n\n");
        Argument arg = new Argument();

        arg.addPremise(new Expression("S -> (N v H)"));
        arg.addPremise(new Expression("S v ~N"));
        arg.setConclusion(new Expression("S -> H"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }

//    @Test
    public void testAss5Q4() {
        System.out.println("===in test arg===\n\n");
        Argument arg = new Argument();

        arg.addPremise(new Expression("A->(B&C)"));
        arg.addPremise(new Expression("~B"));
        arg.setConclusion(new Expression("~A"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }


    //    @Test
    public void testAss5Q5() {
        System.out.println("===in test arg===\n\n");

        Argument arg = new Argument();

        arg.addPremise(new Expression("(P & V) -> S"));
        arg.addPremise(new Expression("(P -> V) & (V -> P)"));
        arg.setConclusion(new Expression("~S & (V v P)"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }

        @Test
    public void testAss5Q6() {
        System.out.println("===in test arg===\n\n");

        Argument arg = new Argument();

        arg.addPremise(new Expression("(M v E)->D"));
        arg.addPremise(new Expression("~E->~M"));
        arg.addPremise(new Expression("M->~E"));
        arg.setConclusion(new Expression("D"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }

    //    @Test
    public void testAss5Q8() {
        System.out.println("===in test arg===\n\n");

        Argument arg = new Argument();

        arg.addPremise(new Expression("A v (B & C)"));
        arg.addPremise(new Expression("~(D -> B)"));
        arg.addPremise(new Expression("~B -> A"));
        arg.setConclusion(new Expression("C & ~A"));
        arg.printArgument();

        arg.printTruthTable();

        System.out.println("\n\n===in test arg===\n\n");
    }
}
