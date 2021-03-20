package Model;

import java.util.*;

/**
 *
 */
public class RadioactiveMaterial extends Material {

    private RadioactiveMaterialName name;


    public RadioactiveMaterial() {
        name = RadioactiveMaterialName.URAN;
    }


    public void Hit(Asteroid a) {
        if(a.GetSunProximity() == true && a.getLayers() == 0) a.Explode();
    }

}