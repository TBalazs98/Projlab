package Controller;
import Controller.InputManager;
import Model.*;
import View.GUI;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Bakonyi Klaudia
 * @author Bihari Bence
 * @author Nagy David
 * @author Toth Balazs
 * @author Wagner Reka
 */

public class Main {
    /**
     * Jelenleg a Test nevu tesztosztalyunk segitsegevel ellenorizzuk modellunk helyesseget.
     */


    //public static GUI g = new GUI();
   // public static Controller c;
    public static Game game = Game.getInstance();
    public static AsteroidBelt ab = AsteroidBelt.getInstance();
    public static boolean Randomize = false;

    public static ArrayList<Material> materials = new ArrayList<>();
    public static ArrayList<Asteroid> asteroids = new ArrayList<>();
    public static ArrayList<TeleportGate> teleportgates = new ArrayList<>();
    public static ArrayList<Settler> settlers=new ArrayList<>();
    public static ArrayList<Robot> robots=new ArrayList<>();
    public static ArrayList<UFO> ufos= new ArrayList<>();

    //region regexek
    String regex = "\\W*((?i)createmap(?-i))\\W*\\s(\\d+\\s){5}\\d+";
    public static String materialregex = "[013]|2\\s[012]";

    public static String lonely_empty_asteroid = "0\\s[01]\\s\\d{1,}\\s0";
    //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
    //pl    : 0 0 25 0

    public static String lonely_not_empty_asteroid = "0\\s[01]\\s\\d{1,}\\s1\\s\\d{1,}";
    //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
    //pl    : 0 0 25 1 5

    public static String not_lonely_not_empty_asteroid = "\\d\\s(\\d{1,},{0,1})*\\s[01]\\s\\d{1,}\\s1\\s\\d{1,}";
    //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
    //pl    : 2 5,2 1 5 1 1
    //hint  : egyelőre nem nézi a vesszőket a szomszédok között
    //        egyelőre nem nézi hogy annyi szomszéd index legyen beolvasva, mint amennyit megadtunk az első inputban
    public static   String not_lonely_empty = "\\d\\s(\\d{1,},{0,1})*\\s[01]\\s\\d{1,}\\s0";
    //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
    //pl    : 5 5,2,3,4,5 1 5 0
    //hint  : egyelőre nem nézi a vesszőket a szomszédok között
    //        egyelőre nem nézi hogy annyi szomszéd index legyen beolvasva, mint amennyit megadtunk az első inputban

    //itt tudtok regexel baszkodni : https://regex101.com/


    public static String tg_placed_regex = "\\d{1,}\\s1\\s\\d{1,}\\s[01]\\s[01]";

    public static String tg_notplaced_regex = "\\d{1,}\\s0\\s[01]\\s[01]";


    public static String settlerregex = "\\d\\s\\d\\s(\\d,)*\\d\\s[0123]\\s\\d|\\d\\s0\\s0";
    public static String settler_wom_and_wotg = "\\d{1,}\\s0\\s0";   //WithOutMaterial and WithOutTG
    public static String settler_wm_and_wotg = "\\d{1,}\\s\\d{1,}\\s(\\d{1,},{0,1})*\\s0";    //WithMateruak and WithOutTG
    public static String settler_wom_and_wtg = "\\d{1,}\\s0\\s[0123]\\s(\\d{1,},{0,1})*";    //WithOutMaterial and WithTG
    public static String settler_wm_and_wtg = "\\d{1,}\\s\\d{1,}\\s(\\d{1,},{0,1})*\\s[0123]\\s[1234]";     //WithMaterial and WithTG
    public static String robotregex = "\\d";
    public static String ufo_wm = "\\d\\s0|\\d\\s\\d\\s(\\d,)*\\d\n";
    public static String ufo_wom = "\\d{1,}\\s\\d{1,}\\s(\\d{1,},{0,1})*";

        //endregion regexek

    public static void main(String[] args)  {

        //ProtoTest pt = new ProtoTest();
       // pt.menu();
//        ArrayList<String> asd = new ArrayList<>();
//        asd = InputManager.GenerateOutput("1in");
//        for (int i=0;i<5;i++){
//            System.out.println("\n");
//        }
//        for(int i=0; i< asd.size();i++){
//            System.out.println(asd.get(i));
//        }

        //ProtoTest.menu();
        /*Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        Settler s1 = new Settler();
        Settler s2 = new Settler();
        Settler s3 = new Settler();
        Settler s4 = new Settler();
        asteroids.add(a1);
        asteroids.add(a2);
        settlers.add(s1);
        settlers.add(s2);
        settlers.add(s3);
        settlers.add(s4);


        GuiController gc= new GuiController(g);*/
        //g.DrawMenu();
        //c = new Controller();
        Asteroid a = new Asteroid();
        a.SetEmpty(false);
        SublimableMaterial sm = new SublimableMaterial();
        a.SetMaterial(sm);
        a.setLayer(4);
        a.SetSunProximityManual(true);


        Asteroid a2 = new Asteroid();
        a2.SetEmpty(false);
        SublimableMaterial sm2 = new SublimableMaterial();
        a.SetMaterial(sm2);
        a.setLayer(5);
        a.SetSunProximityManual(true);



        Asteroid a3 = new Asteroid();
        a3.SetEmpty(false);
        SublimableMaterial sm3 = new SublimableMaterial();
        a.SetMaterial(sm3);
        a.setLayer(2);
        a.SetSunProximityManual(true);

        a3.setNeighbour(a2);
        a3.setNeighbour(a);


        Settler s = new Settler();
        s.setAsteroid(a);
        a.setCharacter(s);

        Settler s2 = new Settler();
        s2.setAsteroid(a2);
        a2.setCharacter(s2);

        asteroids.add(a);
        asteroids.add(a2);
        asteroids.add(a3);

        settlers.add(s);
        settlers.add(s2);


        game.StartGame();

    }


}
