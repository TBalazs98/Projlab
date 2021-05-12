package Model;

import Controller.Main;

import java.util.*;

/**
 * Jatekosok autonom segitoit reprezentalja. Karakterek altal epithetoek.
 */
public class Robot extends Worker implements drillable, Steppable{
    private static final Inventory inventory = new Inventory();

    /**
     * Publikus default konstruktor
     */
    public Robot() {
        super();
        //Logger.getInstance().printCommandCall(this);

        setInventory();

        //Logger.getInstance().printReturnCommand();
    }

    public void Drill(){
        this.asteroid.Drilled();
    }

    /**
     * Publikus 2 parameteres konstrukor
     * @param i - A settler inventory-ja
     * @param a - Aszteroida, amin letre lesz hozva a robot
     */
    public Robot(Inventory i, Asteroid a) {
        super();            //Os konstruktora
        //Object[] p = {i.getClass().getSimpleName(), a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        //fuggveny lefutasa felhasznaloi beavatkozassal
            /*System.out.print("? Do we have enough materials to build a robot?\t(Y)es / (N)o \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ch=='y' || ch=='Y' ) {
                asteroid = a;           //A robot Aszteroidajat beallitjuk a parameterkent kapott Aszteroidara
                a.Accept(this);      //Es ezt a tenyt kozoljuk az Aszteroidaval is
            }
*/
        //fuggveny lefutasa tagvaltozo lekerdezesevel
         setInventory();
            if(inventory.ContainsAllElementsIn(i)) {
                this.asteroid=a;
                a.Accept(this);
            }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Inicializalja az inventory-t. Itt tarolja az epiteshez szukseges anyagokat.
     */
    private void setInventory() {
        //Logger.getInstance().printCommandCall(this);

        HashMap<MaterialName, Integer> m = new HashMap<MaterialName, Integer>();
        m.put(NormalMaterialName.IRON, 1);      //Letrehozzuk a szukseges anyagokat
        m.put(NormalMaterialName.COAL, 1);
        m.put(RadioactiveMaterialName.URAN, 1);
        inventory.fill(m);                      //Majd belepakoljuk a Robot Inventory-jaba

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A controller itt donteni el, hogy mi lesz a robot kovetkezo lepese
     */
    public void Step() {
        //Logger.getInstance().printCommandCall(this);

        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        switch(rand_int){
            case 0 : {
                Drill();
                break;
            }
            case 1 : {
                int id = asteroid.GetRandNeighbour();
                Move(id);
                break;
            }
        }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Metodus, amely kezeli azt az esemenyt, amikor az az aszteroida felrobban,
     * amin a Robot eppen tartozkodik.
     */
    public void Explode() {
        //Logger.getInstance().printCommandCall(this);

        //fuggveny lefutasa felhasznaloi beavatkozassal
          /*  System.out.print("? Does the robot get thrown to an asteroid or a teleportgate?\t(A)steroid / (T)eleportgate \t");
            InputStreamReader br = new InputStreamReader(System.in);
            char ch = ' ';
            try {
                ch=(char)br.read();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int id = 0;
            if(ch=='a' || ch=='A' ) {
                id = 0;
            } else if(ch == 't' || ch == 'T') {
                id = 1;
            }
*/
        //fuggveny lefutasa tagvaltozo lekerdezesevel

        int id = this.asteroid.GetRandNeighbour();
        if(id != -1) {
            System.out.println("IDE AKAROK MENNI: " + id);
            Move(id);
            Game.getInstance().c.g.gamespace.repaint();
            Game.getInstance().c.g.gamespace.validate();
        } else
            Die();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A robot halalanak bekovetkezeset lekezelo metodus
     */
    public void Die(){
//        Main.robots.remove(this);
        this.asteroid.Remove(this);
        Game.getInstance().c.g.getRobotViewByRobot(this).l.setIcon(null);
        Main.robots.remove(this);
        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();
    }

//    public Robot copy() {
//        Robot copy = new Robot();
//        copy.setAsteroid(asteroid);
//        return copy;
//    }
}