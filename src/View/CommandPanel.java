package View;

import Controller.Controller;

import Model.DestinationObject;
import Model.Material;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class CommandPanel extends JPanel {
    JList<String> list ;
    int CurrentCommand;

    CommandPanel(Controller c, GUI g){
        this.setPreferredSize(new Dimension(g.width/2,200));
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        String[] data = {"Build", "Mine", "Dig", "Place", "Move"};
        Icon build=new ImageIcon("Files/Pictures/placebtn.png");
        Icon mine=new ImageIcon("Files/Pictures/drillbtn.png");
        Icon dig=new ImageIcon("Files/Pictures/minebtn.png");
        Icon place=new ImageIcon("Files/Pictures/sus.png");
        Icon[] images ={build,mine,dig,place};

        list = new JList<String >(data);
        //list.addListSelectionListener(new list_lis());
        fasz(list,c);
        //g.gamespace.add(this);
        list.setPreferredSize(new Dimension(g.width/8,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);


    }
    public void fasz(JList list, Controller c) {
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    CurrentCommand = list.getSelectedIndex();
                    c.updateCommand(CurrentCommand);
                    if(CurrentCommand == 0){
                        Vector<String> vector = new Vector<>();
                        vector.add("TeleportGate");
                        vector.add("Robot");
                        vector.add("Base");
                        c.g.dp.buildDetails(vector, c.g);
                    }
                    else if(CurrentCommand == 1){
                        Vector<String> vector = new Vector<>();
                        vector.add("Mine");
                        c.g.dp.mineDetails(vector, c.g);
                    }
                    else  if(CurrentCommand == 2){
                        Vector<String> vector = new Vector<>();
                        vector.add("Dig");
                        c.g.dp.digDetails(vector, c.g);
                        System.out.println(list.getSelectedValue());
                    }

                    else  if(CurrentCommand == 3){
                        Vector<Material> vector = new Vector<>();

                        for(Material i: Controller.settlers.get(c.selectedSettler).GetInventory().GetMaterials()){
                            vector.add(i);
                            System.out.println(vector.size());
                        }
                        c.g.dp.placeDetails(vector, c.g);
                    }
                    else  if(CurrentCommand == 4){
                        Vector<Integer> vector = new Vector<>();
                        int id = 0;

                        for(DestinationObject i: Controller.settlers.get(c.selectedSettler).getAsteroid().GetNeighbours()){
                            vector.add((id));
                            id++;
                            System.out.println(vector.size());
                        }
                        c.g.dp.moveDetails(vector, c.g);
                    }


                }

            }
        });
    }



    public int getCurrentCommand(){return CurrentCommand;}



    /*final class list_lis implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            CurrentCommand = list.getSelectedIndex();

        }

    }*/





}
