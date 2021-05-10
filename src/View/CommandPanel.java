package View;

import Controller.Main;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandPanel extends JPanel implements ActionListener {
    JList<String> list ;
    private JButton cpactionDone = new JButton("DOIT");
    private JPanel buttons;
    private JButton build, drill, move, mine, place;
    private int scaling;

    int CurrentCommand;

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

/*
        cpactionDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().c.stepsettlers();
                //System.out.println("asd");
            }
        });

        this.add(cpactionDone);

        list = new JList<String >(data);
        //list.addListSelectionListener(new list_lis());
        fasz(list);
        //g.gamespace.add(this);
        list.setPreferredSize(new Dimension(Game.getInstance().c.g.width/8,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);


    }
    public void fasz(JList list ) {
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    CurrentCommand = list.getSelectedIndex();
                    Game.getInstance().c.updateCommand(CurrentCommand);
                    if(CurrentCommand == 0){
                        Vector<String> vector = new Vector<>();
                        vector.add("TeleportGate");
                        vector.add("Robot");
                        vector.add("Base");
                        Game.getInstance().c.g.dp.buildDetails(vector, Game.getInstance().c.g);
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(true,Game.getInstance().c.g);
                    }
                    else if(CurrentCommand == 1){
                        Vector<String> vector = new Vector<>();
                        vector.add("Mine");
                        //c.g.dp.mineDetails(vector, c.g);
                        Game.getInstance().c.g.dp.mineDetails(vector,Game.getInstance().c.g);
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
                    }
                    else  if(CurrentCommand == 2){
                        Vector<String> vector = new Vector<>();
                        vector.add("Dig");
                        //c.g.dp.digDetails(vector, c.g);
                        Game.getInstance().c.g.dp.digDetails(vector,Game.getInstance().c.g);
                        System.out.println(list.getSelectedValue());
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
                    }

                    else  if(CurrentCommand == 3){
                        Vector<Material> vector = new Vector<>();

//                        for(Material i: Controller.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().GetMaterials()){
//          #TODO                  vector.add(i);
//                            System.out.println(vector.size());
//                        }
                        Game.getInstance().c.g.dp.placeDetails(vector, Game.getInstance().c.g);
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
                    }
                    else  if(CurrentCommand == 4){
                        Vector<Integer> vector = new Vector<>();
                        int id = 0;

//                        for(DestinationObject i: Controller.settlers.get(Game.getInstance().c.selectedSettler).getAsteroid().GetNeighbours()){
//          #TODO                  vector.add((id));
//                            id++;
//                            System.out.println(vector.size());
//                        }
                        Game.getInstance().c.g.dp.moveDetails(vector, Game.getInstance().c.g);
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
                    }


                }

            }
        });
    }



    public int getCurrentCommand(){return CurrentCommand;}
*/
    public JButton getActionDone1(){return cpactionDone;}

    public void Buttons(){
        scaling = Game.getInstance().c.g.GetWidth()/13;
        int height = 45;


        build = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/buildbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        build.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/builddarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(build);

        drill = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/drillbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        drill.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/drilldarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(drill);

        move = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/movebtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        move.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/movedarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(move);

        place = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/placebtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        place.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/placedarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(place);

        mine = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/minebtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        mine.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/minedarkbtn.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(mine);


        buttons = new JPanel(new FlowLayout());
        buttons.add(move);
        buttons.add(drill);
        buttons.add(mine);
        buttons.add(place);
        buttons.add(build);

        buttons.setOpaque(false);


        this.add(buttons);

    }
    public void InventoryPanel() {
        JPanel inventory = new JPanel(new GridLayout(1,13));

        //inventory.setPreferredSize(new Dimension(Game.getInstance().c.g.getWidth(),50));
        inventory.setBackground(Color.GRAY);
        inventory.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        ImageIcon p = null;
        scaling = 50;
//        System.out.println("INVENTORY: " + Game.getInstance().c.selectedSettler);
        for(Material m : Main.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().GetMaterials()){
            if(m.getName()== NormalMaterialName.IRON) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            }
            if(m.getName()==NormalMaterialName.COAL) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            }
            if(m.getName()== SublimableMaterialName.ICEWATER) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            }
            if(m.getName()== RadioactiveMaterialName.URAN) {
                RadioactiveMaterial rm = (RadioactiveMaterial)m;
                if(rm.GetExposure()==0)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                else if(rm.GetExposure()==1)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                else if(rm.GetExposure()==2)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            }
            inventory.add(new JLabel((p)));
        }

        for(TeleportGate t : Main.settlers.get(Game.getInstance().c.selectedSettler).GetGates()) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            inventory.add(new JLabel(p));
        }
        this.add(inventory);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == move){
            Game.getInstance().c.g.dp.moveDetails(Game.getInstance().c.g);
        }
        if(e.getSource() == drill){
            Game.getInstance().c.g.dp.drillDetails(Game.getInstance().c.g);
        }
        if(e.getSource() == mine){
            Game.getInstance().c.g.dp.mineDetails(Game.getInstance().c.g);
        }
        if(e.getSource() == place){
            Game.getInstance().c.g.dp.placeDetails(Game.getInstance().c.g);
        }
        if(e.getSource() == build){

            Game.getInstance().c.g.dp.buildDetails(Game.getInstance().c.g);
        }


//        Game.getInstance().c.HighlightSettlerStuff();
//        if(Game.getInstance().c.selectedSettler == Main.settlers.size()-1)
//            Game.getInstance().c.selectedSettler = 0;

        this.removeAll();
        Buttons();
        InventoryPanel();
        this.repaint();
        this.validate();
    }





    /*final class list_lis implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            CurrentCommand = list.getSelectedIndex();

        }

    }*/

}