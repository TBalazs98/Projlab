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

        game.StartGame();

    }

}
