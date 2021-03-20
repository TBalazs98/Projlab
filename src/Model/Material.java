package Model;

import java.util.*;

public class Material  {

    NormalMaterialName name;
    public Material() { }

    public void Add(Inventory inventory) {
        inventory.Add(this);
    }

    public void Hit(Asteroid a) { }

    public void Remove(HashMap list) {

    }

    public void setName(NormalMaterialName n){
        name = n;
    }

}