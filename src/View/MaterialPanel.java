package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MaterialPanel extends JPanel {
    MaterialPanel(Controller c, GUI g){
        this.setPreferredSize(new Dimension(g.width/2,200));
        this.setBackground(Color.BLUE);
        this.setVisible(true);
    }
}
