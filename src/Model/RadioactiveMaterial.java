package Model;

import Controller.Main;

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
        if(a.GetSunProximity() == true && a.getLayers() == 0) { //amugy ez a 0 ide nem is kell, mert csak akkor hívodik meg a hit, ha a layers 0
            expose();

            Game.getInstance().c.g.getMaterialViewByMaterial(this).setImage();
        }
        if(exposed == 3) {

            Main.materials.set(Main.materials.indexOf(this),null);
            Game.getInstance().c.g.getMaterialViewByMaterial(this).setImage();
            Game.getInstance().c.g.materials.remove(Game.getInstance().c.g.getMaterialViewByMaterial(this));
            Game.getInstance().c.g.gamespace.repaint();
            Game.getInstance().c.g.gamespace.validate();
            a.Explode();

        }

        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();


        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A Radioaktiv anyag nevenek beallitasa.
     * @param rname Beallitando anyag neve
     */
    public void setName(RadioactiveMaterialName rname) {
       // Object[] p = {rname};
        //Logger.getInstance().printCommandCall(this, p);


        //name = rname;
        super.name = rname;
        //Logger.getInstance().printReturnCommand();


    }

    /**
     * Radioaktiv nyersanyag fajtajanak lekerdezese
     * @return a radioaktiav nyersanyag fajtaja
     */
    public MaterialName getName() {
        return this.name;
    }

    /**
     * Jelenlegi nyersanyag napsugarzasnak kitettseget lekezelo metodus
     */
    public void expose(){
        this.exposed++;
    }

    /**
     * A nyersanyag elozetes napsugarzas-kitettsegeinek beallitasa
     * @param exp a nyersanyag elozetes napsugarzasnak kitettseget beallito ertek
     * */
    public void SetExposure(int exp){
        this.exposed = exp;
    }

    public int GetExposure(){
        return this.exposed;
    }

//    public RadioactiveMaterial copy() {
//        RadioactiveMaterial copy = new RadioactiveMaterial();
//        copy.SetExposure(exposed);
//        return copy;
//    }
}