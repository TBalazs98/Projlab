package Model;

import java.util.*;

/**
 *
 */
public class SublimableMaterial extends Material {
    private SublimableMaterialName sname;

    public SublimableMaterial() {
        sname = SublimableMaterialName.ICEWATER;
    }


    public void Hit(Asteroid a) {
        if(a.getLayers() == 0 && a.GetSunProximity() == true)
            a.RemoveMaterial(a.getMaterial());
    }

    public void setName(SublimableMaterialName name){
        sname = name;
    }

}