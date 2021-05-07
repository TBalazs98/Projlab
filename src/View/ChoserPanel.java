package View;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ChoserPanel extends JPanel {
    private JLabel label;
    private JComboBox<Object> box;

    public ChoserPanel(GUI g, Vector<Object> list, String text){
        this.setPreferredSize(new Dimension(g.height/4,g.height/2+g.height/4));

        this.setLayout(new GridLayout(1,2));
        label = new JLabel(text);
        box = new JComboBox<Object>(list);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);
        this.add(box);
    }


    public Object getSelected(){
        return box.getSelectedItem();
    }

}
