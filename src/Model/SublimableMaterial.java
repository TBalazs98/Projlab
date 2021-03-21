package Model;

import java.util.*;

/**
 *
 */
public class SublimableMaterial extends Material {
    private SublimableMaterialName sname;

    public SublimableMaterial() {
        super();
        Logger.getInstance().printCommandCall(this);

        sname = SublimableMaterialName.ICEWATER;

        Logger.getInstance().printReturnCommand();
    }


    public void Hit(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        if(a.getLayers() == 0 && a.GetSunProximity() == true)
            a.RemoveMaterial(a.getMaterial());

        Logger.getInstance().printReturnCommand();
    }

    public void setName(SublimableMaterialName name){
        Logger.getInstance().printCommandCall(this);

        sname = name;

        Logger.getInstance().printReturnCommand();
    }

}