package Model;

import java.util.*;
import java.util.ArrayList;

/**
 *A jatek peldanyat testesiti meg, egyetlen peldany letezhet belole egyszerre.
 */
public class Game {

    /**
     *Privat konstruktor.
     */
    private Game() {
        Logger.getInstance().printCommandCall(this);
        Logger.getInstance().printReturnCommand();
    }

    private static final Game game = new Game();
    private int rounds;
    private ArrayList<Steppable> steppables;

    /**
     *Jatek inditasa.
     */
    public void StartGame() {
        // TODO implement here
    }

    /**
     * lekeri az egyetlen letezo objektumot
     * @return
     */
    public static Game getInstance() {
        return game;
    }

    /**
     *Jatekosok osszegyujtottek eleg nyersanyagot, felepitik a bazist ezzel nyernek.
     */
    public void WinGame() {
        Logger.getInstance().printCommandCall(this);

        System.out.println("Settlers have won the game");

        Logger.getInstance().printReturnCommand();
    }

    /**
     *Minden karakter meghalt vagy nem talalhato eleg nyersanyag az aszteroida ovben a bazis felepítesehez.
     */
    public void LoseGame() {
        Logger.getInstance().printCommandCall(this);

        System.out.println("Settlers have lost the game");

        Logger.getInstance().printReturnCommand();
    }

    /**
     *Kovetkezo kor inditasa.
     */
    public void NextRound() {
        Logger.getInstance().printCommandCall(this);

        steppables.forEach(s -> s.Step());

        Logger.getInstance().printReturnCommand();
    }

}