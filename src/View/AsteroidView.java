package View;
import Model.*;

import javax.swing.*;

public class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private JButton as;


    public AsteroidView(Asteroid a){
        this.asteroid = a;
        //if (asteroid.GetisEmpty()) {
            as = new JButton( new ImageIcon("Files/Pictures/hollowasteroid.png"));

        //}

    }

    public void Draw(GUI g){
        g.gamespace.setLocation(20,20);
        g.gamespace.add(as);




    }
}
