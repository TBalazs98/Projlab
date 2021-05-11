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

//        System.out.println("move details selected szam "+Game.getInstance().c.SelectedSettler());
        Game.getInstance().c.MoveSetSettler(Main.settlers.get(Game.getInstance().c.SelectedSettler()));
        Game.getInstance().c.DoTheMove();

//        System.out.println("---"+Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().GetNeighbours().contains(Game.getInstance().c.a));





    }
        //todo selectedSettlerÍrtás
public void buildDetails(GUI g){
    this.removeAll();
    this.setLayout(new FlowLayout(FlowLayout.CENTER));



    int scaling = 90, height = 70;

    JButton buildrobot = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/robot.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
    buildrobot.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/selected_robot.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
    SetButton(buildrobot);
    buildrobot.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().Size());
            for (int i=0; i<Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().Size();i++){
                System.out.println( Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().GetMaterials().get(i));
            }
            int PreviousNumberOfRobots = Main.robots.size();
            Main.settlers.get(Game.getInstance().c.SelectedSettler()).BuildRobot();
            Game.getInstance().c.BuildRobot(PreviousNumberOfRobots);
            System.out.println(Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().Size());
            for (int i=0; i<Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().Size();i++){
                System.out.println( Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().GetMaterials().get(i));
            }
            Game.getInstance().c.g.cp.InventoryPanel();
            Game.getInstance().c.g.dp.repaint();
            Game.getInstance().c.g.dp.validate();

        }
    });

    JButton buildteleportgate = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
    buildteleportgate.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/selected_teleportgate.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
    SetButton(buildteleportgate);
    buildteleportgate.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Main.settlers.get(Game.getInstance().c.SelectedSettler()).BuildGate();


            Game.getInstance().c.g.cp.InventoryPanel();
            Game.getInstance().c.g.dp.repaint();
            Game.getInstance().c.g.dp.validate();

        }
    });

    JButton buildbase = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/base.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
    buildbase.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/selected_base.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
    SetButton(buildbase);
    buildbase.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.settlers.get(Game.getInstance().c.SelectedSettler()).BuildBase();

            Game.getInstance().c.g.cp.InventoryPanel();
            Game.getInstance().c.g.dp.repaint();
            Game.getInstance().c.g.dp.validate();

        }
    });

    Game.getInstance().c.g.cp.repaint();
    Game.getInstance().c.g.cp.validate();
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