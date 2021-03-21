package Model;

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
        setInventory();
        if(inventory.ContainsAllElementsIn(i)) {
            asteroid=a;
            a.Accept(this);
        }
        Logger.getInstance().printReturnCommand();
    }

    private void setInventory() {
        Logger.getInstance().printCommandCall(this);
        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 1);
        m.put(NormalMaterialName.COAL, 1);
        m.put(RadioactiveMaterialName.URAN, 1);
        inventory.init(m);
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
        int id = asteroid.GetRandNeighbour();
        Move(id);
        Logger.getInstance().printReturnCommand();
    }

}