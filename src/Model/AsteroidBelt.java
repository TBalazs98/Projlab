package Model;

import java.io.IOException;
import java.io.InputStreamReader;
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

        asteroids = new ArrayList<Asteroid>();

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

        //függvény lefutása felhasználói beavatkozással
            System.out.println("\nAre there any settlers alive?");
            System.out.println(" (Y)es / (N)o");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='n' || ch=='N' ) {
                Game.getInstance().LoseGame();
            }

        //függvény lefutása tagváltozó lekérdezésével
        /*this.settlersAlive--;
        if(settlersAlive == 0)
            Game.getInstance().LoseGame();
        */

        Logger.getInstance().printReturnCommand();
    }

}