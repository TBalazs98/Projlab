package Controller;

import Model.Settler;
import View.GUI;

import java.util.ArrayList;

public  class Controller {
    public GUI g;
    public static ArrayList<Settler> settlers=new ArrayList<>();


    public int command=4;
    public int selectedSettler = 0;

    public Controller(){
       // InitViews(g);
        g=new GUI(this);
        g.DrawMenu();
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

    public void updateCommand(int commandindex){
        command = commandindex;
        System.out.println(command);
        //g.dp.Update(command);
    }

    public void switchCommand(){
        System.out.println("switchcommand");
        switch (command){
            case 4:
                //g.GetAsteroidView().get(0).highlight(g);
        }
    }

    public int getCurrentCommand(){return command;}

    public void moveSettler(int x, int  y){


    }







}
