package Model;

import java.util.HashMap;

public class TeleportGate implements DestinationObject {
    public TeleportGate() {
        setInventory();
    }

    private boolean isActive;
    private boolean isPlaced;
    private TeleportGate pair;
    private Asteroid asteroid;
    private static final Inventory inventory = new Inventory();

    public TeleportGate(Inventory i, Settler s) {
        setInventory();
        if(inventory.ContainsAllElementsIn(i))
            {
                this.pair = new TeleportGate();
                this.isPlaced = false;
                this.isActive = false;
                s.AddGate(this);
            }

    }

    private void setInventory() {
        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 2);
        m.put(SublimableMaterialName.ICEWATER, 1);
        m.put(RadioactiveMaterialName.URAN, 1);

        inventory.init(m);
    }

    public Asteroid Accept(Character c) {
        if (isActive) {
            pair.GetAsteroid().Accept(c);
        }
        return this.pair.GetAsteroid();
    }

    public void Place(Asteroid asteroid) {
        this.asteroid = asteroid;
        isPlaced = true;
        if (pair.GetPlaced()) {
            this.pair.Activate();
            this.Activate();
        }
    }

    public boolean GetPlaced() {
        return pair.isPlaced;
    }

    public Asteroid GetAsteroid() {
        return this.asteroid;
    }

    public TeleportGate GetPair() {
        return this.pair;
    }

    public void Activate() {
        this.isActive = true;
    }

    public void Deactivate() {
        this.isActive = false;
    }

    public void HitByExplosion(Asteroid a) {
        this.Destroy();
        this.GetPair().Deactivate();
    }

    public void Destroy() {
        this.Deactivate();
        this.isPlaced = false;
    }

}