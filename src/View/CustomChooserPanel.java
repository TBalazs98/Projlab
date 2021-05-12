package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Az egyeni jatekok letrehozasakor az egyeni jatek objektumok ertekeinek beallitasaert felelos JPanel leszarmazott panel osztaly
 */
public class CustomChooserPanel extends JPanel {
    private JComboBox<Integer> box;
    final private CustomGamePanel panel;

    public CustomChooserPanel(GUI g, Vector<Integer> list, String text, CustomGamePanel p){
        panel = p;

        this.setPreferredSize(new Dimension(g.height/4,g.height/2+g.height/4));
        this.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(text);
        box = new JComboBox<>(list);
        if(text.equals("Asteroids")){
            box.addActionListener(new updateListener(panel));
        }
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);
        this.add(box);
    }


    /**
     * A jatek ojektum ertekeinek frissitese
     * @param list a jatek objektumok ertekeinek listaja
     */
    public void update(Vector<Integer> list){
        box.removeAllItems();
        for(int i : list){
            box.addItem(i);
        }
    }


    public Object getSelected(){
        return box.getSelectedItem();
    }

    private class updateListener implements ActionListener{

        private final CustomGamePanel panel;

        public updateListener(CustomGamePanel p){
            panel = p;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.update();
        }
    }

}
