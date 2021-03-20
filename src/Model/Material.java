package Model;

import java.util.*;

public class Material  {

    NormalMaterialName name;
    public Material() { }

    public void Add(Inventory inventory) {
        inventory.Add(this.name);
    }

    public void Hit(Asteroid a) {}

    public void Remove(Inventory inventory) {
        inventory.Remove(this.name);
    }

    public void setName(NormalMaterialName n){
        name = n;
    }

}