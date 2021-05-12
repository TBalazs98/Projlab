package View;

import Controller.Main;
import Model.*;
import Model.Robot;
import com.sun.source.tree.IdentifierTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Robot megjelenitese
 */
public class RobotView implements IDrawable {
    private Icon p;
    public JLabel l;
    private int x,y;
    private Robot r;
    private Random rnd = new Random();
    private boolean firstDraw = true;

    RobotView(Robot _r) {
        r = _r;
        p=new ImageIcon(new ImageIcon("Files/Pictures/robot.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        l=new JLabel(p);

        /*l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Game.getInstance().c.g.dp.removeAll();
                JPanel info = new JPanel(new GridLayout(1,5));
                info.add(new JLabel("[ROBOT]"));
                info.add(new JLabel("\nR" + (Main.robots.indexOf(r)+1)));
                info.add(new JLabel("\nASTEROID:\nA" + (Main.asteroids.indexOf(r.getAsteroid()) +1) ));
                Game.getInstance().c.g.dp.add(info);
                Game.getInstance().c.g.dp.repaint();
                Game.getInstance().c.g.dp.validate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Game.getInstance().c.g.dp.removeAll();
                Game.getInstance().c.g.dp.repaint();
                Game.getInstance().c.g.dp.validate();
            }
        });*/
    }

    /**
     * Kirajzolas
     */
    @Override
    public void Draw() {
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( r.getAsteroid());
        SetCoords(av.getX(),av.getY());


        Game.getInstance().c.g.gamespace.add(l,1);
//        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());

    }

    /**
     * Koordinatak beallitas
     * @param x
     * @param y
     */
    public void SetCoords(int x, int y){
        int offsetX = rnd.nextInt(10)+50;
        int offsetY = rnd.nextInt(10)+50;
        this.x = x+offsetX;
        this.y = y+offsetY;
    }
    public Robot getRobot(){
        return this.r;
    }

}
