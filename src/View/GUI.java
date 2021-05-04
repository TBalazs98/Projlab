package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;


public class GUI extends JFrame implements ActionListener {
    public int x, y, width, height;
    Image img;
    JButton startgame, loadgame, settings, exit;

    public GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AsteroidMine");
        this.setContentPane(new JLabel(new ImageIcon("Files/Pictures/space.jpg")));
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.width = getWidth();
        this.height = getHeight();
    }

    public int GetWidth(){
        return this.width;
    }

    public void DrawAll(){
        JPanel gamespace = new JPanel();

        gamespace.setPreferredSize(new Dimension(width,height-200));
        JPanel dp = new DetailsPanel(this);
        JPanel cp = new CommandPanel(this);
        SetPanel(gamespace);

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
        JPanel  title, sg, lg, st, ex;

        this.setPreferredSize(new Dimension(getWidth(),getHeight()));
        this.setLayout(new GridLayout(5,1));

        title = new JPanel();
        SetPanel(title);
        this.add(title);

        //SZAR AZ ELÉRÉSI ÚT:3
        sg = new JPanel();
        startgame = new JButton( new ImageIcon("Files/Pictures/startbtn.png"));
        startgame.setRolloverIcon(new ImageIcon("Files/Pictures/startdarkbtn.png"));
        SetButton(startgame);
        SetPanel(sg);
        sg.add(startgame);
        this.add(sg);


        lg = new JPanel();
        loadgame = new JButton(new ImageIcon("Files/Pictures/loadbtn.png"));
        loadgame.setRolloverIcon(new ImageIcon("Files/Pictures/loaddarkbtn.png"));
        SetButton(loadgame);
        lg.add(loadgame);
        SetPanel(lg);
        this.add(lg);


        st = new JPanel();
        settings = new JButton(new ImageIcon("Files/Pictures/settingsbtn.png"));
        settings.setRolloverIcon(new ImageIcon("Files/Pictures/settingsdarkbtn.png"));
        SetButton(settings);
        st.add(settings);
        SetPanel(st);
        this.add(st);


        ex = new JPanel();
        exit = new JButton(new ImageIcon("Files/Pictures/exitbtn.png"));
        exit.setRolloverIcon(new ImageIcon("Files/Pictures/exitdarkbtn.png"));
        SetButton(exit);
        ex.add(exit);
        ex.setOpaque(false);
        this.add(ex);

        this.pack();
        this.setVisible(true);

    }

    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
    }

    public void SetPanel(JPanel panel) {
        panel.setOpaque(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startgame){

        } else if (e.getSource() == loadgame){

        } else if (e.getSource() == settings) {

        }
        else if(e.getSource() == exit)
            System.exit(0);
    }
}
