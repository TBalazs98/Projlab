package View;

import javax.swing.*;
import java.awt.*;

public class CommandPanel extends JPanel {

    CommandPanel(GUI g){
        this.setPreferredSize(new Dimension(g.width/2,200));
        this.setBackground(Color.CYAN);
        this.setVisible(true);
    }
}
