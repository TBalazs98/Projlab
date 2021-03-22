package Model;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A szublimalo nyersanyagokat kezeli.
 */
public class SublimableMaterial extends Material {
    public SublimableMaterialName name;

    /**
     *A szublimalo nyersanyagok konstruktora.
     */
    public SublimableMaterial() {
        super();
        Logger.getInstance().printCommandCall(this);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Ha az aszteroidat kulso behatas eri, ellenorzi, hogy
     * napkozelben van-e.
     * @param a az aszteroida, ahonnan a kulso behatas erkezett
     */
    public void Hit(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("? Is the asteroid near the sun?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch =='y' || ch =='Y' ) {
                a.RemoveMaterial(this);
            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        /*if(a.getLayers() == 0 && a.GetSunProximity() == true) //ide se kell a getlayers, lasd: radioactive
            a.RemoveMaterial(a.getMaterial());
        */

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Beallitja a nyersanyag nevet.
     * @param sname a kapott nev, amit beallit maganak
     */
    public void setName(SublimableMaterialName sname) {
        Object[] p = {sname};
        Logger.getInstance().printCommandCall(this, p);

        name = sname;

        Logger.getInstance().printReturnCommand();
    }

    public MaterialName getName() {
        return this.name;
    }
}