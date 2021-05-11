package Controller;

import Model.*;
import View.AsteroidView;
import View.GUI;
import View.SettlerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public  class Controller {
    public GUI g;
    public static ArrayList<Settler> settlers=new ArrayList<>();
    public int selectedSettler=0;
    public Settler s=new Settler();
    public Asteroid a = new Asteroid();
    public ArrayList<DestinationObject> d = new ArrayList<>();

    // TODO
    //      Minden felesleges NextSettler hívást, plusszolást akármit ki kell írtani
    //                      A settler ++ olását csak a NextSettler() végzi
    //       Az aktuális Settler helyes lekérdezését csak és kizárólag a SelectedSettler végzi!!

    public Controller(){
        g=new GUI(this);
        g.DrawMenu();
//        InitViews(g);
    }
    public void DoTheMove(){
        System.out.println("neigh index"+s.getAsteroid().GetNeighbourIndex(a));

        System.out.println("-- leptem : Eddig itt voltam"+s.getAsteroid());
        //s.Move(s.getAsteroid().GetNeighbourIndex(a));
        s.Move(s.getAsteroid().GetNeighbourIndex(d.get(0)));
        System.out.println("++ leptem : ide erkeztem"+s.getAsteroid());
        //NextSettler();      //ezt kell hülyebiztosan lekezelni
        g.DrawSettlers();
    }

    public void MoveSetDestination(DestinationObject ddo){
        System.out.println("valasztottam aszteroidat"+ddo);
        d.clear();
        d.add(ddo);
    }

    public void MoveSetSettler(Settler ss){
        System.out.println("valasztottam settlert"+ss);
        s=ss;
    }

    public void MoveSetAsteroid(AsteroidView av){
        System.out.println("valasztottam aszteroidat"+av);
        a=av.getAsteroid();
    }

    public void HighlightSettlerStuff(){
        //Settler s=Main.settlers.get(SelectedSettler());
       // SettlerView sv =Game.getInstance().c.g.getSettlerViewBySettler(s);


        //HighlightEverythingExcept(g.GetSettlerView().get(SelectedSettler()));
        HighlightEverythingExcept(Game.getInstance().c.g.getSettlerViewBySettler(Main.settlers.get(SelectedSettler())));
        //HighAsteroid(s);
        HighAsteroid(Main.settlers.get(SelectedSettler()));
        //switchCommand(s);
        //HighlightAsteroids();


        //if(selectedSettler%settlers.size()!=0)
        //NextSettler();
        //sv.SelectHighlighted(false);
       // else
           // Game.getInstance().NextRound();
    }

    public void NextSettler(){

        selectedSettler++;
        HighlightSettlerStuff();
    }

    public int SelectedSettler(){
        if(selectedSettler==(Main.settlers.size())){
            selectedSettler=0;
            Game.getInstance().NextRound();
        }
        return selectedSettler;
    }
    public void HandleDrill(){
        Main.settlers.get(SelectedSettler()).Drill();
    }
    public void HandleMine(){
        Main.settlers.get(SelectedSettler()).Mine();      //todo ezt vissza
//        Main.ab.StartStorm(Main.asteroids.get(0));
    }
    public void HandlePlaceMaterial(){                      //todo ezzel mi a helyzet?
        //todo SelectedSettler írtás
//        this.removeA ll();
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        Game.getInstance().c.g.dp.removeAll();
        ArrayList<JButton> materials = new ArrayList<>();
        ArrayList<JButton> gates = new ArrayList<>();
        ArrayList<TeleportGate> tgate = new ArrayList<>();
        ArrayList<Material> mat = new ArrayList<>();

        int db = 0;
        JPanel inventory = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inventory.setBackground(Color.GRAY);

        ImageIcon p = null;
        int scalingx= 50, scalingy = 50;
        for(Material m : Main.settlers.get(SelectedSettler()).GetInventory().GetMaterials()) {
            db++;
            mat.add(m);
            if (m.getName() == NormalMaterialName.IRON) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.getName() == NormalMaterialName.COAL) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.getName() == SublimableMaterialName.ICEWATER) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.getName() == RadioactiveMaterialName.URAN) {
                RadioactiveMaterial rm = (RadioactiveMaterial) m;
                if (rm.GetExposure() == 0)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
                else if (rm.GetExposure() == 1)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
                else if (rm.GetExposure() == 2)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            JButton a = new JButton(p);
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    Main.settlers.get(SelectedSettler()).PlaceMaterial(mat.get(materials.indexOf(a)));
//                    Main.settlers.get(SelectedSettler()).GetInventory().Remove(mat.get(materials.indexOf(a)));
//                    Main.materials.remove(mat.get(materials.indexOf(a)));
                    Main.settlers.get(SelectedSettler()).PlaceMaterial(m);
                }
            });
            materials.add(a);
            inventory.add(a);
        }

        for(TeleportGate t : Main.settlers.get(SelectedSettler()).GetGates()) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            tgate.add(t);
            JButton a = new JButton(p);
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.settlers.get(SelectedSettler()).PlaceGate(tgate.get(tgate.indexOf(a)));
                    Main.teleportgates.remove(tgate.get(gates.indexOf(a)));

                }
            });
            gates.add(a);
            inventory.add(a);
        }
        Game.getInstance().c.g.dp.add(inventory);
        //NextSettler();
        //  this.add(inventory);

        Game.getInstance().c.g.dp.repaint();
        Game.getInstance().c.g.dp.validate();
    }


    public void HighlightEverythingExcept(SettlerView sv){

       for(int i=0; i<Game.getInstance().c.g.GetSettlerView().size();i++){
           if(Game.getInstance().c.g.GetSettlerView().get(i)==sv)
               Game.getInstance().c.g.GetSettlerView().get(i).SelectHighlighted(true);
           else
               Game.getInstance().c.g.GetSettlerView().get(i).SelectHighlighted(false);
       }
    }

    public void HighAsteroid(Settler sv){
        for(Asteroid a : Main.asteroids) {
            Game.getInstance().c.g.getAsteroidViewByAsteroid(a).highlight(false, g);
            System.out.println(Game.getInstance().c.g.getAsteroidViewByAsteroid(a).getAsteroid().getLayers());
            //Game.getInstance().c.g.getAsteroidViewByAsteroid(a).setImage();
        }

        for(TeleportGate tg : Main.teleportgates) {
            Game.getInstance().c.g.getTeleportGateViewByTeleportGate(tg).highlightt(false, g);
            //System.out.println(Game.getInstance().c.g.getAsteroidViewByAsteroid(a).getAsteroid().getLayers());
            //Game.getInstance().c.g.getTeleportGateViewByTeleportGate(tg).setImage();
        }

        for(DestinationObject a : sv.getAsteroid().GetNeighbours()){
            if(a instanceof Asteroid)
                Game.getInstance().c.g.getAsteroidViewByAsteroid((Asteroid) a).highlight(true,g);
            if(a instanceof TeleportGate)
                Game.getInstance().c.g.getTeleportGateViewByTeleportGate((TeleportGate) a).highlightt(true,g);

        }
    }

    public void InitViews(GUI g){
        for(int i=0; i<Main.settlers.size();i++){
            g.addSettler(Main.settlers.get(i));
        }
        for(int i=0; i<Main.asteroids.size();i++){
            g.addAsteroid(Main.asteroids.get(i));
            Game.getInstance().AddSteppable(Main.asteroids.get(i));
        }
        for(int i=0; i<Main.robots.size();i++){
            g.addRobot(Main.robots.get(i));
            Game.getInstance().AddSteppable(Main.robots.get(i));
        }
        for(int i=0; i<Main.ufos.size();i++){
            g.addUfo(Main.ufos.get(i));
            Game.getInstance().AddSteppable(Main.ufos.get(i));
        }
        //System.out.println("TELEPORTGATES SIZE" + Main.teleportgates.size());
        for(int i=0; i<Main.teleportgates.size();i++){
            //System.out.println("TeleportgateIIII" + i);
            g.addTeleportGate(Main.teleportgates.get(i));
            Game.getInstance().AddSteppable(Main.teleportgates.get(i));
        }
        for(int i=0; i<Main.materials.size();i++){
            g.addMaterial(Main.materials.get(i));
        }
    }

    public void BuildRobot(int prev){
        int CurrentNumberOfRobots = Main.robots.size();
        if(CurrentNumberOfRobots > prev){
            g.addRobot(Main.robots.get(Main.robots.size()-1));
        }
        for(int i=0; i<g.GetAsteroidView().size() ;i++){
            g.GetAsteroidView().get(i).Draw();
        }

    }

    public void CreateCustomMap(){
        Main.asteroids.clear();
        this.settlers.clear();
        Main.settlers.clear();
        Main.ufos.clear();
        Main.robots.clear();
        Main.materials.clear();
        Main.teleportgates.clear();
        Game.getInstance().c.g.GetAsteroidView().clear();
        ArrayList<Integer> datas = Game.getInstance().c.g.custompanel.GetCreatecount();
        int I = datas.get(0);
        int C = datas.get(1);
        int Ra = datas.get(2);
        int W = datas.get(3);
        int A = datas.get(4);
        int T = datas.get(5);
        int S = datas.get(6);
        int R = datas.get(7);
        int U = datas.get(8);
        int materials = I+C+Ra+W;

        //Nyersanyagok leterhozasa
        for(int i = 0; i< I;i++){
            Material m = new Material();
            m.setName(NormalMaterialName.IRON);
            Main.materials.add(m);

            Game.getInstance().c.g.addMaterial(m);
        }
        for(int i = 0; i< C;i++){
            Material m = new Material();
            m.setName(NormalMaterialName.COAL);
            Main.materials.add(m);
            Game.getInstance().c.g.addMaterial(m);
        }
        for(int i = 0; i< Ra;i++){
            RadioactiveMaterial rm = new RadioactiveMaterial();
            rm.setName(RadioactiveMaterialName.URAN);
            Main.materials.add(rm);
            Game.getInstance().c.g.addMaterial(rm);
        }
        for(int i = 0; i< W;i++){
            SublimableMaterial sm = new SublimableMaterial();
            sm.setName(SublimableMaterialName.ICEWATER);
            Main.materials.add(sm);
            Game.getInstance().c.g.addMaterial(sm);
        }
        //Nyersanyag END.


        //Aszteroidak letrehozas
        for(int i = 0; i< A;i++){
            Asteroid a = new Asteroid();
            Main.asteroids.add(a);
            Main.ab.AddAsteroid(a);
            Game.getInstance().c.g.addAsteroid(a);
        }
        if(materials > 0) {
            for (int i = 0; i < materials; i++) {
                Main.asteroids.get(i).SetMaterial(Main.materials.get(i));
                Main.asteroids.get(i).SetEmpty(false);
                Main.asteroids.get(i).setLayer(3);
            }
        }

        if(A > 1) {
            int rand_int, randd_int;
            for (int i = 0; i < A; i++) {
                Random randd = new Random();
                 randd_int = randd.nextInt(A/3)+1;
                for(int j=0; j<(randd_int);j++) {
                    Random rand = new Random();
                    rand_int = rand.nextInt(A);
                    int db = 0;
                    System.out.println(i);
                    System.out.println(Main.asteroids.get(i).GetNeighbourCount());
                    if (Main.asteroids.get(i).GetNeighbours() != null) {
                        for (DestinationObject o : Main.asteroids.get(i).GetNeighbours()) {
                            if (o == Main.asteroids.get(rand_int)) {
                                db++;
                            }
                        }
                    }

                    if (db == 0)
                        Main.asteroids.get(i).AddNeighbour(Main.asteroids.get(rand_int));
                }
            }
        }
        //Aszteroida END.

        //TeleportGate letrehozas, par beallitas es elhelyezes aszteroidan
        if(T > 0) {
            System.out.println("Teleportgate count: " + T);
            for (int i = 0; i < T; i++) {
                System.out.println(i);
                TeleportGate t = new TeleportGate();
                Main.teleportgates.add(t);
                Game.getInstance().c.g.addTeleportGate(t);
            }

            if (T >= 2) {
                int j = 0;
                if (T % 2 == 0) {
                    while (j < T) {
                        System.out.println("T1: " + j);
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(++j));
                        System.out.println("T2: " + j);
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j - 1));
                        j++;
                    }
                } else {
                    while (j < (T - 1)) {
                        System.out.println("T1: " + j);
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(++j));
                        System.out.println("T2: " + j);
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j-1));
                        j++;
                    }
                    System.out.println("T3: " + j);
                    Main.teleportgates.get(j).setPlaced(true);
                }

            }

            for (int i = 0; i < T; i++)
                while (Main.teleportgates.get(i).GetAsteroid() == null) {
                    Random rand = new Random();
                    int rand_int = rand.nextInt(A);
                    if (Main.teleportgates.get(i).GetPair() != null) {
                        if (Main.teleportgates.get(i).GetPair().GetAsteroid() != Main.asteroids.get(rand_int)) {
                            Main.teleportgates.get(i).setAsteroid(Main.asteroids.get(rand_int));
                            Main.asteroids.get(rand_int).AddNeighbour(Main.teleportgates.get(i));
                            Main.teleportgates.get(i).setActive(true);
                            Main.teleportgates.get(i).setPlaced(true);
                        }
                    } else {
                        Main.teleportgates.get(i).setAsteroid(Main.asteroids.get(rand_int));
                        Main.asteroids.get(rand_int).AddNeighbour(Main.teleportgates.get(i));
                        Main.teleportgates.get(i).setActive(true);
                        Main.teleportgates.get(i).setPlaced(true);
                    }

                }
        }
        //TeleportGate END.

        //Settler letrehozas, lehejezes
        for(int i = 0; i < S;i++){
            Settler s = new Settler();
            Random rand = new Random();
            int rand_int = rand.nextInt(A);
            s.setAsteroid(Main.asteroids.get(rand_int));
            Main.asteroids.get(rand_int).setCharacter(s);
            Main.settlers.add(s);
            Game.getInstance().c.g.addSettler(s);

        }

        //Robot letrehozas, lehejezes
        if(R > 0) {
            for (int i = 0; i < R; i++) {
                Model.Robot r = new Model.Robot();
                Random rand = new Random();
                int rand_int = rand.nextInt(A);
                r.setAsteroid(Main.asteroids.get(rand_int));
                Main.asteroids.get(rand_int).setCharacter(r);
                Main.robots.add(r);
                Game.getInstance().c.g.addRobot(r);
            }
        }

        //UFO letrehozas, lehejezes
        if(U > 0) {
            for (int i = 0; i < U; i++) {
                UFO u = new UFO();
                Random rand = new Random();
                int rand_int = rand.nextInt(A);
                u.setAsteroid(Main.asteroids.get(rand_int));
                Main.asteroids.get(rand_int).setCharacter(u);
                Main.ufos.add(u);
                Game.getInstance().c.g.addUfo(u);
            }
        }

        //lehet nem jó h melyik mi
        Main.Randomize = (datas.get(9) == 0);
    }


}
