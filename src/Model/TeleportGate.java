package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
  * A teleportkapukat kezeli. Tarolja a parjat, melyik aszteroidan van, illetve a jelenlegi allapotat.
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
     * @param i   A telepes inventorija, és osszehasonlitja, a sajat felepitesehez szukseges inventoryval.
     * @param s   Telepses, aki felepitene a teleportkaput.
     */
    public TeleportGate(Inventory i, Settler s) {
        Object[] p = {i.getClass().getSimpleName(), s.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("? Do we have enough materials to build a pair of teleportgates?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                this.pair = new TeleportGate();
                this.isPlaced = false;
                this.isActive = false;
                s.AddGate(this);
                s.AddGate(pair);
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        /*setInventory();
        if(inventory.ContainsAllElementsIn(i))
            {
                this.pair = new TeleportGate();
                this.isPlaced = false;
                this.isActive = false;
                s.AddGate(this);
                s.AddGate(pair);
            }*/

        Logger.getInstance().printReturnCommand();
    }


    /**
     * Inicializalja az inventorit, itt tarolja a felepiteshez szukseges anyagokat.
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
     * Felveszi a ra lepo karaktert, majd visszater az aszteroidaval, ahol talalhato.
     * @param c
     * @return
     */
    public DestinationObject Accept(Character c) {
        Object[] p = {c.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
//        String name = new Object(){}.getClass().getEnclosingMethod().getName();
//        Logger.getInstance().printCommandCall(this.getClass().getSimpleName(), name ,p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("? Is the teleportgate active?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Asteroid a = null;
            if(ch=='y' || ch=='Y' ) {
                a = pair.GetAsteroid();
                a.Accept(c);
            }
            else if(ch == 'n' || ch == 'N'){
                Logger.getInstance().printReturnCommand();
                return null;
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        /*if (isActive) {
            pair.GetAsteroid().Accept(c);
        }*/

        Logger.getInstance().printReturnCommand(a.getClass().getSimpleName());
        return a;
    }

    /**
     * Lehelyezzuk a teleportkapunkat.
     *
     * @param asteroid  Az adott aszteroidara helyezzuk le.
     */
    public void Place(Asteroid asteroid) {
        Object[] p = {asteroid.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this,p);

        this.asteroid = asteroid;
        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("? Is the pair of the teleportgate placed, and can we activate the gates?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                pair.Activate();
                Activate();
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        /*isPlaced = true;
        if (pair.GetPlaced()) {
            this.pair.Activate();
            this.Activate();
        }*/

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Visszater egy logikai ertekkel, hogy a kapunkat leraktuk-e.
     * @return
     */
    public boolean GetPlaced() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(pair.isPlaced);

        return pair.isPlaced;
    }

    /**
     * Visszater az aszteroidaval, amin a telepes talalhato.
     * @return
     */
    public Asteroid GetAsteroid() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(asteroid.getClass().getSimpleName());

        return this.asteroid;
    }

    /**
     * Beallitja az aszteroidat, ahol a kapu talalhato.
     * @param a
     */
    public void setAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this,p);

        asteroid = a;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A teleportkapu parjat adja vissza.
     * @return
     */
    public TeleportGate GetPair() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(pair.getClass().getSimpleName());

        return pair;
    }

    /**
     * Beallitja a teleportkapu parjat.
     * @param t
     */
    public void setPair(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this,p);

        this.pair = t;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Aktivalja a kaput.
     */
    public void Activate() {
        Logger.getInstance().printCommandCall(this);

        this.isActive = true;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Deaktivalja a kaput.
     */
    public void Deactivate() {
        Logger.getInstance().printCommandCall(this);

        this.isActive = false;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Ha az aszteroida felrobban, amin van, a kaput is
     * eleri a robbanas es meghivja a destroy fuggvenyt magara es a parjara is.
     * @param a
     */
    public void HitByExplosion(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.Destroy();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Megsemisul a teleportkapu.
     */
    public void Destroy() {
        Logger.getInstance().printCommandCall(this);

        this.Deactivate();
        pair.Deactivate();
        this.isPlaced = false;

        Logger.getInstance().printReturnCommand();
    }

}