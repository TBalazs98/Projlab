package View;

import Model.*;
import Model.Character;

import javax.swing.*;
import java.util.ArrayList;

public class CharacterView implements IDrawable{
    private Icon p;
    private JLabel l;
    private int x,y;
    private int compnum = 0;
    private ArrayList<Character> chars;
    private GUI g;

    CharacterView(ArrayList<Character> ch) {
        chars = ch;

    }

    public void Draw() {
        g = Game.getInstance().c.g;

        for(int i = 0; i < chars.size(); i++) {
//            if(chars.get(i) instanceof Settler) {
//                Call((Settler)chars.get(i));
//            }
            if(chars.get(i) instanceof Robot) {
                Call((Robot)chars.get(i));
            }
            if(chars.get(i) instanceof UFO) {
                Call((UFO)chars.get(i));
            }

        }
    }

    private void Call(Settler s) {
//        SettlerView sv = null;
//        for(int i = 0; i < g.settlers.size(); i++) {
//            if(g.settlers.get(i).getSettler() == s) {
//                sv = g.settlers.get(i);
//            }
//        }
//        if(sv == null) {
//            sv = new SettlerView(s);
//            g.settlers.add(sv);
//        }
        SettlerView sv = new SettlerView(s);
        // Game.c.g.GetSettlerView().get(i).SetCoords(getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getX(),getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getY());
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid(s.getAsteroid());
        if (av != null){
            sv.SetCoords(av.getX(), av.getY());
            sv.Draw();
        }
    }

    private void Call(UFO u) {
        UfoView uv =Game.getInstance().c.g.getUfoViewByUfo(u);
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( u.getAsteroid());
        if(av!=null) {
            uv.SetCoords(av.getX(), av.getY());
            uv.Draw();
        }
    }

    private void Call(Robot r) {
        RobotView rv = Game.getInstance().c.g.getRobotViewByRobot(r);
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( r.getAsteroid());
        if(av !=null) {
            rv.SetCoords(av.getX(), av.getY());
            rv.Draw();
        }
    }




}
