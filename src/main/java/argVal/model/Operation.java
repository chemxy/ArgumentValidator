package argVal.model;

public class Operation {

    protected enum OperatorType {
        IMPLY,
        AND,
        OR,
        NOT;
    }

    //and
    public static boolean and(boolean A, boolean B) {
        return (A && B);
    }


    //or
    public static boolean or(boolean A, boolean B) {
        return (A || B);
    }


    //imply
    public static boolean imply(boolean A, boolean B) {
        return (!A || B);
    }


    //not
    public static boolean not(boolean A) {
        return !A;
    }


}
