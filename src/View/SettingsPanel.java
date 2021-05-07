package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SettingsPanel extends JPanel {
    final private ChoserPanel asteroid;
    final private ChoserPanel settler;
    final private ChoserPanel robot;
    final private ChoserPanel ufo;
    final private ChoserPanel coal;
    final private ChoserPanel iron;
    final private ChoserPanel water;
    final private ChoserPanel uran;
    final private ChoserPanel portal;
    final private ChoserPanel random;


    public SettingsPanel(GUI g){

        this.setPreferredSize(new Dimension(g.height/2,g.height/2+g.height/4));
        this.setOpaque(true);
        this.setLayout(new GridLayout(12,1));

        JLabel up = new JLabel("New Game settings");
        up.setHorizontalAlignment(JLabel.CENTER);
        this.add(up);

        Vector vector = vector_zero(12,50);
        asteroid = new ChoserPanel(g, vector, "Asteroids");
        this.add(asteroid);

        vector = vector_zero(2,10);
        settler = new ChoserPanel(g, vector, "Settlers");
        this.add(settler);

        vector = vector_zero(3,10);
        robot = new ChoserPanel(g, vector, "Robots");
        this.add(robot);

        vector = vector_zero(3,10);
        ufo = new ChoserPanel(g, vector, "UFOs");
        this.add(ufo);

        vector = vector_zero(3,10);
        coal = new ChoserPanel(g, vector, "Coal");
        this.add(coal);

        vector = vector_zero(3,10);
        iron = new ChoserPanel(g, vector, "Iron");
        this.add(iron);

        vector = vector_zero(3,10);
        water = new ChoserPanel(g, vector, "Water");
        this.add(water);

        vector = vector_zero(3,10);
        uran = new ChoserPanel(g, vector, "Uran");
        this.add(uran);

        vector = vector_zero(3,10);
        portal = new ChoserPanel(g, vector, "Portals");
        this.add(portal);

        Vector rand = new Vector<Boolean>();
        rand.add(true);
        rand.add(false);

        random = new ChoserPanel(g, rand, "Random");
        this.add(random);


        JButton save = new JButton("Save settings");
        save.addActionListener(new setListener("Hello"));
        save.setBorder(BorderFactory.createEmptyBorder(0, g.width/16, 0, g.width/16));
        this.add(save);

    }


    private Vector vector_zero(int min, int max){
        Vector vector = new Vector<Integer>();

        for(int i=min; i<=max; i++){
            vector.add(i);
        }
        return vector;
    }

    private class setListener implements ActionListener{

        private String szo;

        public setListener(String str){
            szo = str;
        }

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
            System.out.println(szo);

        }
    }


}
