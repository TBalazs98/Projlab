package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *Radioaktiv nyersanyagokat megtestesito osztaly.
 */
public class RadioactiveMaterial extends Material {
    public int exposed;
    public RadioactiveMaterialName name;

    /**
     * Pubikus alapertelmezett konstruktor.
     */
    public RadioactiveMaterial() {
        super();
        exposed=0;
        //Logger.getInstance().printCommandCall(this);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Ha az aszteroidat kulso behatas eri, ellenorzi, hogy az adott
     * aszteroida napkozelben van-e, illetve a sziklaretegen teljesen athatoltak. Ha mindket allitas
     * igaz ra, robban.
     * @param a - Asteroid
     */
    public void Hit(Asteroid a) {
        //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
//            System.out.print("? Is the asteroid near the sun?\t(Y)es / (N)o \t");
//            InputStreamReader br = new InputStreamReader(System.in);
//            char ch = ' ';
//            try {
//                ch=(char)br.read();
//                //br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if(ch=='y' || ch=='Y' ) {
//                expose();
//                if(this.exposed==0) {
//                    a.RemoveMaterial(this);
//                    a.Explode();
//                }
//            }

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        if(a.GetSunProximity() == true && a.getLayers() == 0) //amugy ez a 0 ide nem is kell, mert csak akkor hívodik meg a hit, ha a layers 0
            expose();
        if(exposed == 3)
            a.Explode();


        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Radioaktiv anyag nevenek beallitasa.
     * @param rname Beallitando anyag neve
     */
    public void setName(RadioactiveMaterialName rname) {
       // Object[] p = {rname};
        //Logger.getInstance().printCommandCall(this, p);

        name = rname;
        super.name = null;
        //Logger.getInstance().printReturnCommand();
    }

    public MaterialName getName() {
        return this.name;
    }

    public void expose(){
        this.exposed++;
    }

    public void SetExposure(int i){
        this.exposed = i;
    }

    public int GetExposure(){
        return this.exposed;
    }
}