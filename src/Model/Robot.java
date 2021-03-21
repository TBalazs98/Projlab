package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Játékosok autonóm segítőit reprezentálja. Karakterek által építhetőek.
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
     * @param a - Aszteroida, amin létre lesz hozva a robot
     * Publikus 2 paraméteres konstrukor
     */
    public Robot(Inventory i, Asteroid a) {
        super();            //Ős konstruktora
        Logger.getInstance().printCommandCall(this);

        //függvény lefutása felhasználói beavatkozással
            System.out.println("\nDo we have enough materials to build a robot");
            System.out.println(" (Y)es / (N)o");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                asteroid = a;           //A robot Aszteroidáját beállítjuk a paraméterként kapott Aszteroidára
                a.Accept(this);      //És ezt a tényt közöljük az Aszteroidával is
            }

        //függvény lefutása tagváltozó lekérdezésével
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
        m.put(NormalMaterialName.IRON, 1);      //Létrehozzuk a szükséges anyagokat
        m.put(NormalMaterialName.COAL, 1);
        m.put(RadioactiveMaterialName.URAN, 1);
        inventory.fill(m);                      //Majd belepakoljuk a Robot Inventory-jába

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A controller itt dönteni el, hogy mi lesz a robot következő lépése
     */
    public void Step() {
        Logger.getInstance().printCommandCall(this);

        Random rand = new Random();
        if(rand.nextInt() % 2 == 0)         //Lépés eldöntéséhez szükséges belső logika
            Drill();                        //Vagy fúr
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

        //függvény lefutása felhasználói beavatkozással
            System.out.println("\nDo we have enough materials to build a pair of teleportgates?");
            System.out.println(" (A)steroid / (T)eleportgate");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int id = 0;
            if(ch=='a' || ch=='A' ) {
                id = 0;
            } else if(ch == 't' || ch == 'T') {
                id = 1;
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*int id = asteroid.GetRandNeighbour();
        * */
        Move(id);

        Logger.getInstance().printReturnCommand();
    }

}