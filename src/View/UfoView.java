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
    private Random rnd = new Random();

    UfoView(UFO _u) {
        u = _u;
        p=new ImageIcon(new ImageIcon("Files/Pictures/ufo.png").getImage().getScaledInstance(20, 30, Image.SCALE_SMOOTH));
        l=new JLabel(p);
    }

    public void SetCoords(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void Draw() {
        Game.getInstance().c.g.gamespace.add(l);
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);

        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        l.setBounds(this.x+offsetX, this.y+offsetY, p.getIconWidth(), p.getIconWidth());
    }

}
