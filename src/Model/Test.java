package Model;

import java.io.IOException;
import java.io.InputStreamReader;
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

    void DrillLayerandReachSublimableMaterial() {   //1
        Settler s = new Settler();
        SublimableMaterialName subName = SublimableMaterialName.ICEWATER;
        SublimableMaterial m = new SublimableMaterial();
        m.setName(subName.ICEWATER);
        Asteroid asteroid = new Asteroid(0,false,false,m);
        asteroid.setCharacter(s);

        menu();
    }

    void MineSublimableMaterial() {     //2
        Settler s = new Settler();
        SublimableMaterialName subName = SublimableMaterialName.ICEWATER;
        SublimableMaterial material = new SublimableMaterial();
        material.setName(subName.ICEWATER);
        Asteroid asteroid = new Asteroid(0,false,false,material);
        asteroid.setCharacter(s);
        //asdkl

    }

    void MoveSettlertoAsteroid(){       }//3

    void MoveSettlertoTeleportGate(){}  //4

    void MoveSettlerwhilebeinginSunStorm(){    }    //5

    void PlaceSublimeMaterial(){    }   //6

    void SettlerPlaceTeleportGate(){    }   //7

    void BuildBase(){ } //8

    void BuildRobot(){} //9

    void BuildTeleportGate(){}      //10

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
        m.Add(/* */); //TODO s.getInventory
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

    void MineRadioactiveMaterial(){}        //17

    void PlaceNormalMaterial(){}        //18

    void PlaceRadioactiveMaterial(){}       //19

    void MinebutCoreisEmpty(){}     //20

    void MinebutInventoryisFull(){      //21
        Settler settler = new Settler();
        Asteroid asteroid = new Asteroid();
        Inventory iv = new Inventory();
        HashMap<MaterialName, Integer> m = new HashMap<>();
        m.put(NormalMaterialName.IRON, 10);
        iv.init(m);
    }

}
