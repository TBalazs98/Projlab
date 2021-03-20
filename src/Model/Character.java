package Model;

import java.util.*;

/**
 * 
 */
public abstract class Character {

    private Asteroid asteroid;

    public Character() {
    }

    public void Die() {}
    public abstract void Explode();

    //FATAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!4
    public void Move(int idx) {
       // asteroid.GetNeighbour(idx);
        asteroid.Remove(this);
        asteroid = (Asteroid)asteroid.GetNeighbour(idx);
    }

    public void addAsteroid(Asteroid a){
        this.asteroid = a;
    }

    public Asteroid getAsteroid(){
        return asteroid;
    }

    public void setAsteroid(Asteroid a){
        this.asteroid = a;
    }

}