package View;

import Model.Game;
import Model.UFO;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UfoView implements IDrawable{
    private Icon p;
    private JLabel l;
    private int x,y;
    private UFO u;
    private int compnum;
    private Random rnd = new Random();

    UfoView(UFO _u) {
        u = _u;
        p=new ImageIcon(new ImageIcon("Files/Pictures/ufo.png").getImage().getScaledInstance(20, 30, Image.SCALE_SMOOTH));
        l=new JLabel(p);
    }

    @Override
    public void Draw() {
        Game.getInstance().c.g.gamespace.add(l);
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);
        x = Game.getInstance().c.g.getX()+offsetX+40;
        y = Game.getInstance().c.g.getY()+offsetY+40;
        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        //g.gamespace.getComponent(compnum).setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
    }

    public void setCompnum(int n) {
        compnum = n;
    }
}
