package Model;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A szublimáló nyersanyagokat kezeli.
 */
public class SublimableMaterial extends Material {
    private SublimableMaterialName sname;

    /**
     *A szublimáló nyersanyagok konstruktora.
     */
    public SublimableMaterial() {
        super();
        Logger.getInstance().printCommandCall(this);

        sname = SublimableMaterialName.ICEWATER;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Ha az aszteroidát külső behatás éri, ellenőrzi, hogy
     * napközelben van-e.
     * @param a
     */
    public void Hit(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        //függvény lefutása felhasználói beavatkozással
            System.out.println("\nIs the asteroid near the sun?");
            System.out.println(" (Y)es / (N)o");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                a.RemoveMaterial(this);
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*if(a.getLayers() == 0 && a.GetSunProximity() == true) //ide se kell a getlayers, lásd: radioactive
            a.RemoveMaterial(a.getMaterial());
        */

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Beállítja a nyersanyag nevét.
     * @param name
     */
    public void setName(SublimableMaterialName name){
        Logger.getInstance().printCommandCall(this);

        sname = name;

        Logger.getInstance().printReturnCommand();
    }

}