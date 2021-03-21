package Model;

import java.util.*;


public class Inventory {
    public HashMap<MaterialName, Integer > inventory;
//asd

    public Inventory() {
        Logger.getInstance().printCommandCall(this);
        inventory = new HashMap<MaterialName, Integer>();
        Logger.getInstance().printReturnCommand();
    }

    //TODO NEW
    public Integer get_amount(MaterialName m) {
        Object[] p = {m};
        Logger.getInstance().printCommandCall(this, p);
        Logger.getInstance().printReturnCommand(inventory.get(m));
        return inventory.get(m);
    }

    public void init(HashMap<MaterialName, Integer> m) {
        Object[] p = {m.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        inventory.putAll(m);
        Logger.getInstance().printReturnCommand();
    }
    public void Add(MaterialName m) {
        Object[] p = {m};
        Logger.getInstance().printCommandCall(this, p);
        if(this.inventory.size()<10) {
            int n = 0;                          //segédváltozó ahhoz, hogy mennyi keyhez tartozó value van
            if(inventory.containsKey(m))        //muszáj ellenőrizni, hogy létezik e már, mert ha nem akkor
                n = inventory.get(m);           //nullexception hibát dob
            n++;
            this.inventory.put(m,n);
        }
        Logger.getInstance().printReturnCommand();
    }

    public void Remove(MaterialName m) {
        Object[] p = {m};
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

    public boolean ContainsAllElementsIn(Inventory i) {
        Object[] p = {i.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        for(MaterialName  m : this.inventory.keySet() ){    //végigmegyünk az összes materiálunkon
            if(i.inventory.get(m) < this.inventory.get(m)){ //ha a paraméterként kapott inventoryban kevesebb van ( mint amennyire szükség lenne)
                Logger.getInstance().printReturnCommand(false);
                return false;                               //akkor építkezés no-no
            }
        }
        Logger.getInstance().printReturnCommand(true);
        return true;                                        //amúgy zsa
    }

    public int Size() {
        Logger.getInstance().printCommandCall(this);
        int size=0;
        for(MaterialName m : this.inventory.keySet()){      //végigmegyünk az összes nálunk lévő materiálon
            size+=this.inventory.get(m);                    //és az adott materiálhoz tartozó mennyiséget returnoljuk
        }
        Logger.getInstance().printReturnCommand(size);
        return size;
    }

}
