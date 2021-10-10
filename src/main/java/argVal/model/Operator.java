package argVal.model;

public class Operator {

    protected enum OperatorType{
        IMPLY,
        AND,
        OR,
        NOT;
    }

    //and
    public static boolean and(boolean A, boolean B){
        return (A && B);
    }
    public static boolean and(Symbol A, Symbol B){
        return (A.getValue() && B.getValue());
    }


    //or
    public static boolean or(boolean A, boolean B){
        return (A || B);
    }
    public static boolean or(Symbol A, Symbol B){
        return (A.getValue() || B.getValue());
    }


    //imply
    public static boolean imply(boolean A, boolean B){
        return (!A || B);
    }
    public static boolean imply(Symbol A, Symbol B){
        return (!A.getValue() || B.getValue());
    }


    //not
    public static boolean not(boolean A){
        return !A;
    }
    public static boolean not(Symbol A){
        return !A.getValue();
    }


}
