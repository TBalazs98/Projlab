package Model;

import java.util.HashMap;
import java.util.Scanner;


//KEZDETLEGES
public class Test {

    void menu()  {
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
        System.out.println("\nMelyik tesztesetet futtatná?");

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
                DrillLayerandreachRadioactiveMaterial();
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

        s.Mine();
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


        s.setAsteroid(a);
        //tg.setPair(pair);
        //pair.setPair(tg);
        a.setCharacter(s);
        a.setNeighbour(b);
        b.setNeighbour(a);

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

    void DrillLayerandnotreachCore(){    }

    void DrillLayerandreachemptyCore(){ }

    void DrillLayerandreachNormalMaterial() {}

    void MineNormalMaterial() {}

    void DrillLayerandreachRadioactiveMaterial(){}

    void PlaceMaterialbutAsteroidisnotemptyordrilledthrough(){}

    void MineRadioactiveMaterial(){}

    void PlaceNormalMaterial(){}

    void PlaceRadioactiveMaterial(){}

    void MinebutCoreisEmpty(){}

    void MinebutInventoryisFull(){
        Settler settler = new Settler();
        Asteroid asteroid = new Asteroid();
        Inventory iv = new Inventory();
        HashMap<MaterialName, Integer> m = new HashMap<>();
        m.put(NormalMaterialName.IRON, 10);
        iv.init(m);
    }

}
