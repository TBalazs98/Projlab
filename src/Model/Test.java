package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;


//KEZDETLEGES
public class Test {

    void menu() {
        System.out.println("1 - Drill Layer and reach Sublime Material");
        System.out.println("2 - Mine Sublime Material");
        System.out.println("3 - Move Settler to Asteroid");
        System.out.println("4 - Move Settler to Teleport Gate");
        System.out.println("5 - Move Settler while being in SunStorm");
        System.out.println("6 - Place Sublime Material");
        System.out.println("7 - Settler Place Teleport Gate");
        System.out.println("8 - Build Base");
        System.out.println("9 - Build Robot");
        System.out.println("10 - Build Teleport Gate");
        System.out.println("11 - Drill Layer and not reach Core");
        System.out.println("12 - Drill Layer and reach empty Core");
        System.out.println("13 - Drill Layer and reach Normal Material");
        System.out.println("14 - Mine Normal Material");
        System.out.println("15 - Drill Layer and reach Radioactive Material");
        System.out.println("16 - Place Material but Asteroid is not empty or drilled through");
        System.out.println("17 - Mine Radioactive Material");
        System.out.println("18 - Place Normal Material");
        System.out.println("19 - Place Radioactive Material");
        System.out.println("20 - Mine but Core is empty");
        System.out.println("21 - Mine but inventory full");

        Scanner in = new Scanner(System.in);
        System.out.println("\nWhich test case would you like to run?");

        int testcase = in.nextInt();


        switch (testcase){
            case 1:
                DrillLayerandReachSublimableMaterial();
                break;
            case 2 :
                MineSublimableMaterial();
                break;
            case 3 :
                MoveSettlertoAsteroid();
                break;
            case 4 :
                MoveSettlertoTeleportGate();
                break;
            case 5 :
                MoveSettlerwhilebeinginSunStorm();
                break;
            case 6 :
                PlaceSublimeMaterial();
                break;
            case 7 :
                SettlerPlaceTeleportGate();
                break;
            case 8 :
                BuildBase();
                break;
            case 9 :
                BuildRobot();
                break;
            case 10 :
                BuildTeleportGate();
                break;
            case 11 :
                DrillLayerandnotreachCore();
                break;
            case 12 :
                DrillLayerandreachemptyCore();
                break;
            case 13 :
                DrillLayerandreachNormalMaterial();
                break;
            case 14 :
                MineNormalMaterial();
                break;
            case 15 :
                try {
                    DrillLayerandreachRadioactiveMaterial();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 16 :
                PlaceMaterialbutAsteroidisnotemptyordrilledthrough();
                break;
            case 17 :
                MineRadioactiveMaterial();
                break;
            case 18 :
                PlaceNormalMaterial();
                break;
            case 19 :
                PlaceRadioactiveMaterial();
                break;
            case 20 :
                MinebutCoreisEmpty();
                break;
            case 21 :
                MinebutInventoryisFull();
                break;
            case 0 :
                System.out.println("Invalid number!");
                menu();
                break;
            default :
                System.out.println("Invalid number!");
                menu();
                break;
        }
    }

    void DrillLayerandReachSublimableMaterial() {
        Settler s = new Settler();
        Asteroid asteroid;

        int layerr = 2000;          //bekérni
        boolean isnear = false;     //bekérni
        boolean isempty = false;    //bekérni

        if(!isempty){
            SublimableMaterialName subName = SublimableMaterialName.ICEWATER;
            SublimableMaterial m = new SublimableMaterial();
            m.setName(subName.ICEWATER);
            asteroid = new Asteroid(layerr, false, isnear, m);
        } else {
            asteroid = new Asteroid(layerr, false, isnear, null);
        }

        asteroid.setCharacter(s);

        s.Drill();

    }

    void MineSublimableMaterial() {
        Settler s = new Settler();
        SublimableMaterialName subName = SublimableMaterialName.ICEWATER;

        SublimableMaterial material = new SublimableMaterial();
        material.setName(subName.ICEWATER);
        Asteroid asteroid = new Asteroid();
        //asteroid.setLayer(0);
        //asteroid.setMaterial(m);
        asteroid.setCharacter(s);
        //asdkl

    }

    void MoveSettlertoAsteroid(){
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Asteroid goingTO = new Asteroid();

        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        asteroid.AddNeighbour(goingTO);
        goingTO.AddNeighbour(asteroid);

        s.Move(0);
    }

    void MoveSettlertoTeleportGate(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Asteroid b = new Asteroid();
        TeleportGate tg = new TeleportGate();
        TeleportGate pair = new TeleportGate();

        tg.setAsteroid(a);
        pair.setAsteroid(b);

        s.setAsteroid(a);
        tg.setPair(pair);
        pair.setPair(tg);
        a.setCharacter(s);
        a.setNeighbour(tg);
        b.setNeighbour(pair);

        s.Move(0);

    }

    void MoveSettlerwhilebeinginSunStorm(){
        Settler s = new Settler();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Game g = Game.getInstance();
        Asteroid onEmpty = new Asteroid();
        ab.AddAsteroid(onEmpty);
        s.setAsteroid(onEmpty);
        onEmpty.Accept(s);
        ab.StartStorm();

        DestinationObject helper = onEmpty.GetNeighbour(0);

        s.Move(0);

    }

    void PlaceSublimeMaterial(){
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        SublimableMaterial sm = new SublimableMaterial();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        //s.AddInventroy(inventroy);
        //asteroid.setProximity(false);
        //asteroid.setLayer(0);

        int layer = 0;

        Scanner in = new Scanner(System.in);
        System.out.println("\nIs the asteroid empty (y/n)?");
        String isEmpty = in.nextLine();

        Scanner in2 = new Scanner(System.in);
        System.out.println("\nIs the asteroid near Sun (y/n)?");
        String isNear = in2.nextLine();

        s.PlaceMaterial(sm);

    }

    void SettlerPlaceTeleportGate(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        TeleportGate pair = new TeleportGate();
        TeleportGate tg = new TeleportGate();
        Asteroid b = new Asteroid();

        s.setAsteroid(a);
        a.Accept(s);
        //pair.setPair(tg);
        //tg.setPair(pair);
        //pair.setAsteroid(b);

        s.PlaceGate(tg);

    }

    void BuildBase(){
        Settler s = new Settler();
        Settler s2 = new Settler();
        Asteroid a = new Asteroid();
        Game g = Game.getInstance();

        s.setAsteroid(a);
        a.Accept(s);
        s2.setAsteroid(a);
        a.Accept(s2);

        //feltölteni azt a két buzit

        s.BuildBase();

    }

    void BuildRobot(){
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);

        s.BuildRobot();
    }

