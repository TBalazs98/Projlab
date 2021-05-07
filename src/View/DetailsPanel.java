package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DetailsPanel extends JPanel {
    JPanel buildpanel=new JPanel();
    JPanel materialpanel=new JPanel();
    JPanel movepanel=new JPanel();
    ArrayList<JPanel> panels = new ArrayList<>();

    DetailsPanel(Controller c, GUI g){
        panels.add(buildpanel);
        panels.add(materialpanel);
        panels.add(movepanel);

         this.setPreferredSize(new Dimension(g.width/2,200));
         this.setOpaque(true);
         this.setBackground(Color.RED);
         this.setVisible(true);

         this.add(panels.get(c.getCurrentCommand()));
    }
}