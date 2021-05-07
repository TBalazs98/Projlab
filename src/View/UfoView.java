package View;

import Model.UFO;

import javax.swing.*;
import java.awt.*;

public class UfoView implements IDrawable{
    private Icon p;
    private JLabel l;
    private int x,y;
    private UFO u;
    private int compnum;

    UfoView(UFO _u) {
        u = _u;
        p=new ImageIcon(new ImageIcon("Files/Pictures/ufo.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        l=new JLabel(p);
    }

    @Override
    public void Draw(GUI g) {
        g.gamespace.add(l);
        x = g.getX();
        y = g.getY();
        g.gamespace.getComponent(compnum).setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
    }

    public void setCompnum(int n) {
        compnum = n;
    }
}
