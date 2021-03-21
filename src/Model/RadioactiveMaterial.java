package Model;

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
        if(a.GetSunProximity() == true && a.getLayers() == 0)
            a.Explode();
        Logger.getInstance().printReturnCommand();
    }

    public void setName(RadioactiveMaterialName rname){
        Logger.getInstance().printCommandCall(this);
        name = rname;
        Logger.getInstance().printReturnCommand();
    }

}