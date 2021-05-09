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
    private ArrayList<Material> mat = new ArrayList<>();
    int db = 0, index = -1;

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
//        list.addListSelectionListener(new digListener(Game.getInstance().c));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.repaint();
        this.validate();
    }

    public void mineDetails(GUI g){
        this.removeAll();

        Main.settlers.get(Game.getInstance().c.selectedSettler).Mine();

        this.repaint();
        this.validate();
    }



    public void placeDetails(Vector vector, GUI g){
        this.removeAll();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        materials.clear();
        mat.clear();

        JPanel inventory = new JPanel(new GridLayout(1,13));
        inventory.setBackground(Color.YELLOW);

        ImageIcon p = null;
        int scalingx= 50, scalingy = 50;
        int set;
        if (Game.getInstance().c.selectedSettler == (Main.settlers.size())-2)
            set = 0;
            else
                set = Game.getInstance().c.selectedSettler + 1;
        System.out.println("DETAILS" + set);
        for(Material m : Main.settlers.get(set).GetInventory().GetMaterials()) {
            db++;
            mat.add(m);
            if (m.getName() == NormalMaterialName.IRON) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.getName() == NormalMaterialName.COAL) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.getName() == SublimableMaterialName.ICEWATER) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.getName() == RadioactiveMaterialName.URAN) {
                RadioactiveMaterial rm = (RadioactiveMaterial) m;
                if (rm.GetExposure() == 0)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
                else if (rm.GetExposure() == 1)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
                else if (rm.GetExposure() == 2)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            JButton a = new JButton(p);
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(materials.indexOf(a) + " " + mat.get(materials.indexOf(a)).getName() +  " " + Game.getInstance().c.selectedSettler);
                    Main.settlers.get(set).PlaceMaterial(mat.get(materials.indexOf(a)));
                    Main.settlers.get(set).GetInventory().Remove(mat.get(materials.indexOf(a)));
                    Main.materials.remove(mat.get(materials.indexOf(a)));

                }
            });
            materials.add(a);
            inventory.add(a);
        }

        this.add(inventory);

        this.repaint();
        this.validate();
    }

    public void buildDetails(Vector vector, GUI g){
        this.removeAll();

        list = new JList<>(vector);
//        list.addListSelectionListener(new buildListener(list.getSelectedIndex() , Game.getInstance().c));
        list.setPreferredSize(new Dimension(g.width/4,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

        this.repaint();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == e.getSource()){
//        Main.settlers.get(Game.getInstance().c.selectedSettler).PlaceMaterial(materials.get(e.getSource()));
//        Main.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().Remove(materials.get(e.getSource()));
//        Main.materials.remove(materials.get(e.getSource()));


//    }
        System.out.println(materials.size());
        if(e.getSource() == materials.get(0)){
            Main.settlers.get(Game.getInstance().c.selectedSettler).PlaceMaterial(Main.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().GetMaterials().get(0));
            Main.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().Remove(Main.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().GetMaterials().get(0));
            mineDetails(Game.getInstance().c.g);
            System.out.println(":(((");
        }
    }
/*
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

*/
}