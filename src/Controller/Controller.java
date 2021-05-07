package Controller;

import Model.Settler;
import View.GUI;

import java.util.ArrayList;

public  class Controller {
    GUI g= new GUI();
    public static ArrayList<Settler> settlers=new ArrayList<>();





    int command;

    public Controller(GUI g){
       // InitViews(g);
        settlers=Main.settlers;
    }

    public void InitViews(GUI g){
        for(int i=0; i<Main.settlers.size();i++){
            g.addSettler(Main.settlers.get(i));
        }
        for(int i=0; i<Main.asteroids.size();i++){
            g.addAsteroid(Main.asteroids.get(i));
        }
    }

    public void updateCommand(GUI g){
        command = g.cp.getCurrentCommand();
        System.out.println(command);
    }

    public void moveSettler(int x, int  y){


    }







}
