package View;

import Controller.CommandManager;
import Model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenuItem save;
    private JMenuItem exit;
    GUI gui;
    static boolean gamesaved = false;

    /**
     * Konstruktor
     * @param g GUI
     */
    public MenuBar(GUI g){
        file = new JMenu("Game Options");
        save = new JMenuItem("Save Game");
        exit = new JMenuItem("Exit to Main Menu");
        gui = g;

        this.add(file);
        file.add(save);
        file.add(exit);

        exit.addActionListener(new exitListener());
        save.addActionListener(new saveListener());

        this.setVisible(true);
    }

    /**
     * Exitgame listener
     */
    private class exitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!gamesaved) {
                int n = JOptionPane.showConfirmDialog(
                        gui, "If you exit without saving the game, the state of your current game progress will be lost. Do you still want to Exit?",
                        "Exit Warning",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    exitMenu();
                }
            }
                else
                {
                    exitMenu();
                }
            }

        /**
         * Exitmenu
         */
        private void exitMenu() {
            gui.getContentPane().removeAll();
            gui.DrawMenu();
            Game.getInstance().c.g.startgame.setEnabled(true);
            gui.repaint();
            gui.validate();
            gamesaved = false;
            Game.getInstance().c.g.startgame.setEnabled(false);
        }
    }

    /**
     * Savegame listener
     */
    private static class saveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gamesaved = true;
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

}
