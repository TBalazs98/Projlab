package View;

import Controller.CommandManager;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem exit;
    GUI gui;

    public MenuBar(GUI g){
        file = new JMenu("Game");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit to Main Menu");
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
            gui.DrawMenu();
            Game.getInstance().c.g.startgame.setEnabled(true);
            gui.repaint();
            gui.validate();
        }
    }

    private static class saveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane gamesaved = new JOptionPane("Your game saved successfully!",JOptionPane.INFORMATION_MESSAGE);
            final JDialog d = new JDialog(GUI.getFrames()[0], "Save Game", true);
            d.setSize(300, 160);
            d.setLocationRelativeTo(GUI.getFrames()[0]);
            d.getContentPane().setLayout(null);
            JLabel l = new JLabel("Your game is going to be saved as:");
            l.setBounds(50, 16, 300, 20);
            final JTextField name = new JTextField();
            name.setBounds(55, 50, 175, 20);
            d.getContentPane().add(name);
            d.add(name);
            d.add(l);
            JButton button = new JButton("Save");
            button.setBounds(100, 85, 78, 23);
            d.add(button);
            button.addActionListener(e1 -> {
                String newsave = name.getText();
                CommandManager.saveMap(newsave);
                d.dispose();
                JDialog dialog = gamesaved.createDialog("Game Saved");
                dialog.setVisible(true);
                dialog.setAlwaysOnTop(true);
            });
            d.setVisible(true);
        }
    }

    private class loadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO jatek mentes
            gui.getContentPane().removeAll();
            gui.Load();
            gui.repaint();
            gui.validate();
        }
    }


}
