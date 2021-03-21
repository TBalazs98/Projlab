package Model;

import java.util.ArrayList;

/**
 * proba
 */
public class AsteroidBelt {

    public int settlersAlive;

    private static final AsteroidBelt ab = new AsteroidBelt();

    private ArrayList<Asteroid> asteroids;

    private AsteroidBelt() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand();
    }

    public static AsteroidBelt getInstance() {
        return ab;
    }

    public void Step() {
        Logger.getInstance().printCommandCall(this);
        this.StartStorm();
        Logger.getInstance().printReturnCommand();
    }

    public void StartStorm() {
        Logger.getInstance().printCommandCall(this);
        asteroids.forEach(a -> a.HitBySunstorm());
        Logger.getInstance().printReturnCommand();
    }

    public void RemoveAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        this.asteroids.remove(a);
        Logger.getInstance().printReturnCommand();
    }

    public void AddAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);
        this.asteroids.add(a);
        Logger.getInstance().printReturnCommand();
    }

    public void SetSettlersAlive() {
        Logger.getInstance().printCommandCall(this);
        this.settlersAlive--;
        if(settlersAlive == 0)
            Game.getInstance().LoseGame();
        Logger.getInstance().printReturnCommand();
    }

}