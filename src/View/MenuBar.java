package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem exit;
    GUI gui;

    public MenuBar(GUI g){
        file = new JMenu("File");
        load = new JMenuItem("Load");
        save = new JMenuItem("save");
        exit = new JMenuItem("exit");
        gui = g;

        this.add(file);
        file.add(load);
        file.add(save);
        file.add(exit);

        exit.addActionListener(new exitListener());
        load.addActionListener(new loadListener());
        save.addActionListener(new saveListener());

        this.setVisible(true);
    }

    private class exitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO jatek mentes
            gui.getContentPane().removeAll();
            gui.repaint();
            gui.DrawMenu();
        }
    }

    private class saveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO egesz
            System.out.println("save");
        }
    }

    private class loadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO jatek mentes
            gui.getContentPane().removeAll();
            gui.repaint();
            gui.Load();
        }
    }


}
