package Model;

import Controller.Main;

import java.util.*;

/**
 * Az Inventory osztaly testesiti meg a Settler nyersanyag tarolojat.
 */
public class Inventory {
    public HashMap<MaterialName, Integer > inventory;
    private ArrayList<Material> materials = new ArrayList<>();
    /**
     *  Publikus alapertelmezett konstruktor.
     */
    public Inventory() {
        //Logger.getInstance().printCommandCall(this);
        inventory = new HashMap<MaterialName, Integer>();
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Egy nyersanyag nevet kap parameterkent es visszaadja a lekerdezni kivant nyersanyag mennyiseget az Inventory-ban.
     * @param m a lekerdezni kivant nyersanyag neve
     * @return a nyersanyag darabszama ami a jelenlegi Inventory-ban van
     */
    public Integer get_amount(MaterialName m) {
        //Object[] p = {m};
        //Logger.getInstance().printCommandCall(this, p);
        //Logger.getInstance().printReturnCommand(inventory.get(m));
        return inventory.get(m);
    }

    /**
     * Parameterkent kap egy nyersanyag nevet es egy mennyiseget, amely egy objektum (Robot,Base,Teleport Gate) letrehozasahoz szukseges,
     * es berakja a jelenlegi Inventory-ba.
     * @param m a nyersanyag nevek es azok mennyisege, amelyek egy objektum felepitesehez kellenek
     */
    public void fill(HashMap<MaterialName, Integer> m) {
        //Object[] p = {m.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        inventory.putAll(m);
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Egy nyersanyag nevet ker parameterul, es a jelenlegi Inventory-ba belerakja.
     * @param m az Inventory-ba rakni kivant nyersanyag neve
     */
    public void Add(Material m) {
        //Object[] p = {m};
        //Logger.getInstance().printCommandCall(this, p);

            int n = 0;                          //segedvaltozo ahhoz, hogy mennyi keyhez tartozo value van
            if(inventory.containsKey(m.name))        //muszaj ellenorizni, hogy letezik e mar, mert ha nem akkor
                n = inventory.get(m.name);           //nullexception hibat dob
            n++;
            this.inventory.put(m.name,n);
            try {
                this.materials.add(m);
            }catch (NullPointerException e){
            }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Egy nyersanyag nevet ker parameterul, es azt a nyersanyagot kiveszi az Inventory-bol.
     * @param m a eltavolitani kivant nyersanyag neve
     */
    public void Remove(Material m) {
        //Object[] p = {m};
        //Logger.getInstance().printCommandCall(this, p);

        if(inventory.size() > 0) {
            int n = 0;                          //segedvaltozo ahhoz, hogy mennyi keyhez tartozo value van
            if (inventory.containsKey(m.name)) {      //muszaj ellenorizni, hogy letezik e mar, mert ha nem akkor
                n = inventory.get(m.name) -1;           //nullexception hibat dob
                //this.inventory.put(m.name, n);
                this.inventory.put(m.name, n);
                this.materials.remove(m);
            }
        }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Egy Inventory-t kap parameterul, es osszehasonlitja a jelenlegi Inventory-val, hogy tartalmazza-e azon nyersanyagokat,
     * valamint azon mennyisegeket, amit parameterul adtunk.
     * @param i az osszehasonlitani kivant Inventory
     * @return ha tartalmazza a nyersanyagokat es azon mennyisegeket igaz ertekkel ter vissza a metodus, kulonben hamis ertekkel ter vissza
     */
    public boolean ContainsAllElementsIn(Inventory i) {
        //Object[] p = {i.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        if(i.Size() < this.Size()) {
            return false;
        } else {
            for(MaterialName  m : this.inventory.keySet() ){    //vegigmegyunk az osszes materialunkon
                if(i.inventory.get(m) < this.inventory.get(m)){ //ha a parameterkent kapott inventoryban kevesebb van ( mint amennyire szukseg lenne)
                    //Logger.getInstance().printReturnCommand(false);
                    return false;                               //akkor epitkezes no-no
                }
            }
        }
        //Logger.getInstance().printReturnCommand(true);
        return true;                                        //amugy zsa
    }

    /**
     * A jelenlegi Inventory-ban tarolt nyersanyagok mennyiseget vizsgalja.
     * @return visszaadja a jelenlegi Inventory-ban tarolt nyersanyagok mennyiseget
     */
    public int Size() {
        //Logger.getInstance().printCommandCall(this);
        int size=0;
        for(MaterialName m : this.inventory.keySet()){      //vegigmegyunk az osszes nalunk levo materialon
            size+=this.inventory.get(m);                    //es az adott materialhoz tartozo mennyiseget returnoljuk
        }
        //Logger.getInstance().printReturnCommand(size);
        return size;
    }

    void RemoveLot(Inventory i){

    }


    /**
     * Inventory nyersanyagainak listzasa
     * @return visszaadja az Invertory nyersanyagainak listqajat
     */
    public ArrayList<Material> GetMaterials(){
        return  this.materials;
    }

    public Material GetMaterialByName(MaterialName m){
        for(Material mat : this.materials) {
            if (mat.name.equals(m)) {
                Main.materials.remove(mat);
                int n = 0;                          //segedvaltozo ahhoz, hogy mennyi keyhez tartozo value van
                if (inventory.containsKey(m)) {      //muszaj ellenorizni, hogy letezik e mar, mert ha nem akkor
                    n = inventory.get(m) - 1;
                    return mat;
                }
            }
        }
        return  null;
    }

    /**
     * Az Inventory-gazda halalanak bekovetkezeset lekezelo metodus
     */
    public void CharacterDied(){
//        for(Material m : Main.materials)
//            Main.materials.remove(m);
        for(Material m : materials) {
            materials.remove(m);
            Main.materials.remove(m);
        }
        this.inventory.clear();
    }

//    public Inventory copy() {
//        Inventory copy = new Inventory();
//        for(Material m: materials) {
//            copy.Add(m);
//        }
//        copy.fill(inventory);
//        return copy;
//    }
}
