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
    }

    public static AsteroidBelt getInstance() {
        return ab;
    }

    public void Step() {
        this.StartStorm();
    }

    public void StartStorm() {
        asteroids.forEach(a -> a.HitBySunstorm());
    }

    public void RemoveAsteroid(Asteroid a) {
        this.asteroids.remove(a);
    }

    public void AddAsteroid(Asteroid a) {
        this.asteroids.add(a);
    }

    public void SetSettlersAlive() {
        this.settlersAlive--;
        if(settlersAlive == 0)
            Game.getInstance().LoseGame();
    }

}