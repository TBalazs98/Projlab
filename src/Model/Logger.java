package Model;

import java.lang.reflect.Method;

public class Logger {
    private Logger() {}

    private static final Logger l = new Logger();

    public static Logger getInstance() {
        return l;
    }

    int intend = 0;

    private String getClassName(Object o) {
        return o.getClass().getSimpleName();
    }

    //source: https://stackoverflow.com/questions/442747/getting-the-name-of-the-currently-executing-method
    private static String getMethodName(final int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[ste.length - 2 - depth].getMethodName();
    }

    public void printCommandCall(Object o) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print(">>");

        System.out.print("[:" + getClassName(o) + "]." + getMethodName(intend) + "()");

        System.out.println();

        intend++;
    }

    public void printCommandCall(Object o, String[] params) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print(">>");

        System.out.print("[:" + getClassName(o) + "]." + getMethodName(intend) + "(");
        for(int i = 0; i < params.length; i++) {
            System.out.print(params[i]);
            if(i == params.length - 1)
                System.out.print(")");
        }

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
