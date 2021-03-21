package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class RadioactiveMaterial extends Material {

    private RadioactiveMaterialName name;

    public RadioactiveMaterial() {
        Logger.getInstance().printCommandCall(this);

        name = RadioactiveMaterialName.URAN;

        Logger.getInstance().printReturnCommand();
    }

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

            if(ch=='y' || ch=='Y' ) {
                a.Explode();
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*if(a.GetSunProximity() == true && a.getLayers() == 0) //amúgy ez a 0 ide nem is kell, mert csak akkor hívódik meg a hit, ha a layers 0
            a.Explode();
        */

        Logger.getInstance().printReturnCommand();
    }

    public void setName(RadioactiveMaterialName rname){
        Logger.getInstance().printCommandCall(this);

        name = rname;

        Logger.getInstance().printReturnCommand();
    }

}