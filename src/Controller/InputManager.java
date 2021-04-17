package Controller;

import Model.*;

import java.util.regex.Pattern;

public class InputManager {

    public static void letrehoz(int what, String params,int actual ){
        switch (what){
            case 0:
                if(Pattern.matches(Main.materialregex,params)){
                    createMaterial(params);
                }
                break;
            case 1:
                if(Pattern.matches(Main.lonely_empty_asteroid,params)){
                    createAsteroid1(params,actual);
                }else if(Pattern.matches(Main.lonely_not_empty_asteroid,params)) {
                    createAsteroid2(params,actual);
                }else if(Pattern.matches(Main.not_lonely_empty,params)){
                    createAsteroid3(params,actual);
                }else if(Pattern.matches(Main.not_lonely_not_empty_asteroid,params)){
                    createAsteroid4(params,actual);
                }
                break;
            case 2:
                if(Pattern.matches(Main.tg_placed_regex,params)){
                    createTeleportGate1(params,actual);
                }else if(Pattern.matches(Main.tg_notplaced_regex,params)){
                    createTeleportGate2(params,actual);
                }
                break;
            case 3:
                if(Pattern.matches(Main.settler_wom_and_wotg,params)){
                    createSettler1(params,actual);
                }else if(Pattern.matches(Main.settler_wm_and_wotg,params)){
                    createSettler2(params,actual);
                }else if(Pattern.matches(Main.settler_wom_and_wtg,params)){
                    createSettler3(params,actual);
                }else if(Pattern.matches(Main.settler_wm_and_wtg,params)){
                    createSettler4(params,actual);
                }
                break;
            case 4:
                if(Pattern.matches(Main.robotregex,params)){
                    createRobot(params,actual);
                }
                break;
            case 5:
                if(Pattern.matches(Main.ufo_wm,params)){
                    createUfo1(params,actual);
                }else if(Pattern.matches(Main.ufo_wom,params)){
                    createUfo2(params,actual);
                }
                break;

        }

    }

