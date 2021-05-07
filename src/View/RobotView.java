package View;

import Model.Robot;
import Model.UFO;
import com.sun.source.tree.IdentifierTree;

import javax.swing.*;
import java.awt.*;

public class RobotView implements IDrawable {
    private Icon p;
    private JLabel l;
    private int x,y;
    private Robot r;
    private int compnum;

    RobotView(Robot _r) {
        r = _r;
        p=new ImageIcon(new ImageIcon("Files/Pictures/robot.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
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
