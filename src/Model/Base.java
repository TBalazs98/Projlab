package Model;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *A jatekosok altal felepitendo bazist reprezentalja.
 */
public class Base {

    private static final Inventory inventory = new Inventory();

    /**
     *Konstruktor a bazishoz.
     */
    public Base() {
        //Logger.getInstance().printCommandCall(this);

        setInventory();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Konstruktor a bazis epitesehez.
     * @param i Settler inventoryja
     */
    public Base(Inventory i) {
        //Object[] p = {i.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
           /* System.out.print("Do we have enough materials to build the base?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                Build();
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel*/
        setInventory();
        if(inventory.ContainsAllElementsIn(i))
            Build();


        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Beallitja a bazis inventory-jat, az alapjan, hogy mennyi es milyen nyersanyag kell az epiteshez.
     */
    private void setInventory() {
        //Logger.getInstance().printCommandCall(this);

        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 3);
        m.put(NormalMaterialName.COAL, 3);
        m.put(SublimableMaterialName.ICEWATER, 3);
        m.put(RadioactiveMaterialName.URAN, 3);
        inventory.fill(m);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Bazis felepitese.
     */
    public void Build() {
        //Logger.getInstance().printCommandCall(this);

        Game.getInstance().WinGame();

        //Logger.getInstance().printReturnCommand();
    }

    public Inventory GetInventory(){
        return inventory;
    }
}