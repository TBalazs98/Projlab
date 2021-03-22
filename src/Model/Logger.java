package Model;

import java.lang.reflect.Method;

/**
 * A Logger asztaly segitsegevel iratjuk ki a fuggvenyhivasokat,
 * az indentalasokat, es a szibolumokat
 * a konzol kimenetere a megfelelo formatumban.
 */
public class Logger {
    /**
     * A Logger osztaly privat konstruktora.
     */
    private Logger() {}

    private static final Logger l = new Logger();

    /**
     * Lekeri az egyetlen letezo peldanyt ami a teszteleshez szukseges.
     * @return visszaadja az objektumot
     */
    public static Logger getInstance() {
        return l;
    }

    int intend = 0;

    /**
     * Visszater a parameterben kapott osztaly nevevel.
     * @param o a bemeneti osztaly
     * @return az osztaly neve
     */
    private String getClassName(Object o) {
        return o.getClass().getSimpleName();
    }

    /**
     * Visszater a legutobb hivott relevans metodus nevevel.
     * @param depth a konzol kimeneten megjeleno intendalas melysege
     * @return az osztaly nevenek szoveg formatumba alakitasa az intendalas figyelembevetelevel
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
     * Kiirja konzolra a legutolso relevans fuggvenyhivast (osztalynev).fuggvenynev() formatumban, megfelelo intendalassal.
     * @param o az osztaly, aminek nevet ki akarjuk iratni a konzol kimenetere
     */
    //fuggvenyhivas kiirasa, ha nincs parameter
    public void printCommandCall(Object o) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print("}");

        System.out.print("(" + getClassName(o) + ")." + getMethodName(intend) + "()");

        System.out.println();

        intend++;
    }

    /**
     * Kiirja konzolra a legutolso relevans fuggvenyhivast (osztalynev).fuggvenynev(parameterek) formatumban, megfelelo intendalassal.
     * @param o az osztaly, aminek nevet ki akarjuk iratni a konzol kimenetre
     * @param params a metodus parameterei, amit ki akarunk iratni
     */
    //fuggvenyhivas kiirasa, ha van parameter
    public void printCommandCall(Object o, Object[] params) {
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }

        System.out.print("}");

        System.out.print("(" + getClassName(o) + ")." + getMethodName(intend) + "(");

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
     * Kiirja a konzolra a legutobbi relevans fuggvenyhivas visszatereset, megfelelo intendalassal.
     */
    //fuggveny visszateresenek kiirasa visszateresi ertek nelkul
    public void printReturnCommand() {
        intend--;
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        System.out.print("{");
        System.out.println();
    }

    /**
     * Kiirja a konzolra a legutobbi relevans fuggvenyhivas visszatereset visszateresi ertekkel, megfelelo intendalassal.
     * @param value a visszaeresi ertek, amit szeretnenk a konzol kimenetere irtani
     */
    //fuggveny visszateresenek kiirasa visszateresi ertekkel
    public void printReturnCommand(Object value) {
        intend--;
        for(int i = 0; i < intend; i++) {
            System.out.print("\t");
        }
        String toPrint = value.toString();
        System.out.print("{ " + toPrint);
        System.out.println();
    }
}