    void BuildTeleportGate(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.setAsteroid(a);
        a.Accept(s);

        s.BuildGate();

    }

    void DrillLayerandnotreachCore(){   //11
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        //asteroid.SetLayer(5); //TODO
        Material m=new Material();
        asteroid.AddMaterial(m);
        s.Drill();

    }

    void DrillLayerandreachemptyCore(){
        Asteroid a=new Asteroid();
        Settler s = new Settler();
        s.setAsteroid(a);
        a.Accept(s);
        //s.SetLayer(1) ||  Dávid : szerintem inkább a.SetLayer(1) lehetne inkább :D //TODO
        s.Drill();

    }       //12

    void DrillLayerandreachNormalMaterial() {
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Material m = new Material();
        s.setAsteroid(a);
        a.Accept(s);
        //a.SetLayer(1);
        a.AddMaterial(m);
        s.Drill();  //-> a.Drilled() -> m.Hit()


    }  //13

    void MineNormalMaterial() {
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Material m = new Material();
        s.setAsteroid(a);
        a.Accept(s);
        //a.setLayer(0);
        a.AddMaterial(m);
        s.Mine(); // asteroidban m.hit(asteroid) hiányzik ;
        //m.Add(/* */); //TODO s.getInventory
        //az addban helyesen futnak le a dolgok


    }        //14

