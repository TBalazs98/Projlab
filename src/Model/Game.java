package Model;

import Controller.Controller;
import Controller.InputManager;
import Controller.Main;

import java.util.ArrayList;

/**
 *A jatek peldanyat testesiti meg, egyetlen peldany letezhet belole egyszerre.
 */
public class Game {
    public Controller c ;
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

        if(Main.Randomize == true)
            for(Steppable s: steppables)
                s.Step();

        //Logger.getInstance().printReturnCommand();
    }

    public void AddSteppable(Object s){
        this.steppables.add((Steppable)s);
    }

    public void RemoveSteppabe(Object s){
        this.steppables.remove((Steppable)s);
    }

}