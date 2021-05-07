package View;

import Model.Character;
import Model.Robot;
import Model.Settler;
import Model.UFO;

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

    public void Draw(GUI _g) {
        g = _g;
        compnum = g.getCompnum();
        for(int i = 0; i < chars.size(); i++) {
            if(chars.get(i) instanceof Settler) {
                Call((Settler)chars.get(i));
            }
            if(chars.get(i) instanceof Robot) {
                Call((Robot)chars.get(i));
            }
            if(chars.get(i) instanceof UFO) {
                Call((UFO)chars.get(i));
            }

            compnum++;
        }
    }

    private void Call(Settler s){
        SettlerView sv = new SettlerView(s);
        sv.setCompnum(compnum);
        sv.Draw(g);
    }

    private void Call(UFO u) {
        UfoView uv = new UfoView(u);
        uv.setCompnum(compnum);
        uv.Draw(g);
    }

    private void Call(Robot r) {
        RobotView rv = new RobotView(r);
        rv.setCompnum(compnum);
        rv.Draw(g);
    }
}