    public static void create(int what, int how_man){
        switch (what){
            case 0:
                for(int i=0; i<how_man;i++){
                    Main.materials.add(new Material());
                }
                break;
            case 1:
                for(int i=0; i<how_man;i++){
                    Main.asteroids.add(new Asteroid());
                }
                break;
            case 2:
                for(int i=0; i<how_man;i++){
                    Main.teleportgates.add(new TeleportGate());
                }
                break;
            case 3:
                for(int i=0; i<how_man;i++){
                    Main.settlers.add(new Settler());
                }
                break;
            case 4:
                for(int i=0; i<how_man;i++){
                    Main.robots.add(new Robot());
                }
                break;
            case 5:
                for(int i=0; i<how_man;i++){
                    Main.ufos.add(new UFO());
                }
                break;

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

    public static void createAsteroid1(String params,int actual){        //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
        //pl    : 0 0 25 0
        String[] cmd = params.split("\\t");

        //Asteroid a =Main.asteroids.get(actual);
        setCommonAsteroid(Main.asteroids.get(actual),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));

    }
    public static void createAsteroid2(String params,int actual){         //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
        //pl    : 0 0 25 1 5
        String[] cmd = params.split("\\t");
        //Asteroid a =Main.asteroids.get(actual);
        setCommonAsteroid(Main.asteroids.get(actual),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
        Main.asteroids.get(actual).AddMaterial(Main.materials.get(Integer.parseInt(cmd[4])-1));

    }
    public static void createAsteroid3(String params,int actual){         //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
        //pl    : 2 1,2 1 5 0
        String[] cmd = params.split("\\t");
        String[] neighbors={""};
        int ize = Integer.parseInt(cmd[0]);
        if(ize==1) {
            System.out.println( "itt vagyok");
            String asd = cmd[1];
            neighbors[0] = asd;
        }else{
            neighbors = cmd[1].split(",");
        }

        // Asteroid a = Main.asteroids.get(actual);
        for(int i=0; i<Integer.parseInt(cmd[0]);i++){
            Main.asteroids.get(actual).AddNeighbour(Main.asteroids.get(Integer.parseInt(neighbors[i])-1));
        }
        setCommonAsteroid(Main.asteroids.get(actual),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
    }
    public static void createAsteroid4(String params,int actual){          //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
        //pl    : 5 5,2,3,4,5 1 5 0
        createAsteroid3(params,actual);
        String[] cmd = params.split("\\t");
        Main.asteroids.get(actual).AddMaterial(Main.materials.get(Integer.parseInt(cmd[5])-1));

    }
    public static void setCommonAsteroid(Asteroid a, int sunprox, int layer, int empty){
        a.SetSunProximityManual(IntToBoolean(sunprox));
        a.setLayer(layer);
        a.SetEmpty(IntToBoolean(empty));
    }

    public static void createTeleportGate1(String params, int actual){
        String[] cmd = params.split( "\\t");
        TeleportGate tg = Main.teleportgates.get(actual);
        setCommonTG(tg,Integer.parseInt(cmd[0]),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
        tg.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[2])-1));

    }
    public static void createTeleportGate2(String params,int actual){
        String[] cmd = params.split( "\\t");
        TeleportGate tg = Main.teleportgates.get(actual);
        setCommonTG(tg,Integer.parseInt(cmd[0]),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
    }
    public static void setCommonTG(TeleportGate tg,int pair,int placed,int active, int crazy){
        if(pair!=0){
            tg.setPair(Main.teleportgates.get(pair-1));
        }
        tg.setPlaced(IntToBoolean(placed));
        tg.setActive(IntToBoolean(active));
        tg.setCrazy(IntToBoolean(crazy));
    }
    public static boolean IntToBoolean(int integer){
        return integer==1;
    }

    public static void createSettler1(String params, int actual){    //WithOutMaterial and WithOutTG
        Settler s = Main.settlers.get(actual);
        String[] cmd = params.split( "\\t");
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));

    }
    public static void createSettler2(String params, int actual){   //With material and without tg
        Settler s = Main.settlers.get(actual);
        String[] cmd = params.split( "\\t");
        String[] materials=cmd[2].split(",");
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        for(int i=0; i<materials.length;i++){
            s.AddMaterial(Main.materials.get(Integer.parseInt(materials[i])-1));
        }
    }
    public static void createSettler3(String params, int actual){   //WithOutMaterial and WithTG
        Settler s = Main.settlers.get(actual);
        String[] cmd = params.split( "\\t");
        String[] tgs=cmd[3].split(",");
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        for(int i=0; i<tgs.length;i++){
            s.AddGate(Main.teleportgates.get(Integer.parseInt(tgs[i])-1));
        }
    }
    public static void createSettler4(String params, int actual){ //wm and wtg
        String[] cmd = params.split( "\\t");
        String[] materials=cmd[2].split(",");
        String[] tgs=cmd[3].split(",");
        Settler s = Main.settlers.get(actual);
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        for(int i=0; i<materials.length;i++){
            s.AddMaterial(Main.materials.get(Integer.parseInt(materials[i])-1));
        }
        for(int i=0; i<tgs.length;i++){
            s.AddGate(Main.teleportgates.get(Integer.parseInt(tgs[i])-1));
        }
    }
    public static void createRobot(String params, int actual){
        String[] cmd=params.split("\\t");
        Main.robots.get(actual).setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])));
    }
    public static void createUfo1(String params,int actual){
        String[] cmd=params.split("\\t");
        Main.ufos.get(actual).setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
    }
    public static void createUfo2(String params, int actual){
        String[] cmd=params.split("\\t");
        UFO u =Main.ufos.get(actual);
        u.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        String[] materials=cmd[2].split(",");
        u.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        for(int i=0; i<materials.length;i++){
            u.GetInventory().Add(Main.materials.get(Integer.parseInt(materials[i])-1).name);
        }

    }
}
