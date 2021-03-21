package Model;

import java.lang.reflect.Method;


//PLS DONT TOUCH, ITS MAGIC
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
        return ste[ste.length - 4 - depth].getMethodName(); //CSAK TESZTELÉSHEZ
        //return ste[ste.length - 2 - depth].getMethodName(); //ÉLESBEN EZ LESZ A JÓ
    }

    //függvényhívás kiírása, ha nincs paraméter
    public void printCommandCall(Object o) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print(">>");

        System.out.print("[:" + getClassName(o) + "]." + getMethodName(intend) + "()");

        System.out.println();

        intend++;
    }

    //függvényhívás kiírása, ha van paraméter
    public void printCommandCall(Object o, Object[] params) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print(">>");

        System.out.print("[:" + getClassName(o) + "]." + getMethodName(intend) + "(");

        for(int i = 0; i < params.length; i++) {
            String toPrint = params[i].toString();
            System.out.print(toPrint);
            if(i == params.length - 1)
                System.out.print(")");
        }

        System.out.println();

        intend++;
    }

    //függvény visszatérésének kiírása visszatérési érték nélkül
    public void printReturnCommand() {
        intend--;
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        System.out.print("<<");
        System.out.println();
    }

    //függvény visszatérésének kiírása visszatérési értékkel
    public void printReturnCommand(Object value) {
        intend--;
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        String toPrint = value.toString();
        System.out.print("<< " + toPrint);
        System.out.println();
    }
}
