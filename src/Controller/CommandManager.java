package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;

/**
 *A bemeneti nyelv parancsait kezeli amit a felhasznalo begepelhet
 */
public class CommandManager {

    /**
     *Kilepesi feltelet kezeljuk vele
     */
    private static boolean stop = true;

    /**
    *Konstruktor
     */
    public CommandManager(){}

    /**
     * A parancsokat kezeljuk vele, a megadott bemenet alapjan meghivaj a megfelelo fuggvenyt
     * @param command   bemeneti parancs
     * @param generateoutput    ha iagaz az erteke, elemnti a kimenetet egy Stringbe
     */
    public static void command(String command, boolean generateoutput){

        String[] cmd = command.split(" ");

        cmd[0] = cmd[0].toLowerCase();

        switch (cmd[0]){
            case "drill":{
                drill(cmd[1]);
                break;
            }
            case "mine":{
                mine(cmd[1]);
                break;
            }
            case "build":{
                build(cmd);
                break;
            }
            case "put":{
                put(cmd);
                break;
            }
            case "placegate":{
                placegate(cmd);
                break;
            }
            case "move":{
                move(cmd);
                break;
            }
            case "listmaterials":{
                listMaterials(generateoutput);
                break;
            }
            case "listteleportgates":{
                listTeleportGates(generateoutput);
                break;
            }
            case "listcharacters":{
                listCharacters(generateoutput);
                break;
            }
            case "listasteroids":{
                listAsteroids(generateoutput);
                break;
            }
            case "sunstorm":{
                SunStorm(cmd);
                break;
            }
            case "setproximity":{
                setProximity(cmd);
                break;
            }
            case "step":{
                step();
                break;
            }
            case "setrandomize":{
                setRandomize(cmd[1]);
                break;
            }
            case "startgame":{
                startGame();
                break;
            }
            case "endgame":{
                endGame();
                break;
            }
            case "loadmap":{
                loadmap(cmd[1]);
                break;
            }
            case "savemap":{
                saveMap(cmd[1]);
                break;
            }
            case "stop":{
                stop = false;
                break;
            }
            default:
                System.out.println("Invalid command!");
        }

    }

    /**
     * Visszaadja hogy fut-e meg a beolvasas vagy sem, stop parancsig olvas
     * @return visszaadja a stop erteket
     */
    public static boolean IsRunning(){
        return stop;
    }

    /**
     * Beallitja nullara az eletben levo settlerek szamat
     */
    public static void Reset(){
        stop = true;
        Main.ab.settlersAlive = 0;
    }

