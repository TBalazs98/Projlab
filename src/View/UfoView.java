package View;

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
        p=new ImageIcon(new ImageIcon("Files/Pictures/ufo.png").getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH));
        l=new JLabel(p);
    }

    @Override
    public void Draw(GUI g) {
        g.gamespace.add(l);
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);
        x = g.getX()+offsetX+40;
        y = g.getY()+offsetY+40;
        g.gamespace.setComponentZOrder(l, 0);
        //g.gamespace.getComponent(compnum).setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
    }

    public void setCompnum(int n) {
        compnum = n;
    }
}
