package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SettingsPanel extends JPanel {
    public ChoserPanel asteroid;
    final private GUI gui;
    private ChoserPanel settler;
    private ChoserPanel robot;
    private ChoserPanel ufo;
    private ChoserPanel coal;
    private ChoserPanel iron;
    private ChoserPanel water;
    private ChoserPanel uran;
    private ChoserPanel portal;
    private JComboBox randbox;
    private int asteroidnumber = 12;



    public SettingsPanel(GUI g){
        gui = g;

        this.setPreferredSize(new Dimension(g.height/2,g.height/2+g.height/4));
        this.setOpaque(true);
        this.setLayout(new GridLayout(12,1));

        JLabel up = new JLabel("New Game settings");
        up.setHorizontalAlignment(JLabel.CENTER);
        this.add(up);

        Vector<Integer> vector = vector_zero(12,50);
        asteroid = new ChoserPanel(g, vector, "Asteroids", this);
        this.add(asteroid);

        vector = vector_zero(2,10);
        settler = new ChoserPanel(g, vector, "Settlers", this);
        this.add(settler);

        vector = vector_zero(3,10);
        robot = new ChoserPanel(g, vector, "Robots", this);
        this.add(robot);

        vector = vector_zero(3,10);
        ufo = new ChoserPanel(g, vector, "UFOs", this);
        this.add(ufo);

        vector = vector_zero(3,asteroidnumber/4);
        coal = new ChoserPanel(g, vector, "Coal", this);
        this.add(coal);

        vector = vector_zero(3,asteroidnumber/4);
        iron = new ChoserPanel(g, vector, "Iron", this);
        this.add(iron);

        vector = vector_zero(3,asteroidnumber/4);
        water = new ChoserPanel(g, vector, "Water", this);
        this.add(water);

        vector = vector_zero(3,asteroidnumber/4);
        uran = new ChoserPanel(g, vector, "Uran", this);
        this.add(uran);

        vector = vector_zero(3,10);
        portal = new ChoserPanel(g, vector, "Portals", this);
        this.add(portal);

        Vector<Boolean> rand = new Vector<>();
        rand.add(true);
        rand.add(false);

        randbox = new JComboBox(rand);
        JPanel random = new JPanel();
        JLabel label = new JLabel("Random");
        label.setHorizontalAlignment(JLabel.CENTER);
        random.setPreferredSize(new Dimension(g.height/4,g.height/2+g.height/4));
        random.setLayout(new GridLayout(1,2));

        random.add(label);
        random.add(randbox);

        this.add(random);


        JButton save = new JButton("Save settings");
        save.addActionListener(new setListener());
        save.setBorder(BorderFactory.createEmptyBorder(0, g.width/16, 0, g.width/16));
        this.add(save);

    }

    private Vector<Integer> vector_zero(int min, int max){
        Vector<Integer> vector = new Vector<>();

        for(int i=min; i<=max; i++){
            vector.add(i);
        }
        return vector;
    }

    public void update(){
        asteroidnumber = (int)asteroid.getSelected();

        Vector<Integer> vector = vector_zero(3, asteroidnumber/4);

        coal.update(vector);
        iron.update(vector);
        water.update(vector);
        uran.update(vector);
    }

    private class setListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(asteroid.getSelected());
            System.out.println(settler.getSelected());
            System.out.println(robot.getSelected());
            System.out.println(ufo.getSelected());
            System.out.println(coal.getSelected());
            System.out.println(iron.getSelected());
            System.out.println(water.getSelected());
            System.out.println(uran.getSelected());
            System.out.println(portal.getSelected());
            System.out.println(randbox.getSelectedItem());

        }
    }

    public ChoserPanel getAsteroid(){
        return asteroid;
    }


}
