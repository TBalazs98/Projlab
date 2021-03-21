package Model;

import java.util.*;

public class Material  {

    NormalMaterialName name;
    public Material() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand();
    }

    public void Add(Inventory inventory) {
        Logger.getInstance().printCommandCall(this);
        inventory.Add(this.name);
        Logger.getInstance().printReturnCommand();
    }

    public void Hit(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        Logger.getInstance().printReturnCommand();
    }

    public void Remove(Inventory inventory) {
        Object[] p = {inventory.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        inventory.Remove(this.name);
        Logger.getInstance().printReturnCommand();
    }

    public void setName(NormalMaterialName n) {
        Object[] p = {n.name()};
        Logger.getInstance().printCommandCall(this, p);
        name = n;
        Logger.getInstance().printReturnCommand();
    }

}