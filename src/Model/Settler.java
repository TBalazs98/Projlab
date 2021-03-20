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

    private Inventory inventory;

    public void Mine() {
        Material m = asteroid.Mined();
        if(m != null)
            inventory.Add(m.name);
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
        if(asteroid.AddMaterial(m))
            inventory.Remove(m.name);
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