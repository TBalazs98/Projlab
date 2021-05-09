package View;

import Model.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class CustomGamePanel extends JPanel {
    final private  ChoserPanel asteroid;
    final private ChoserPanel settler;
    final private ChoserPanel robot;
    final private ChoserPanel ufo;
    final private ChoserPanel coal;
    final private ChoserPanel iron;
    final private ChoserPanel water;
    final private ChoserPanel uran;
    final private ChoserPanel portal;
    final private JComboBox randbox;
    private int asteroidnumber = 12;
    final private JButton save;
    final private ArrayList<Integer> data = new ArrayList<>();


    public CustomGamePanel(GUI g, int x,int  y){
        this.setPreferredSize(new Dimension(g.height/2,g.height/2+g.height/4));
        this.setOpaque(true);
        this.setLayout(new GridLayout(12,1));

        JLabel up = new JLabel("New Game settings");
        up.setHorizontalAlignment(JLabel.CENTER);
        this.add(up);

        int maximumasteroids = (int) Math.floor(x * y * 0.37);

        Vector<Integer> vector = vector_zero(1,maximumasteroids);
        asteroid = new ChoserPanel(g, vector, "Asteroids", this);
        this.add(asteroid);

        vector = vector_zero(1,40);
        settler = new ChoserPanel(g, vector, "Settlers", this);
        this.add(settler);

        vector = vector_zero(0,40);
        robot = new ChoserPanel(g, vector, "Robots", this);
        this.add(robot);

        vector = vector_zero(0,40);
        ufo = new ChoserPanel(g, vector, "UFOs", this);
        this.add(ufo);

        vector = vector_zero(0,asteroidnumber/4);
        coal = new ChoserPanel(g, vector, "Coal", this);
        this.add(coal);

        vector = vector_zero(0,asteroidnumber/4);
        iron = new ChoserPanel(g, vector, "Iron", this);
        this.add(iron);

        vector = vector_zero(0,asteroidnumber/4);
        water = new ChoserPanel(g, vector, "Water", this);
        this.add(water);

        vector = vector_zero(0,asteroidnumber/4);
        uran = new ChoserPanel(g, vector, "Uran", this);
        this.add(uran);

        vector = vector_zero(0,12);
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

        save = new JButton("Save settings");
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
            if(e.getSource() == save){
                Game.getInstance().c.g.getContentPane().removeAll();
                Game.getInstance().c.g.DrawMenu();
                Game.getInstance().c.g.repaint();

                if(iron.getSelected() != null) {
                    data.add((Integer) iron.getSelected());
                } else data.add(0);
                if(coal.getSelected() != null) {
                    data.add((Integer) coal.getSelected());
                } else data.add(0);
                if(uran.getSelected() != null) {
                    data.add((Integer) uran.getSelected());
                } else data.add(0);
                if(water.getSelected() != null) {
                    data.add((Integer) water.getSelected());
                } else data.add(0);
                data.add((Integer) asteroid.getSelected());
                data.add((Integer) portal.getSelected());
                data.add((Integer) settler.getSelected());
                data.add((Integer) robot.getSelected());
                data.add((Integer) ufo.getSelected());
                data.add((Boolean)randbox.getSelectedItem() ? 1 : 0);
            }
        }
    }

    public ChoserPanel getAsteroid(){
        return asteroid;
    }

    public ArrayList<Integer> GetCreatecount(){
        return this.data;
    }
}