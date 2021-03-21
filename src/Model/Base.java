package Model;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 */
public class Base {

    private static final Inventory inventory = new Inventory();
    public Base() {
        Logger.getInstance().printCommandCall(this);

        setInventory();

        Logger.getInstance().printReturnCommand();
    }

    public Base(Inventory i) {
        Object[] p = {i.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        //függvény lefutása felhasználói beavatkozással
            System.out.print("Do we have enough materials to build the base?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                Build();
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*setInventory();
        if(inventory.ContainsAllElementsIn(i))
            Build();
        */

        Logger.getInstance().printReturnCommand();
    }

    private void setInventory() {
        Logger.getInstance().printCommandCall(this);

        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 3);
        m.put(NormalMaterialName.COAL, 3);
        m.put(SublimableMaterialName.ICEWATER, 3);
        m.put(RadioactiveMaterialName.URAN, 3);
        inventory.fill(m);

        Logger.getInstance().printReturnCommand();
    }

    public void Build() {
        Logger.getInstance().printCommandCall(this);

        Game.getInstance().WinGame();

        Logger.getInstance().printReturnCommand();
    }

}