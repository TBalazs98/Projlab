package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;

public class CommandManager {

    private static boolean stop = true;
    public CommandManager(){}

    public static void command(String command){

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
                listMaterials();
                break;
            }
            case "listteleportgates":{
                listTeleportGates();
                break;
            }
            case "listcharacters":{
                listCharacters();
                break;
            }
            case "listasteroids":{
                listAsteroids();
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

    public static boolean IsRunning(){
        return stop;
    }

    public static void drill(String character){

        if(character.length() >= 2) {
            character = character.toUpperCase();
            try{
                int index = Integer.parseInt(character.substring(1));
                if (character.contains("S")) {
                    if ((index >= 0) && (index < Main.settlers.size()))
                        Main.settlers.get(index).Drill();
                } else if (character.contains("R")) {
                    if ((index >= 0) && (index < Main.robots.size()))
                        Main.robots.get(index).Drill();
                } else
                    System.out.println("Invalid index number!");
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        } else
            System.out.println("Invalid input!");
    }

    public static  void mine (String character){
        if(character.length() >= 2) {
            character = character.toUpperCase();
            try{
                int index = Integer.parseInt(character.substring(1));
                if (character.contains("S")) {
                    if ((index >= 0) && (index < Main.settlers.size()))
                        Main.settlers.get(index).Mine();
                } else if (character.contains("U")) {
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
    public static void build (String[] cmd){
        //ellenőrizni a tömb méretét
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            if( cmd[1].charAt(0) == 'S') {
                if ((index <= 0) && (index < Main.settlers.size())) {
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
    public static void put (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            int indexMaterial = Integer.parseInt(cmd[2].substring(1));
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

    public static void placegate (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            int indexGate = Integer.parseInt(cmd[2].substring(1));
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
                    if ((index >= 0) && (index < Main.settlers.size()))
                        if (Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.asteroids.get(indexDObject)) != -1) {
                            if ((indexDObject >= 0) && (indexDObject < Main.teleportgates.size()) && (towhere=='G'))
                                Main.settlers.get(index).Move(Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.teleportgates.get(indexDObject)));
                            if ((indexDObject >= 0) && (indexDObject < Main.asteroids.size()) && (towhere == 'A'))
                                Main.settlers.get(index).Move(Main.settlers.get(index).getAsteroid().GetNeighbourIndex(Main.asteroids.get(indexDObject)));
                        }
                }
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }
    public static void listMaterials (){
        int i = 0;
        if (Main.materials.size() == 0)
            System.out.println("No Material!");
        else {
            for(Material m : Main.materials){
                i++;
                System.out.print("M" + i + " " + m.name);
                if(m.name == RadioactiveMaterialName.URAN) {
                    RadioactiveMaterial r = (RadioactiveMaterial) m;
                    System.out.print(" " + r.exposed);
                }
                System.out.println();
            }
        }
    }

    public static void listAsteroids (){
        int i = 0;
        int mat = 0;
        if(Main.asteroids.size() == 0)
            System.out.println("No Asteroids!");
        else{
            for (Asteroid a : Main.asteroids){
                i++;
                if(!a.GetisEmpty())
                    mat = (Main.materials.indexOf(a.getMaterial()) + 1);
                System.out.print("A" + i + " " + a.getLayers() + " " + a.GetSunProximity() + " " + a.GetisEmpty() + " " +
                        (a.GetisEmpty()?"null ":("M" +mat + " ")));
                ArrayList<String> neigh = new ArrayList<>();
                for (DestinationObject o : a.GetNeighbours()) {
                    if(Main.asteroids.indexOf(o) != -1)
                    neigh.add("A" + (Main.asteroids.indexOf(o) + 1));
                    if(Main.teleportgates.indexOf(o) != -1)
                    neigh.add("G" + (Main.teleportgates.indexOf(o) + 1));
                }
                System.out.println(String.join(",",neigh));
            }
        }

    }
    public static void listTeleportGates (){
        int i = 0;
        int pairid = 0;
        int ast = 0;
            if (Main.teleportgates.size() == 0)
                System.out.println("No TeleportGates!");
            else {
                for (TeleportGate g : Main.teleportgates) {
                    i++;
                    if (g.GetAsteroid() != null) {
                        ast = (Main.asteroids.indexOf(g.GetAsteroid()) + 1);
                    }
                    if (g.GetPair() != null) {
                        pairid = Main.teleportgates.indexOf(g.GetPair()) + 1;
                    }
                    System.out.println("G" + i + " " + g.GetisActive() + " " + (pairid != 0 ? ("G" + pairid) : "") + " " + (ast != 0 ? ("A" + ast) : ""));
                }
            }

    }
    public static void listCharacters (){
        if(Main.robots.size() == 0 && Main.settlers.size() == 0 && Main.ufos.size() == 0){
            System.out.println("No Characters!");
        }
        int i = 0;
        for(Settler s : Main.settlers){
            i++;
            int aindex = Main.asteroids.indexOf((s.getAsteroid())) + 1 ;
            System.out.print("S" + i + " " + "A" + aindex);
            if(s.GetInventory().Size() != 0) {
                System.out.println(s.GetInventory().Size() + " ");
                ArrayList<String> smat = new ArrayList<>();
                for (Material m : s.GetInventory().GetMaterials())
                    smat.add("M" + (Main.materials.indexOf(m) + 1));
                System.out.print((String.join(",", smat)));
            }
            else {
                System.out.print(" null ");
            }
            if(s.GetGates().size() != 0){
                ArrayList<String> sgat = new ArrayList<>();
                for (TeleportGate t : Main.teleportgates)
                    sgat.add("G" + (Main.teleportgates.indexOf(t) + 1));
                System.out.print((String.join(",", sgat)));
            }
            System.out.println("");
        }
        i = 0;
        for(Robot r : Main.robots){
            i++;
            int aindex = Main.asteroids.indexOf((r.getAsteroid())) + 1 ;
            System.out.println("R" + i + " " + "A" + aindex);
        }
        i = 0;
        for(UFO u : Main.ufos){
            i++;
            int aindex = Main.asteroids.indexOf((u.getAsteroid())) + 1 ;
            System.out.print("U" + i + " " + "A" + aindex);
            Material[] mat =  u.GetInventory().GetMaterials();
            if(u.GetInventory().Size() != 0) {
                ArrayList<String> umat = new ArrayList<>();
                for (Material m : u.GetInventory().GetMaterials())
                    umat.add(Integer.toString((Main.materials.indexOf(m) + 1)));
                System.out.println((String.join(",", umat)));
            }
        }

    }
    public static void SunStorm (String[] cmd){
        //ellenőrizni a tömb méretét
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            if( cmd[1].charAt(0) == 'A') {
                if ((index >= 0) && (index < Main.asteroids.size())) {
                    switch (cmd[2]){
                        case "ON" : {
                            Main.ab.StartStorm(Main.asteroids.get(index));
                            break;
                        }
                        case "OFF" : {

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
    public static void setProximity(String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            if( cmd[1].charAt(0) == 'A') {
                if ((index >= 0) && (index < Main.asteroids.size())) {
                    switch (cmd[2]) {
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

    public static void startGame(){
        Main.game.StartGame();
    }

    public static void endGame (){
        Main.game.LoseGame();
    }

    public static void loadmap(String filename){    }

    public static void saveMap (String filename){
        int M = Main.materials.size();
        int A = Main.asteroids.size();
        int T = Main.teleportgates.size();
        int S = Main.settlers.size();
        int R = Main.robots.size();
        int U = Main.ufos.size();

        FileWriter fw = null;
        try {
            fw = new FileWriter("Files/Saved/" + filename + ".txt");
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
                pw.println("2   " + r.exposed);
            } else if (m.name.equals(SublimableMaterialName.ICEWATER)) {
                pw.println("3");
            }

        for(Asteroid a : Main.asteroids){
            pw.print(a.GetNeighbourCount() + "  ");
            if(a.GetNeighbourCount() != 0) {
                ArrayList<String> neig = new ArrayList<>();
                for (DestinationObject n : a.GetNeighbours())
                    neig.add(Integer.toString((Main.asteroids.indexOf(n) + 1)));

                pw.print(String.join(",",neig));
            }
            if(a.GetSunProximity())
                pw.print("  " + 1);
            else
                pw.print("  "+ 0);
            pw.print("  " + a.getLayers());
            if(a.GetisEmpty())
                pw.println("  " + 0);
            else{
                pw.print("  " + 1);
                pw.println((Main.materials.indexOf(a.getMaterial()) + 1));
            }

        }

        for(TeleportGate t : Main.teleportgates){
            if(t.GetPair() != null)
                pw.print((Main.teleportgates.indexOf(t.GetPair())+1) + "  ");
            else
                pw.print("null  ");

            if(t.GetisPlaced())
                pw.print(1 + "  " + (Main.asteroids.indexOf(t.GetAsteroid()) + 1) + "   ");
            else
                pw.print(0 + "  ");

            if (t.GetisActive())
                pw.print(1 + "  ");
            else
                pw.print(0 + "  ");

            if(t.GetIsHit())
                pw.println(1);
            else
                pw.println(0);
        }

        for(Settler s : Main.settlers){
            pw.print((Main.asteroids.indexOf(s.getAsteroid()) + 1) + "  ");
            pw.print(s.GetInventory().Size() + " ");
            if(s.GetInventory().Size() != 0) {
                ArrayList<String> mat = new ArrayList<>();
                for (Material m : s.GetInventory().GetMaterials())
                    mat.add(Integer.toString((Main.materials.indexOf(m) + 1)));

                pw.print(String.join(",",mat));
            }
            pw.print(s.GetGates().size());
            if(s.GetGates().size() != 0) {
                ArrayList<String> gat = new ArrayList<>();
                for (TeleportGate t : s.GetGates())
                    gat.add(Integer.toString((Main.teleportgates.indexOf(t))+ 1));
                pw.println(String.join(",",gat));

            }
        }

        for(Robot r : Main.robots){
            pw.println(Main.asteroids.indexOf(r.getAsteroid())+1);
        }

        for(UFO u : Main.ufos){
            pw.print((Main.asteroids.indexOf(u.getAsteroid()) + 1) + "  ");
            if(u.GetInventory().Size() != 0) {
                ArrayList<String> mat = new ArrayList<>();
                for (Material m : u.GetInventory().GetMaterials())
                    mat.add(Integer.toString((Main.materials.indexOf(m) + 1)));

                pw.print(String.join(",", mat));
            }
        }

        pw.close();
    }


}
