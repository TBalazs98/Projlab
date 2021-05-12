package Model;

import Controller.Controller;
import Controller.Main;
import View.SettlerView;

import java.util.*;

/**
 * Jatekosok altal iranyitott karakterek, akik az aszteroidaovon mozognak es nyersanyagot
 * gyujtenek.
 */
public class Settler extends Worker implements drillable, moveable {

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
    private  SettlerView sv;

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

    public void Drill(){
        this.asteroid.Drilled();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildBase() {
        //Logger.getInstance().printCommandCall(this);

        Inventory setlersOnsameAsteroid = new Inventory();
        ArrayList<Settler> BaseBuilders = new ArrayList<>();
        for(Settler s : Main.settlers){
            if (s.getAsteroid() == this.asteroid){
                if(s.inventory.GetMaterials() != null)
                    BaseBuilders.add(s);
                for(Material m : s.inventory.GetMaterials())
                    setlersOnsameAsteroid.Add(m);
            }

        }
        Base base = new Base(setlersOnsameAsteroid);        //bazis epitese, atadva a sajat inventorynkat (ebben nezi meg a Base, hogy fel tud-e epulni)1
        /*if(base !=null){
           ArrayList<Integer> basecount = new ArrayList<>();
           basecount.add(3); basecount.add(3); basecount.add(3); basecount.add(3);
                for(Settler s : BaseBuilders){
                    for(Material m: s.GetInventory().GetMaterials()) {
                        System.out.println("count: " + s.GetInventory().Size());
                        if (basecount.get(0) > 0 && m.name == NormalMaterialName.IRON && s.GetInventory().Size() != 0) {
                            s.inventory.Remove(s.inventory.GetMaterialByName(NormalMaterialName.IRON));
                            basecount.set(0,basecount.get(0)-1);
                        }
                        if (basecount.get(1) > 0 && m.name == NormalMaterialName.COAL && s.GetInventory().Size() != 0) {
                            s.inventory.Remove(s.inventory.GetMaterialByName(NormalMaterialName.COAL));
                            basecount.set(1,basecount.get(1)-1);
                        }
                        if (basecount.get(2) > 00 && m.name == RadioactiveMaterialName.URAN && s.GetInventory().Size() != 0) {
                            s.inventory.Remove(s.inventory.GetMaterialByName(RadioactiveMaterialName.URAN));
                            basecount.set(2,basecount.get(2)-1);
                        }
                        if (basecount.get(3) > 0 && m.name == SublimableMaterialName.ICEWATER && s.GetInventory().Size() != 0) {
                            s.inventory.Remove(s.inventory.GetMaterialByName(SublimableMaterialName.ICEWATER));
                            basecount.set(3,basecount.get(3)-1);
                        }
                    }
                //}
            }
        }*/

    }
        //Logger.getInstance().printReturnCommand();

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildGate() {
        //Logger.getInstance().printCommandCall(this);

        if(gates.size()<=1) {       //teleportGate konstruktorban 2 TG hozodik letre
            TeleportGate gate = new TeleportGate(inventory, this);
//            TeleportGate g1 = new TeleportGate(inventory, this); //TG  epitese, atadva a sajat inventorynkat (ebben nezi meg a TG, hogy fel tud-e epulni)
//            if (g1 != null){
//                Main.teleportgates.add(g1);
//                Main.teleportgates.add(g1.GetPair());

                this.inventory.Remove(this.inventory.GetMaterialByName(NormalMaterialName.IRON));
                this.inventory.Remove(this.inventory.GetMaterialByName(RadioactiveMaterialName.URAN));
                this.inventory.Remove(this.inventory.GetMaterialByName(SublimableMaterialName.ICEWATER));
                this.inventory.Remove(this.inventory.GetMaterialByName(NormalMaterialName.IRON));
            //}
        }
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler bazis epitesehez szukseges metodusa
     */
    public void BuildRobot() {
        //Logger.getInstance().printCommandCall(this);

        Model.Robot r = new Robot(inventory, asteroid);       //Robot epitese, atadva a sajat inventorynkat (ebben nezi meg a Robot, hogy fel tud-e epulni)
        if(r.getAsteroid() !=null){
            Main.robots.add(r);
            this.inventory.Remove(this.inventory.GetMaterialByName(NormalMaterialName.IRON));
            this.inventory.Remove(this.inventory.GetMaterialByName(NormalMaterialName.COAL));
            this.inventory.Remove(this.inventory.GetMaterialByName(RadioactiveMaterialName.URAN));
        }

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
        Material drop = m;
        m.Remove(inventory);
        boolean success = asteroid.AddMaterial(drop);
        if(!success)
            drop.Add(inventory);                        //Eltavolitjuk az inventorynkbol

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Metodus, amely kezeli azt az esemenyt, amikor az az aszteroida felrobban,
     * amin a Settler eppen tartozkodik.
     */
    public void Explode() {
        //Logger.getInstance().printCommandCall(this);

        this.Die();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Settler halalat levezenylo metodus
     */
    public void Die() {
        //Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);                  //levesszuk az adott Settlert az aszteroidarol (mert meghalt)
        AsteroidBelt.getInstance().SetSettlersAlive();  //Es atallitjuk a jelenleg eletben levo Settlereket
        this.inventory.CharacterDied();

        this.setAsteroid(null);
        Game.getInstance().c.g.getSettlerViewBySettler(this).l.setIcon(null);
        Main.settlers.remove(this);


                //Logger.getInstance().printReturnCommand();
//            }
//        }
        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();
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

        if(this.inventory.GetMaterials().size() < 10)
            m.Add(inventory);

        //Logger.getInstance().printReturnCommand();
    }

    public  Inventory GetInventory(){
        return  this.inventory;
    }
    public  ArrayList<TeleportGate> GetGates(){
        return this.gates;
    }

//    public Settler copy() {
//        Settler copy = new Settler();
//        for(TeleportGate tg: gates) {
//            copy.AddGate(tg.copy());
//        }
//        copy.setAsteroid(asteroid);
//        copy.setInventory(inventory.copy());
//        return copy;
//    }
}