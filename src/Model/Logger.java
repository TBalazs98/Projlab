package Model;

import java.lang.reflect.Method;


//PLS DONT TOUCH, ITS MAGIC
public class Logger {
    /**
     * Privat konstruktor
     */
    private Logger() {}

    private static final Logger l = new Logger();

    /**
     * lekeri az egyetlen letezo objektumot
     * @return
     */
    public static Logger getInstance() {
        return l;
    }

    int intend = 0;

    /**
     * visszater a parameterben kapott objektum nevevel
     * @param o
     * @return
     */
    private String getClassName(Object o) {
        return o.getClass().getSimpleName();
    }

    /**
     * visszater a legutobb hivott relevans metodus nevevel
     * @param depth
     * @return
     */
    //source: https://stackoverflow.com/questions/442747/getting-the-name-of-the-currently-executing-method
    private static String getMethodName(final int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
//        for(int i = 0; i < ste.length; i++) {
//            System.out.println(ste[i].getMethodName());
//        }
//        System.out.println();
        return ste[ste.length - 4 - depth].getMethodName(); //CSAK TESZTELÉSHEZ
        //return ste[ste.length - 2 - depth].getMethodName(); //ÉLESBEN EZ LESZ A JÓ
    }

    /**
     * kiirja konzolra a legutolso relevans fuggvenyhivast [:osztalynev].fuggvenynev() formatumban, megfelelo intendalassal
     * @param o
     */
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

    /**
     * kiirja konzolra a legutolso relevans fuggvenyhivast [:osztalynev].fuggvenynev(parameterek) formatumban, megfelelo intendalassal
     * @param o
     * @param params
     */
    //függvényhívás kiírása, ha van paraméter
    public void printCommandCall(Object o, Object[] params) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print(">>");

        System.out.print("[:" + getClassName(o) + "]." + getMethodName(intend) + "(");

        for(int i = 0; i < params.length; i++) {
            String toPrint = params[i].toString();

            if(i == params.length - 1) {
                System.out.print(toPrint + ")");
            }
            else {
                System.out.print(toPrint + ", ");
            }

        }

        System.out.println();

        intend++;
    }

    /**
     * kiirja a konzolra a legutobbi relevans fuggvenyhivas visszatereset, megfelelo intendalassal
     */
    //függvény visszatérésének kiírása visszatérési érték nélkül
    public void printReturnCommand() {
        intend--;
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        System.out.print("<<");
        System.out.println();
    }

    /**
     * kiirja a konzolra a legutobbi relevans fuggvenyhivas visszatereset visszateresi ertekkel, megfelelo intendalassal
     * @param value
     */
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
