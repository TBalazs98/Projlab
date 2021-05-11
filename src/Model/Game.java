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
        ArrayList<Asteroid> prev = Main.asteroids;
        for(Steppable s: steppables) {
            System.out.println("haliak");
            s.Step();
            c.g.update();

        }
        ArrayList<Asteroid> after = Main.asteroids;
        ArrayList<Integer> indexes = lookfordifference(prev,after);
        for(int i =0; i<indexes.size();i++){
            Main.asteroids.remove(i);
            c.g.GetAsteroidView().remove(i);
        }


    }

    public ArrayList<Integer> lookfordifference(ArrayList<Asteroid>a,ArrayList<Asteroid>b){
        ArrayList<Integer> diff=new ArrayList<>();
        for(int i=0; i<a.size();i++){
            for(int j=0; j<b.size();j++){
                if(a.get(i)!=b.get(j)){
                    diff.add(i);
                }
            }
        }
        return diff;
    }

    public void AddSteppable(Steppable s){
        this.steppables.add(s);
    }

    public void RemoveSteppabe(Object s){
        this.steppables.remove((Steppable)s);
    }

}