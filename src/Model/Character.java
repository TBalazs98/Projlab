package Model;

import java.util.*;

/**
 *A jatekban talalhato minden karakter ososztalya, olyan tulajdonsagokat valosit meg amely minden karaktertol elvart.
 */
public abstract class Character {

    protected Asteroid asteroid;

    /**
     * Konstruktor.
     */
    public Character() {
    }

    /**
     *Karakter meghal, eltavolitasra kerul a jatekbol.
     */
    public void Die() {
        Logger.getInstance().printCommandCall(this);

        asteroid.Remove(this);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Karakter felrobbant egy aszteroidaval, a tipusanak megfelelő muveletsor tortenik vele.
     */
    public abstract void Explode();

    /**
     * Karakter parameternek megfelelő helyre mozog.
     * @param idx
     */
    public void Move(int idx) {
        Object[] p = {idx};
        Logger.getInstance().printCommandCall(this, p);

        asteroid.Remove(this);
        DestinationObject goingTo = asteroid.GetNeighbour(idx);
        Asteroid a = (Asteroid) goingTo.Accept(this);
        if(a != null) {
            asteroid = a; //fixen asteroid lesz, mivel a DestinationObject minden implementálásánál Asteroiddal tér vissza
        }
        else {
            asteroid.Accept(this);
        }

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Karaker aszteroidajanak beallítasa.
     * @param a
     */
    public void addAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.asteroid = a;

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Getter az aszteroidajahoz.
     * @return
     */
    public Asteroid getAsteroid() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand(asteroid.getClass().getSimpleName());

        return asteroid;
    }

    /**
     * Setter az aszteroidahoz.
     * @param a
     */
    public void setAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.asteroid = a;

        Logger.getInstance().printReturnCommand();
    }

}