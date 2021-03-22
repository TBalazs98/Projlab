package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *A jatek palyajat reprezentalja, minden jatekhoz 1 darab tartozik.
 */
public class AsteroidBelt {

    public int settlersAlive;

    private static final AsteroidBelt ab = new AsteroidBelt();

    private ArrayList<Asteroid> asteroids;

    /**
     *Privát konstruktor az aszteroida ovhoz.
     */
    private AsteroidBelt() {
        Logger.getInstance().printCommandCall(this);

        asteroids = new ArrayList<Asteroid>();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * lekeri az egyetlen letezo objektumot
     * @return
     */
    public static AsteroidBelt getInstance() {
        return ab;
    }

    /**
     *Az aszteroida ov egy kore, itt idokozonkent indit egy napvihart.
     */
    public void Step() {
        Logger.getInstance().printCommandCall(this);

        this.StartStorm();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * A napvihar elinditása.
     */
    public void StartStorm() {
        Logger.getInstance().printCommandCall(this);
        for(int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).HitBySunstorm();
        }

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Aszteroida eltavolitasa az aszteroida mezobol.
     * @param a
     */
    public void RemoveAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.asteroids.remove(a);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Aszteroida felvetele az aszteroida mezobe.
     * @param a
     */
    public void AddAsteroid(Asteroid a) {
        Object[] p = {a.getClass().getSimpleName()};
        Logger.getInstance().printCommandCall(this, p);

        this.asteroids.add(a);

        Logger.getInstance().printReturnCommand();
    }

    /**
     * eletben levo telepesek allitasa.
     */
    public void SetSettlersAlive() {
        Logger.getInstance().printCommandCall(this);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            System.out.print("Are there any settlers alive?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='n' || ch=='N' ) {
                Game.getInstance().LoseGame();
            }

        //fuggvény lefutasa tagvaltozo lekerdezesevel
        /*this.settlersAlive--;
        if(settlersAlive == 0)
            Game.getInstance().LoseGame();
        */

        Logger.getInstance().printReturnCommand();
    }

}