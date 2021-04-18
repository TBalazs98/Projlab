package Model;

import java.util.Random;

public class UFO extends Character implements Steppable, mine{
    Inventory inventory;

    public UFO(){
        super();
        inventory=new Inventory();
    }

    public void Explode() {
        int id = this.asteroid.GetRandNeighbour();
        Move(id);
    }

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
    public void Mine(){
        Material m = asteroid.Mined();
        if(m != null)
            m.Add(inventory);
    }

    public Inventory GetInventory(){
        return this.inventory;
    }

    public void Die(){
        Main.ufos.remove(this);
        this.inventory.CharacterDied();
        this.asteroid.Remove(this);
    }


}
