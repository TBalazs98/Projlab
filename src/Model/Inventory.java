package Model;

import java.util.*;


public class Inventory {
    public HashMap<MaterialName, Integer > inventory;
//asd

    public Inventory() {
        inventory = new HashMap<MaterialName, Integer>();
    }

    //TODO NEW
    public Integer get_amount(MaterialName m) {
        return inventory.get(m);
    }

    public void init(HashMap<MaterialName, Integer> m) {
        inventory.putAll(m);
    }
    public void Add(MaterialName m) {
        if(this.inventory.size()<10) {
            int n = 0;                          //segédváltozó ahhoz, hogy mennyi keyhez tartozó value van
            if(inventory.containsKey(m))        //muszáj ellenőrizni, hogy létezik e már, mert ha nem akkor
                n = inventory.get(m);           //nullexception hibát dob
            n++;
            this.inventory.put(m,n);
        }
    }

    public void Remove(MaterialName m) {
        String[] p = {m.toString()};
        Logger.getInstance().printCommandCall(this, p);
        if(this.inventory.size()<10) {
            int n = 0;                          //segédváltozó ahhoz, hogy mennyi keyhez tartozó value van
            if(inventory.containsKey(m)) {      //muszáj ellenőrizni, hogy létezik e már, mert ha nem akkor
                n = inventory.get(m);           //nullexception hibát dob
                this.inventory.put(m,n);
            }
        }
        Logger.getInstance().printReturnCommand();
    }

    public boolean ContainsAllElementsIn(Inventory i ) {
        for(MaterialName  m : this.inventory.keySet() ){    //végigmegyünk az összes materiálunkon
            if(i.inventory.get(m) < this.inventory.get(m)){ //ha a paraméterként kapott inventoryban kevesebb van ( mint amennyire szükség lenne)
                return false;                               //akkor építkezés no-no
            }
        }
        return true;                                        //amúgy zsa
    }

    public int Size() {
        int size=0;
        for(MaterialName m : this.inventory.keySet()){      //végigmegyünk az összes nálunk lévő materiálon
            size+=this.inventory.get(m);                    //és az adott materiálhoz tartozó mennyiséget returnoljuk
        }
        return size;
    }

}
