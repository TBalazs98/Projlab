package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controller.*;
import Model.Asteroid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Flow;


public class GUI extends JFrame implements ActionListener {
    public int x, y, width, height;
    Image img;
    JButton startgame, loadgame, settings, exit;
    MenuBar bar = new MenuBar(this);
    public JPanel gamespace;

    private ArrayList<IDrawable> drawables;
    private ArrayList<AsteroidView> asteroids;


    public GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AsteroidMine");
        this.setContentPane(new JLabel(new ImageIcon("Files/Pictures/space.jpg")));
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setVisible(true);
        this.width = getWidth();
        this.height = getHeight();
        this.setJMenuBar(null);
        ImageIcon img = new ImageIcon("Files/Pictures/space.jpg");
        this.setIconImage(img.getImage());

        drawables = new ArrayList<>();
        asteroids = new ArrayList<>();
    }

    public int GetWidth(){
        return this.width;
    }

    public void DrawAll(){
        this.setLayout(new FlowLayout());
        gamespace = new JPanel();

        gamespace.setPreferredSize(new Dimension(width,height-200));
        JPanel dp = new DetailsPanel(this);
        JPanel cp = new CommandPanel(this);

        JPanel controls = new JPanel();
        controls.setBackground(new Color(0,0,0,64));
        controls.setLayout(new FlowLayout());
        SetPanel(gamespace);
        gamespace.setLayout(null);
        //gamespace.setBackground(Color.GREEN);
        this.add(gamespace, BorderLayout.CENTER);

        /**
         * RAJZOLASOK IDE gamespacebe
         */
        Random rnd = new Random();
        int asteroidx=0;
        int asteroidy = 0;
        Asteroid asteroid;
        AsteroidView a;
        for(int i=0; i<10; i++){
            int newasteroidx, newasteroidy;
            asteroid = new Asteroid();
            a = new AsteroidView(asteroid);
            a.SetCoords(asteroidx,asteroidy);
            a.setCompNum(i);
            asteroids.add(a);
            newasteroidx = rnd.nextInt(width-130);
            newasteroidy = rnd.nextInt(height-330);
            int counter =0;
            boolean allisfar = false;
            while(allisfar == false && i < 9) {
                boolean breakout = false;
                for(int j = 0; j < i; j++) {
                    int first = newasteroidx-asteroids.get(j).getX();
                    int second = newasteroidy-asteroids.get(j).getY();
                    int scale = 130;
                    if((first < scale && first > (scale *-1)) || (second < scale && second > (scale *-1))) {
                        breakout = true;
                        break;
                    }
                }
                if(breakout == false) {
                    asteroidx = newasteroidx;
                    asteroidy = newasteroidy;
                    allisfar = true;

                }
                else {
                    if(counter >= 7) {
                        i--;
                        asteroids.remove(asteroids.size()-1);
                        break;
                    }
                    newasteroidx = rnd.nextInt(width-130);
                    newasteroidy = rnd.nextInt(height-330);
                    counter++;
                }
            }
        }




        for(IDrawable i : asteroids){
            i.Draw(this);
        }



        //gamespace.repaint();


        this.add(dp, BorderLayout.PAGE_END);
        controls.add(cp);
        controls.add(dp);
        this.add(controls, BorderLayout.PAGE_END);

        this.setJMenuBar(bar);
        //this.pack();
        this.setVisible(true);

    }

    public void Update(){
        this.setPreferredSize(new Dimension(1200,600));
        DrawAll();
        this.setVisible(true);
    }

    public void Load(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(this.height/12);
        this.setLayout(layout);

        LoadPanel loadpanel = new LoadPanel(this);
        //loadpanel.setBackground(new Color(0,0,0,64));
        this.add(loadpanel);

        this.setJMenuBar(bar);
        this.setVisible(true);

    }

    public void  DrawMenu(){
        JPanel  title, sg, lg, st, ex;

        this.setPreferredSize(new Dimension(getWidth(),getHeight()));
        this.setLayout(new GridLayout(5,1));

        title = new JPanel();
        SetPanel(title);
        this.add(title);

        /**TODO
         * SZAR AZ ELÉRÉSI ÚT:3
         */
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

        this.setJMenuBar(null);
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
            this.getContentPane().removeAll();
            this.repaint();
            DrawAll();
        } else if (e.getSource() == loadgame){
            this.getContentPane().removeAll();
            this.repaint();
            this.Load();

        } else if (e.getSource() == settings) {

        }
        else if(e.getSource() == exit)
            System.exit(0);
    }




}
