package Controller;

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


    public int command=4;
    public int selectedSettler =0;

    public Controller(){
       // InitViews(g);
        Main.settlers.add(new Settler());
        g=new GUI(this);
        g.DrawMenu();
        settlers=Main.settlers;
        InitViews(g);

    }

    public void stepsettlers(){
        Settler s=settlers.get(selectedSettler);
        SettlerView sv ;
        for  (SettlerView sw : g.GetSettlerView()){
            if(sw.getSettler()==s)
                System.out.println("faszfasz");
                //sv=sw;
                //sw.l.setIcon(new ImageIcon(new ImageIcon("Files/Pictures/loadbtn.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        }

    }

    public void NextSettler(){
        selectedSettler++;
    }

    public void InitViews(GUI g){
//        for(int i=0; i<Main.settlers.size();i++){
//            g.addSettler(Main.settlers.get(i));
//        }
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
