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
        SublimableMaterialName subName = SublimableMaterialName.ICEWATER;
        SublimableMaterial m = new SublimableMaterial();
        m.setName(subName.ICEWATER);
        Asteroid asteroid = new Asteroid(0,false,false,m);
        asteroid.setCharacter(s);

        menu();
    }

    void MineSublimableMaterial() {
        Settler s = new Settler();
        SublimableMaterialName subName = SublimableMaterialName.ICEWATER;
        SublimableMaterial material = new SublimableMaterial();
        material.setName(subName.ICEWATER);
        Asteroid asteroid = new Asteroid(0,false,false,material);
        asteroid.setCharacter(s);
        //asdkl

    }

    void MoveSettlertoAsteroid(){    }

    void MoveSettlertoTeleportGate(){}

    void MoveSettlerwhilebeinginSunStorm(){    }

    void PlaceSublimeMaterial(){    }

    void SettlerPlaceTeleportGate(){    }

    void BuildBase(){ }

    void BuildRobot(){}

    void BuildTeleportGate(){}

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
