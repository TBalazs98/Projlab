package Model;

public class TeleportGate implements DestinationObject {
    public TeleportGate() {
    }

    private boolean isActive;
    private boolean isPlaced;
    private TeleportGate pair;
    private Asteroid asteroid;
    private static Inventory iv;

    public TeleportGate(Inventory inventory, Settler s) {
           if(iv.ContainsAllElementsIn(inventory))
            {
                this.pair = new TeleportGate();
                this.isPlaced = false;
                this.isActive = false;
                this.asteroid = s.getAsteroid();
                this.Place(this.asteroid);
                s.AddGate(this);
            }
    }

    public Asteroid Accept(Character c) {
        if (isActive) {
            pair.GetAsteroid().Accept(c);
        }
        return this.pair.GetAsteroid();
    }

    public void Place(Asteroid asteroid) {
        this.asteroid = asteroid;
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
        this.Deactivate();
        this.isPlaced = false;
    }

}