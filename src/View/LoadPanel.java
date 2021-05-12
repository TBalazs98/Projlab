package View;

import Controller.InputManager;
import Controller.Main;
import Model.Game;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


/**
 * A jatek betolteseert, illetve megjeleniteseert felelos osztaly, mely egy JPanel leszarmazott
 */
public class LoadPanel extends JPanel {

    private GameData data = new GameData();
    JButton button;
    JTable table;
    String chosen;

    /**
     * A LoadPanel publikus konstruktora
     * @param g A felhasznaloi felulet ahova felrajzoljuk a jatek kivalaszto panelt
     */
    public LoadPanel(GUI g) {
        this.setPreferredSize(new Dimension(g.height/5+g.height/5,g.height/5+g.height/5));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        table = new JTable();
        JPanel also = new JPanel(new CardLayout());
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.getSelectionModel().addListSelectionListener(new gameChoseListener());
        button = new JButton(new ImageIcon("Files/Pictures/button_load-game_l.png"));
        button.setRolloverIcon(new ImageIcon("Files/Pictures/button_load-game_d.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setEnabled(false);
        button.addActionListener(new loadGameListener());
        also.setPreferredSize(new Dimension(g.width/4,75));
        also.add(button);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(also, BorderLayout.SOUTH);
        table.setModel(data);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

public JPanel getPanel()
{
    return this;
}

    /**
     * Figyeli, ha megnyomtuk a Load Game gombot a mentett jatekot betolti a kepernyore
     */
    private class loadGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Game.getInstance().c.g.gamespace.removeAll();
                Game.getInstance().c.g.materials.clear();
                Game.getInstance().c.g.ufos.clear();
                Game.getInstance().c.g.teleportgates.clear();
                Game.getInstance().c.g.GetAsteroidView().clear();
                Game.getInstance().c.g.GetSettlerView().clear();
                Main.asteroids.clear();
                Game.getInstance().c.g.settlers.clear();
                Main.settlers.clear();
                Main.ufos.clear();
                Main.robots.clear();
                Main.materials.clear();
                Main.teleportgates.clear();
                Game.getInstance().c.g.clearCoords();
                InputManager.FromFileInput(chosen,false);
                Game.getInstance().c.g.remove(getPanel());
                Game.getInstance().c.InitViews( Game.getInstance().c.g);
                System.out.println(Game.getInstance().c.g.GetAsteroidView().size());
                Game.getInstance().c.g.DrawAll();
                Game.getInstance().c.g.repaint();
                Game.getInstance().c.g.validate();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Figyeli, ha kivalasztottunk a mentett jatokok tablazatbol egy elemet, akkor a Load Gomb-ot bekapcsolja,
     * valamint a kivalasztott mentett jatek fajl elerhetoseget inicializalja string formatumban
     */
    private class gameChoseListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting())
            {
                chosen = (String) data.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
                button.setEnabled(true);
            }

        }
    }

}
