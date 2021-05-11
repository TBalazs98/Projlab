package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class InputManager {

    private static int[] ObjCounts = {0,0,0,0,0,0}; //Material, Asteroid, TG, Settler, Robot, Ufo   //a legelso sor felsplittelt erekei, melyik obj-bol mennyit kell letrehozni
    private static String[] cmd={}; //a reader felsplittelt resz szavai
    private static String line; //a reader aktualisan beolvasott sora
    private static BufferedReader reader ;  //reader
    private static ArrayList<String> output = new ArrayList<>();    //a stdoutputnak a lementese ebbe a gyujtemenybe

    /**
     * A metodus kiuriti a parameterkent kaott ArrayListet
     * @param array tetszoleges ArrayList<>
     */
    public static void removeObjects(ArrayList<?> array ){
        array.clear();
    }

    /**
     * Sajat valtozoba menti a parameterkent kapott Stringet
     * @param generateoutput boolean, ami megmondja, hogy az stdout-ot elmentsuk-e egy sajat valtozoba, amit majd kesobb (tesztelesnel) fel tudunk hasznalni
     * @param output String, amit kiirt az stdout
     */
    public static void write_to_output(boolean generateoutput,String output){
        if(generateoutput)
            InputManager.GetOutput().add(output);
    }

    /**
     * Gettter az output tagvaltozora
     * @return private output struktura
     */
    public static ArrayList<String> GetOutput(){
        return output;
    }

    /**
     * Kiuriti az output tartalmat
     */
    public static void zero(){
        InputManager.output.clear();
    }


    public static ArrayList<String> GenerateOutput(String filename){
        InputManager.FromFileInput(filename,true);
        ArrayList<String> output_list = new ArrayList<String>();
        for(int i=0; i<InputManager.output.size(); i++){
            output_list.add(InputManager.output.get(i));
        }
        return output_list;
    }

    /**
     * A beolvasast es az inicializalast megvalosito metodus
     * @param generateoutput boolean, le legyen e mentve a stdout egy sajat valtozoba
     */
    public static void InputCore(Boolean generateoutput){
        CommandManager cm = new CommandManager();
        try {
            line=reader.readLine();
            cmd=line.split(" ");
            for(int i=1; i<7;i++) {
                ObjCounts[i - 1] = Integer.parseInt(cmd[i]);
            }

            for(int i=0; i<ObjCounts[0];i++){           //itt eloszor a materialokat hozzuk letre,
                String params = reader.readLine();      //mert a create metodusban nem tudnánk kul típusu objektumot letrehozni, csak materialt
                System.out.println(params);
                InputManager.createMaterial(params);
            }

            for(int i=1; i<6;i++){                      //itt pedig minden mast, vegigmegyunk az ObjCounts tombon, hogy mibol mennyi kell
                InputManager.createObject(i,ObjCounts[i]);    //eloszor csak letrehozzuk
                for(int j=0; j<ObjCounts[i];j++){
                    String params=reader.readLine();
                    System.out.println(params);
                    InputManager.initObjects(i,params,j);  //majd inicializaljuk is
                }
            }

           reader.readLine();      //ures sor beolvasasa, doksi szerint

            while(cm.IsRunning()){      //addig amig a bemenetben nem talalja meg a stop-ot
                line=reader.readLine();
                cm.command(line,generateoutput);
            }
            /*System.out.println("[Actual output]");
            cm.listMaterials(generateoutput);   //
            cm.listAsteroids(generateoutput);
            cm.listTeleportGates(generateoutput);
            cm.listCharacters(generateoutput);
            //cm.saveMap("savedmap");*/

            /*removeObjects(Main.materials);  //uritjuk a tarolokat
            removeObjects(Main.asteroids);
            removeObjects(Main.teleportgates);
            removeObjects(Main.settlers);
            removeObjects(Main.robots);
            removeObjects(Main.ufos);*/

            cm.Reset();

           // reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A fajbol valo olvasast es inicializalast valositja meg
     * @param filename filename, fajlbol valo olvasashoz (.txt nelkul)
     * @param generateoutput boolean, le legyen e mentve a stdout egy sajat valtozoba
     */
    public static void FromFileInput(String filename,boolean generateoutput){
        try {
            File currentfile = InputManager.getFile("Files","Saved" ,filename);
            reader = new BufferedReader(new FileReader(currentfile));
            InputManager.InputCore(generateoutput);   //TODO ezt kell átváltoztatni bool generateoutput-ra, meg paraméterben felvenni
        }catch (IOException e){                       //TODO és a ParamTest-ben átváltoztatni minden hívást, kiegészíteni false-ra
            System.out.println("Input file not found!");
        }
    }

    /**
     * A stdin-rol valo olvasast es inicializalast valositja meg
     */
    public static void FromUserInput(){
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            InputManager.InputCore(false);
        }catch (Exception e){
            System.out.println("User input: Input file not found!");
        }
    }

    /**
     * OPrendszer fuggetlen filebeolvasast valositja meg
     * @param dir gyokermappa elso szintjen levo konyvtar
     * @param dir2 gyokermappa masodik szintjen levo konyvtar
     * @param filename cel file neve
     * @return wanted, a kulonbozo parameterkent kapott directorykban talalhato file
     * @throws IOException ha a file nem talalhato
     */
    public static File getFile (String dir, String dir2, String filename)throws IOException {
        File dir_File=new File (dir);
        String caononical_path= dir_File.getCanonicalPath();
        File dir_File_Input=new File(caononical_path,dir2);
        String canonical_path_2=dir_File_Input.getCanonicalPath();
        File wanted = new File(canonical_path_2,filename+".txt");

        /*BufferedReader read = new BufferedReader(new FileReader(wanted));

        System.out.println(read.readLine());*/

        return wanted;
    }

    /**
     *
     * @param type_of_object Milyen tipusu objektumrol van szo (Material, Asteroid, Settler....)
     * @param init_settings Az inicializalashoz szukseges szamfolyam
     * @param type Hanyadik objektumrol van szo (Az ArrayList-ekben valo eleresekhez)
     */
    public static void initObjects(int type_of_object, String init_settings, int type ){
        switch (type_of_object){
            case 0:
                if(Pattern.matches(Main.materialregex,init_settings)){
                    createMaterial(init_settings);
                }
                break;
            case 1:
                if(Pattern.matches(Main.lonely_empty_asteroid,init_settings)){
                    createAsteroid1(init_settings,type);
                }else if(Pattern.matches(Main.lonely_not_empty_asteroid,init_settings)) {
                    createAsteroid2(init_settings,type);
                }else if(Pattern.matches(Main.not_lonely_empty,init_settings)){
                    createAsteroid3(init_settings,type);
                }else if(Pattern.matches(Main.not_lonely_not_empty_asteroid,init_settings)){
                    createAsteroid4(init_settings,type);
                }
                break;
            case 2:
                if(Pattern.matches(Main.tg_placed_regex,init_settings)){
                    createTeleportGate1(init_settings,type);
                }else if(Pattern.matches(Main.tg_notplaced_regex,init_settings)){
                    createTeleportGate2(init_settings,type);
                }
                break;
            case 3:
                if(Pattern.matches(Main.settler_wom_and_wotg,init_settings)){
                    createSettler1(init_settings,type);
                }else if(Pattern.matches(Main.settler_wm_and_wotg,init_settings)){
                    createSettler2(init_settings,type);
                }else if(Pattern.matches(Main.settler_wom_and_wtg,init_settings)){
                    createSettler3(init_settings,type);
                }else if(Pattern.matches(Main.settler_wm_and_wtg,init_settings)){
                    createSettler4(init_settings,type);
                }
                break;
            case 4:
                if(Pattern.matches(Main.robotregex,init_settings)){
                    createRobot(init_settings,type);
                }
                break;
            case 5:
                if(Pattern.matches(Main.ufo_wm,init_settings)){
                    createUfo1(init_settings,type);
                }else if(Pattern.matches(Main.ufo_wom,init_settings)){
                    createUfo2(init_settings,type);
                }
                break;

        }

    }

    /**
     * Letrehozza elore az objektumokat, es eltarolja az objektumnak megfelelo ArrayList-ben
     * @param type_of_object milyen tipusu objektum (Material, Asteroid, Settler...)
     * @param size_of_objects Mennyit kell letrehozni
     */
    public static void createObject(int type_of_object, int size_of_objects){
        switch (type_of_object){
            case 0:
                for(int i=0; i<size_of_objects;i++){
                    Main.materials.add(new Material());
                }
                break;
            case 1:
                for(int i=0; i<size_of_objects;i++){
                    Main.asteroids.add(new Asteroid());
                }
                break;
            case 2:
                for(int i=0; i<size_of_objects;i++){
                    Main.teleportgates.add(new TeleportGate());
                }
                break;
            case 3:
                for(int i=0; i<size_of_objects;i++){
                    Main.settlers.add(new Settler());
                }
                break;
            case 4:
                for(int i=0; i<size_of_objects;i++){
                    Main.robots.add(new Robot());
                }
                break;
            case 5:
                for(int i=0; i<size_of_objects;i++){
                    Main.ufos.add(new UFO());
                }
                break;

        }
    }

    /**
     * Letrehozza a bemeneti params alapján a Materialt, a szukseges fuggosegekkel
     * @param material_settings Az inicializalashoz szukseges ertekhalmaz
     */
    public static void createMaterial(String material_settings){
        String[] input = material_settings.split("\\t");
        Material normal = new Material();
        RadioactiveMaterial radmat = new RadioactiveMaterial();
        SublimableMaterial submat= new SublimableMaterial();
        NormalMaterialName normalMaterialName;
        RadioactiveMaterialName radioactiveMaterialName;
        SublimableMaterialName sublimableMaterialName;
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
                int c = Integer.parseInt(input[1]);
                radioactiveMaterialName = RadioactiveMaterialName.URAN;
                radmat.setName(radioactiveMaterialName);
                radmat.SetExposure(Integer.parseInt(input[1]));
                Main.materials.add(radmat);
                //radioactiveMaterialName = RadioactiveMaterialName.URAN;
                Main.materials.get(Main.materials.size()-1).setName(RadioactiveMaterialName.URAN);
//                (RadioactiveMaterial)(Main.materials.get(Main.materials.size()-1))
                break;
            case 3:
                Main.materials.add(submat);
                sublimableMaterialName = SublimableMaterialName.ICEWATER;
                Main.materials.get(Main.materials.size()-1).setName(sublimableMaterialName);
                break;
        }
    }

    /**
     * Letrehozza a bemeneti params alapján az Asteroidot, a szukseges fuggosegekkel, ami illeszkedik a lonely_empty_asteroid regexre
     * @param asteroid_settings_type1 Az inicializalashoz szukseges ertekhalmaz
     * @param type hanyad objektumrol van szo
     */
    public static void createAsteroid1(String asteroid_settings_type1,int type){        //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
        //pl    : 0 0 25 0
        String[] cmd = asteroid_settings_type1.split("\\t");

        //Asteroid a =Main.asteroids.get(type);
        setCommonAsteroid(Main.asteroids.get(type),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));

    }

    /**
     * Letrehozza a bemeneti params alapján a Asteroidot, a szukseges fuggosegekkel, ami illeszkedik a lonely_not_empty_asteroid regexre
     * @param asteroid_settings_type2 Az inicializalashoz szukseges ertekhalmaz
     * @param type hanyad objektumrol van szo
     */
    public static void createAsteroid2(String asteroid_settings_type2,int type){         //input : szomszédszám = [kötelezően 0], napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
        //pl    : 0 0 25 1 5
        String[] cmd = asteroid_settings_type2.split("\\t");
        setCommonAsteroid(Main.asteroids.get(type),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
        int idx = Integer.parseInt(cmd[4])-1;
        Main.asteroids.get(type).SetMaterial(Main.materials.get(idx));

    }

    /**
     * Letrehozza a bemeneti params alapján a Asteroidot, a szukseges fuggosegekkel, ami illeszkedik a not_lonely_empty regexre
     * @param asteroid_settings_type3 Az inicializalashoz szukseges ertekhalmaz
     * @param type hanyad objektumrol van szo
     */
    public static void createAsteroid3(String asteroid_settings_type3,int type){         //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 1], nyersi index = [bármi int]
        //pl    : 2 1,2 1 5 0
        String[] cmd = asteroid_settings_type3.split("\\t");
        String[] neighbors={""};
        int ize = Integer.parseInt(cmd[0]);
        if(ize==1) {
            String asd = cmd[1];
            neighbors[0] = asd;
        }else{
            neighbors = cmd[1].split(",");
        }
        for(int i=0; i<Integer.parseInt(cmd[0]);i++){
            Main.asteroids.get(type).AddNeighbour(Main.asteroids.get(Integer.parseInt(neighbors[i])-1));
        }
        setCommonAsteroid(Main.asteroids.get(type),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
    }

    /**
     * Letrehozza a bemeneti params alapján a Asteroidot, a szukseges fuggosegekkel, ami illeszkedik a not_lonely_not_empty_asteroid regexre
     * @param asteroid_settings_type4 Az inicializalashoz szukseges ertekhalmaz
     * @param type hanyad objektumrol van szo
     */
    public static void createAsteroid4(String asteroid_settings_type4,int type){          //input : szomszédszám = [bármi int], szomszéd index[bármi int, (utolsó után nem kell)],  napközel = [0,1], rétegszám = [bármi int], üresség = [kötelezően 0]
        //pl    : 5 5,2,3,4,5 1 5 0
        createAsteroid3(asteroid_settings_type4,type);
        String[] cmd = asteroid_settings_type4.split("\\t");
        Main.asteroids.get(type).SetMaterial(Main.materials.get(Integer.parseInt(cmd[5])-1));
    }

    /**
     * Metodus ami a parameterkent kapott aszteroidanak beallitja a parameterkent kapott naptavolsag, retegszam és uresseg ertekeit
     * @param a Melyik aszteroida
     * @param sunprox, naptavolsag ertek
     * @param layer, retegszam ertek,
     * @param empty, uresseg ertek
     */
    public static void setCommonAsteroid(Asteroid a, int sunprox, int layer, int empty){
        a.SetSunProximityManual(IntToBoolean(sunprox));
        a.setLayer(layer);
        a.SetEmpty(!IntToBoolean(empty));
    }

    /**
     * Letrehozza a bemeneti params alapján a Teleportkaput, a szukseges fuggosegekkel, ami illeszkedik a tg_placed_regex regexre
     * @param tpg_setting_type1 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createTeleportGate1(String tpg_setting_type1, int actual){
        String[] cmd = tpg_setting_type1.split( "\\t");
        TeleportGate tg = Main.teleportgates.get(actual);
        setCommonTeleportGate(tg,Integer.parseInt(cmd[0]),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
        tg.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[2])-1));
        Main.asteroids.get(Integer.parseInt(cmd[2])-1).AddNeighbour(tg);

    }

    /**
     * Letrehozza a bemeneti params alapján a Asteroidot, a szukseges fuggosegekkel, ami illeszkedik a tg_notplaced_regex regexre
     * @param tpg_settings_type2 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createTeleportGate2(String tpg_settings_type2,int actual){
        String[] cmd = tpg_settings_type2.split( "\\t");
        TeleportGate tg = Main.teleportgates.get(actual);
        setCommonTeleportGate(tg,Integer.parseInt(cmd[0]),Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
        //Main.asteroids.get(Integer.parseInt(cmd[2])-1).AddNeighbour(tg);
    }

    /**
     *  * Metodus ami a parameterkent kapott teleportkapunak beallitja a parameterkent kapott parja, lehelyezett-e aktiv és orult ertekeit
     * @param tg, melyik teleportkapurol van szo
     * @param pair, parja ertek (index)
     * @param placed, lehelyezett-e ertek
     * @param active, aktiv-e ertek
     * @param craziness_factor, orult-e ertek
     */
    public static void setCommonTeleportGate(TeleportGate tg, int pair, int placed, int active, int craziness_factor){
        if(pair!=0){
            tg.setPair(Main.teleportgates.get(pair-1));
        }
        tg.setPlaced(IntToBoolean(placed));
        tg.setActive(IntToBoolean(active));
        tg.setCrazy(IntToBoolean(craziness_factor));
    }

    /**
     * Segedmetodus, amely int ertekbol booleant kepez
     * @param integer bemeneti ertek
     * @return logikai igaz vagy hamis
     */
    public static boolean IntToBoolean(int integer){
        return integer==1;
    }

    /**
     * Letrehozza a bemeneti params alapján a Settlert, a szukseges fuggosegekkel, ami illeszkedik a settler_wim_and_wotg regexre
     * @param settler_settings_type1 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createSettler1(String settler_settings_type1, int actual){    //WithOutMaterial and WithOutTG
        Settler s = Main.settlers.get(actual);
        String[] cmd = settler_settings_type1.split( "\\t");
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        Main.asteroids.get(Integer.parseInt(cmd[0])-1).Accept(s);
        Main.ab.settlersAlive += 1; //kiszedve, a teszteléshez szükséges settlerek minimalizálásához
    }

    /**
     * Letrehozza a bemeneti params alapján a Settlert, a szukseges fuggosegekkel, ami illeszkedik a settler_wm_and_wotg regexre
     * @param settler_settings_type2 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createSettler2(String settler_settings_type2, int actual){   //With material and without tg
        Settler s = Main.settlers.get(actual);
        String[] cmd = settler_settings_type2.split( "\\t");
        String[] materials=cmd[2].split(",");
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        Main.asteroids.get(Integer.parseInt(cmd[0])-1).Accept(s);
        for(int i=0; i<materials.length;i++){
            s.AddMaterial(Main.materials.get(Integer.parseInt(materials[i])-1));
        }
        Main.ab.settlersAlive += 1; //kiszedve, a teszteléshez szükséges settlerek minimalizálásához
    }

    /**
     * Letrehozza a bemeneti params alapján a Settlert, a szukseges fuggosegekkel, ami illeszkedik a settler_wom_and_wtg regexre
     * @param settler_settings_type3 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createSettler3(String settler_settings_type3, int actual){   //WithOutMaterial and WithTG
        Settler s = Main.settlers.get(actual);
        String[] cmd = settler_settings_type3.split( "\\t");
        String[] tgs=cmd[3].split(",");
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        Main.asteroids.get(Integer.parseInt(cmd[0])-1).Accept(s);
        for(int i=0; i<tgs.length;i++){
            s.AddGate(Main.teleportgates.get(Integer.parseInt(tgs[i])-1));
        }
        Main.ab.settlersAlive += 1; //kiszedve, a teszteléshez szükséges settlerek minimalizálásához
    }

    /**
     * Letrehozza a bemeneti params alapján a Settlert, a szukseges fuggosegekkel, ami illeszkedik a settler_wm_and_wtg regexre
     * @param settler_settings_type4 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createSettler4(String settler_settings_type4, int actual){ //wm and wtg
        String[] cmd = settler_settings_type4.split( "\\t");
        String[] materials=cmd[2].split(",");
        String[] tgs=cmd[3].split(",");
        Settler s = Main.settlers.get(actual);
        s.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        Main.asteroids.get(Integer.parseInt(cmd[0])-1).Accept(s);
        for(int i=0; i<materials.length;i++){
            s.AddMaterial(Main.materials.get(Integer.parseInt(materials[i])-1));
        }
        for(int i=0; i<tgs.length;i++){
            s.AddGate(Main.teleportgates.get(Integer.parseInt(tgs[i])-1));
        }
        Main.ab.settlersAlive += 1; //kiszedve, a teszteléshez szükséges settlerek minimalizálásához
    }

    /**
     * Letrehozza a bemeneti params alapjan a Robotot, a szukseges fuggosegekkel, ami illeszkedik a robotregex regexre
     * @param robot_settings Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createRobot(String robot_settings, int actual){
        Model.Robot r = Main.robots.get(actual);
        String[] cmd=robot_settings.split("\\t");
        Main.robots.get(actual).setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        Main.asteroids.get(Integer.parseInt(cmd[0])-1).Accept(r);

    }

    /**
     * Letrehozza a bemeneti params alapjan a Ufot, a szukseges fuggosegekkel, ami illeszkedik a ufo_wm regexre
     * @param ufo_settings_type1 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createUfo1(String ufo_settings_type1,int actual){
        UFO u = Main.ufos.get(actual);
        String[] cmd=ufo_settings_type1.split("\\t");
        Main.ufos.get(actual).setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        Main.asteroids.get(Integer.parseInt(cmd[0])-1).Accept(u);
    }

    /**
     * Letrehozza a bemeneti params alapján a Settlert, a szukseges fuggosegekkel, ami illeszkedik a ufo_wom regexre
     * @param ufo_settings_type2 Az inicializalashoz szukseges ertekhalmaz
     * @param actual hanyad objektumrol van szo
     */
    public static void createUfo2(String ufo_settings_type2, int actual){
        String[] cmd=ufo_settings_type2.split("\\t");
        UFO u =Main.ufos.get(actual);
        u.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        String[] materials=cmd[2].split(",");
        u.setAsteroid(Main.asteroids.get(Integer.parseInt(cmd[0])-1));
        for(int i=0; i<materials.length;i++){
            u.GetInventory().Add(Main.materials.get(Integer.parseInt(materials[i])-1));
        }

    }
}
