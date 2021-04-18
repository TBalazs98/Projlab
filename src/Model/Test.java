package Model;

import java.util.HashMap;
import java.util.Scanner;


/**
 * Tesztesetek osztalya
 */
public class Test {

    /**
     * A tesztesetek megvalositasahoz letrehozott menuszerkezet.
     * Itt valaszthatnak az egyes felhasznalok mely teszteset hajtodjon vegre.
     */
    void menu() {
        Scanner in = new Scanner(System.in);
        int testcase ;
        do {
        //region printek
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
        //endregion printek

        System.out.println("\n? Which test case would you like to run?");

         testcase = in.nextInt();

            switch (testcase) {
                case 1:
                    DrillLayerandReachSublimableMaterial();
                    break;
                case 2:
                    MineSublimableMaterial();
                    break;
                case 3:
                    MoveSettlertoAsteroid();
                    break;
                case 4:
                    MoveSettlertoTeleportGate();
                    break;
                case 5:
                    MoveSettlerwhilebeinginSunStorm();
                    break;
                case 6:
                    PlaceSublimeMaterial();
                    break;
                case 7:
                    SettlerPlaceTeleportGate();
                    break;
                case 8:
                    BuildBase();
                    break;
                case 9:
                    BuildRobot();
                    break;
                case 10:
                    BuildTeleportGate();
                    break;
                case 11:
                    DrillLayerandnotreachCore();
                    break;
                case 12:
                    DrillLayerandreachemptyCore();
                    break;
                case 13:
                    DrillLayerandreachNormalMaterial();
                    break;
                case 14:
                    MineNormalMaterial();
                    break;
                case 15:
                    DrillLayerandreachRadioactiveMaterial();
                    break;
                case 16:
                    PlaceMaterialbutAsteroidisnotemptyordrilledthrough();
                    break;
                case 17:
                    MineRadioactiveMaterial();
                    break;
                case 18:
                    PlaceNormalMaterial();
                    break;
                case 19:
                    PlaceRadioactiveMaterial();
                    break;
                case 20:
                    MinebutCoreisEmpty();
                    break;
                case 21:
                    MinebutInventoryisFull();
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

    /**
     * Az adott tesztesetben a Drill() fuggveny vegrehajtasat nezzuk, abban az esetben,
     * ha a szublimalo nyersanyagot talalhato benne.
     */
    void DrillLayerandReachSublimableMaterial() {                   //1.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        SublimableMaterial m = new SublimableMaterial();

        m.setName(SublimableMaterialName.ICEWATER);
        asteroid.setCharacter(s);
        asteroid.AddMaterial(m);
        s.addAsteroid(asteroid);

        s.Drill();
    }

    /**
     * A Mine() fuggvenyt hívjuk meg szubkimalo nyersanyagra.
     */
    void MineSublimableMaterial() {                                 //2.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        SublimableMaterial material = new SublimableMaterial();

        material.setName(SublimableMaterialName.ICEWATER);
        asteroid.Accept(s);
        s.setAsteroid(asteroid);
        asteroid.AddMaterial(material);

        s.Mine();

    }

    /**
     * A telepesunket mozgatjuk egy szomszsdos aszteroidara.
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
     * A telepesunket mozgatjuk egy aszteroidara, teleportkapun keresztul.
     * Mindketto le van helyezve es aktivak.
     */
    void MoveSettlertoTeleportGate(){                           //4.

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
        a.Accept(s);
        a.setNeighbour(tg);
        b.setNeighbour(pair);

        s.Move(0);

    }

    /**
     * A mozgatast hajtjuk vegre, mikozben napvihar van az aszteroidaovon.
     */
    void MoveSettlerwhilebeinginSunStorm(){                      //5.
        Settler s = new Settler();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Game g = Game.getInstance();
        Asteroid onEmpty = new Asteroid();
        Asteroid a = new Asteroid();

        ab.AddAsteroid(onEmpty);
        s.setAsteroid(onEmpty);
        onEmpty.AddNeighbour(a);
        a.AddNeighbour(onEmpty);
        onEmpty.Accept(s);
        onEmpty.setLayer(5);

       // ab.StartStorm();

        s.Move(0);

    }

    /**
     * Lehelyezunk egy szublimalo nyersanyagot.
     */
    void PlaceSublimeMaterial(){                                //6.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        SublimableMaterial sm = new SublimableMaterial();

        sm.setName(SublimableMaterialName.ICEWATER);
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        s.AddMaterial(sm);

        s.PlaceMaterial(sm);

    }

    /**
     * A telepes lerakja a nala levo teleprotkaput, aminek a parja mar le van helyezve.
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
        b.AddNeighbour(pair);
        s.AddGate(tg);

        s.PlaceGate(tg);

    }

    /**
     * Felepul a bazis, ha a minden nyersanyag rendellkezesre all.
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
     * Felepul egy robot, ha minden nyersanyag rendelkezesre all.
     */
    void BuildRobot(){                                          //9.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();

        s.setAsteroid(asteroid);
        asteroid.Accept(s);

        s.BuildRobot();
    }

    /**
     * A telepes letrehoz egy teleportkapu part, ha minden nyersanyag rendelkezesre all.
     */
    void BuildTeleportGate(){                                    //10.
        Settler s = new Settler();
        Asteroid a = new Asteroid();

        s.setAsteroid(a);
        a.Accept(s);

        s.BuildGate();

    }

    /**
     * Furas egy aszterodian, ahol nem erjuk el a nyersanyagot.
     */
    void DrillLayerandnotreachCore(){                           //11.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Material m=new Material();

        m.setName(NormalMaterialName.IRON);
        asteroid.setLayer(5);
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        asteroid.AddMaterial(m);

        s.Drill();

    }

    /**
     * Furas egy ureges aszteroidan.
     */
    void DrillLayerandreachemptyCore(){                         //12.
        Asteroid a=new Asteroid();
        Settler s = new Settler();

        s.setAsteroid(a);
        a.Accept(s);

        s.Drill();

    }

    /**
     * Furas az aszteroidan.
     */
    void DrillLayerandreachNormalMaterial() {               //13.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Material m = new Material();

        m.setName(NormalMaterialName.COAL);
        s.setAsteroid(a);
        a.Accept(s);
        a.AddMaterial(m);
        a.setLayer(1);

        s.Drill();  //-> a.Drilled() -> m.Hit()

    }

    /**
     * Normal nyersanyag banyaszasa.
     */
    void MineNormalMaterial() {                             //14.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Material m = new Material();

        m.setName(NormalMaterialName.IRON);
        s.setAsteroid(a);
        a.Accept(s);
        a.AddMaterial(m);

        s.Mine();

    }

    /**
     * Furunk, ezzel elerjuk a radioaktiv magot.
     */
    void DrillLayerandreachRadioactiveMaterial() {   //15.
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Asteroid asteroid = new Asteroid();
        Asteroid a = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        Robot r = new Robot();
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();
        RadioactiveMaterial material = new RadioactiveMaterial();

        material.setName(RadioactiveMaterialName.URAN);
        asteroid.AddNeighbour(an);
        asteroid.AddNeighbour(tn);
        asteroid.Accept(s);
        asteroid.Accept(r);
        asteroid.AddMaterial(material);
        r.setAsteroid(asteroid);
        s.setAsteroid(asteroid);
        ab.AddAsteroid(asteroid);
        ab.AddAsteroid(a);
        ab.AddAsteroid(an);
        tn.setAsteroid(asteroid);
        tn.setPair(pair);
        tn.Activate();
        an.AddNeighbour(asteroid);
        pair.setAsteroid(a);
        a.AddNeighbour(pair);
        pair.setPair(tn);
        pair.Activate();

        s.Drill();

    }

    /**
     * Sima nyersanyag banyaszasa, egy nem atfurt aszteroidan.
     */
    void PlaceMaterialbutAsteroidisnotemptyordrilledthrough(){      //16.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Material m = new Material();

        m.setName(NormalMaterialName.COAL);
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        asteroid.setLayer(5);

        s.AddMaterial(m);
        s.PlaceMaterial(m);

    }

    /**
     * Radioaktiv nyersaynag banyaszasa.
     */
    void MineRadioactiveMaterial(){                         //17.
        Game g = Game.getInstance();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        Asteroid asteroid = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        RadioactiveMaterial material = new RadioactiveMaterial();
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();

        material.setName(RadioactiveMaterialName.URAN);
        asteroid.Accept(s);
        asteroid.AddNeighbour(an);
        an.AddNeighbour(asteroid);
        asteroid.AddMaterial(material);
        s.setAsteroid(asteroid);
        asteroid.setLayer(0);

        s.Mine();

    }

    /**
     * Lehelyezunk egy sima nyersanyagot.
     */
    void PlaceNormalMaterial(){                             //18.
        Settler s = new Settler();
        Asteroid asteroid = new Asteroid();
        Material m = new Material();

        m.setName(NormalMaterialName.IRON);
        s.setAsteroid(asteroid);
        asteroid.Accept(s);
        s.AddMaterial(m);

        s.PlaceMaterial(m);

    }

    /**
     * Lehelyezunk egy radioaktiv nyersanyagot.
     */
    void PlaceRadioactiveMaterial() {                        //19.
        Asteroid asteroid = new Asteroid();
        Asteroid a = new Asteroid();
        Asteroid an = new Asteroid();
        Settler s = new Settler();
        RadioactiveMaterial rm = new RadioactiveMaterial();
        rm.setName(RadioactiveMaterialName.URAN);
        Robot r = new Robot();
        AsteroidBelt ab = AsteroidBelt.getInstance();
        TeleportGate tn = new TeleportGate();
        TeleportGate pair = new TeleportGate();

        asteroid.AddNeighbour(an);
        asteroid.AddNeighbour(tn);
        asteroid.Accept(s);
        asteroid.Accept(r);
        r.setAsteroid(asteroid);
        s.setAsteroid(asteroid);
        ab.AddAsteroid(asteroid);
        ab.AddAsteroid(a);
        ab.AddAsteroid(an);
        tn.setAsteroid(asteroid);
        tn.setPair(pair);
        tn.Activate();
        an.AddNeighbour(asteroid);
        pair.setAsteroid(a);
        a.AddNeighbour(pair);
        pair.setPair(tn);
        pair.Activate();
        s.AddMaterial(rm);

        s.PlaceMaterial(rm);

    }

    /**
     * Banyaszas, de nincs benne nyersanyag.
     */
    void MinebutCoreisEmpty(){                          //20.
        Asteroid a = new Asteroid();
        Settler s = new Settler();

        a.Accept(s);
        s.setAsteroid(a);

        s.Mine();

    }

    /**
     * Banyaszas, de az inventori tele.
     */
    void MinebutInventoryisFull(){                      //21.
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Inventory inventory = new Inventory();
        HashMap<MaterialName, Integer> material = new HashMap<>();
        material.put(NormalMaterialName.IRON, 10);
        inventory.fill(material);

        s.setInventory(inventory);
        s.setAsteroid(a);
        a.setLayer(0);

        s.Mine();
    }
}
