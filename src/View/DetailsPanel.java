package View;

import Controller.Controller;
import Model.Asteroid;
import Model.DestinationObject;
import Model.Game;
import Model.Material;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class DetailsPanel extends JPanel {
    JPanel buildpanel=new JPanel();
    JPanel materialpanel=new JPanel();
    JPanel movepanel=new JPanel();
   // final Controller controller;
    CommandPanel com;
    ArrayList<JPanel> panels = new ArrayList<>();
    JList<String> list;
    private JButton dpactionDone = new JButton("DOIT");

    DetailsPanel( ){
       // panels.add(buildpanel);
       // panels.add(materialpanel);
       // panels.add(movepanel);
       // com = new CommandPanel(c,g);


        this.setOpaque(true);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(Game.getInstance().c.g.width/2,200));
        this.setBackground(Color.RED);
        this.add(dpactionDone);
        list = new JList<String >();
        list.setPreferredSize(new Dimension(Game.getInstance().c.g.width/4,100));

        this.add(new JScrollPane(list),BorderLayout.CENTER);



/*
         //this.add(panels.get(c.getCurrentCommand()));
          */

    }


    public void buildDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.addListSelectionListener(new buildListener(list.getSelectedIndex() , Game.getInstance().c));
        list.setPreferredSize(new Dimension(g.width/4,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.updateUI();
    }


    public void mineDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.addListSelectionListener(new mineListener(Game.getInstance().c));
        list.setPreferredSize(new Dimension(g.width/4,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.updateUI();
    }

    public void digDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.setPreferredSize(new Dimension(g.width/4,100));
        list.addListSelectionListener(new digListener(Game.getInstance().c));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.updateUI();
    }


    public void placeDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.setPreferredSize(new Dimension(g.width/4,100));
        //list.addListSelectionListener(new placeListener(controller, ));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.updateUI();
    }

    public void moveDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.setPreferredSize(new Dimension(g.width/4,100));
        list.addListSelectionListener(new moveListener(Game.getInstance().c, list.getSelectedIndex()));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.updateUI();
    }

    private class buildListener implements ListSelectionListener{

        private final int str;
        private final Controller control;

        public buildListener(int v, Controller c){
            str = v;
            control = c;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()){
                if(str == 0){
                    Controller.settlers.get(control.selectedSettler).BuildGate();
                }
                else if(str == 1){
                    Controller.settlers.get(control.selectedSettler).BuildRobot();
                }
                else if(str == 2){
                    Controller.settlers.get(control.selectedSettler).BuildBase();
                }
            }
        }
    }

    private class mineListener implements ListSelectionListener{
        private final Controller control;

        public mineListener(Controller c){
            control = c;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()) {
                Controller.settlers.get(control.selectedSettler).Mine();
            }
        }
    }


    private class digListener implements ListSelectionListener{
        private final Controller control;

        public digListener(Controller c){
            control = c;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()){
                Controller.settlers.get(control.selectedSettler).Mine();
            }
        }
    }

    private class placeListener implements ListSelectionListener{
        private final Controller control;
        private final Material material;

        public placeListener(Controller c, Material m){
            control = c;
            material = m;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()) {
                Controller.settlers.get(control.selectedSettler).getAsteroid().AddMaterial(material);
            }

        }
    }


    private class moveListener implements ListSelectionListener{
        private final Controller control;
        private final int ide;

        public moveListener(Controller c, int i){
            control = c;
            ide = i;
        }


        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()) {
                Controller.settlers.get(control.selectedSettler).Move(ide);
            }
        }
    }


}