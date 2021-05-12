package View;

import Controller.Main;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


/**
 * Settler megjelenitese.
 */
public class SettlerView implements IDrawable{

    private Settler s;
    private Icon p;
    public JLabel l = new JLabel();
    private int x,y;
    private Random rnd = new Random();
    boolean currentlySelected =false;
    private boolean firstDraw = true;



    public SettlerView(Settler _s) {
        p=new ImageIcon(new ImageIcon("Files/Pictures/sus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        l=new JLabel(p);
        s = _s;



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
                info.add(new JLabel("[SETTLER]"));
                info.add(new JLabel("\nS" + (Main.settlers.indexOf(s)+1)));
                info.add(new JLabel("\nASTEROID:\nA" + (Main.asteroids.indexOf(s.getAsteroid()) +1) ));
                info.add(new JLabel("\nINVENTORY:"));
                if(s.GetInventory().GetMaterials().size() > 0){
                    for(Material m : s.GetInventory().GetMaterials())
                        info.add(new JLabel("\nM" + (Main.materials.indexOf(m) +1)));
                }else {
                    info.add(new JLabel("\nINVENTORY IS EMPTY"));
                }
                info.add(new JLabel("\nTELEPORTGATES:"));
                if(s.GetGates().size() > 0){
                    for(TeleportGate tg : s.GetGates())
                        info.add(new JLabel("\nG" + (Main.teleportgates.indexOf(tg) +1)));
                }else {
                    info.add(new JLabel("\nNO TELEPORTGATES"));
                }

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
     * Koordinatak beallitasa.
     * @param x
     * @param y
     */
    public void SetCoords(int x, int y){
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);
        this.x = x + offsetX;
        this.y = y + offsetY;
    }

    /**
     * Kirajzolas
     */
    public void Draw() {

            AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( s.getAsteroid());
            SetCoords(av.getX(),av.getY());


        if(p == null){
            l.setBounds(this.x,this.y,0,0);
        }else {
            l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        }


    }

    /**
     * Settler aszteroida mozgatasa
     * @param l
     * @param sv
     * @param g
     */
    public static void MoveToAsteroidListener(JLabel l, SettlerView sv,GUI g){
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("settler"+e.getX());

            }
        });
    }

    public void step(){



    }
    public Settler getSettler(){return this.s;}

    public void SelectHighlighted(boolean b){
        currentlySelected = b;
        SetPicture();
    }

    /**
     * Kep beallitasa
     */
    public void SetPicture() {
        if (this.currentlySelected == true) {
            Icon pp = new ImageIcon(new ImageIcon("Files/Pictures/selectedsus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            l.setIcon(pp);
        } else {
            p = new ImageIcon(new ImageIcon("Files/Pictures/sus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            l.setIcon(p);
        }
        if (Game.getInstance().c.g.getAsteroidViewByAsteroid(s.getAsteroid()).getExpoloding())
            l.setIcon(null);

    }
}
