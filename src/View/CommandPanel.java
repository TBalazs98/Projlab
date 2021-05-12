package View;

import Controller.Main;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandPanel extends JPanel implements ActionListener {
    private JButton cpactionDone = new JButton("DOIT");
    private JPanel buttons;
    private JButton build, drill, move, mine, place, nextsettler;
    private int scaling;
    private boolean next = false;
    JPanel inventory = new JPanel();



    CommandPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(Game.getInstance().c.g.width / 2, 200));
        this.setBackground(Color.GRAY);
        String[] data = {"Build", "Mine", "Dig", "Place", "Move"};

       Buttons();
        InventoryPanel();


        this.setVisible(true);
    }
    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        //button.setPreferredSize(new Dimension(this.getWidth()/5,70));
        button.addActionListener(this);
    }

    public void SetButtonMine(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        //button.setPreferredSize(new Dimension(this.getWidth()/5,70));
        button.addActionListener(this);
        if(Main.settlers.get(Game.getInstance().c.SelectedSettler()) != null) {
            if ((Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().getLayers() > 0 && Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().GetisEmpty() != true))
                button.setEnabled(false);
            else {
                button.setEnabled(true);
            }
        }
    }
    public void SetButtonDrill(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        //button.setPreferredSize(new Dimension(this.getWidth()/5,70));
        button.addActionListener(this);
        if(Main.settlers.get(Game.getInstance().c.SelectedSettler()) != null) {
            if (Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().getLayers() <= 0)
                button.setEnabled(false);
            else {
                button.setEnabled(true);
            }
        }
    }



    public void Buttons(){
        scaling = Game.getInstance().c.g.GetWidth()/20;
        int height = 60;


        build = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_build.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        build.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_build (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(build);

        drill = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_drill.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        drill.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_drill (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButtonDrill(drill);

        move = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_move.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        move.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_move (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(move);

        place = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_place.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        place.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_place (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(place);

        mine = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_mine.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        mine.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_mine (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButtonMine(mine);

        nextsettler = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_next.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        nextsettler.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_next (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(nextsettler);


        buttons = new JPanel(new FlowLayout());
        buttons.add(move);
        buttons.add(drill);
        buttons.add(mine);
        buttons.add(place);
        buttons.add(build);
        buttons.add(nextsettler);

        buttons.setOpaque(false);


        this.add(buttons);

    }


    //  TODO TODO TODO ITT A VÉGTELEN SELECTEDSETTLERT KI KELL CSERÉLNI  Game.getInstance().c.SelectedSettler() - re
    //                                  @Klau approve pls
    //      Minden felesleges NextSettler hívást, plusszolást akármit ki kell írtani
    //                      A settler ++ olását csak a NextSettler() végzi
    //       Az aktuális Settler helyes lekérdezését csak és kizárólag a SelectedSettler végzi!!

    public void InventoryPanel() {
        inventory.removeAll();
        inventory = new JPanel(new GridLayout(1,13));

        //inventory.setPreferredSize(new Dimension(Game.getInstance().c.g.getWidth(),50));
        inventory.setBackground(Color.GRAY);
        inventory.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        ImageIcon p = null;
        scaling = 50;
//        System.out.println("INVENTORY: " + Game.getInstance().c.selectedSettler);
        if(Main.settlers.get(Game.getInstance().c.SelectedSettler())!=null) {
            for (Material m : Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().GetMaterials()) {
                if (m.name == NormalMaterialName.IRON) {
                    p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                if (m.name == NormalMaterialName.COAL) {
                    p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                if (m.name == SublimableMaterialName.ICEWATER) {
                    p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                if (m.name == RadioactiveMaterialName.URAN) {
                    RadioactiveMaterial rm = (RadioactiveMaterial) m;
                    if (rm.GetExposure() == 0)
                        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                    else if (rm.GetExposure() == 1)
                        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                    else if (rm.GetExposure() == 2)
                        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                inventory.add(new JLabel((p)));
            }

            for (TeleportGate t : Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetGates()) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                inventory.add(new JLabel(p));
            }
        }
        this.add(inventory);
        this.repaint();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == move){
//            Game.getInstance().c.DoTheMove();
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.g.dp.moveDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == drill){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.HandleDrill();     //todo @Klau ez miért van itt is meg a details panelben is??
//            Game.getInstance().c.g.dp.drillDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == mine){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.HandleMine();      //todo @Klau meg ez is?
//            Game.getInstance().c.g.dp.mineDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == place){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.HandlePlaceMaterial();
//            Game.getInstance().c.g.dp.placeDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == build){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.g.dp.buildDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == nextsettler){
////            System.out.println("Actual settler: " + Game.getInstance().c.selectedSettler);
//            Game.getInstance().c.NextSettler();
//            if (Game.getInstance().c.selectedSettler == Main.settlers.size()) {
//                Game.getInstance().c.selectedSettler = 0;
//                Game.getInstance().NextRound();
//            }
//            //Game.getInstance().c.HighlightSettlerStuff();
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.NextSettler();
        }
        this.removeAll();
        Buttons();
        if(next) {
            setButtonsOff();
            next = false;
        }
        InventoryPanel();
        this.repaint();
        this.validate();
    }

    public void setButtonsOff(){
        this.move.setEnabled(false);
        this.drill.setEnabled(false);
        this.mine.setEnabled(false);
        this.build.setEnabled(false);
        this.place.setEnabled(false);
    }


}