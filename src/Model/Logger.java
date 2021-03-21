package Model;

public class Logger {
    private Logger() {}

    private static final Logger l = new Logger();

    public static Logger getInstance() {
        return l;
    }

    int intend = 0;
    public void printCommandCall(/*Object classN, Object methodN,*/String s1, String s2, String s3) {
        //String className = classN.getClass().getSimpleName();
        //String methodName = methodN.getClass().getEnclosingMethod().getName();
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        System.out.print(">>");

        System.out.print("[:" + s1 + "]." + s2 + "(" + s3 + ")");
        System.out.println();
        intend++;
    }

    public void printCommandCall(/*Object classN, Object methodN*/String s1, String s2) {
        //String className = classN.getClass().getSimpleName();
        //String methodName = methodN.getClass().getEnclosingMethod().getName();
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        System.out.print(">>");

        System.out.print("[:" + s1 + "]." + s2 + "()");
        System.out.println();
        intend++;
    }

    public void printReturnCommand() {
        intend--;
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        System.out.print("<<");
        System.out.println();
    }
}
