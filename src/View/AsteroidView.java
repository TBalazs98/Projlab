package View;
import Model.*;

import javax.swing.*;
import java.awt.*;

public class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private JButton as;
    private ImageIcon p;



    public AsteroidView(Asteroid a){
        this.asteroid = a;
        //if (asteroid.GetisEmpty()) {
            p = new ImageIcon("Files/Pictures/hollowasteroid.png");
            as = new JButton(p );
            as.setRolloverIcon(new ImageIcon("Files/Pictures/explosion.png"));
            as.setBorderPainted(false);
            as.setContentAreaFilled(false);
            as.setFocusPainted(false);
            as.setOpaque(false);

        //}

    }

    public void Draw(GUI g){
        //as.setPreferredSize(new Dimension(20,20));
        //g.gamespace.setLocation(20,20);

        //as.setLocation(20,20);
        g.gamespace.add(as);
        g.gamespace.getComponent(0).setBounds(g.x,g.y,p.getIconWidth(),p.getIconWidth());




    }
}
