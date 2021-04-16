package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    public static ArrayList<Material> materials = new ArrayList<>();
    public static ArrayList<Asteroid> asteroids=new ArrayList<>();
    public static ArrayList<TeleportGate> teleportgates = new ArrayList<>();
    public static ArrayList<Settler> settlers=new ArrayList<>();
    public static ArrayList<Robot> robots=new ArrayList<>();
    public static ArrayList<UFO> ufos= new ArrayList<>();

    String regex = "\\W*((?i)createmap(?-i))\\W*\\s(\\d+\\s){5}\\d+";
    static String materialregex = "[013]|2\\s[012]";

    static String lonely_empty_asteroid ="0\\s[01]\\s\\d{1,}\\s0";
    //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
    //pl    : 0 0 25 0

    static String lonely_not_empty_asteroid ="0\\s[01]\\s\\d{1,}\\s1\\s\\d{1,}";
    //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
    //pl    : 0 0 25 1 5

    static String not_lonely_not_empty_asteroid ="\\d\\s(\\d{1,},{0,1})*\\s[01]\\s\\d{1,}\\s1\\s\\d{1,}";
    //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
    //pl    : 2 5,2 1 5 1 1
    //hint  : egyelőre nem nézi a vesszőket a szomszédok között
    //        egyelőre nem nézi hogy annyi szomszéd index legyen beolvasva, mint amennyit megadtunk az első inputban
    static String not_lonely_empty="\\d\\s(\\d{1,},{0,1})*\\s[01]\\s\\d{1,}\\s0";
    //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
    //pl    : 5 5,2,3,4,5 1 5 0
    //hint  : egyelőre nem nézi a vesszőket a szomszédok között
    //        egyelőre nem nézi hogy annyi szomszéd index legyen beolvasva, mint amennyit megadtunk az első inputban

    //itt tudtok regexel baszkodni : https://regex101.com/

    String tgregex ="\\d\\s[01]\\s\\d\\s[01]\\s[01]";
    String settlerregex ="\\d\\s\\d\\s(\\d,)*\\d\\s[0123]\\s\\d|\\d\\s0\\s0";
    String robotregex ="\\d";
    String uforegex ="\\d\\s0|\\d\\s\\d\\s(\\d,)*\\d\n";


    public static void main(String[] args)  {

        Scanner in = new Scanner(System.in);
        String[] input={};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String[] cmd={};
        String line;
        int[] ObjCounts = {0,0,0,0,0,0}; //Material, Asteroid, TG, Settler, Robot, Ufo
        try {

            line=reader.readLine();
            cmd=line.split(" ");
            for(int i=1; i<7;i++){
                ObjCounts[i-1]=Integer.parseInt(cmd[i]);
            }
            listaz(ObjCounts);
            //System.out.println(reader.readLine());


            for(int i=0; i<6;i++){
                for(int j=0; j<ObjCounts[i];j++){
                    String temp=reader.readLine();

                    System.out.println("apad"+temp);
                    letrehoz(i,temp);

                }
            }

            CommandManager cm = new CommandManager();
            //cm.listMaterials();
            cm.listAsteroids();

//
//            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Test t = new Test();
        //t.menu();               //teszt menu meghivasa



//        for(int i=0; i<Main.materials.size();i++){
//            System.out.println(Main.materials.get(i).name);
//        }



    }

    public static void letrehoz(int what, String params ){
        switch (what){
            case 0:
                if(Pattern.matches(materialregex,params)){
                    createMaterial(params);
                    System.out.println(Main.materials.get(0).name);
                    break;
                    }
            case 1:
                if(Pattern.matches(lonely_empty_asteroid,params)){
                    createAsteroid1(params);
                }else if(Pattern.matches(lonely_not_empty_asteroid,params)) {
                    createAsteroid2(params);
                }else if(Pattern.matches(not_lonely_empty,params)){
                    createAsteroid3(params);
                }else if(Pattern.matches(not_lonely_not_empty_asteroid,params)){
                    createAsteroid4(params);
                }
            case 2:
            case 3:
            case 4:
            case 5:
        }

    }

    public static void createMaterial(String params){
        String[] input = params.split("\t");
        Material normal = new Material();
        RadioactiveMaterial radmat = new RadioactiveMaterial();
        SublimableMaterial submat= new SublimableMaterial();
        NormalMaterialName normalMaterialName;
        RadioactiveMaterialName radioactiveMaterialName;
        SublimableMaterialName sublimableMaterialName;
        //todo regex ellenörzés
        switch (Integer.parseInt(input[0])){
            case 0:
                Main.materials.add(normal);
                normalMaterialName = NormalMaterialName.IRON;
                Main.materials.get(Main.materials.size()-1).setName(normalMaterialName);
                break;
            case 1:
                Main.materials.add(normal);
                normalMaterialName = NormalMaterialName.COAL;
                Main.materials.get(Main.materials.size()-1).setName(normalMaterialName);
                break;
            case 2:
                Main.materials.add(radmat);
                radioactiveMaterialName = RadioactiveMaterialName.URAN;
                Main.materials.get(Main.materials.size()-1).setName(RadioactiveMaterialName.URAN);
                //Main.materials.get(Main.materials.size()-1).setExposure(input[1]);
                  //TODO itt kell még a params paraméterből kiolvasni az exposure értéket
                break;
            case 3:
                Main.materials.add(submat);
                sublimableMaterialName = SublimableMaterialName.ICEWATER;
                Main.materials.get(Main.materials.size()-1).setName(sublimableMaterialName);
                break;
        }
    }

    public static void createAsteroid1(String params){        //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
                                                              //pl    : 0 0 25 0
        String[] cmd = params.split(" ");
        Main.asteroids.add(new Asteroid());
        Asteroid a =Main.asteroids.get(Main.asteroids.size()-1);
        Main.setCommonAsteroid(a,Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));

    }
    public static void createAsteroid2(String params){         //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
                                                                //pl    : 0 0 25 1 5
        String[] cmd = params.split(" ");
        Main.asteroids.add(new Asteroid());
        Asteroid a =Main.asteroids.get(Main.asteroids.size()-1);
        Main.setCommonAsteroid(a,Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
        a.AddMaterial(Main.materials.get(Integer.parseInt(cmd[4])-1));

    }
    public static void createAsteroid3(String params){         //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
                                                               //pl    : 2 5,2 1 5 1 1

    }
    public static void createAsteroid4(String params){          //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
                                                                 //pl    : 5 5,2,3,4,5 1 5 0

    }

    public static void setCommonAsteroid(Asteroid a, int sunprox, int layer, int empty){
        //a.SetSunProximity(sunprox);
        a.setLayer(layer);
        //a.SetEmpty
    }









    public static void listaz(int[]anyad){
        for(int i=0; i<anyad.length;i++){
            System.out.println( anyad[i]);
        }

    }
    public static int osszead(int[]anyad){
        int sum=0;
        for(int i=0;i<anyad.length;i++){
            sum+=anyad[i];
        }
        return sum;
    }

    public  void InitWhat(int what, String string){
        switch (what){
            case 1 : //material
                break;
            case 4:
                settlers.add(new Settler());


        }
    }

    //anyad
    //neked is





}
