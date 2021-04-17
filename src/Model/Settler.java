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
        //Logger.getInstance().printCommandCall(this);

        inventory = new Inventory();    //Letrehozza az inventory-t
        gates = new ArrayList<TeleportGate>();

        //Logger.getInstance().printReturnCommand();
    }

    private ArrayList<TeleportGate> gates;

    private Inventory inventory;

    /**
     * Kibanyassza az aszteroida magjaban talalhato nyersanyagot amin
     * tartozkodik.
     */
    public void Mine() {
        //Logger.getInstance().printCommandCall(this);

        if(inventory.Size() >= 10) {
            //Logger.getInstance().printReturnCommand();
            return;
        }

        Material m = asteroid.Mined();
        if(m != null)
            m.Add(inventory);           //Kibanyaszott nyesanyag eltarolasa az inventory-ban

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildBase() {
        //Logger.getInstance().printCommandCall(this);

        Base base = new Base(inventory);        //bazis epitese, atadva a sajat inventorynkat (ebben nezi meg a Base, hogy fel tud-e epulni)

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildGate() {
        //Logger.getInstance().printCommandCall(this);

        if(gates.size()<=1) {       //teleportGate konstruktorban 2 TG hozodik letre
            TeleportGate g1 = new TeleportGate(inventory, this);        //TG  epitese, atadva a sajat inventorynkat (ebben nezi meg a TG, hogy fel tud-e epulni)
        }
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildRobot() {
        //Logger.getInstance().printCommandCall(this);

        Robot r = new Robot(inventory, asteroid);       //Robot epitese, atadva a sajat inventorynkat (ebben nezi meg a Robot, hogy fel tud-e epulni)

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * @param m A lehelyezendo material
     * Visszahelyez egy egyseg nyersanyagot ureges
     * aszteroida magjaba.
     */
    public void PlaceMaterial(Material m) {
        //Object[] p = {m.getName()};
        //Logger.getInstance().printCommandCall(this, p);

        if(asteroid.AddMaterial(m))
            m.Remove(inventory);                        //Eltavolitjuk az inventorynkbol

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Metodus, amely kezeli azt az esemenyt, amikor az az aszteroida felrobban,
     * amin a Settler eppen tartozkodik.
     */
    public void Explode() {
        //Logger.getInstance().printCommandCall(this);

        Die();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler halalat levezenylo metodus
     */
    public void Die() {
        //Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);                  //levesszuk az adott Settlert az aszteroidarol (mert meghalt)
        AsteroidBelt.getInstance().SetSettlersAlive();  //Es atallitjuk a jelenleg eletben levo Settlereket

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Hozzaad egy teleportkaput a Settlernel levo teleportkapukhoz
     * @param t Eltarolando Teleportkapu
     */
    public void AddGate(TeleportGate t) {
        //Object[] p = {t.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        if(gates.size()<3)
            gates.add(t);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Settler elhelyez egy teleportkaput az adott Aszteroidan, amin all
     * @param t Lehelyezendo teleportkapu
     */
    public void PlaceGate(TeleportGate t) {
        //Object[] p = {t.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        t.Place(asteroid);          //Lerakjuk a TG-t arra az Aszteroidara, amin eppen allunk
        gates.remove(t);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A parameterul kapott inventoryt allitja be a telepes hatizsakjanak
     * @param i inventory peldany, amire beallitodik a telepes hatizsakja
     */
    public void setInventory(Inventory i ) {
        //Object[] p = {i.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        inventory = i;

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Hozzaadja a parameterul kapott nyersanyagot a telepes hatizsakjahoz
     * @param m A hatizsakhoz adando nyersanyag
     */
    public void AddMaterial(Material m) {
        //Object[] p = {m.getName()};
        //Logger.getInstance().printCommandCall(this, p);

        m.Add(inventory);

        //Logger.getInstance().printReturnCommand();
    }

    public  Inventory GetInventory(){
        return  this.inventory;
    }
    public  ArrayList<TeleportGate> GetGates(){
        return this.gates;
    }
}