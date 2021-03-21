package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *Radioaktív nyersanyagokat megtestesítő osztály.
 */
public class RadioactiveMaterial extends Material {

    private RadioactiveMaterialName name;

    /**
     * Pubikus alapértelmezett konstruktor.
     */
    public RadioactiveMaterial() {
        Logger.getInstance().printCommandCall(this);

        name = RadioactiveMaterialName.URAN;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param a - Asteroid
     *  Ha az aszteroidát külső behatás éri, ellenőrzi, hogy az adott
     * aszteroida napközelben van-e, illetve a sziklarétegen teljesen áthatoltak. Ha mindkét állítás
     * igaz rá, robban.
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
                a.Explode();
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*if(a.GetSunProximity() == true && a.getLayers() == 0) //amúgy ez a 0 ide nem is kell, mert csak akkor hívódik meg a hit, ha a layers 0
            a.Explode();
        */

        Logger.getInstance().printReturnCommand();
    }

    /**
     * @param rname
     * A Radioaktív anyag nevének beállítása.
     */
    public void setName(RadioactiveMaterialName rname){
        Logger.getInstance().printCommandCall(this);

        name = rname;

        Logger.getInstance().printReturnCommand();
    }

}