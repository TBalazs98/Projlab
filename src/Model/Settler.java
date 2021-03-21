package Model;

import java.util.*;

/**
 * Játékosok által irányított karakterek, akik az aszteroidaövön mozognak és nyersanyagot
 * gyűjtenek.
 */
public class Settler extends Worker {

    /**
     * Publikus default konstrukor
     */
    public Settler() {
        super();                        //Ős konstruktor
        Logger.getInstance().printCommandCall(this);

        inventory = new Inventory();    //Létrehozza az inventory-t

        Logger.getInstance().printReturnCommand();
    }

    private ArrayList<TeleportGate> gates;

    private Inventory inventory;

    /**
     * Kibányássza az aszteroida magjában található nyersanyagot amin
     * tartózkodik.
     */
    public void Mine() {
        Logger.getInstance().printCommandCall(this);

        Material m = asteroid.Mined();
        if(m != null)
            m.Add(inventory);           //Kibányászott nyesanyag eltárolása az inventory-ban

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bázis építéséhez szükséges metódusa
     */
    public void BuildBase() {
        Logger.getInstance().printCommandCall(this);

        Base base = new Base(inventory);        //Bázis építése, átadva a saját inventorynkat (ebben nézi meg a Base, hogy fel tud-e épülni)

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bázis építéséhez szükséges metódusa
     */
    public void BuildGate() {
        Logger.getInstance().printCommandCall(this);

        TeleportGate g1 = new TeleportGate(inventory, this);        //TG  építése, átadva a saját inventorynkat (ebben nézi meg a TG, hogy fel tud-e épülni)

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bázis építéséhez szükséges metódusa
     */
    public void BuildRobot() {
        Logger.getInstance().printCommandCall(this);

        Robot r = new Robot(inventory, asteroid);       //Robot építése, átadva a saját inventorynkat (ebben nézi meg a Robot, hogy fel tud-e épülni)

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param m A lehelyezendő material
     * Visszahelyez egy egység nyersanyagot üreges
     * aszteroida magjába.
     */
    public void PlaceMaterial(Material m) {
        Object[] p = {m.name};
        Logger.getInstance().printCommandCall(this, p);

        if(asteroid.AddMaterial(m))
            m.Remove(inventory);                        //Eltávolítjuk az inventorynkból

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Metódus, amely kezeli azt az eseményt, amikor az az aszteroida felrobban,
     * amin a Settler éppen tartózkodik.
     */
    public void Explode() {
        Logger.getInstance().printCommandCall(this);

        Die();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler halálát levezénylő metódus
     */
    public void Die() {
        Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);                  //levesszük az adott Settlert az aszteroidáról (mert meghalt)
        AsteroidBelt.getInstance().SetSettlersAlive();  //És átállítjuk a jelenleg életben lévő Settlereket

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param t Eltárolandó Teleportkapu
     * Hozzáad egy teleportkaput a Settlernél lévő teleportkapukhoz
     */
    public void AddGate(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        gates.add(t);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param t Lehelyezendő teleportkapu
     * Settler elhelyez egy teleportkaput az adott Aszteroidán, amin áll
     */
    public void PlaceGate(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        t.Place(asteroid);          //Lerakjuk a TG-t arra az Aszteroidára, amin éppen állunk
        gates.remove(t);

        Logger.getInstance().printReturnCommand();
    }

}