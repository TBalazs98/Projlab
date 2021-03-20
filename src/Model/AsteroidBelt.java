package Model;

import java.util.ArrayList;

/**
 * proba
 */
public class AsteroidBelt {

    private int settlersAlive;

    private ArrayList<Asteroid> asteroids;

    public AsteroidBelt() {
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
    }

}