package View;
import Model.*;
import Model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public  class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private JButton as;
    private Icon p;
    private JLabel l;
    private int x, y;
    private int compnum = 0;
    private boolean highlight = false;
    int scaling = 130;
    private boolean isInsusntorm = false, exploding = false;


    public AsteroidView(Asteroid a) {
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


//        else if(a.GetisEmpty()){
//            p = new ImageIcon(new ImageIcon("Files/Pictures/hollow.png").getImage().getScaledInstance(scaling,scaling,Image.SCALE_SMOOTH));
//        }


        //as = new JButton(p );


        //as.setRolloverIcon(new ImageIcon("Files/Pictures/explosion.png"));
           /* as.setBorderPainted(false);
            as.setContentAreaFilled(false);
            as.setFocusPainted(false);
            as.setOpaque(false);
*/
        //}


        getAsteroidCoordsListener(l, this);


    }

    public void setImg(){
        if (this.asteroid.getLayers() > 0 && !this.asteroid.GetSunProximity() && !isInsusntorm) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }

        if (this.asteroid.getLayers() > 0 && this.asteroid.GetSunProximity() && !isInsusntorm)
            p = new ImageIcon(new ImageIcon("Files/Pictures/nearsun_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if (this.asteroid.getLayers() == 0 && !this.asteroid.GetSunProximity() && !isInsusntorm) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollowasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }

        if (this.asteroid.getLayers() == 0 && this.asteroid.GetSunProximity() && !isInsusntorm) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollow_nearsun_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }

        if (asteroid.getLayers() > 0 && this.asteroid.GetSunProximity() && isInsusntorm) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/nearsun_and_sunstorm_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }
        if (asteroid.getLayers() == 0 && this.asteroid.GetSunProximity() && isInsusntorm) {

            p = new ImageIcon(new ImageIcon("Files/Pictures/hollow_nearsun_sunstorm_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }
        if(exploding)
        p = new ImageIcon(new ImageIcon("Files/Pictures/explosion.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        l.setIcon(p);
    }

    public void SetCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void Draw() {
        Game.getInstance().c.g.gamespace.add(l);
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        CharacterView cv = new CharacterView(asteroid.getCharacters());
        cv.Draw();

    }


    public void getAsteroidCoordsListener(JLabel l, AsteroidView av) {
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x = " + av.x + "y = " + av.y);
                Game.getInstance().c.MoveSetAsteroid(av);

            }
        });
    }

    public void highlight(boolean b, GUI g) {
        highlight = b;
        setImage();
    }

    private void setImage() {
        if (highlight == true) {
            int scaling = 130;
            p = new ImageIcon(new ImageIcon("Files/Pictures/selectedasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            l.setIcon(p);
        } else {
            int scaling = 130;
            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            l.setIcon(p);
        }
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }


    public void setInSunstorm(boolean bool) {
        this.isInsusntorm = bool;
    }

    public boolean getisInSunstorm(){
        return isInsusntorm;
    }

    public void setExploding() {
        this.exploding = true;
    }
}
