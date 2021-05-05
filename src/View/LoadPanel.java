package View;

import javax.swing.*;
import java.awt.*;


public class LoadPanel extends JPanel {

    public LoadPanel(GUI g) {
        this.setPreferredSize(new Dimension(g.height/2+g.height/4,g.height/2+g.height/4));


        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        JTable table = new JTable();
        JPanel also = new JPanel();
        also.setPreferredSize(new Dimension(g.width/4,75));
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(also, BorderLayout.SOUTH);

    }
}
