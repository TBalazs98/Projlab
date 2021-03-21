package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
  * A teleportkapukat kezeli. Tárolja a párját, melyik aszteroidán van, illetve a jelenlegi állapotát.
 */
public class TeleportGate implements DestinationObject {
    public TeleportGate() {
        setInventory();
    }

    private boolean isActive;
    private boolean isPlaced;
    private TeleportGate pair;
    private Asteroid asteroid;
    private static final Inventory inventory = new Inventory();


    /**
     * A teleportkapu konstuktora.
     *
     * @param i   A telepes inventorija, és összehasonlítja, a saját felépítéséhez szükséges inventoryval.
     * @param s   Telepses, aki felépítené a teleportkaput.
     */
    public TeleportGate(Inventory i, Settler s) {
        Object[] p = {i.getClass().getSimpleName(), s.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        //függvény lefutása felhasználói beavatkozással
            System.out.println("\nDo we have enough materials to build a pair of teleportgates?");
            System.out.println(" (Y)es / (N)o");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                this.pair = new TeleportGate();
                this.isPlaced = false;
                this.isActive = false;
                s.AddGate(this);
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*setInventory();
        if(inventory.ContainsAllElementsIn(i))
            {
                this.pair = new TeleportGate();
                this.isPlaced = false;
                this.isActive = false;
                s.AddGate(this);
            }*/

        Logger.getInstance().printReturnCommand();
    }


    /**
     * Inicializálja az inventorit, itt tárolja a felépítéséhez szükséges anyagokat.
     */
    private void setInventory() {
        Logger.getInstance().printCommandCall(this);

        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 2);
        m.put(SublimableMaterialName.ICEWATER, 1);
        m.put(RadioactiveMaterialName.URAN, 1);

        inventory.fill(m);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Felveszi a ré lépő karaktert, majd visszatér az aszteroidával, ahol található.
     * @param c
     * @return
     */
    public DestinationObject Accept(Character c) {
        Object[] p = {c.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
//        String name = new Object(){}.getClass().getEnclosingMethod().getName();
//        Logger.getInstance().printCommandCall(this.getClass().getSimpleName(), name ,p);

        //függvény lefutása felhasználói beavatkozással
            System.out.print("Is the teleportgate active?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Asteroid a = null;
            if(ch=='y' || ch=='Y' ) {
                a = pair.GetAsteroid();
                a.Accept(c);
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*if (isActive) {
            pair.GetAsteroid().Accept(c);
        }*/

        Logger.getInstance().printReturnCommand(a.getClass().getSimpleName());
        return a;
    }

    /**
     * Lehelyezzük a teleportkapunkat.
     *
     * @param asteroid  Az adott aszteroidára helyezzük le.
     */
    public void Place(Asteroid asteroid) {
        Object[] p = {asteroid.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this,p);

        this.asteroid = asteroid;
        //függvény lefutása felhasználói beavatkozással
            System.out.println("\nIs the pair of the teleportgate placed, and can we activate the gates?");
            System.out.println(" (Y)es / (N)o");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                pair.Activate();
                Activate();
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*isPlaced = true;
        if (pair.GetPlaced()) {
            this.pair.Activate();
            this.Activate();
        }*/

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Visszatér egy logikai értékkel, hogy a kapunkat leraktuk-e.
     * @return
     */
    public boolean GetPlaced() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(pair.isPlaced);

        return pair.isPlaced;
    }

    /**
     * Visszatér az aszteroidával, amin a telepes található.
     * @return
     */
    public Asteroid GetAsteroid() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(asteroid.getClass().getSimpleName());

        return this.asteroid;
    }

    /**
     * Beállítja az aszteroidát, ahol a kapu található.
     * @param a
     */
    public void setAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this,p);

        asteroid = a;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A teleportkapu párját adja vissza.
     * @return
     */
    public TeleportGate GetPair() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(pair.getClass().getSimpleName());

        return pair;
    }

    /**
     * Beállítja a teleportkapu párját.
     * @param t
     */
    public void setPair(TeleportGate t) {
        Logger.getInstance().printCommandCall(this);

        this.pair = t;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Aktiválja a kaput.
     */
    public void Activate() {
        Logger.getInstance().printCommandCall(this);

        this.isActive = true;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Deaktiválja a kaput.
     */
    public void Deactivate() {
        Logger.getInstance().printCommandCall(this);

        this.isActive = false;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Ha az aszteroida felrobban, amin van, a kaput is
     * eléri a robbanás és meghívja a destroy függvényt magára és a párjára is.
     * @param a
     */
    public void HitByExplosion(Asteroid a) {
        Logger.getInstance().printCommandCall(this);

        this.Destroy();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Megsemisűl a teleportkapu.
     */
    public void Destroy() {
        Logger.getInstance().printCommandCall(this);

        this.Deactivate();
        pair.Deactivate();
        this.isPlaced = false;

        Logger.getInstance().printReturnCommand();
    }

}