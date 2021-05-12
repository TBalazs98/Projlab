package View;

import Controller.Main;
import Model.Game;
import Model.Material;
import Model.TeleportGate;
import Model.UFO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        l.addMouseListener(new MouseListener() {
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
                info.add(new JLabel("[UFO]"));
                info.add(new JLabel("\nU" + (Main.ufos.indexOf(u) + 1)));
                info.add(new JLabel("\nASTEROID:\nA" + (Main.asteroids.indexOf(u.getAsteroid()) + 1)));
                info.add(new JLabel("\nINVENTORY:"));
                if (u.GetInventory().GetMaterials().size() > 0) {
                    for (Material m : u.GetInventory().GetMaterials())
                        info.add(new JLabel("\nM" + (Main.materials.indexOf(m) + 1)));
                } else {
                    info.add(new JLabel("\nINVENTORY IS EMPTY"));

                    Game.getInstance().c.g.dp.add(info);
                    Game.getInstance().c.g.dp.repaint();
                    Game.getInstance().c.g.dp.validate();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Game.getInstance().c.g.dp.removeAll();
                Game.getInstance().c.g.dp.repaint();
                Game.getInstance().c.g.dp.validate();
            }
        });
//        Game.getInstance().c.g.gamespace.add(l);
    }

    public void SetCoords(int x, int y){
        int offsetX = rnd.nextInt(31)+50;
        int offsetY = rnd.nextInt(31)+50;
        this.x = x+offsetX;
        this.y = y+offsetY;
    }
    @Override
    public void Draw() {
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( u.getAsteroid());
        SetCoords(av.getX(),av.getY());


        //Game.getInstance().c.g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.add(l,1);

//        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 1);
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
    }

    public UFO getUfo(){return this.u;}

}
