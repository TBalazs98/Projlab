package View;

import Model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SettlerView implements IDrawable{

    private Settler s;
    private ImageIcon p;
    public JLabel l;
    private int x,y;

    SettlerView(Settler s,GUI g){
        this.s=s;
        p=new ImageIcon("Files/Pictures/sus.png");
        l=new JLabel(p);
        this.x = 50;
        this.y = 50;
        MoveToAsteroidListener(l,this,g);

    }

    public void SetCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void Draw(GUI g){


        g.gamespace.add(l);
        //g.gamespace.getComponent(1).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
       // g.gamespace.getComponent(index).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());

        l.setBounds(this.x,this.y,p.getIconWidth(),p.getIconHeight());
        System.out.println(g.GetAsteroidView().indexOf(s.getAsteroid()));
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

}
