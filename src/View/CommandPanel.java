package View;

import Controller.GuiController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CommandPanel extends JPanel {
    JList<String> list ;
    int CurrentCommand;

    CommandPanel(GUI g){
        this.setPreferredSize(new Dimension(g.width/2,200));
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        String[] data = {"Build", "Mine", "Dig", "Place"};
        Icon build=new ImageIcon("Files/Pictures/sus.png");
        Icon mine=new ImageIcon("Files/Pictures/sus.png");
        Icon dig=new ImageIcon("Files/Pictures/sus.png");
        Icon place=new ImageIcon("Files/Pictures/sus.png");
        Icon[] images ={build,mine,dig,place};

        list = new JList<String >(data);
        //list.addListSelectionListener(new list_lis());
        fasz(list,g);
        //g.gamespace.add(this);
        list.setPreferredSize(new Dimension(500,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);


    }
    public void fasz(JList list,GUI g) {
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                CurrentCommand = list.getSelectedIndex();
                g.gc.updateCommand(g);
            }
        });
    }

    public int getCurrentCommand(){return CurrentCommand;}

    final class list_lis implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            CurrentCommand = list.getSelectedIndex();

        }

    }
}
