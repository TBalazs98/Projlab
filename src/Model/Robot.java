package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Jatekosok autonom segitoit reprezentalja. Karakterek altal epithetoek.
 */
public class Robot extends Worker {
    private static final Inventory inventory = new Inventory();

    /**
     * Publikus default konstruktor
     */
    public Robot() {
        super();
        Logger.getInstance().printCommandCall(this);

        setInventory();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param i - A settler inventory-ja
     * @param a - Aszteroida, amin letre lesz hozva a robot
     * Publikus 2 paraméteres konstrukor
     */
    public Robot(Inventory i, Asteroid a) {
        super();            //Ős konstruktora
        Object[] p = {i.getClass().getSimpleName(), a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("Do we have enough materials to build a robot?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                asteroid = a;           //A robot Aszteroidajat beallitjuk a parameterkent kapott Aszteroidara
                a.Accept(this);      //Es ezt a tenyt kozoljuk az Aszteroidaval is
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        /*  setInventory();
            if(inventory.ContainsAllElementsIn(i)) {
                asteroid=a;
                a.Accept(this);
            }*/

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Inicializálja az inventory-t. Itt tárolja az építéshez szükséges anyagokat.
     */
    private void setInventory() {
        Logger.getInstance().printCommandCall(this);

        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 1);      //Letrehozzuk a szukseges anyagokat
        m.put(NormalMaterialName.COAL, 1);
        m.put(RadioactiveMaterialName.URAN, 1);
        inventory.fill(m);                      //Majd belepakoljuk a Robot Inventory-jaba

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A controller itt dönteni el, hogy mi lesz a robot következő lépése
     */
    public void Step() {
        Logger.getInstance().printCommandCall(this);

        Random rand = new Random();
        if(rand.nextInt() % 2 == 0)         //Lepes eldontesehez szukseges belso logika
            Drill();                        //Vagy fur
        else {
            int id = asteroid.GetRandNeighbour();
            Move(id);                       //Vagy mozog
        }

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Metódus, amely kezeli azt az eseményt, amikor az az aszteroida felrobban,
     * amin a Robot éppen tartózkodik.
     */
    public void Explode() {
        Logger.getInstance().printCommandCall(this);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("Does the robot get thrown to an asteroid or a teleportgate?\t(A)steroid / (T)eleportgate \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int id = 0;
            if(ch=='a' || ch=='A' ) {
                id = 0;
            } else if(ch == 't' || ch == 'T') {
                id = 1;
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        /*int id = asteroid.GetRandNeighbour();
        * */
        Move(id);

        Logger.getInstance().printReturnCommand();
    }

}