package View;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.GuiController;

public  class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private JButton as;
    private Icon p;
    private JLabel l;
    private int x,y;
    private int compnum = 0;


    public AsteroidView(Asteroid a){
        this.asteroid = a;
        //if (asteroid.GetisEmpty()) {

        int scaling = 130;
        if(a.getLayers()>0) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }
        else if(a.getLayers() == 0) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollowasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }
//        else if(!a.GetisEmpty()){
//            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling,scaling,Image.SCALE_SMOOTH));
//        }
            //as = new JButton(p );
            l = new JLabel(p);

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

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void Draw(GUI g) {
        //as.setPreferredSize(new Dimension(20,20));
        //g.gamespace.setLocation(20,20);

        //as.setLocation(20,20);

        g.gamespace.add(l);
        g.gamespace.getComponent(compnum).setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
    }
    public void getAsteroidCoordsListener(JLabel l, AsteroidView av) {
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x = "+av.x+ "y = " + av.y);
               // GuiController.moveSettler(av.x,av.y);

            }
        });
    }



    public void setCompNum(int n) {
        compnum = n;
    }
}
