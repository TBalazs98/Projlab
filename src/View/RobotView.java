package View;

import Model.Game;
import Model.Robot;
import Model.UFO;
import com.sun.source.tree.IdentifierTree;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RobotView implements IDrawable {
    private Icon p;
    private JLabel l;
    private int x,y;
    private Robot r;
    private Random rnd = new Random();

    RobotView(Robot _r) {
        r = _r;
        p=new ImageIcon(new ImageIcon("Files/Pictures/robot.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        l=new JLabel(p);
    }

    @Override
    public void Draw() {
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);

        Game.getInstance().c.g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        l.setBounds(this.x+offsetX, this.y+offsetY, p.getIconWidth(), p.getIconWidth());

    }
    public void SetCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

}
