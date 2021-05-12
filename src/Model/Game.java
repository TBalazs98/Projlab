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
    private static final Game game = new Game();

    private int rounds;
    private ArrayList<Steppable> steppables = new ArrayList<Steppable>();

    /**
     *Privat konstruktor.
     */
    private Game() {
        //Logger.getInstance().printCommandCall(this);
        //Logger.getInstance().printReturnCommand();
    }

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

//        System.out.println("Settlers have won the game");
//        InputManager.write_to_output(true,"Settlers have won the game");

        Game.getInstance().c.SettlersHaveWon();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Minden karakter meghalt vagy nem talalhato eleg nyersanyag az aszteroida ovben a bazis felepitesehez.
     */
    public void LoseGame() {
        //Logger.getInstance().printCommandCall(this);

//        System.out.println("Settlers have lost the game");
//        InputManager.write_to_output(true,"Settlers have lost the game");

        Game.getInstance().c.SettlersLost();
        //Logger.getInstance().printReturnCommand();
    }


    /**
     * Kovetkezo kor inditasa, minden steppelheto objektumot leptet
     */
    public void NextRound() {
//        ArrayList<Asteroid> prev = Main.asteroids;
        if(Main.Randomize) {
            for (Steppable s : steppables) {
//                if(s!=null) {
                    s.Step();
                    c.g.update();
//                }
            }
        }

    }

    /**
     * Uj steppelheto objektum hozzaadasa
     * @param s uj objektum hozzadasa
     */
    public void AddSteppable(Steppable s){
        this.steppables.add(s);
    }

    /**
     * Steppelheto objektum kivetele
     * @param s kivetendo objektum
     */
    public void RemoveSteppabe(Object s){
        this.steppables.remove((Steppable)s);
    }

}