package View;

import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    DetailsPanel(GUI g){
         this.setPreferredSize(new Dimension(g.width/2,200));
         this.setBackground(Color.PINK);
         this.setVisible(true);
    }
}