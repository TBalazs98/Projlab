package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class BuildPanel extends JPanel {
    JList<String> list ;

    BuildPanel(Controller c, GUI g){
        this.setPreferredSize(new Dimension(g.width/2,200));
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        String[] data = {"Robot", "Teleportgate", "Base"};
        list = new JList<String >(data);
        list.setPreferredSize(new Dimension(500,100));
        this.add(new JScrollPane(list),BorderLayout.CENTER);

    }
}
