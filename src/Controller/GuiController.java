package Controller;

import View.GUI;

public  class GuiController {
    int command;

    public GuiController(GUI g){
       // InitViews(g);

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
