package View;

import Controller.Main;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class SettlerView implements IDrawable{

    private Settler s;
    private Icon p;
    public JLabel l;
    private int x,y;
    private Random rnd = new Random();
    boolean currentlySelected =false;



    public SettlerView(Settler _s) {
        p=new ImageIcon(new ImageIcon("Files/Pictures/sus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        l=new JLabel(p);
        s = _s;

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
        });

//        Game.getInstance().c.g.gamespace.add(l);
    }

    public void SetCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void Draw(){
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);
       // x = Game.getInstance().c.g.getX()+offsetX+10;
        //y = Game.getInstance().c.g.getY()+offsetY+10;
        //g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        //g.gamespace.getComponent(1).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
       // g.gamespace.getComponent(index).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
        //g.gamespace.getComponent(compnum).setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        l.setBounds(this.x+offsetX, this.y+offsetY, p.getIconWidth(), p.getIconWidth());
        //System.out.println(g.GetAsteroidView().indexOf(s.getAsteroid()));
        //index = Arrays.asList(container.getComponents()).indexOf(container.getComponentAt(x, y));

    }

    public static void MoveToAsteroidListener(JLabel l, SettlerView sv,GUI g){
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("settler"+e.getX());
                //for(AsteroidView av : g.GetAsteroidView()){
                 //  av.getAsteroidCoordsListener(l,av);
                    //System.out.println("settler x = "+sv.x+ "settler y = " + sv.y);
              //  }
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
    public void SetPicture(){
        if(this.currentlySelected==true){
           Icon pp= new ImageIcon(new ImageIcon("Files/Pictures/selectedsus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            l.setIcon(pp);


        }else {
            p= new ImageIcon(new ImageIcon("Files/Pictures/sus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            l.setIcon(p);

        }


    }


}
