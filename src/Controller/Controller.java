package Controller;

import Model.*;
import View.GUI;
import View.SettlerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public  class Controller {
    public GUI g;
    public static ArrayList<Settler> settlers=new ArrayList<>();


    public int command=0;
    public int selectedSettler=0;
    public int mx=0,my=0;

    public Controller(){
        //InitViews(g);
        //Main.settlers.add(new Settler());
        g=new GUI(this);
        g.DrawMenu();
        //settlers=Main.settlers;
        InitViews(g);

    }

    public void stepsettlers(){
        Settler s=Main.settlers.get(selectedSettler%(Main.settlers.size()));
        //SettlerView sv =Game.getInstance().c.g.GetSettlerView().get(selectedSettler%Game.getInstance().c.g.GetSettlerView().size());
        SettlerView sv =Game.getInstance().c.g.getSettlerViewBySettler(s);

        //sv.l.setIcon(new ImageIcon("Files/Pictures/ice.png"));
        //sv.SelectHighlighted(true);
        HighlightEverythingExcept(g.GetSettlerView().get(selectedSettler%(Game.getInstance().c.g.GetSettlerView().size())));
        HighAsteroid(s);
        //switchCommand(s);
        //HighlightAsteroids();


        //if(selectedSettler%settlers.size()!=0)
        NextSettler();
        //sv.SelectHighlighted(false);
       // else
           // Game.getInstance().NextRound();
    }

    public void HighlightEverythingExcept(SettlerView sv){
       // HighlightAsteroids();
       for(int i=0; i<Game.getInstance().c.g.GetSettlerView().size();i++){
           if(Game.getInstance().c.g.GetSettlerView().get(i)==sv)
               Game.getInstance().c.g.GetSettlerView().get(i).SelectHighlighted(true);
           else
               Game.getInstance().c.g.GetSettlerView().get(i).SelectHighlighted(false);
       }
    }

    public void HighAsteroid(Settler sv){
//        for  (DestinationObject o :sv.getSettler().getAsteroid().GetNeighbours()){
//            g.getAsteroidViewByAsteroid((Asteroid)o).highlight(true,g);
//
//        }

//        for(int j=0; j<Main.asteroids.size();j++){
//            System.out.println("J, aminek az értéke [0,1,2]" + j);
//            for(int i=0; i<sv.getAsteroid().GetNeighbours().size();i++){
//                System.out.println("I, aminak az értéke az aktuális aszteroida szomszédaitól fugg"+i);
//                System.out.println("get(j) = "+ Main.asteroids.get(j) + "get(i) = "+ sv.getAsteroid().GetNeighbour(i));
//                if((DestinationObject) Main.asteroids.get(j)==sv.getAsteroid().GetNeighbour(i)){
//                    g.getAsteroidViewByAsteroid(Main.asteroids.get(j)).highlight(true,g);
//                }else
//                    g.getAsteroidViewByAsteroid(Main.asteroids.get(j)).highlight(false,g);
//            }
//        }
//        System.out.println("-----");



        for(Asteroid a : Main.asteroids){
            Game.getInstance().c.g.getAsteroidViewByAsteroid(a).highlight(false,g);
        }
        for(DestinationObject a : sv.getAsteroid().GetNeighbours()){
            Game.getInstance().c.g.getAsteroidViewByAsteroid((Asteroid) a).highlight(true,g);
        }
    }




    public void switchCommand(Settler s){
        g.cp.getActionDone1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (command){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        System.out.println("before"+s.getAsteroid().getLayers());
                        s.Drill();
                        System.out.println("after"+s.getAsteroid().getLayers());
                        System.out.println("------------------");
                        break;
                    case 3:
                        break;
                    case 4:
                        Settler s=Main.settlers.get(selectedSettler%Game.getInstance().c.g.GetSettlerView().size());
                        System.out.println("mx = "+mx+ " my"+ my);
                        break;

                }

            }
        });
    }

    public void printxy(){
        System.out.println("cx = " + mx + " cy = " + my );
    }

    public void NextSettler(){

            selectedSettler++;
    }

    public void InitViews(GUI g){
       for(int i=0; i<Main.settlers.size();i++){
            g.addSettler(Main.settlers.get(i));
        }
        for(int i=0; i<Main.asteroids.size();i++){
            g.addAsteroid(Main.asteroids.get(i));
        }
    }

    public void updateCommand(int commandindex){
        command = commandindex;
        System.out.println(command);
        //g.dp.Update(command);
    }



    public int getCurrentCommand(){return command;}



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
        }
        for(int i = 0; i< C;i++){
            Material m = new Material();
            m.setName(NormalMaterialName.COAL);
            Main.materials.add(m);
        }
        for(int i = 0; i< Ra;i++){
            RadioactiveMaterial rm = new RadioactiveMaterial();
            rm.setName(RadioactiveMaterialName.URAN);
            Main.materials.add(rm);
        }
        for(int i = 0; i< W;i++){
            SublimableMaterial sm = new SublimableMaterial();
            sm.setName(SublimableMaterialName.ICEWATER);
            Main.materials.add(sm);
        }
        //Nyersanyag END.


        //Aszteroidak letrehozas
        for(int i = 0; i< A;i++){
            Asteroid a = new Asteroid();
            Main.asteroids.add(a);
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
            for (int i = 0; i < A; i++) {
                Random rand = new Random();
                int rand_int = rand.nextInt(Main.asteroids.size());
                int db = 0;
                System.out.println(i);
                System.out.println(Main.asteroids.get(i).GetNeighbourCount());
                if (Main.asteroids.get(i).GetNeighbourCount() != -1) {
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
        //Aszteroida END.

        //TeleportGate letrehozas, par beallitas es elhelyezes aszteroidan
        if(T > 0) {
            for (int i = 0; i < T; i++) {
                TeleportGate t = new TeleportGate();
                Main.teleportgates.add(t);
            }

            if(T > 2) {
                int j = 0;
                if (T % 2 == 0) {
                    while (j < T) {
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j++));
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j - 1));
                        j++;
                    }
                } else {
                    while (j < T - 1) {
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j++));
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j - 1));
                        j++;
                    }
                }
            }
            for (int i = 0; i < T; i++)
                while (Main.teleportgates.get(i).GetAsteroid() != null) {
                    Random rand = new Random();
                    int rand_int = rand.nextInt(A);
                    if (Main.teleportgates.get(i).GetPair().GetAsteroid() != Main.asteroids.get(rand_int))
                        Main.teleportgates.get(i).setAsteroid(Main.asteroids.get(rand_int));
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
            SettlerView sv = new SettlerView(s);
            Game.getInstance().c.g.settlers.add(sv);
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
            }
        }

        //lehet nem jó h melyik mi
        Main.Randomize = (datas.get(9) == 0);
    }





}
