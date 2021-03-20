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
        System.out.println("Settlers have won the game");
    }

    public void LoseGame() {
        System.out.println("Settlers have lost the game");
    }

    public void NextRound() {
        steppables.forEach(s -> s.Step());
    }

}