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
        this.layers = 0;
        this.isEmpty = true;
        this.material = null;
        this.isNearSun = false;
        this.neighbours = new ArrayList<DestinationObject>();
        this.characters = new ArrayList<Character>();
    }

    public Asteroid(int layer, boolean isempty,boolean isnearsun, Material mat){
        this.layers = layer;
        this.isEmpty = isempty;
        this.isNearSun = isnearsun;
        this.material = mat;
        this.characters = new ArrayList<Character>();
        this.neighbours = new ArrayList<DestinationObject>();
    }

    public void setCharacter(Character c){
        characters.add(c);
    }

    public void setNeighbour(DestinationObject d){
        neighbours.add(d);
    }

    public int getLayers() {
        return  this.layers; }

    public Material getMaterial() {return  this.material; }

    public Asteroid Accept(Character c) {
        characters.add(c);
        return this;
    }

    public void Remove(Character c) {
        characters.remove(c);
    }

    public void Step() {
        this.SetSunProximity();
        if((layers == 0) && (!isEmpty))
            this.material.Hit(this);
    }

    public void Drilled() {

        this.layers--;
    }

    public Material Mined() {
        Logger.getInstance().printCommandCall(this);
        if(!isEmpty) {
            Logger.getInstance().printReturnCommand();
            return material;
        }
        Logger.getInstance().printReturnCommand();
        return null;
    }

    public void AddNeighbour(DestinationObject d) {
        if(!neighbours.contains(d))
            this.neighbours.add(d);
    }

    public void RemoveNeighbour(DestinationObject d) {
        if(neighbours.contains(d))
            this.neighbours.remove(d);
    }

    public boolean AddMaterial(Material m) {
        if(isEmpty) {
            isEmpty = false;
            this.material = m;
            return true;
        }
        return false;
    }

    public void RemoveMaterial(Material m) {
        if(!isEmpty)
            this.material = null;
    }

    public DestinationObject GetNeighbour(int id) {
        if((id > neighbours.size()) || (id < 0))
            return null;
        return neighbours.get(id);
    }

    public int GetRandNeighbour() {
        Random rand = new Random();
        return  rand.nextInt(neighbours.size());
    }

    public void SetSunProximity() {
        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        switch(rand_int){
            case 0 : isNearSun = false;
            case 1 : isNearSun = true;
        }
    }

    public boolean GetSunProximity() {
        return isEmpty;
    }

    public void HitBySunstorm() {
        if((layers!=0) && (!isEmpty))
            this.characters.forEach(c -> c.Explode());
    }

    public void HitByExplosion(Asteroid a) {
        this.neighbours.remove(a);
    }

    public void Explode() {
        this.neighbours.forEach(n -> n.HitByExplosion(this));
        this.characters.forEach(c -> c.Explode());
    }

}