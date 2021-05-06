package Model;

import Controller.Main;

import java.util.Random;

/**
 * Az aszteroidaovben garazdalkodo Ufok. Ugy viselkedik mint a robotot robbanasokkor.
 */
public class UFO extends Character implements Steppable, mine{
    Inventory inventory;

    public UFO(){
        super();
        inventory=new Inventory();
    }

    /**
     * Metodus, amely kezeli azt az esemenyt, amikor az az aszteroida felrobban,
     * amin eppen tartozkodik.
     */
    public void Explode() {
        int id = this.asteroid.GetRandNeighbour();
        Move(id);
    }

    /**
     * A controller itt donteni el, hogy mi lesz az ufo kovetkezo lepese
     */
    public void Step(){
        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        switch(rand_int){
            case 0 : {
                Mine();
                break;
            }
            case 1 : {
                int id = asteroid.GetRandNeighbour();
                Move(id);
                break;
            }
        }
    }

    /**
     * Kibanyassza az aszteroida magjaban talalhato nyersanyagot amin
     * tartozkodik.
     */
    public void Mine(){
        Material m = asteroid.Mined();
        if(m != null)
            m.Add(inventory);
    }

    public Inventory GetInventory(){
        return this.inventory;
    }

    /**
     * Az UFO halalat levezenylo metodus
     */
    public void Die(){
        Main.ufos.remove(this);
        this.inventory.CharacterDied();
        this.asteroid.Remove(this);
    }


}
