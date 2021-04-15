package Model;

public class CommandManager {

    public CommandManager(){}

    void command(String command){

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
            default:
                System.out.println("Invalid command!");
        }

    }

    void drill(String character){

        if(character.length() >= 2) {
            character = character.toUpperCase();
            try{
                int index = Integer.parseInt(character.substring(1));
                if (character.contains("S")) {
                    if ((index <= 0) && (index < Main.settlers.size()))
                        Main.settlers.get(index).Drill();
                } else if (character.contains("R")) {
                    if ((index <= 0) && (index < Main.robots.size()))
                        Main.robots.get(index).Drill();
                } else
                    System.out.println("Invalid index number!");
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        } else
            System.out.println("Invalid input!");
    }

    void mine (String character){
        if(character.length() >= 2) {
            character = character.toUpperCase();
            try{
                int index = Integer.parseInt(character.substring(1));
                if (character.contains("S")) {
                    if ((index <= 0) && (index < Main.settlers.size()))
                        Main.settlers.get(index).Mine();
                } else if (character.contains("U")) {
                    if ((index <= 0) && (index < Main.ufos.size()))
                        Main.ufos.get(index).Mine();
                } else
                    System.out.println("Invalid index number!");
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        } else
            System.out.println("Invalid input!");
    }
    void build (String[] cmd){
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
    void put (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            int indexMaterial = Integer.parseInt(cmd[2].substring(1));
            if ((cmd[1].charAt(0) == 'S') && (cmd[2].charAt(0) == 'M')) {
                if ((index <= 0) && (index < Main.settlers.size()))
                    if ((indexMaterial <= 0) && (indexMaterial < Main.materials.size()))
                        Main.settlers.get(index).PlaceMaterial(Main.materials.get(indexMaterial));
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    void placegate (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            int indexGate = Integer.parseInt(cmd[2].substring(1));
            if ((cmd[1].charAt(0) == 'S') && (cmd[2].charAt(0) == 'G')) {
                if ((index <= 0) && (index < Main.settlers.size()))
                    if ((indexGate <= 0) && (indexGate < Main.teleportgates.size()))
                        Main.settlers.get(index).PlaceGate(Main.teleportgates.get(indexGate));
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }

    void move (String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            int indexDObject = Integer.parseInt(cmd[2].substring(1));
            if ((cmd[1].charAt(0) == 'S') && ((cmd[2].charAt(0) == 'A') || (cmd[2].charAt(0) == 'G')) ) {
                if ((index <= 0) && (index < Main.settlers.size()))
                    if ((indexDObject <= 0) && (indexDObject < Main.teleportgates.size()))
                        Main.settlers.get(index).PlaceGate(Main.teleportgates.get(indexDObject));
            }
            else
                System.out.println("Invalid input!");
        }  catch (NumberFormatException e){
            System.out.println("Invalid input!");
        }
    }
    void listMaterials (){
        int i = 0;
        if (Main.materials.size() == 0)
            System.out.println("No Material!");
        else {
            for(Material m : Main.materials){
                i++;
                System.out.println("M" + i + " " + m.getName());
                if(m.getName() == RadioactiveMaterialName.URAN) {
                    RadioactiveMaterial r = (RadioactiveMaterial) Main.materials.get(i);
                    System.out.print(" " + r.exposed);
                }
            }
        }
    }

    void listAsteroids (){
        int i = 0;
        if(Main.asteroids.size() == 0)
            System.out.println("No Asteroids!");
        else{
            for (Asteroid a : Main.asteroids){
                i++;
                System.out.println("A" + i + " " + a.getLayers() + " " + a.GetSunProximity() + " " + a.GetisEmpty() + " " +
                        (a.GetisEmpty()?a.getMaterial():"null"));
            }
        }

    }
    void listTeleportGates (){
        int i = 0;
        int pairid = 0;
        if(Main.asteroids.size() == 0)
            System.out.println("No Asteroids!");
        else{
            for (TeleportGate g : Main.teleportgates){
                i++;
                if (g.GetPair() != null) {
                    pairid = Main.teleportgates.indexOf(g.GetPair()) + 1;
                }
                System.out.println("G" + i + " " + g.GetisActive() + " " + (pairid > 0?( "G" + pairid):"") + " " +  (g.GetisPlaced()? g.GetAsteroid():""));
            }
        }

    }
    void listCharacters (){
        int i = 0;
        for(Settler s : Main.settlers){
            i++;
            int aindex = Main.asteroids.indexOf((s.getAsteroid())) + 1 ;
            System.out.print("S" + i + " " + "A" + aindex);
            Material[] mat =  s.GetInvenotry().GetMaterials();
            for(Material m : mat){
                System.out.print(" M" + (Main.materials.indexOf(m) + 1));
            }
            System.out.println();
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
            for(Material m : mat){
                System.out.print(" M" + (Main.materials.indexOf(m) + 1));
            }
            System.out.println();
        }

    }
    void SunStorm (String[] cmd){
        //ellenőrizni a tömb méretét
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            if( cmd[1].charAt(0) == 'A') {
                if ((index <= 0) && (index < Main.asteroids.size())) {
                    switch (cmd[2]){
                        case "ON" : {
                        }
                        case "OFF" : {
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
    void setProximity(String[] cmd){
        cmd[1] = cmd[1].toUpperCase();
        cmd[2] = cmd[2].toUpperCase();

        try {
            int index = Integer.parseInt(cmd[1].substring(1));
            if( cmd[1].charAt(0) == 'A') {
                if ((index <= 0) && (index < Main.asteroids.size())) {
                    switch (cmd[2]) {
                        case "NEAR": {
                        }
                        case "FAR": {
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
    void step (){
        for(Asteroid a : Main.asteroids)
            a.Step();
        for(Robot r : Main.robots)
            r.Step();
        for(UFO u : Main.ufos)
            u.Step();
    }
    void setRandomize(String cmd){
        //globális vált
        cmd = cmd.toUpperCase();
        switch (cmd){
            case "ON":{
            }
            case "OFF" :{
            }
            default:
                System.out.println("Invalid input");
        }

    }

    void startGame(){

    }

    void endGame (){

    }

    void loadmap(String filename){

    }

    void saveMap (String filename){

    }



















}
