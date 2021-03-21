package Model;

import java.util.*;

/**
 * 
 */
public abstract class Character {

    protected Asteroid asteroid;

    public Character() {
    }

    public void Die() {
        Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);

        Logger.getInstance().printReturnCommand();
    }

    public abstract void Explode();

    public void Move(int idx) {
        Object[] p = {idx};
        Logger.getInstance().printCommandCall(this, p);

        asteroid.Remove(this);
        DestinationObject goingTo = asteroid.GetNeighbour(idx);
        asteroid = (Asteroid) goingTo.Accept(this); //fixen asteroid lesz, mivel a DestinationObject minden implementálásánál Asteroiddal tér vissza

        Logger.getInstance().printReturnCommand();
    }

    public void addAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.asteroid = a;

        Logger.getInstance().printReturnCommand();
    }

    public Asteroid getAsteroid() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(asteroid.getClass().getSimpleName());

        return asteroid;
    }

    public void setAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.asteroid = a;

        Logger.getInstance().printReturnCommand();
    }

}