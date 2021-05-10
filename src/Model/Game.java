package Model;

import Controller.Controller;
import Controller.InputManager;
import Controller.Main;
import View.SettlerView;

import java.util.ArrayList;

/**
 *A jatek peldanyat testesiti meg, egyetlen peldany letezhet belole egyszerre.
 */
public class Game {
    public Controller c ;
    public boolean lostgame = false;
    private ArrayList<Settler> settlers = new ArrayList<Settler>();
    private Settler currentSettler;
    /**
     *Privat konstruktor.
     */
    private Game() {
        //Logger.getInstance().printCommandCall(this);
        //Logger.getInstance().printReturnCommand();
    }

    private static final Game game = new Game();
    private int rounds;
    private ArrayList<Steppable> steppables;

    /**
     *Jatek inditasa.
     */
    public void StartGame() {
        c=new Controller();
        //settlers = Main.settlers;
    }

    /**
     * lekeri az egyetlen letezo objektumot
     * @return visszater az objektum peldannyal
     */
    public static Game getInstance() {
        return game;
    }

    /**
     *Jatekosok osszegyujtottek eleg nyersanyagot, felepitik a bazist ezzel nyernek.
     */
    public void WinGame() {
        //Logger.getInstance().printCommandCall(this);

        System.out.println("Settlers have won the game");
        InputManager.write_to_output(true,"Settlers have won the game");

        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Minden karakter meghalt vagy nem talalhato eleg nyersanyag az aszteroida ovben a bazis felepitesehez.
     */
    public void LoseGame() {
        //Logger.getInstance().printCommandCall(this);

        System.out.println("Settlers have lost the game");
        InputManager.write_to_output(true,"Settlers have lost the game");

        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Kovetkezo kor inditasa.
     */
    public void NextRound() {
        //Logger.getInstance().printCommandCall(this);

//        if(Main.Randomize == true) {
//            for(Steppable s: steppables)
//                s.Step();
//        }
//        //for(int i = 0; i < settlers.size(); i++) {
//            currentSettler = settlers.get(0);
//            SettlerView csv = null;
//            for(int j = 0; j < c.g.settlers.size(); j++) {
//                if(c.g.settlers.get(j).getSettler() == currentSettler) {
//                    csv = c.g.settlers.get(j);
//                    csv.Highlight();
//                }
//            }
//            //do something();
//        //}
//
//
//        //Logger.getInstance().printReturnCommand();
        for(Asteroid a: Main.asteroids) {
            Game.getInstance().c.g.getAsteroidViewByAsteroid(a).setInSunstorm(false);
        }
    }

    public void AddSteppable(Object s){
        this.steppables.add((Steppable)s);
    }

    public void RemoveSteppabe(Object s){
        this.steppables.remove((Steppable)s);
    }

}