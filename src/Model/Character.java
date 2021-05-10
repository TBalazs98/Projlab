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
        //Logger.getInstance().printCommandCall(this);


        asteroid.Remove(this);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Karakter felrobbant egy aszteroidaval, a tipusanak megfelelo muveletsor tortenik vele.
     */
    public abstract void Explode();

    /**
     * Karakter parameternek megfelelo helyre mozog.
     * @param idx parameter amely megadja az aszteroidat amelyre a mozgas tortenik
     */
    public void Move(int idx) {
        //Object[] p = {idx};
        //Logger.getInstance().printCommandCall(this, p);

        asteroid.Remove(this);
        DestinationObject goingTo = asteroid.GetNeighbour(idx);
        Asteroid a = (Asteroid) (goingTo.Accept(this));
        if(a != null) {
            asteroid = a; //fixen asteroid lesz, mivel a DestinationObject minden implementalasanal Asteroiddal ter vissza
            //a.Accept(this);
        }
        else {
            asteroid.Accept(this);
        }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Karaker aszteroidajanak beallitasa.
     * @param a aszteroida amelyen a karakter tartozkodik
     */
    public void addAsteroid(Asteroid a) {
        //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        this.asteroid = a;

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Getter az aszteroidajahoz.
     * @return visszaadja a karakter aszteroidajat
     */
    public Asteroid getAsteroid() {
//        //Logger.getInstance().printCommandCall(this);
//        //Logger.getInstance().printReturnCommand(asteroid.getClass().getSimpleName());

        return asteroid;
    }

    /**
     * Setter az aszteroidahoz.
     * @param a karakter amelyen az aszteroida tartozodik
     */
    public void setAsteroid(Asteroid a) {
       //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        this.asteroid = a;

        //Logger.getInstance().printReturnCommand();
    }

//    public abstract Character copy();

}