    /**
     * A Drill parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonosito ervenyes
     * @param character bemeneti nyelvrol olvasott karakter azonositoja
     */
    public static void drill(String character){

        character = character.toUpperCase();
        try{
            int index = Integer.parseInt(character.substring(1)) - 1;
            if (character.charAt(0) == 'S') {
                if ((index >= 0) && (index < Main.settlers.size())) {
                    Main.settlers.get(index).Drill();
                }
            }
            if (character.charAt(0) == 'R') {
                if ((index >= 0) && (index < Main.robots.size()))
                    Main.robots.get(index).Drill();
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * A Mine parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonosito ervenyes
     * @param character bemeneti nyelvrol olvasott karakter azonositoja
     */
    public static  void mine (String character){
        if(character.length() >= 2) {
            character = character.toUpperCase();
            try{
                int index = Integer.parseInt(character.substring(1)) - 1;
                if (character.charAt(0) == 'S') {
                    if ((index >= 0) && (index < Main.settlers.size()))
                        Main.settlers.get(index).Mine();
                } else if (character.charAt(0) == 'U') {
                    if ((index >= 0) && (index < Main.ufos.size()))
                        Main.ufos.get(index).Mine();
                } else
                    System.out.println("Invalid index number!");
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        } else
            System.out.println("Invalid input!");
    }

    /**
     * A Build parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonositok ervenyesek
     * @param cmd bemeneti nyelvrol olvasott karakter azonositoja illetve az epiteni kivant dolog
     */
    public static void build (String[] cmd){
        //ellenőrizni a tömb méretét
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1))-1;
            if( cmd[1].charAt(0) == 'S') {
                if ((index >= 0) && (index < Main.settlers.size())) {
                    switch (cmd[2]) {
                        case "ROBOT": {
                            Main.settlers.get(index).BuildRobot();
                            break;
                        }
                        case "TELEPORTGATE": {
                            Main.settlers.get(index).BuildGate();
                            break;
                        }
                        case "BASE": {
                            Main.settlers.get(index).BuildBase();
                            break;
                        }
                    }
                }
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * A PlaceMaterial parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonositok ervenyesek
     * @param cmd bemeneti nyelvrol olvasott karakter azonositoja illetve a visszahelyezni kivant nyersanyag azonositoja
     */
    public static void put (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1))-1;
            int indexMaterial = Integer.parseInt(cmd[2].substring(1))-1;
            if ((cmd[1].charAt(0) == 'S') && (cmd[2].charAt(0) == 'M')) {
                if ((index >= 0) && (index < Main.settlers.size()))
                    if ((indexMaterial >= 0) && (indexMaterial < Main.materials.size()))
                        Main.settlers.get(index).PlaceMaterial(Main.materials.get(indexMaterial));
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * A PlaceGate parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonositok ervenyesek
     * @param cmd bemeneti nyelvrol olvasott karakter azonositoja illetve a lehelyezni kivant teleportkapu azonositoja
     */
    public static void placegate (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1))-1;
            int indexGate = Integer.parseInt(cmd[2].substring(1))-1;
            if ((cmd[1].charAt(0) == 'S') && (cmd[2].charAt(0) == 'G')) {
                if ((index >= 0) && (index < Main.settlers.size()))
                    if ((indexGate >= 0) && (indexGate < Main.teleportgates.size()))
                        Main.settlers.get(index).PlaceGate(Main.teleportgates.get(indexGate));
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * A Move parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonositok ervenyesek
     * @param cmd bemeneti nyelvrol olvasott karakter azonositoja illetve annak a DestinationObjectnek az azonositoja ahova utaznank
     */
    public static void move (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();
        char towhere = cmd[2].charAt(0);

        try {
            int index = Integer.parseInt(cmd[1].substring(1)) -1;
            int indexDObject = Integer.parseInt(cmd[2].substring(1)) -1;
            if ((cmd[1].charAt(0) == 'S') && ((cmd[2].charAt(0) == 'A') || (cmd[2].charAt(0) == 'G')) ) {
                if(((towhere == 'G') && (indexDObject > Main.teleportgates.size())) || ((towhere == 'A') && (indexDObject > Main.asteroids.size())))
                    System.out.println("Invalid index number!");
                else {
                    try {
                        if ((index >= 0) && (index < Main.settlers.size())) {
                            if (Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.asteroids.get(indexDObject)) != -1) {
                                if ((indexDObject >= 0) && (indexDObject < Main.asteroids.size()) && (towhere == 'A'))
                                    Main.settlers.get(index).Move(Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.asteroids.get(indexDObject)));
                            }
                            if (Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.teleportgates.get(indexDObject)) != -1) {

                                if ((indexDObject >= 0) && (indexDObject < Main.teleportgates.size()) && (towhere == 'G'))
                                    Main.settlers.get(index).Move(Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.teleportgates.get(indexDObject)));

                            }
                        }
                    } catch (IndexOutOfBoundsException e) {}
                }
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * Kilistazza a nyersanyagokat
     * @param generateoutput ha iagaz az erteke, elemnti a kimenetet egy Stringbe
     */
    public static void listMaterials (boolean generateoutput){

        int i = 0;
        if (Main.materials.size() == 0) {
//            System.out.println("No Material!");
//            InputManager.write_to_output(generateoutput,"No Material!");
        }
        else {
            for(Material m : Main.materials){
                i++;
                System.out.print("M" + i + "\t" + m.name);
                if(m.name == RadioactiveMaterialName.URAN) {
                    RadioactiveMaterial r = (RadioactiveMaterial) m;
                    System.out.print("\t" + r.exposed);
                    InputManager.write_to_output(generateoutput,"M" + i + "\t" + m.name + "\t" + r.exposed);
                } else {
                    InputManager.write_to_output(generateoutput,"M" + i + "\t" + m.name);
                }

                System.out.println();
            }
        }
    }

    /**
     * Kilistazza az aszteroidakat
     * @param generateoutput ha iagaz az erteke, elemnti a kimenetet egy Stringbe
     */
    public static void listAsteroids (boolean generateoutput){
        int i = 0;
        int mat = 0;
        if(Main.asteroids.size() == 0) {
//            System.out.println("No Asteroids!");
//            InputManager.write_to_output(generateoutput,"No Asteroids!");
        }
        else{
            for (Asteroid a : Main.asteroids){
                if(a == null) {
                    i++;
                }
                else if(a != null) {
                    i++;
                    if (!a.GetisEmpty())
                        mat = (Main.materials.indexOf(a.getMaterial()) + 1);
                    System.out.print("A" + i + "\t" + a.getLayers() + "\t" + a.GetSunProximity() + "\t" + a.GetisEmpty() + "\t" + (a.GetisEmpty() ? "null\t" : ("M" + mat + "\t")));


                    ArrayList<String> neigh = new ArrayList<>();
                    for (DestinationObject o : a.GetNeighbours()) {
                        if (Main.asteroids.indexOf(o) != -1)
                            neigh.add("A" + (Main.asteroids.indexOf(o) + 1));
                        if (Main.teleportgates.indexOf(o) != -1)
                            neigh.add("G" + (Main.teleportgates.indexOf(o) + 1));
                    }
                    System.out.println(String.join(",", neigh));
                    String asdasd = String.join(",", neigh);
                    InputManager.write_to_output(generateoutput, "A" + i + "\t" + a.getLayers() + "\t" + a.GetSunProximity() + "\t" + a.GetisEmpty() + (a.GetisEmpty() ? "\tnull" : ("\tM" + mat))/*+(a.GetisEmpty()?"":("\t" + asdasd))*/);
                    //TODO EZ MIEZ Bakonyi
                }
            }
        }

    }

    /**
     * Kilistazza a teleportkapukat
     * @param generateoutput ha iagaz az erteke, elemnti a kimenetet egy Stringbe
     */
    public static void listTeleportGates (boolean generateoutput){
        int i = 0;
        int pairid = 0;
        int ast = 0;
            if (Main.teleportgates.size() == 0) {
//                System.out.println("No TeleportGates!");
//                InputManager.write_to_output(generateoutput,"No Teleportgates!");
            }
            else {
                for (TeleportGate g : Main.teleportgates) {
                    i++;
                    if (g.GetAsteroid() != null) {
                        ast = (Main.asteroids.indexOf(g.GetAsteroid()) + 1);
                    } else {
                        ast = 0;
                    }
                    if (g.GetPair() != null) {
                        pairid = Main.teleportgates.indexOf(g.GetPair()) + 1;
                    }
                    System.out.println("G" + i + "\t" + g.GetisActive() + "\t" + (pairid != 0 ? ("G" + pairid) : "") + (ast != 0 ? ("\tA" + ast) : "\tnull"));
                    InputManager.write_to_output(generateoutput,"G" + i + "\t" + g.GetisActive() + (pairid != 0 ? ("\tG" + pairid) : "") + (ast != 0 ? ("\tA" + ast) : "\tnull"));
                }
            }

    }

    /**
     * Kilistazza a karakterket
     * @param generateoutput ha iagaz az erteke, elemnti a kimenetet egy Stringbe
     */
    public static void listCharacters (boolean generateoutput){
        if(Main.robots.size() == 0 && Main.settlers.size() == 0 && Main.ufos.size() == 0){
//            System.out.println("No Characters!");
//            InputManager.write_to_output(generateoutput,"No Characters!");
        }

        /**
         * Telepesek kilistazasa
         */
        int i = 0;
        for(Settler s : Main.settlers){
            if(s == null) {
                i++;
            }
            else if(s != null) {
                String setler = "";
                i++;
                int aindex = Main.asteroids.indexOf((s.getAsteroid())) + 1;
                System.out.print("S" + i + "\t" + "A" + aindex + "\t");
                setler += ("S" + i + "\t" + "A" + aindex + "\t");
                if (s.GetInventory().Size() != 0) {
                    System.out.print(s.GetInventory().Size() + " ");
                    ArrayList<String> smat = new ArrayList<>();
                    for (Material m : s.GetInventory().GetMaterials())
                        smat.add("M" + (Main.materials.indexOf(m) + 1));
                    System.out.print((String.join(",", smat)));
                    setler += ((String.join(",", smat)));
                } else {
                    System.out.print("null");
                    setler += "null";
                }
                if (s.GetGates().size() != 0) {
                    ArrayList<String> sgat = new ArrayList<>();
                    for (TeleportGate t : s.GetGates())
                        sgat.add("G" + (Main.teleportgates.indexOf(t) + 1));
                    System.out.print((String.join(",", sgat)));
                    setler += ("\t" + (String.join(",", sgat)));
                }
                System.out.println("");
                InputManager.write_to_output(generateoutput, setler);
            }
        }

        /**
         * Robotok kilistazasa
         */


        i = 0;
        for(Robot r : Main.robots){
            i++;
            String robot = "";
            int aindex = Main.asteroids.indexOf((r.getAsteroid())) + 1 ;
            System.out.println("R" + i + "\t" + "A" + aindex);
            InputManager.write_to_output(generateoutput,("R" + i + "\t" + "A" + aindex));
        }

        /**
         * Ufok kilistazasa
         */



        i = 0;
        for(UFO u : Main.ufos){
            i++;
            String ufo = "";
            int aindex = Main.asteroids.indexOf((u.getAsteroid())) + 1 ;
            System.out.print("U" + i + "\t" + "A" + aindex/* + "\t"*/);
            ufo+=("U" + i + "\t" + "A" + aindex/* + " "*/);
            ArrayList<Material> mat =  u.GetInventory().GetMaterials();
            if(u.GetInventory().Size() != 0) {
                ArrayList<String> umat = new ArrayList<>();
                for (Material m : u.GetInventory().GetMaterials())
                    umat.add(Integer.toString((Main.materials.indexOf(m) + 1)));
                System.out.println((String.join(",", umat)));
                ufo+=("\t" + String.join(",", umat));
            }
            System.out.println();
            InputManager.write_to_output(generateoutput,ufo);
        }

    }

    /**
     * A SunStorm parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonosito ervenyes
     * @param cmd aszteroida azonositoja amin elinditjuk a napvihart
     */
    public static void SunStorm (String[] cmd){
        //ellenőrizni a tömb méretét
        cmd[1] = cmd[1].toUpperCase();
        try {
            int index = Integer.parseInt(cmd[1].substring(1)) - 1;
            if( cmd[1].charAt(0) == 'A') {
                if ((index >= 0) && (index < Main.asteroids.size())) {
                    Main.ab.StartStorm(Main.asteroids.get(index));
                }
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }


    }

    /**
     * A SetSunProximityManul parancsot hajtja vegre, ha a bemeneti nyelvben megirt azonosito ervenyes
     * @param cmd a beallitani kivant ertek és az aszteroida azonositoja
     */
    public static void setProximity(String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[2].substring(1)) - 1;
            if( cmd[2].charAt(0) == 'A') {
                if ((index >= 0) && (index < Main.asteroids.size())) {
                    switch (cmd[1]) {
                        case "NEAR": {
                            Main.asteroids.get(index).SetSunProximityManual(true);
                            break;
                        }
                        case "FAR": {
                            Main.asteroids.get(index).SetSunProximityManual(false);
                            break;
                        }
                        default:
                            System.out.println("Invalid command!");
                    }
                }
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }

    }

    /**
     * A Step parancsot hajtja vegre, ami lepteti a leptetheto dolgokat
     */
    public static void step (){
      /*
        for(Asteroid a : Main.asteroids)
            a.Step();
        for(Robot r : Main.robots)
            r.Step();
        for(UFO u : Main.ufos)
            u.Step();
        for(TeleportGate tp : Main.teleportgates)
            tp.Step();
        */
        Main.game.NextRound();

    }

    /**
     * A determinisztikussagot kezeli
     * @param cmd a bemenetrol beolvasott ertek
     */
    public static void setRandomize(String cmd){
        //globális vált
        cmd = cmd.toUpperCase();
        switch (cmd){
            case "ON":{
                Main.Randomize = true;
                break;
            }
            case "OFF" : {
                Main.Randomize = false;
                break;
            }
            default:
                System.out.println("Invalid input");
        }

    }

    /**
     * A jatekot inditja el
     */
    public static void startGame(){
        Main.game.StartGame();
    }

    /**
     * Befejezi a jatekot mentes nelkul
     */
    public static void endGame (){
        Main.game.LoseGame();
    }

    /**
     * Terke betoltese fajbol
     * @param filename fajlnev
     *
     * **** DO NOT USE IT PLEASE :c ****
     */
    public static void loadmap(String filename){
//        try {
//           // File currentfile = InputManager.getFile("Files", "Saved", filename);
//
//        }catch (IOException e){}
    }

    /**
     * Elmenti a jatekallast a megadott fajlba
     * @param filename a bemenetrol beolvasott fajlnev
     */
    public static void saveMap(String filename){
        int M = Main.materials.size();
        int A = Main.asteroids.size();
        int T = Main.teleportgates.size();
        int S = Main.settlers.size();
        int R = Main.robots.size();
        int U = Main.ufos.size();

        FileWriter fw = null;
        try {
            File currentfile = InputManager.getFile("Files","Saved",filename);
            fw = new FileWriter(currentfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println("createmap " + M + " " + A + " " + T + " " + S + " " + R + " " + U);

        for(Material m : Main.materials)
            if (m.name.equals(NormalMaterialName.IRON)) {
                pw.println("0");
            } else if (m.name.equals(NormalMaterialName.COAL)) {
                pw.println("1");
            } else if (m.name.equals(RadioactiveMaterialName.URAN)) {
                RadioactiveMaterial r = (RadioactiveMaterial) m;
                pw.println("2\t" + r.exposed);
            } else if (m.name.equals(SublimableMaterialName.ICEWATER)) {
                pw.println("3");
            }

        for(Asteroid a : Main.asteroids){
            pw.print(a.GetNeighbourCount() + "\t");
            if(a.GetNeighbourCount() != 0) {
                ArrayList<String> neigh = new ArrayList<>();
                for (DestinationObject o : a.GetNeighbours()) {
                    if (Main.asteroids.contains(o))             //Main.asteroids.indexOf(o) != -1
                        neigh.add(""+(Main.asteroids.indexOf(o) + 1));
                    if (Main.teleportgates.contains(o))          //Main.teleportgates.indexOf(o) != -1
                        neigh.add(""+(Main.teleportgates.indexOf(o) + 1));
                }
                pw.print(String.join(",",neigh));
                pw.print("\t");
            }
            if(a.GetSunProximity())
                pw.print(1);
            else
                pw.print(0);
            pw.print("\t" + a.getLayers());
            if(a.GetisEmpty())
                pw.println("\t" + 0);
            else{
                pw.print("\t" + 1 + "\t");
                pw.println((Main.materials.indexOf(a.getMaterial()) + 1));
            }

        }

        for(TeleportGate t : Main.teleportgates){
            if(t.GetPair() != null)
                pw.print((Main.teleportgates.indexOf(t.GetPair())+1) + "\t");
            else
                pw.print("null\t");

            if(t.GetisPlaced())
                pw.print(1 + "\t" + (Main.asteroids.indexOf(t.GetAsteroid()) + 1) + "\t");
            else
                pw.print(0 + "\t");

            if (t.GetisActive())
                pw.print(1 + "\t");
            else
                pw.print(0 + "\t");

            if(t.GetIsHit())
                pw.println(1);
            else
                pw.println(0);
        }

        for(Settler s : Main.settlers){
            pw.print((Main.asteroids.indexOf(s.getAsteroid()) + 1) + "\t");
            pw.print(s.GetInventory().Size() + "\t");
            if(s.GetInventory().Size() != 0) {
                ArrayList<String> mat = new ArrayList<>();
                for (Material m : s.GetInventory().GetMaterials())
                    mat.add(Integer.toString((Main.materials.indexOf(m) + 1)));

                pw.print(String.join(",",mat));
                pw.print("\t");
            }

            pw.print(s.GetGates().size());
            if(s.GetGates().size() != 0) {
                pw.print("\t");
                ArrayList<String> gat = new ArrayList<>();
                for (TeleportGate t : s.GetGates())
                    gat.add(Integer.toString((Main.teleportgates.indexOf(t))+ 1));
                pw.print(String.join(",",gat));

            }
            System.out.println("");
            pw.print("\n");
        }

        for(Robot r : Main.robots){
            pw.println(Main.asteroids.indexOf(r.getAsteroid())+1);
        }

        for(UFO u : Main.ufos){
            pw.print((Main.asteroids.indexOf(u.getAsteroid()) + 1));
            if(u.GetInventory().Size() != 0) {
                pw.print("\t");
                ArrayList<String> mat = new ArrayList<>();
                for (Material m : u.GetInventory().GetMaterials())
                    mat.add(Integer.toString((Main.materials.indexOf(m) + 1)));

                pw.print(String.join(",", mat));
            }
            pw.print("\n");
        }
        pw.println();
        pw.println("setRandomize " +((Main.Randomize)?"on":"off"));
        pw.println("stop");
        pw.close();
    }

}