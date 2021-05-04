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

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());

        this.add(gamespace, BorderLayout.CENTER);


        this.add(dp, BorderLayout.PAGE_END);
        controls.add(cp);
        controls.add(dp);
        this.add(controls, BorderLayout.PAGE_END);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void Update(){}

}
