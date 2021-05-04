package View;

import javax.swing.*;
import Controller.*;

import java.awt.*;


public class GUI extends JFrame {
    int x,y;

    public void DrawAll(){
        this.setSize(new Dimension(300,400));

       //FlowLayout fl = new FlowLayout();
        JPanel gamespace = new JPanel();

        gamespace.setSize(new Dimension(200,200));
        gamespace.setPreferredSize(new Dimension(400,400));
        gamespace.setBackground(Color.BLUE);
        JPanel dp = new DetailsPanel();
        JPanel cp = new CommandPanel();


        this.add(gamespace, BorderLayout.CENTER);
        this.add(dp, BorderLayout.PAGE_END);
        this.add(cp, BorderLayout.PAGE_END);
        this.pack();
        this.setVisible(true);
    }

    public void Update(){}

}
