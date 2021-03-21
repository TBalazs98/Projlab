package Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Tesztesetek osztálya
 */
public class Test {

    /**
     * A tesztesetek megvalósításához létrehozott menüszerkezet.
     * Itt választhatnak az egyes felhasználók mely teszteset hajtódjon végre.
     */
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
        System.out.println("22 - Exit!");

        Scanner in = new Scanner(System.in);
        System.out.println("\nWhich test case would you like to run?");

        int testcase = in.nextInt();

        //Logger.getInstance().printCommandCall(this);
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
            case 22 : break;
            default :
                System.out.println("Invalid number!");
                menu();
                break;
        }

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Az adott tesztesetben a Drill() függvény végrehajtását nézzük, abban az esetben,
     * ha a szublimáló nyersanyagot adunk hozzá.
     */
    void DrillLayerandReachSublimableMaterial() {                   //1.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();

        asteroid.setCharacter(s);

        s.Drill();

    }

    /**
     * A Mine() függvényt nézzük szublimáló nyersanyagra.
     */
    void MineSublimableMaterial() {                                 //2.
        Settler s = new Settler();
        SublimableMaterialName subName = SublimableMaterialName.ICEWATER;

        SublimableMaterial material = new SublimableMaterial();
        material.setName(subName.ICEWATER);
        Asteroid asteroid = new Asteroid();
        asteroid.setCharacter(s);

        s.Mine();

    }

    /**
     * A telepesünket mozgatjuk egy szomszédos aszteroidára.
     */
    void MoveSettlertoAsteroid(){                                   //3.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Asteroid goingTO = new Asteroid();

        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        asteroid.AddNeighbour(goingTO);
        goingTO.AddNeighbour(asteroid);

        s.Move(0);
    }

    /**
     * A telepesünket mozgatjuk egy aszteroidára, teleportkapun keresztül.
     */
    void MoveSettlertoTeleportGate(){                           //4.
        //Logger.getInstance().printCommandCall(this);
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
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A mozgatást hajtjuk végre, miközben napvihar van az aszteroidaövön.
     */
    void MoveSettlerwhilebeinginSunStorm(){                      //5.
        Settler s = new Settler();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Game g = Game.getInstance();
        Asteroid onEmpty = new Asteroid();

        ab.AddAsteroid(onEmpty);
        s.setAsteroid(onEmpty);
        onEmpty.Accept(s);
        ab.StartStorm();

        s.Move(0);

    }

    /**
     * Lehelyeezünk egy szublimáló nyersanyagot.
     */
    void PlaceSublimeMaterial(){                                //6.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        SublimableMaterial sm = new SublimableMaterial();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);

        s.PlaceMaterial(sm);

    }

    /**
     * A telepest lerakja a nála lévő teleprotkaput.
     */
    void SettlerPlaceTeleportGate(){                            //7.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        TeleportGate pair = new TeleportGate();
        TeleportGate tg = new TeleportGate();
        Asteroid b = new Asteroid();

        s.setAsteroid(a);
        a.Accept(s);
        pair.setPair(tg);
        tg.setPair(pair);
        pair.setAsteroid(b);

        s.PlaceGate(tg);

    }

    /**
     * Megépítjük a bázist.
     */
    void BuildBase(){                                           //8.
        Settler s = new Settler();
        Settler s2 = new Settler();
        Asteroid a = new Asteroid();
        Game g = Game.getInstance();

        s.setAsteroid(a);
        a.Accept(s);
        s2.setAsteroid(a);
        a.Accept(s2);

        s.BuildBase();

    }

    /**
     * Építünk egy robotot.
     */
    void BuildRobot(){                                          //9.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);

        s.BuildRobot();
    }

    /**
     * A telepes létrehoz egy teleportkaput.
     */
    void BuildTeleportGate(){                                    //10.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.setAsteroid(a);
        a.Accept(s);

        s.BuildGate();

    }

    /**
     * Fúrunk az aszteroidán egyet, amiben nyersanyag található.
     */
    void DrillLayerandnotreachCore(){                           //11.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        Material m=new Material();
        asteroid.AddMaterial(m);

        s.Drill();

    }

    /**
     * Fúrunk az üreges aszteroidán egyet.
     */
    void DrillLayerandreachemptyCore(){                         //12.
        Asteroid a=new Asteroid();
        Settler s = new Settler();
        s.setAsteroid(a);
        a.Accept(s);

        s.Drill();

    }

    /**
     * Fúrás az aszteroidán.
     */
    void DrillLayerandreachNormalMaterial() {               //13.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Material m = new Material();
        s.setAsteroid(a);
        a.Accept(s);
        a.AddMaterial(m);

        s.Drill();  //-> a.Drilled() -> m.Hit()

    }

    /**
     * Normál nyersanyag bányászása.
     */
    void MineNormalMaterial() {                             //14.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Material m = new Material();
        s.setAsteroid(a);
        a.Accept(s);
        a.AddMaterial(m);

        s.Mine();

    }

    /**
     *
     */
    void DrillLayerandreachRadioactiveMaterial() throws IOException {   //15.
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Asteroid asteroid = new Asteroid();
        Asteroid a = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        Robot r = new Robot();
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();
        RadioactiveMaterial material = new RadioactiveMaterial();

        ab.AddAsteroid(asteroid);
        ab.AddAsteroid(an);
        ab.AddAsteroid(a);
        asteroid.AddNeighbour(an);
        an.AddNeighbour(asteroid);
        tn.setAsteroid(asteroid);
        pair.setAsteroid(a);
        asteroid.AddNeighbour(tn);
        a.AddNeighbour(pair);
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        r.setAsteroid(asteroid);
        asteroid.Accept(r);
        tn.setPair(pair);
        asteroid.AddMaterial(material);
        tn.Activate();
        pair.setPair(tn);
        pair.Activate();

        s.Drill();

    }

    /**
     * Sima nyersanyag bányászása, de
     */
    void PlaceMaterialbutAsteroidisnotemptyordrilledthrough(){      //16.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Material m = new Material();

        s.setAsteroid(asteroid);
        asteroid.Accept(s);

        s.PlaceMaterial(m);

    }

    /**
     * Radioaktiv nyersaynag bányászása.
     */
    void MineRadioactiveMaterial(){                         //17.
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
        asteroid.AddMaterial(material);
        s.setAsteroid(asteroid);

        s.Mine();

    }

    /**
     * Lehelyezünke egy sima nyersanyagot
     */
    void PlaceNormalMaterial(){                             //18.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Material m = new Material();
        NormalMaterialName rmn = NormalMaterialName.IRON;

        s.setAsteroid(asteroid);
        asteroid.Accept(s);

        s.PlaceMaterial(m);

    }

    /**
     * Lehelyezünk egy radioaktav nyersanyagot.
     */
    void PlaceRadioactiveMaterial(){                        //19.
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
        tn.setPair(pair);
        tn.Activate();
        an.AddNeighbour(asteroid);
        pair.Place(a);
        tn.setPair(pair);
        pair.Activate();

        s.PlaceMaterial(rm);

    }

    /**
     * Bányászás, de nincs benne nyersanyag.
     */
    void MinebutCoreisEmpty(){                          //20.
        Asteroid a = new Asteroid();
        Settler s = new Settler();

        a.Accept(s);
        s.setAsteroid(a);

        s.Mine();

    }

    /**
     * Bányászás.
     */
    void MinebutInventoryisFull(){                      //21.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Inventory inventory = new Inventory();
        HashMap<MaterialName, Integer> material = new HashMap<>();
        material.put(NormalMaterialName.IRON, 10);
        inventory.fill(material);

        s.setAsteroid(a);

        s.Mine();
    }
}
