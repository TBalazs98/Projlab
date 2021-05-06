package View;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.GuiController;

public  class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private JButton as;
    private ImageIcon p;
    private JLabel l;
    public int x,y;
    private int valami=1;

    public AsteroidView(Asteroid a){
        this.asteroid = a;
        //if (asteroid.GetisEmpty()) {
        if(a.GetisEmpty() && a.getLayers()>0)
            p = new ImageIcon("Files/Pictures/hollowasteroid.png");
        else if(a.GetisEmpty() && a.getLayers() == 0)
            p = new ImageIcon("Files/Pictures/hollowasteroid.png");
        else if(!a.GetisEmpty()){
            p = new ImageIcon("Files/Pictures/asteroid.png");
        }
        //as = new JButton(p );
        l = new JLabel( p);
        this.x = 100;
        this.y = 100;

        //as.setRolloverIcon(new ImageIcon("Files/Pictures/explosion.png"));
           /* as.setBorderPainted(false);
            as.setContentAreaFilled(false);
            as.setFocusPainted(false);
            as.setOpaque(false);
*/
        //}
        getAsteroidCoordsListener(l,this);


    }

    public void SetCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void Draw(GUI g){
        //as.setPreferredSize(new Dimension(20,20));
        //g.gamespace.setLocation(20,20);

        //as.setLocation(20,20);

        g.gamespace.add(l);
       // g.gamespace.getComponent(0).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
        l.setBounds(this.x,this.y,p.getIconWidth(),p.getIconHeight());

    }

    public  void getAsteroidCoordsListener(JLabel l, AsteroidView av) {
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x = "+av.x+ "y = " + av.y);
                av.valami=13423;
               // GuiController.moveSettler(av.x,av.y);

            }
        });
    }


}


