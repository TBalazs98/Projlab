package Model;

import java.util.*;

/**
 * 
 */
public abstract class Character {

    protected Asteroid asteroid;

    public Character() {
    }

    public void Die() {}
    public abstract void Explode();

    public void Move(int idx) {
        asteroid.Remove(this);
        DestinationObject goingTo = asteroid.GetNeighbour(idx);
        asteroid = (Asteroid) goingTo.Accept(this); //fixen asteroid lesz, mivel a DestinationObject minden implementálásánál Asteroiddal tér vissza
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