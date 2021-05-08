package View;

import Controller.Controller;

import Model.DestinationObject;
import Model.Game;
import Model.Material;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CommandPanel extends JPanel {
    JList<String> list ;
    private JButton cpactionDone = new JButton("DOIT");

    int CurrentCommand;

    CommandPanel(){
        this.setPreferredSize(new Dimension(Game.getInstance().c.g.width/2,200));
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        String[] data = {"Build", "Mine", "Dig", "Place", "Move"};
        Icon build=new ImageIcon("Files/Pictures/placebtn.png");
        Icon mine=new ImageIcon("Files/Pictures/drillbtn.png");
        Icon dig=new ImageIcon("Files/Pictures/minebtn.png");
        Icon place=new ImageIcon("Files/Pictures/sus.png");
        Icon[] images ={build,mine,dig,place};


        cpactionDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().c.stepsettlers();
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

                        for(Material i: Controller.settlers.get(Game.getInstance().c.selectedSettler).GetInventory().GetMaterials()){
                            vector.add(i);
                            System.out.println(vector.size());
                        }
                        Game.getInstance().c.g.dp.placeDetails(vector, Game.getInstance().c.g);
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
                    }
                    else  if(CurrentCommand == 4){
                        Vector<Integer> vector = new Vector<>();
                        int id = 0;

                        for(DestinationObject i: Controller.settlers.get(Game.getInstance().c.selectedSettler).getAsteroid().GetNeighbours()){
                            vector.add((id));
                            id++;
                            System.out.println(vector.size());
                        }
                        Game.getInstance().c.g.dp.moveDetails(vector, Game.getInstance().c.g);
                        Game.getInstance().c.g.GetAsteroidView().get(0).highlight(false,Game.getInstance().c.g);
                    }


                }

            }
        });
    }



    public int getCurrentCommand(){return CurrentCommand;}

    public JButton getActionDone1(){return cpactionDone;}





    /*final class list_lis implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            CurrentCommand = list.getSelectedIndex();

        }

    }*/





}
