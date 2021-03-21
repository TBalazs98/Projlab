package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 */
public class Robot extends Worker {
    private static final Inventory inventory = new Inventory();

    public Robot() {
        super();
        Logger.getInstance().printCommandCall(this);

        setInventory();

        Logger.getInstance().printReturnCommand();
    }

    public Robot(Inventory i, Asteroid a) {
        super();
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
                asteroid = a;
                a.Accept(this);
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*  setInventory();
            if(inventory.ContainsAllElementsIn(i)) {
                asteroid=a;
                a.Accept(this);
            }*/

        Logger.getInstance().printReturnCommand();
    }

    private void setInventory() {
        Logger.getInstance().printCommandCall(this);

        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 1);
        m.put(NormalMaterialName.COAL, 1);
        m.put(RadioactiveMaterialName.URAN, 1);
        inventory.fill(m);

        Logger.getInstance().printReturnCommand();
    }

    public void Step() {
        Logger.getInstance().printCommandCall(this);

        Random rand = new Random();
        if(rand.nextInt() % 2 == 0)
            Drill();
        else {
            int id = asteroid.GetRandNeighbour();
            Move(id);
        }

        Logger.getInstance().printReturnCommand();
    }

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