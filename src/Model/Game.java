package Model;

import java.util.*;

/**
 * 
 */
public class Game {
    private static final Game game = new Game();

    private Game(){}

    public static Game getInstance(){
        return game;
    }

    private int rounds;
    private Set<Steppable> steppable;
    public void StartGame() {
        // TODO implement here
    }
    public void WinGame() {
        // TODO implement here
    }
    public void LoseGame() {
        // TODO implement here
    }
    public void NextRound() {
        // TODO implement here
    }

}