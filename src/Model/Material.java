package Model;

import java.util.*;

/**
 * A Material a nyersaanyagot reprezentalo ososztaly.
 */
public class Material  {

    public MaterialName name;

    /**
     * A Material osztaly alapertelmezett konstruktora.
     */
    public Material() {
        //Logger.getInstance().printCommandCall(this);
       // Logger.getInstance().printReturnCommand();
    }

    /**
     * A parameterul adott Inventory-ba belehelyezi a nyersanyagot.
     * @param inventory az Inventory amibe szeretnenk elhelyezni nyersanyagot
     */
    public void Add(Inventory inventory) {
       // Object[] p = {inventory.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        inventory.Add(this);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Akkor hivodik meg a metodus, ha a nyersanyagot interakcio eri.
     * @param a az Aszteroida, amin a jenenlegi nyersanyag talalhato
     */
    public void Hit(Asteroid a) {
        //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        //Logger.getInstance().printReturnCommand();
    }

    /** Az Inventory-bol kiveszi a nyersanyagot.
     * @param inventory az Inventory, melybol kivetelre kerul a nyersanyag
     */
    public void Remove(Inventory inventory) {
       // Object[] p = {inventory.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        inventory.Remove(this);

       // Logger.getInstance().printReturnCommand();
    }

    /** Parameterul kap egy normal nyersanyag nevet es beallitja a jelenlegi nyersanyag neveve.
     * @param n a normal nyersanyag neve
     */
    public void setName(MaterialName n) {
       // Object[] p = {n};
       // Logger.getInstance().printCommandCall(this, p);

        name = n;

        //Logger.getInstance().printReturnCommand();
    }

    public MaterialName getName() {
        return this.name;
    }

//    public Material copy() {
//        Material copy = new Material();
//        copy.setName(name);
//        return copy;
//    }
}