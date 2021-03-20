package Model;

import java.util.*;

/**
 * 
 */
public class Robot extends Worker {
    private static final Inventory inventory = new Inventory();

    public Robot() {
        setInventory();
    }

    public Robot(Inventory i, Asteroid a) {
        setInventory();
        if(inventory.ContainsAllElementsIn(i)) {
            asteroid=a;
            a.Accept(this);
        }
    }

    private void setInventory() {
        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 1);
        m.put(NormalMaterialName.COAL, 1);
        m.put(RadioactiveMaterialName.URAN, 1);
        inventory.init(m);
    }
    public void Step() {
        Random rand = new Random();
        if(rand.nextInt() % 2 == 0)
            Drill();
        else {
            int id = asteroid.GetRandNeighbour();
            Move(id);
        }
    }

    public void Explode() {
        int id = asteroid.GetRandNeighbour();
        Move(id);
    }

}