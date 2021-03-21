package Model;

import java.util.*;

/**
 * Jatekosok altal iranyitott karakterek, akik az aszteroidaovon mozognak es nyersanyagot
 * gyujtenek.
 */
public class Settler extends Worker {

    /**
     * Publikus default konstrukor
     */
    public Settler() {
        super();                        //Os konstruktor
        Logger.getInstance().printCommandCall(this);

        inventory = new Inventory();    //Letrehozza az inventory-t

        Logger.getInstance().printReturnCommand();
    }

    private ArrayList<TeleportGate> gates;

    private Inventory inventory;

    /**
     * Kibanyassza az aszteroida magjaban talalhato nyersanyagot amin
     * tartozkodik.
     */
    public void Mine() {
        Logger.getInstance().printCommandCall(this);

        Material m = asteroid.Mined();
        if(m != null)
            m.Add(inventory);           //Kibanyaszott nyesanyag eltarolasa az inventory-ban

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildBase() {
        Logger.getInstance().printCommandCall(this);

        Base base = new Base(inventory);        //bazis epitese, atadva a sajat inventorynkat (ebben nezi meg a Base, hogy fel tud-e epulni)

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildGate() {
        Logger.getInstance().printCommandCall(this);

        TeleportGate g1 = new TeleportGate(inventory, this);        //TG  epitese, atadva a sajat inventorynkat (ebben nezi meg a TG, hogy fel tud-e epulni)

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildRobot() {
        Logger.getInstance().printCommandCall(this);

        Robot r = new Robot(inventory, asteroid);       //Robot epitese, atadva a sajat inventorynkat (ebben nezi meg a Robot, hogy fel tud-e epulni)

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param m A lehelyezendo material
     * Visszahelyez egy egyseg nyersanyagot ureges
     * aszteroida magjaba.
     */
    public void PlaceMaterial(Material m) {
        Object[] p = {m.name};
        Logger.getInstance().printCommandCall(this, p);

        if(asteroid.AddMaterial(m))
            m.Remove(inventory);                        //Eltavolitjuk az inventorynkbol

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Metodus, amely kezeli azt az esemenyt, amikor az az aszteroida felrobban,
     * amin a Settler eppen tartozkodik.
     */
    public void Explode() {
        Logger.getInstance().printCommandCall(this);

        Die();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler halalat levezenylo metodus
     */
    public void Die() {
        Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);                  //levesszuk az adott Settlert az aszteroidarol (mert meghalt)
        AsteroidBelt.getInstance().SetSettlersAlive();  //Es atallitjuk a jelenleg eletben levo Settlereket

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param t Eltarolando Teleportkapu
     * Hozzaad egy teleportkaput a Settlernel levo teleportkapukhoz
     */
    public void AddGate(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        gates.add(t);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param t Lehelyezendo teleportkapu
     * Settler elhelyez egy teleportkaput az adott Aszteroidan, amin all
     */
    public void PlaceGate(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        t.Place(asteroid);          //Lerakjuk a TG-t arra az Aszteroidára, amin éppen állunk
        gates.remove(t);

        Logger.getInstance().printReturnCommand();
    }

}