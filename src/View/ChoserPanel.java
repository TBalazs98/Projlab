package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ChoserPanel extends JPanel {
    private JComboBox<Integer> box;
    final private SettingsPanel panel;

    public ChoserPanel(GUI g, Vector<Integer> list, String text, SettingsPanel p){
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

        private final SettingsPanel panel;

        public updateListener(SettingsPanel p){
            panel = p;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.update();
        }
    }

}
