package View;

import Controller.InputManager;
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


public class LoadPanel extends JPanel {

    private GameData data = new GameData();
    JLabel label;
    JButton button;
    JTable table;
    String chosen;


    public LoadPanel(GUI g) {
        this.setPreferredSize(new Dimension(g.height/5+g.height/5,g.height/5+g.height/5));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        table = new JTable();
        JPanel also = new JPanel(new FlowLayout());
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.addMouseListener(new gameChoseListener());
        label = new JLabel("Chosen game to load");
        label.setPreferredSize(new Dimension(g.width/12, 50));
        button = new JButton("Load Game");
        button.setEnabled(false);
        button.setPreferredSize(new Dimension(g.width/10, 40));
        button.addActionListener(new loadGameListener());
        also.setPreferredSize(new Dimension(g.width/4,75));
        also.add(label);
        also.add(button);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(also, BorderLayout.SOUTH);
        table.setModel(data);
    }

public JPanel getPanel()
{
    return this;
}

    private class loadGameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                InputManager.FromFileInput(chosen,false);
                Game.getInstance().c.g.remove(getPanel());

//                Game.getInstance().c.g.DrawAll();
//

//                Game.getInstance().c.g.DrawAll();
                Game.getInstance().c.InitViews( Game.getInstance().c.g);
                Game.getInstance().c.g.DrawAll();
                Game.getInstance().c.g.repaint();
                Game.getInstance().c.g.validate();

               //TODO játék indítása
            } catch(Exception ex) {
                ex.printStackTrace();
            }


        }

    }


    private class gameChoseListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            int rowIndex = table.rowAtPoint(e.getPoint());
            int colIndex = table.columnAtPoint(e.getPoint());
            chosen = (String) data.getValueAt(rowIndex, colIndex);
            label.setText(chosen.toString());
            button.setEnabled(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }





}
