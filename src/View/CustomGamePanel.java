package View;

import Model.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Egyedi jatek beallitasa
 */
public class CustomGamePanel extends JPanel {
    final private CustomChooserPanel asteroid;
    final private CustomChooserPanel settler;
    final private CustomChooserPanel robot;
    final private CustomChooserPanel ufo;
    final private CustomChooserPanel coal;
    final private CustomChooserPanel iron;
    final private CustomChooserPanel water;
    final private CustomChooserPanel uran;
    final private CustomChooserPanel portal;
    final private JComboBox randbox;
    private int asteroidnumber = 12;
    final private JButton saveButton;
    final private ArrayList<Integer> data = new ArrayList<>();


    /**
     * Konstruktor
     * @param g
     * @param x
     * @param y
     */
    public CustomGamePanel(GUI g, int x,int  y){
        this.setPreferredSize(new Dimension(g.height/2,g.height/2+g.height/4));
        this.setOpaque(true);
        this.setLayout(new GridLayout(12,1));

        JLabel up = new JLabel("New Game settings");
        up.setHorizontalAlignment(JLabel.CENTER);
        this.add(up);

        //int maximumasteroids = (int) Math.floor(x * y * 0.37);

        Vector<Integer> vector = vector_zero(12,24);
        asteroid = new CustomChooserPanel(g, vector, "Asteroids", this);
        this.add(asteroid);

        vector = vector_zero(2,20);
        settler = new CustomChooserPanel(g, vector, "Settlers", this);
        this.add(settler);

        vector = vector_zero(0,20);
        robot = new CustomChooserPanel(g, vector, "Robots", this);
        this.add(robot);

        vector = vector_zero(0,20);
        ufo = new CustomChooserPanel(g, vector, "UFOs", this);
        this.add(ufo);

        vector = vector_zero(0,asteroidnumber/4);
        coal = new CustomChooserPanel(g, vector, "Coal", this);
        this.add(coal);

        vector = vector_zero(0,asteroidnumber/4);
        iron = new CustomChooserPanel(g, vector, "Iron", this);
        this.add(iron);

        vector = vector_zero(0,asteroidnumber/4);
        water = new CustomChooserPanel(g, vector, "IceWater", this);
        this.add(water);

        vector = vector_zero(0,asteroidnumber/4);
        uran = new CustomChooserPanel(g, vector, "Uran", this);
        this.add(uran);

        vector = vector_portal(6);
        portal = new CustomChooserPanel(g, vector, "Portals", this);
        this.add(portal);

        Vector<Boolean> rand = new Vector<>();
        rand.add(true);
        rand.add(false);

        randbox = new JComboBox(rand);
        JPanel random = new JPanel();
        JLabel label = new JLabel("Randomize enviroment");
        label.setHorizontalAlignment(JLabel.CENTER);
        random.setPreferredSize(new Dimension(g.height/4,g.height/2+g.height/4));
        random.setLayout(new GridLayout(1,2));

        random.add(label);
        random.add(randbox);

        this.add(random);

        saveButton = new JButton(new ImageIcon("Files/Pictures/button_save-game-settings.png"));
        saveButton.setRolloverIcon(new ImageIcon("Files/Pictures/button_save-game-settings (1).png"));
        saveButton.setBorderPainted(false);
        saveButton.setContentAreaFilled(false);
        saveButton.setFocusPainted(false);
        saveButton.setOpaque(false);
        saveButton.addActionListener(new setListener());
        saveButton.setBorder(BorderFactory.createEmptyBorder(0, g.width/16, 0, g.width/16));
        this.add(saveButton);

    }

    /**
     * Vector feltoltese szamokkal a ket ertek kozott
     * @param min
     * @param max
     * @return
     */
    private Vector<Integer> vector_zero(int min, int max){
        Vector<Integer> vector = new Vector<>();

        for(int i=min; i<=max; i++){
            vector.add(i);
        }
        return vector;
    }


    /**
     * portal szam
     * @param max
     * @return
     */
    private Vector<Integer> vector_portal(int max){
        Vector<Integer> vector = new Vector<>();

        for(int i=0; i<=max; i = i+2){
            vector.add(i);
        }
        return vector;
    }


    /**
     * Material szam frissitese
     */
    public void update(){
        asteroidnumber = (int)asteroid.getSelected();

        Vector<Integer> vector = vector_zero(3, asteroidnumber/4);

        coal.update(vector);
        iron.update(vector);
        water.update(vector);
        uran.update(vector);
    }

    /**
     * ertekek beallitasa
     */
    private class setListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == saveButton){
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
                Game.getInstance().c.g.startgame.setEnabled(true);
            }
        }
    }

    public CustomChooserPanel getAsteroid(){
        return asteroid;
    }

    /**
     * adat visszaadasa
     * @return
     */
    public ArrayList<Integer> GetCreatecount(){
        return this.data;
    }
}