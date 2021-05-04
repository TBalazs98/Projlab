package View;

import javax.swing.*;
import Controller.*;

import java.awt.*;
import java.awt.event.ComponentListener;
import java.util.Set;


public class GUI extends JFrame {
    int x, y;
    Image img;
    public GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AsteroidMine");
        this.setContentPane(new JLabel(new ImageIcon("Files/Pictures/space.jpg")));
        //this.add(background);
        //background.setLayout(new FlowLayout());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void DrawAll(){
       //FlowLayout fl = new FlowLayout();
        JPanel gamespace = new JPanel();

        gamespace.setPreferredSize(new Dimension(800,800));
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



        this.pack();
        this.setVisible(true);
    }

    public void Update(){
        this.setPreferredSize(new Dimension(800,800));
        this.setVisible(true);
    }

    public void  DrawMenu(){
        JButton startgame, loadgame, settings, exit;
        JPanel  title, sg, lg, st, ex;

        this.setPreferredSize(new Dimension(800,800));
        this.setContentPane(new JLabel(new ImageIcon("Files/Pictures/space.jpg")));

        this.setLayout(new GridLayout(5,1));

        title = new JPanel();
        title.setOpaque(true);
        this.add(title);

        //SZAR AZ ELÉRÉSI ÚT:3
        sg = new JPanel();
        startgame = new JButton( new ImageIcon("Files/Pictures/startbtn.png"));
        SetButton(startgame);
        sg.setOpaque(true);
        sg.add(startgame);
        this.add(sg);


        lg = new JPanel();
        loadgame = new JButton(new ImageIcon("Files/Pictures/loadbtn.png"));
        SetButton(loadgame);
        lg.add(loadgame);
        this.add(lg);


        st = new JPanel();
        settings = new JButton(new ImageIcon("Files/Pictures/settingsbtn.png"));
        SetButton(settings);
        st.add(settings);
        this.add(st);


        ex = new JPanel();
        exit = new JButton(new ImageIcon("Files/Pictures/exitbtn.png"));
        SetButton(exit);
        ex.add(exit);
        this.add(ex);

        //this.setResizable(false);
        this.pack();
        this.setVisible(true);

    }

    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

}
