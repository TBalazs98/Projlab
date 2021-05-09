package Controller;

import Model.Asteroid;
import Model.DestinationObject;
import Model.Game;
import Model.Settler;
import View.GUI;
import View.SettlerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

 //   public void HighlightAsteroids(){
//        for(int i=0; i< Main.settlers.size();i++){
//            for(int j=0; j<Main.asteroids.size();j++){
//                if(isNeighbour(Main.settlers.get(i),Main.asteroids.get(j))){
//                    Game.getInstance().c.g.getAsteroidViewByAsteroid(Main.asteroids.get(j)).highlight(true,g);
//                }
//                Game.getInstance().c.g.getAsteroidViewByAsteroid(Main.asteroids.get(j)).highlight(false,g);
//            }
//
//        }
       // Settler s=Main.settlers.get(selectedSettler%(Game.getInstance().c.g.GetSettlerView().size()));

//            for(int j=0; j<Main.asteroids.size();j++){
//
//                if(isNeighbour(Main.settlers.get(selectedSettler%(Game.getInstance().c.g.GetSettlerView().size())), Main.asteroids.get(j))){
//                    System.out.println("faszomat a jebe " + j);
//                    Game.getInstance().c.g.getAsteroidViewByAsteroid(Main.asteroids.get(j)).highlight(true,g);
//                }
//                Game.getInstance().c.g.getAsteroidViewByAsteroid(Main.asteroids.get(j)).highlight(false,g);
//            }

  //  }

    public boolean isNeighbour(Settler s , DestinationObject d){
        //System.out.println("----"+s.getAsteroid().GetNeighbours().contains(d));

        for(int i=0; i< s.getAsteroid().GetNeighbours().size();i++){
            if(s.getAsteroid().GetNeighbours().get(i)==d){
                System.out.println("----isneighbour");
                return true;
            }
        }
        return false;
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









}
