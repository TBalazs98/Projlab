package Model;

import Controller.InputManager;

import java.util.Scanner;

public class ProtoTest {

    public static void menu(){
        Scanner in = new Scanner(System.in);
        int testcase ;
        do {

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
            System.out.println("22 - Exit!");


            System.out.println("\n? Which test case would you like to run?");

            testcase = in.nextInt();

            switch (testcase) {
                case 1:
                    InputManager.FromFileInput("1in");
                    break;
                case 2:
                    InputManager.FromFileInput("2in");
                    break;
                case 3:
                    InputManager.FromFileInput("3in");
                    break;
                case 4:
                    InputManager.FromFileInput("4in");
                    break;
                case 5:
                    InputManager.FromFileInput("5in");
                    break;
                case 6:
                   // PlaceSublimeMaterial();
                    break;
                case 7:
                   // SettlerPlaceTeleportGate();
                    break;
                case 8:
                   // BuildBase();
                    break;
                case 9:
                   // BuildRobot();
                    break;
                case 10:
                   // BuildTeleportGate();
                    break;
                case 11:
                   // DrillLayerandnotreachCore();
                    break;
                case 12:
                    //DrillLayerandreachemptyCore();
                    break;
                case 13:
                   // DrillLayerandreachNormalMaterial();
                    break;
                case 14:
                   // MineNormalMaterial();
                    break;
                case 15:
                   // DrillLayerandreachRadioactiveMaterial();
                    break;
                case 16:
                   // PlaceMaterialbutAsteroidisnotemptyordrilledthrough();
                    break;
                case 17:
                   // MineRadioactiveMaterial();
                    break;
                case 18:
                    //PlaceNormalMaterial();
                    break;
                case 19:
                   // PlaceRadioactiveMaterial();
                    break;
                case 20:
                    //MinebutCoreisEmpty();
                    break;
                case 21:
                    //MinebutInventoryisFull();
                    break;
                case 22:
                    break;
                default:
                    System.out.println("Invalid number!");
                    menu();
                    break;
            }
        }while(testcase!=22);
        //Logger.getInstance().printReturnCommand();
    }


}
