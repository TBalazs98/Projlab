package Model;


import java.util.HashMap;

/**
 * 
 */
public class Base {

    private static final Inventory inventory = new Inventory();
    public Base() {
        setInventory();
    }

    public Base(Inventory i) {
        setInventory();
        if(inventory.ContainsAllElementsIn(i))
            Build();
    }

    private void setInventory() {
        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 3);
        m.put(NormalMaterialName.COAL, 3);
        m.put(SublimableMaterialName.ICEWATER, 3);
        m.put(RadioactiveMaterialName.URAN, 3);
        inventory.init(m);
    }

    public void Build() {
        Game.getInstance().WinGame();
    }

}