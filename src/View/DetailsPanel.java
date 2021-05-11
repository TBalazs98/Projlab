package View;

import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DetailsPanel extends JPanel{
    private ArrayList<JButton> materials = new ArrayList<>();
    private ArrayList<JButton> gates = new ArrayList<>();
    private ArrayList<TeleportGate> tgate = new ArrayList<>();
    private ArrayList<Material> mat = new ArrayList<>();
    int db = 0, index = -1;


    DetailsPanel( ){
        this.setOpaque(true);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(Game.getInstance().c.g.width/2,200));
        this.setBackground(Color.RED);


    }




    public void moveDetails(GUI g){
        this.removeAll();
        //Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
//        int set;
//        if (Game.getInstance().c.selectedSettler == (Main.settlers.size())-2)
//            set = 0;
//        else
//            set = Game.getInstance().c.selectedSettler + 1;

        System.out.println("move details selected szam "+Game.getInstance().c.SelectedSettler());
        Game.getInstance().c.MoveSetSettler(Main.settlers.get(Game.getInstance().c.SelectedSettler()));
        Game.getInstance().c.DoTheMove();

        //Game.getInstance().c.HighAsteroid(Main.settlers.get(Game.getInstance().c.SelectedSettler()));
        //HighAsteroid(Main.settlers.get(SelectedSettler()));

        //Game.getInstance().c.HighlightEverythingExcept(Game.getInstance().c.g.getSettlerViewBySettler(Main.settlers.get(Game.getInstance().c.SelectedSettler())));
        //HighlightEverythingExcept(Game.getInstance().c.g.getSettlerViewBySettler(Main.settlers.get(SelectedSettler())));

        //Game.getInstance().c.HighlightSettlerStuff();
        System.out.println("---"+Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().GetNeighbours().contains(Game.getInstance().c.a));
       // Game.getInstance().c.NextSettler();

        //Game.getInstance().c.stepsettlers();




    }

//    public void placeDetails(GUI g){
//        this.removeAll();
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
//        //Game.getInstance().c.HandlePlaceMaterial();
//        materials.clear();
//        mat.clear();
//
//        JPanel inventory = new JPanel(new GridLayout(1,13));
//        inventory.setBackground(Color.YELLOW);
//
//        ImageIcon p = null;
//        int scalingx= 50, scalingy = 50;
//        int set;
//        if (Game.getInstance().c.selectedSettler == (Main.settlers.size())-2)
//            set = 0;
//            else
//                set = Game.getInstance().c.selectedSettler + 1;
//        System.out.println("DETAILS" + set);
//        for(Material m : Main.settlers.get(set).GetInventory().GetMaterials()) {
//            db++;
//            mat.add(m);
//            if (m.getName() == NormalMaterialName.IRON) {
//                p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            if (m.getName() == NormalMaterialName.COAL) {
//                p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            if (m.getName() == SublimableMaterialName.ICEWATER) {
//                p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            if (m.getName() == RadioactiveMaterialName.URAN) {
//                RadioactiveMaterial rm = (RadioactiveMaterial) m;
//                if (rm.GetExposure() == 0)
//                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//                else if (rm.GetExposure() == 1)
//                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//                else if (rm.GetExposure() == 2)
//                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            }
//            JButton a = new JButton(p);
//            a.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    Main.settlers.get(set).PlaceMaterial(mat.get(materials.indexOf(a)));
//                    Main.settlers.get(set).GetInventory().Remove(mat.get(materials.indexOf(a)));
//                    Main.materials.remove(mat.get(materials.indexOf(a)));
//
//                }
//            });
//            materials.add(a);
//            inventory.add(a);
//        }
//
//        for(TeleportGate t : Main.settlers.get(set).GetGates()) {
//            p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            tgate.add(t);
//            JButton a = new JButton(p);
//            a.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    Main.settlers.get(set).PlaceGate(tgate.get(tgate.indexOf(a)));
//                    Main.teleportgates.remove(tgate.get(gates.indexOf(a)));
//
//                }
//            });
//            gates.add(a);
//            inventory.add(a);
//        }
//        Game.getInstance().c.NextSettler();
//        this.add(inventory);
//
//        this.repaint();
//        this.validate();
//    }
        //todo selectedSettlerÍrtás
    public void buildDetails(GUI g){
        this.removeAll();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));



        int scaling = 90, height = 70;

        JButton buildrobot = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/robot.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        buildrobot.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/builddarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(buildrobot);
        buildrobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int PreviousNumberOfRobots = Main.robots.size();
                Main.settlers.get(Game.getInstance().c.SelectedSettler()).BuildRobot();
                Game.getInstance().c.BuildRobot(PreviousNumberOfRobots);
            }
        });

        JButton buildteleportgate = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        buildteleportgate.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/drilldarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(buildteleportgate);
        buildteleportgate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.settlers.get(Game.getInstance().c.SelectedSettler()).BuildGate();
            }
        });

        JButton buildbase = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/base.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        buildbase.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/movedarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(buildbase);
        buildbase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.settlers.get(Game.getInstance().c.SelectedSettler()).BuildBase();
            }
        });


        this.add(buildrobot);
        this.add(buildteleportgate);
        this.add(buildbase);
        this.repaint();
        this.validate();
    }

    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

    }
}