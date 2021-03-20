package Model;

import java.util.*;

/**
 *
 */
public class TeleportGate implements DestinationObject {
    public TeleportGate() {
    }

    private boolean isActive;
    private boolean isPlaced;
    private Settler creator;
    private TeleportGate pair;
    private Asteroid asteroid;
    private Inventory iv;

    public TeleportGate(Inventory inventory, Settler s) {
        this.creator = s;
        this.pair = new TeleportGate();
         /*   if(inventory.ContainsAllElementsIn(iv))
            {
            }*/
    }

    public void Accept(Character c) {
        if (isActive) {
            pair.GetAsteroid().Accept(c);
        }
    }

    public void Place(Asteroid asteroid) {
        this.asteroid = creator.getAsteroid();
        if (this.GetPlaced()) {
            this.GetPair().Activate();
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
        this.isPlaced = false;

    }

}