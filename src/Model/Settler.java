package Model;

import java.util.*;

/**
 * 
 */
public class Settler extends Worker {

    public Settler() {
        super();
    }

    private ArrayList<TeleportGate> gates;

    private Inventory inventory = new Inventory();

    public void Mine() {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        Logger.getInstance().printCommandCall(this.getClass().getSimpleName(), name);
        Material m = asteroid.Mined();
        if(m != null)
            inventory.Add(m.name);
        Logger.getInstance().printReturnCommand();
    }

    public void BuildBase() {
        Base base = new Base(inventory);
    }

    public void BuildGate() {
        TeleportGate g1 = new TeleportGate(inventory, this);
    }

    public void BuildRobot() {
        Robot r = new Robot(inventory, asteroid);
    }

    public void PlaceMaterial(Material m) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        Logger.getInstance().printCommandCall(this.getClass().getSimpleName(), name, m.name.toString());
        if(asteroid.AddMaterial(m))
            inventory.Remove(m.name);
        //System.out.printf("hello");
        Logger.getInstance().printReturnCommand();
    }


    public void Explode() {
        Die();
    }


    public void Die() {
        asteroid.Remove(this);
        AsteroidBelt.getInstance().SetSettlersAlive();
    }

    public void AddGate(TeleportGate t) {
        gates.add(t);
    }


    public void PlaceGate(TeleportGate t) {
        t.Place(asteroid);
    }

}