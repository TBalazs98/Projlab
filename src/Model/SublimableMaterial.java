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
            System.out.print("Is the asteroid near the sun?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch =='y' || ch =='Y' ) {
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
    public void setName(SublimableMaterialName name) {
        Logger.getInstance().printCommandCall(this);

        sname = name;

        Logger.getInstance().printReturnCommand();
    }

}