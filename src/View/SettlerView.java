package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class SettlerView implements IDrawable{

    private Settler s;
    private Icon p;
    public JLabel l;
    private int x,y;
    private int compnum;
    private Random rnd = new Random();
    boolean currentlySelected = false;
    private boolean highlighted = false;

//    SettlerView(Settler s){
//        this.s=s;
//        p=new ImageIcon("Files/Pictures/sus.png");
//        l=new JLabel(p);
//       // this.x = 50;
//        //this.y = 50;
//        //MoveToAsteroidListener(l,this,g);

    //}

    SettlerView(Settler _s) {
        p=new ImageIcon(new ImageIcon("Files/Pictures/sus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        l=new JLabel(p);
        s = _s;
    }

//    public void SetCoords(int x, int y){
//        this.x = x;
//        this.y = y;
//    }

    public void Draw(){
        int offsetX = rnd.nextInt(20);
        int offsetY = rnd.nextInt(20);
        x = Game.getInstance().c.g.getX()+offsetX+10;
        y = Game.getInstance().c.g.getY()+offsetY+10;
        //g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        //g.gamespace.getComponent(1).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
       // g.gamespace.getComponent(index).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
        //g.gamespace.getComponent(compnum).setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        //System.out.println(g.GetAsteroidView().indexOf(s.getAsteroid()));
        //index = Arrays.asList(container.getComponents()).indexOf(container.getComponentAt(x, y));

    }

    public void Highlight() {
        highlighted = !highlighted;
        //Game.getInstance().c.g.gamespace.remove(l);
        if(highlighted) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/selectedsus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            l.setIcon(p);
        }
        else {
            p=new ImageIcon(new ImageIcon("Files/Pictures/sus.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            l.setIcon(p);
        }
        //Game.getInstance().c.g.gamespace.add(l);
        //Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        //l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
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

    public void setCompnum(int n) {
        compnum = n;
    }
}
