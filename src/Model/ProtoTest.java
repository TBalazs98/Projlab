package Model;

import Controller.InputManager;

import java.util.Scanner;

/**
 * Tesztesetek osztalya
 */
public class ProtoTest {

    /**
     * A tesztesetek megvalositasahoz letrehozott menuszerkezet.
     * Itt valaszthatnak az egyes felhasznalok mely teszteset hajtodjon vegre.
     */
    public static void menu(){
        Scanner in = new Scanner(System.in);
        int testcase ;
        do {
            System.out.println("\n? Which test case would you like to run?");
            System.out.println("0 - I want to run my own test, given by me, on stdin");
            System.out.println("1 - Drill Layer and reach Sublime Material Nearsun");
            System.out.println("2 - Mine Sublime Material");
            System.out.println("3 - Mine Sublime Material Nearsun");
            System.out.println("4 - Move Settler to Asteroid");
            System.out.println("5 - Move Settler to Asteroid that is not exists");
            System.out.println("6 - Place Sublime Material");
            System.out.println("7 - Move Settler to Teleport Gate");
            System.out.println("8 - Move Settler while being in SunStorm");
            System.out.println("9 - Place Sublime Material");
            System.out.println("10 - Place Sublime Material Nearsun");
            System.out.println("11 - Settler Place Teleport Gate");
            System.out.println("12 - Build Base");
            System.out.println("13 - Build Robot");
            System.out.println("14 - Build Teleport Gate");
            System.out.println("15 - Drill Layer and not reach Core");
            System.out.println("16 - Drill Layer and reach empty Core");
            System.out.println("17 - Drill Layer and reach Normal Material");
            System.out.println("18 - Mine Normal Material");
            System.out.println("19 - Drill Layer and reach Radioactive Material");
            System.out.println("20 - Drill Layer and reach Radioactive Material Nearsun");
            System.out.println("21 - Place Material but Asteroid is not empty or drilled through");
            System.out.println("22 - Mine Radioactive Material");
            System.out.println("23 - Mine Radioactive Material Nearsun");
            System.out.println("24 - Place Normal Material");
            System.out.println("25 - Place Radioactive Material");
            System.out.println("26 - Place not fully exposed Radioactive Material Nearsun");
            System.out.println("27 - Mine but Core is empty");
            System.out.println("28 - Mine but inventory full");
            System.out.println("29 - Settler places fully exposed RadioactiveMaterial while nearsun");
            System.out.println("30 - UFO victim of explosion");
            System.out.println("31 - Settlers intend to mine and build robot");
            System.out.println("32 - Settlers and Robot victims of explosion");
            System.out.println("33 - One survives one dies in a Sunstorm");
            System.out.println("34 - Settler build TeleportGate but has no storage for it");
            System.out.println("35 - Settler intends to mine but UFO steals material");
            System.out.println("36 - Settler enters TeleportGate but pair goes crazy");
            System.out.println("37 - Exit!");

            testcase = in.nextInt();

            switch (testcase) {
                case 0:
                    InputManager.FromUserInput();
                    break;
                case 1:
                    InputManager.FromFileInput("1in",false);
                    break;
                case 2:
                    InputManager.FromFileInput("2in",false);
                    break;
                case 3:
                    InputManager.FromFileInput("3in",false);
                    break;
                case 4:
                    InputManager.FromFileInput("4in",false);
                    break;
                case 5:
                    InputManager.FromFileInput("5in",false);
                    break;
                case 6:
                    InputManager.FromFileInput("6in",false);
                    break;
                case 7:
                    InputManager.FromFileInput("7in",false);
                    break;
                case 8:
                    InputManager.FromFileInput("8in",false);
                    break;
                case 9:
                    InputManager.FromFileInput("9in",false);
                    break;
                case 10:
                    InputManager.FromFileInput("10in",false);
                    break;
                case 11:
                    InputManager.FromFileInput("11in",false);
                    break;
                case 12:
                    InputManager.FromFileInput("12in",false);
                    break;
                case 13:
                    InputManager.FromFileInput("13in",false);
                    break;
                case 14:
                    InputManager.FromFileInput("14in",false);
                    break;
                case 15:
                    InputManager.FromFileInput("15in",false);
                    break;
                case 16:
                    InputManager.FromFileInput("16in",false);
                    break;
                case 17:
                    InputManager.FromFileInput("17in",false);
                    break;
                case 18:
                    InputManager.FromFileInput("18in",false);
                    break;
                case 19:
                    InputManager.FromFileInput("19in",false);
                    break;
                case 20:
                    InputManager.FromFileInput("20in",false);
                    break;
                case 21:
                    InputManager.FromFileInput("21in",false);
                    break;
                case 22:
                    InputManager.FromFileInput("22in",false);
                    break;
                case 23:
                    InputManager.FromFileInput("23in",false);
                    break;
                case 24:
                    InputManager.FromFileInput("24in",false);
                    break;
                case 25:
                    InputManager.FromFileInput("25in",false);
                    break;
                case 26:
                    InputManager.FromFileInput("26in",false);
                    break;
                case 27:
                    InputManager.FromFileInput("27in",false);
                    break;
                case 28:
                    InputManager.FromFileInput("28in",false);
                    break;
                case 29:
                    InputManager.FromFileInput("29in",false);
                    break;
                case 30:
                    InputManager.FromFileInput("30in",false);
                    break;
                case 31:
                    InputManager.FromFileInput("31in",false);
                    break;
                case 32:
                    InputManager.FromFileInput("32in",false);
                    break;
                case 33:
                    InputManager.FromFileInput("33in",false);
                    break;
                case 34:
                    InputManager.FromFileInput("34in",false);
                    break;
                case 35:
                    InputManager.FromFileInput("35in",false);
                    break;
                case 36:
                    InputManager.FromFileInput("36in",false);
                    break;
                case 37:
                    break;
                default:
                    System.out.println("Invalid number!");
                    ProtoTest.menu();
                    break;
            }
        }while(testcase!=37);
        //Logger.getInstance().printReturnCommand();
    }

}