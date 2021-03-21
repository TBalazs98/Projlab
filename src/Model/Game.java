package Model;

import java.util.*;
import java.util.ArrayList;

/**
 * A játék egy példányát testesít meg, singleton.
 */
public class Game {

    /**
     *Privát konstruktor.
     */
    private Game(){}

    private static final Game game = new Game();
    private int rounds;
    private ArrayList<Steppable> steppables;

    /**
     *
     */
    public void StartGame() {
        // TODO implement here
    }

    public static Game getInstance() {
        return game;
    }

    /**
     *Akkor fut le ha a telepesek sikeresen felípítették a bázist.
     */
    public void WinGame() {
        Logger.getInstance().printCommandCall(this);
        System.out.println("Settlers have won the game");
        Logger.getInstance().printReturnCommand();
    }

    /**
     *Akkor fut le ha a minden telepes meghalt, vagy nincs elég nyersanyag az aszteroida mezőben a
     * játék megnyeréséhez.
     */
    public void LoseGame() {
        Logger.getInstance().printCommandCall(this);
        System.out.println("Settlers have lost the game");
        Logger.getInstance().printReturnCommand();
    }

    /**
     *Következő kör kezdése.
     */
    public void NextRound() {
        Logger.getInstance().printCommandCall(this);
        steppables.forEach(s -> s.Step());
        Logger.getInstance().printReturnCommand();
    }

}