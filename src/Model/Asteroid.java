package Model;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class Asteroid implements DestinationObject {

    private boolean isEmpty;

    private int layers;

    private boolean isNearSun;

    private Material material;

    private ArrayList<Character> characters;

    private ArrayList<DestinationObject> neighbours;

    public Asteroid(){
        Logger.getInstance().printCommandCall(this);
        this.layers = 0;
        this.isEmpty = true;
        this.material = null;
        this.isNearSun = false;
        this.neighbours = new ArrayList<DestinationObject>();
        this.characters = new ArrayList<Character>();
        Logger.getInstance().printReturnCommand();
    }

    public Asteroid(int layer, boolean isempty,boolean isnearsun, Material mat){
        Object[] p = {layer, isempty, isnearsun, mat.name};
        Logger.getInstance().printCommandCall(this, p);
        this.layers = layer;
        this.isEmpty = isempty;
        this.isNearSun = isnearsun;
        this.material = mat;
        this.characters = new ArrayList<Character>();
        this.neighbours = new ArrayList<DestinationObject>();
        Logger.getInstance().printReturnCommand();
    }

    public void setCharacter(Character c){
        Object[] p = {c.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        characters.add(c);
        Logger.getInstance().printReturnCommand();
    }

    public void setNeighbour(DestinationObject d){
        Object[] p = {d.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        neighbours.add(d);
        Logger.getInstance().printReturnCommand();
    }

    public int getLayers() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(this.layers);
        return this.layers;
    }

    public Material getMaterial() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(this.material.name);
        return this.material;
    }

    public Asteroid Accept(Character c) {
        Object[] p = {c.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        characters.add(c);
        Logger.getInstance().printReturnCommand(this.getClass().getSimpleName());
        return this;
    }

    public void Remove(Character c) {
        Object[] p = {c.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        characters.remove(c);
        Logger.getInstance().printReturnCommand();
    }

    public void Step() {
        Logger.getInstance().printCommandCall(this);
        this.SetSunProximity();
        if((layers == 0) && (!isEmpty))
            this.material.Hit(this);

        Logger.getInstance().printReturnCommand();
    }

    public void Drilled() {
        Logger.getInstance().printCommandCall(this);
        this.layers--;
        if(!isEmpty && layers == 0) {
            material.Hit(this);
        }
        Logger.getInstance().printReturnCommand();
    }

    public Material Mined() {
        Logger.getInstance().printCommandCall(this);
        if(!isEmpty && layers == 0) {
            material.Hit(this);
            isEmpty = true;
            Logger.getInstance().printReturnCommand(material.name);
            return material;
        }
        Logger.getInstance().printReturnCommand();
        return null;
    }

    public void AddNeighbour(DestinationObject d) {
        Object[] p = {d.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        if(!neighbours.contains(d))
            this.neighbours.add(d);
        Logger.getInstance().printReturnCommand();
    }

    public void RemoveNeighbour(DestinationObject d) {
        Object[] p = {d.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        if(neighbours.contains(d))
            this.neighbours.remove(d);
        Logger.getInstance().printReturnCommand();
    }

    public boolean AddMaterial(Material m) {
        Object[] p = {m.name};
        Logger.getInstance().printCommandCall(this, p);
        if(isEmpty && layers == 0) {
            isEmpty = false;
            this.material = m;
            Logger.getInstance().printReturnCommand(true);
            return true;
        }
        Logger.getInstance().printReturnCommand(false);
        return false;
    }

    public void RemoveMaterial(Material m) {
        Object[] p = {m.name};
        Logger.getInstance().printCommandCall(this, p);
        if(!isEmpty)
            this.material = null;
        Logger.getInstance().printReturnCommand();
    }

    public DestinationObject GetNeighbour(int id) {
        Object[] p = {id};
        Logger.getInstance().printCommandCall(this, p);
        if((id > neighbours.size()) || (id < 0)) {
            Logger.getInstance().printReturnCommand();
            return null;
        }
        Logger.getInstance().printReturnCommand(neighbours.get(id).getClass().getSimpleName());
        return neighbours.get(id);
    }

    public int GetRandNeighbour() {
        Logger.getInstance().printCommandCall(this);
        Random rand = new Random();
        int n = rand.nextInt(neighbours.size());
        Logger.getInstance().printReturnCommand(n);
        return n;
    }

    public void SetSunProximity() {
        Logger.getInstance().printCommandCall(this);
        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        switch(rand_int){
            case 0 : isNearSun = false;
            case 1 : isNearSun = true;
        }
        Logger.getInstance().printReturnCommand();
    }

    public boolean GetSunProximity() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(isEmpty);
        return isEmpty;
    }

    public void HitBySunstorm() {
        Logger.getInstance().printCommandCall(this);
        if((layers!=0) && (!isEmpty))
            this.characters.forEach(c -> c.Explode());
        Logger.getInstance().printReturnCommand();
    }

    public void HitByExplosion(Asteroid a) {
        Logger.getInstance().printCommandCall(this);
        this.neighbours.remove(a);
        Logger.getInstance().printReturnCommand();
    }

    public void Explode() {
        Logger.getInstance().printCommandCall(this);
        this.neighbours.forEach(n -> n.HitByExplosion(this));
        this.characters.forEach(c -> c.Explode());
        Logger.getInstance().printReturnCommand();
    }

}