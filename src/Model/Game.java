package Model;

import java.util.*;
import java.util.ArrayList;

/**
 * 
 */
public class Game {

    private Game(){}

    private static final Game game = new Game();
    private int rounds;
    private ArrayList<Steppable> steppables;

    public void StartGame() {
        // TODO implement here
    }

    public static Game getInstance() {
        return game;
    }

    public void WinGame() {
        Logger.getInstance().printCommandCall(this);
        System.out.println("Settlers have won the game");
        Logger.getInstance().printReturnCommand();
    }

    public void LoseGame() {
        Logger.getInstance().printCommandCall(this);
        System.out.println("Settlers have lost the game");
        Logger.getInstance().printReturnCommand();
    }

    public void NextRound() {
        Logger.getInstance().printCommandCall(this);
        steppables.forEach(s -> s.Step());
        Logger.getInstance().printReturnCommand();
    }

}