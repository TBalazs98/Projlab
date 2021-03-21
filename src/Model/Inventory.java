package Model;

import java.util.*;


/**
 * Az Inventory osztaly testesiti meg a Settler nyersanyag tarolojat.
 */
public class Inventory {
    public HashMap<MaterialName, Integer > inventory;

    /**
     *  Publikus alapertelmezett konstruktor.
     */
    public Inventory() {
        Logger.getInstance().printCommandCall(this);
        inventory = new HashMap<MaterialName, Integer>();
        Logger.getInstance().printReturnCommand();
    }

    /**
     * Egy nyersanyag nevet kap parameterkent es visszaadja a lekerdezni kivant nyersanyag mennyiseget az Inventory-ban.
     * @param m : a lekerdezni kivant nyersanyag neve
     * @return : a nyersanyag darabszama ami a jelenlegi Inventory-ban van
     */
    public Integer get_amount(MaterialName m) {
        Object[] p = {m};
        Logger.getInstance().printCommandCall(this, p);
        Logger.getInstance().printReturnCommand(inventory.get(m));
        return inventory.get(m);
    }

    /**
     * Parameterkent kap egy nyersanyag nevet es egy mennyiseget, amely egy objektum (Robot,Base,Teleport Gate) letrehozasahoz szukseges, es berakja a jelenlegi Inventory-ba.
     * @param m : a nyersanyag nevek es azok mennyisege, amelyek egy objektum felepitesehez kellenek
     */
    public void fill(HashMap<MaterialName, Integer> m) {
        Object[] p = {m.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        inventory.putAll(m);
        Logger.getInstance().printReturnCommand();
    }

    /**
     * Egy nyersanyag nevet ker parameterul, es a jelenlegi Inventory-ba belerakja.
     * @param m : az Inventory-ba rakni kivant nyersanyag neve
     */
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

    /**
     * Egy nyersanyag nevet ker parameterul, es azt a nyersanyagot kiveszi az Inventory-bol.
     * @param m : a eltavolitani kivant nyersanyag neve
     */
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

    /**
     * Egy Inventory-t kap parameterul, es osszehasonlitja a jelenlegi Inventory-val, hogy tartalmazza-e azon nyersanyagokat, valamint azon mennyisegeket, amit parameterul adtunk.
     * @param i : az osszehasonlitani kivant Inventory
     * @return : ha tartalmazza a nyersanyagokat es azon mennyisegeket igaz ertekkel ter vissza a metodus, kulonben hamis ertekkel ter vissza
     */
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

    /**
     * A jelenlegi Inventory-ban tarolt nyersanyagok mennyiseget vizsgalja.
     * @return : visszaadja a jelenlegi Inventory-ban tarolt nyersanyagok mennyiseget
     */
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
