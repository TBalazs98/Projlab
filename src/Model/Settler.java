package Model;

import java.util.*;

/**
 * 
 */
public class Settler extends Worker {

    public Settler() {
        super();
        Logger.getInstance().printCommandCall(this);

        inventory = new Inventory();

        Logger.getInstance().printReturnCommand();
    }

    private ArrayList<TeleportGate> gates;

    private Inventory inventory;

    public void Mine() {
        Logger.getInstance().printCommandCall(this);

        Material m = asteroid.Mined();
        if(m != null)
            m.Add(inventory);

        Logger.getInstance().printReturnCommand();
    }

    public void BuildBase() {
        Logger.getInstance().printCommandCall(this);

        Base base = new Base(inventory);

        Logger.getInstance().printReturnCommand();
    }

    public void BuildGate() {
        Logger.getInstance().printCommandCall(this);

        TeleportGate g1 = new TeleportGate(inventory, this);

        Logger.getInstance().printReturnCommand();
    }

    public void BuildRobot() {
        Logger.getInstance().printCommandCall(this);

        Robot r = new Robot(inventory, asteroid);

        Logger.getInstance().printReturnCommand();
    }

    public void PlaceMaterial(Material m) {
        Object[] p = {m.name};
        Logger.getInstance().printCommandCall(this, p);

        if(asteroid.AddMaterial(m))
            m.Remove(inventory);

        Logger.getInstance().printReturnCommand();
    }

    public void Explode() {
        Logger.getInstance().printCommandCall(this);

        Die();

        Logger.getInstance().printReturnCommand();
    }

    public void Die() {
        Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);
        AsteroidBelt.getInstance().SetSettlersAlive();

        Logger.getInstance().printReturnCommand();
    }

    public void AddGate(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        /*if(gates.size() < 2)*/
            gates.add(t);

        Logger.getInstance().printReturnCommand();
    }

    public void PlaceGate(TeleportGate t) {
        Object[] p = {t.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        t.Place(asteroid);
        gates.remove(t);

        Logger.getInstance().printReturnCommand();
    }

}