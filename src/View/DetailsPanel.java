package View;

import Controller.*;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DetailsPanel extends JPanel implements ActionListener {
    JPanel buildpanel=new JPanel();
    JPanel materialpanel=new JPanel();
    JPanel movepanel=new JPanel();
   // final Controller controller;
    CommandPanel com;
    ArrayList<JPanel> panels = new ArrayList<>();
    JList<String> list;
    private JButton dpactionDone = new JButton("DOIT");
    private ArrayList<JButton> materials = new ArrayList<>();
    int db = 0;

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




    public void moveDetails(Vector vector, GUI g){
        this.removeAll();
//
//        list = new JList<>(vector);
//        list.setPreferredSize(new Dimension(g.width/4,100));
//        list.addListSelectionListener(new moveListener(Game.getInstance().c, list.getSelectedIndex()));
//        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.repaint();
        this.validate();

    }


    public void drillDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.setPreferredSize(new Dimension(g.width/4,100));
        list.addListSelectionListener(new digListener(Game.getInstance().c));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.repaint();
        this.validate();
    }

    public void mineDetails(Vector vector, GUI g){
        this.removeAll();

//        list = new JList<>(vector);
//        list.addListSelectionListener(new mineListener(Game.getInstance().c));
//        list.setPreferredSize(new Dimension(g.width/4,100));
//        this.add(new JScrollPane(list),BorderLayout.CENTER);

        JPanel inventory = new JPanel(new GridLayout(1,13));
        inventory.setBackground(Color.YELLOW);

        ImageIcon p = null;
        int scalingx, scalingy;
        for(Material m : Main.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().GetMaterials()) {
            db++;
//            if(m.getName()== NormalMaterialName.IRON) {
//                scalingx = 110;
//                scalingy = 90;
//                p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            if(m.getName()==NormalMaterialName.COAL) {
//                scalingx = 40;
//                scalingy = 40;
//                p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            if(m.getName()== SublimableMaterialName.ICEWATER) {
//                scalingx = 40;
//                scalingy = 40;
//                p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            if(m.getName()== RadioactiveMaterialName.URAN) {
//                scalingx = 120;
//                scalingy = 100;
//                RadioactiveMaterial rm = (RadioactiveMaterial)m;
//                if(rm.GetExposure()==0)
//                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//                else if(rm.GetExposure()==1)
//                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//                else if(rm.GetExposure()==2)
//                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            JButton a = new JButton(p);
//            a.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    Main.settlers.get(Game.getInstance().c.selectedSettler).PlaceMaterial(Main.materials.get(db));
//                }
//            });
//            materials.add(a);
            this.add(new MaterialList());
//        }
//
//        for(JButton jb : materials)
//            inventory.add(jb);
        }
        this.add(inventory);

        this.repaint();
        this.validate();
    }



    public void placeDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.setPreferredSize(new Dimension(g.width/4,100));
        //list.addListSelectionListener(new placeListener(controller, ));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.repaint();
        this.validate();
    }

    public void buildDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
        list.addListSelectionListener(new buildListener(list.getSelectedIndex() , Game.getInstance().c));
        list.setPreferredSize(new Dimension(g.width/4,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.repaint();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == materials.get(0)){
            System.out.println("SZEDJ KI!");
                Main.settlers.get(Game.getInstance().c.selectedSettler).PlaceMaterial(Main.materials.get(e.getID()));
        }
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