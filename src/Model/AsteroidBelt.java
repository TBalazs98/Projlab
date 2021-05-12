package Model;

import Controller.Main;

import java.util.ArrayList;
import java.util.Random;

/**
 *A jatek palyajat reprezentalja, minden jatekhoz 1 darab tartozik.
 */
public class AsteroidBelt implements Steppable{

    public int settlersAlive = 0;

    private static final AsteroidBelt ab = new AsteroidBelt();

    private ArrayList<Asteroid> asteroids;

    /**
     *Privat konstruktor az aszteroida ovhoz.
     */
    private AsteroidBelt() {
        //Logger.getInstance().printCommandCall(this);

        asteroids = new ArrayList<Asteroid>();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * lekeri az egyetlen letezo objektumot
     * @return visszaadja az egy letezo aszteroidamezo peldanyt
     */
    public static AsteroidBelt getInstance() {
        return ab;
    }

    /**
     *Az aszteroida ov egy kore, itt idokozonkent indit egy napvihart.
     */
    public void Step() {
        //Logger.getInstance().printCommandCall(this);

        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        switch(rand_int){
            case 0 : {
                this.StartStorm(this.asteroids.get(rand.nextInt(asteroids.size())));
                break;
            }
        }


        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A napvihar elinditasa.
     */
    public void StartStorm(Asteroid a) {
        //Logger.getInstance().printCommandCall(this);
        ArrayList<DestinationObject> aneighb = a.GetNeighbours();
        a.HitBySunstorm();
        for(int i = 0; i < a.GetNeighbourCount(); i++) {
            aneighb.get(i).HitBySunstorm();
        }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Aszteroida eltavolitasa az aszteroida mezobol.
     * @param a Eltavolitando aszteroida
     */
    public void RemoveAsteroid(Asteroid a) {
        //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        this.asteroids.remove(a);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Aszteroida felvetele az aszteroida mezobe.
     * @param a Felvevendo aszteroida
     */
    public void AddAsteroid(Asteroid a) {
        //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        this.asteroids.add(a);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * eletben levo telepesek allitasa.
     */
    public void SetSettlersAlive() {
        //Logger.getInstance().printCommandCall(this);

        //fuggveny lefutasa felhasznaloi beavatkozassal
           /* System.out.print("? Are there any settlers alive?\t(Y)es / (N)o \t");
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

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        */
        this.settlersAlive--;
        if(settlersAlive == 0)
            Main.game.getInstance().LoseGame();

        //Logger.getInstance().printReturnCommand();
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

}