    void DrillLayerandreachRadioactiveMaterial() throws IOException {
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Asteroid asteroid = new Asteroid();
        Asteroid a = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        Robot r = new Robot();
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();//8
        RadioactiveMaterial material = new RadioactiveMaterial();
        ab.AddAsteroid(asteroid);
        ab.AddAsteroid(an);
        ab.AddAsteroid(a);//12
        asteroid.AddNeighbour(an);
        an.AddNeighbour(asteroid);
        //tn.setAsteroid(asteroid);
        //pair.setAsteroid(a);
        asteroid.AddNeighbour(tn);
        a.AddNeighbour(pair);//18
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        r.setAsteroid(asteroid);
        asteroid.Accept(r);//22
        //tn.setPair(pair);
        asteroid.AddMaterial(material);
        //asteroid.setLayer(1);
        tn.Activate();
        //pair.setPair(tn);//27
        pair.Activate();

        System.out.println("\nIs the asteroid near to the Sun?\n");
        System.out.println(" (Y)es / (N)o");
        InputStreamReader br = new InputStreamReader(System.in);
        char ch = ' ';
        ch=(char)br.read();

        if(ch=='y' || ch=='Y' )
            asteroid.SetSunProximity(); //ide kell valami setter

        s.Drill();


    }      //15

    void PlaceMaterialbutAsteroidisnotemptyordrilledthrough(){
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Material m = new Material();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        //asteroid.setLayer(5);

        s.PlaceMaterial(m);

    }     //16

    void MineRadioactiveMaterial(){
        Game g = Game.getInstance();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Asteroid asteroid = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        RadioactiveMaterial material = new RadioactiveMaterial();
        RadioactiveMaterialName materialName = RadioactiveMaterialName.URAN;
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();

        asteroid.Accept(s);
        asteroid.AddNeighbour(an);
        asteroid.AddMaterial(material); // SetMaterial()
        s.setAsteroid(asteroid);

        s.Mine();

    }

    void PlaceNormalMaterial(){
        Settler s = new Settler();
        // Inventory inventory = new Inventory();
        Asteroid asteroid = new Asteroid();
        Material m = new Material();
        //NormalMaterialName rmn = NormalMaterialName.IRON;

        s.setAsteroid(asteroid);
        // asteroid.SetLayer(0);
        asteroid.Accept(s);


        s.PlaceMaterial(m);

    }

    void PlaceRadioactiveMaterial(){
        Asteroid asteroid = new Asteroid();
        Asteroid a = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        RadioactiveMaterial rm = new RadioactiveMaterial();
        RadioactiveMaterialName rmn = RadioactiveMaterialName.URAN;
        Robot r = new Robot();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();

        rm.setName(rmn);
        //asteroid.SetLayer(0);
        asteroid.AddNeighbour(an);
        asteroid.AddNeighbour(tn);
        asteroid.Accept(s);
        asteroid.AddMaterial(rm);
        asteroid.Accept(r);
        r.setAsteroid(asteroid);
        s.setAsteroid(asteroid);
        ab.AddAsteroid(asteroid);
        ab.AddAsteroid(a);
        ab.AddAsteroid(an);
        tn.Place(asteroid);
        //tn.SetPair(pair);
        tn.Activate();
        an.AddNeighbour(asteroid);
        pair.Place(a); //setAsteroid();
        //tn.SetPair(pair);
        pair.Activate();


        s.PlaceMaterial(rm);

    }

    void MinebutCoreisEmpty(){
        Asteroid a = new Asteroid();
        Settler s = new Settler();

        a.Accept(s);
        s.setAsteroid(a);

        s.Mine();

    }

    void MinebutInventoryisFull(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Inventory inventory = new Inventory();
        HashMap<MaterialName, Integer> material = new HashMap<>();
        material.put(NormalMaterialName.IRON, 10);
        inventory.fill(material);

        s.setAsteroid(a);
        //s.SetInventory(inventory);

        s.Mine();
    }
}
