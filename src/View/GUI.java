package View;

import javax.swing.*;
import Controller.*;
import Model.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class GUI extends JFrame implements ActionListener {
    public int  width, height;

    JButton startgame, loadgame, settings, exit;
    MenuBar bar = new MenuBar(this);
    public JLayeredPane gamespace;
    public DetailsPanel dp;
    public CommandPanel cp ;/*= new CommandPanel(this);*/
    public CustomGamePanel custompanel;

    private ArrayList<IDrawable> drawables;
    public  ArrayList<SettlerView> settlers=new ArrayList<>();
    private ArrayList<AsteroidView> asteroids = new ArrayList<>();
    private ArrayList<RobotView> robots = new ArrayList<>();
    public  ArrayList<UfoView> ufos=new ArrayList<>();
    //private ArrayList<Tele> teleportgates = new ArrayList<>();    //#todo
    public  ArrayList<MaterialView> materials=new ArrayList<>();
    private Coordinates[][] coords;
    private int coordswidth;
    private int coordsheight;
    private Random gen = new Random();
    private Controller c;
    private ArrayList<Model.Character> chars = new ArrayList<>();

    public GUI(Controller _c) {
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
        ImageIcon img = new ImageIcon("Files/Pictures/sus.jpg");
        this.setIconImage(img.getImage());
        c = _c;

        drawables = new ArrayList<>();

        coordsheight = (height-330)/130;
        coordswidth = (width-130)/130;
        coords = new Coordinates[coordsheight][coordswidth];

        for(int i = 0; i < coordswidth; i++) {
            for(int j = 0; j < coordsheight; j++) {
                int offsetX = gen.nextInt(60);
                int offsetY = gen.nextInt(60);
                coords[j][i] = new Coordinates(i*130+offsetX, j*130+offsetY);
            }
        }

        System.out.println(this.width + " x " + this.height + "\t" + coordswidth + " x " + coordsheight);

    }


    private Coordinates getRandEmptyCoord() {
        int x = gen.nextInt(coordsheight);
        int y = gen.nextInt(coordswidth);
        while(coords[x][y].isToggled() == true) {
            x = gen.nextInt(coordsheight);
            y = gen.nextInt(coordswidth);
        }
        return coords[x][y];
    }

    private Coordinates getLonelyCoord() {
        int x = gen.nextInt(coordsheight);
        int y = gen.nextInt(coordswidth);

        while(coords[x][y].isToggled() ||coords[(x-1 < 0) ? 0 : x-1][y].isToggled() || coords[x][(y-1 < 0) ? 0 : y-1].isToggled() || coords[x][(y+1 >= coordswidth) ? y : y+1].isToggled() || coords[(x+1 >= coordsheight) ?  x : x+1][y].isToggled()) {
            x = gen.nextInt(coordsheight);
            y = gen.nextInt(coordswidth);
        }
        return coords[x][y];
    }



    public void DrawAll(){
        //region helo
        this.setLayout(new FlowLayout());
        gamespace = new JLayeredPane();
        gamespace.setPreferredSize(new Dimension(width,height-200));
        dp = new DetailsPanel();
        cp = new CommandPanel();
        JPanel controls = new JPanel();
        controls.setBackground(new Color(0,0,0,64));
        controls.setLayout(new FlowLayout());
        gamespace.setLayout(null);
        this.add(gamespace, BorderLayout.CENTER);
        //endregion

        /**
         * RAJZOLASOK IDE gamespacebe
         */

        for(int i=0; i<asteroids.size();i++){
            Coordinates coordinates = getLonelyCoord();
            coordinates.toggle();

            asteroids.get(i).SetCoords(coordinates.getX(),coordinates.getY());
            asteroids.get(i).Draw();
        }
        for(int i=0; i<this.settlers.size();i++){
            settlers.get(i).SetCoords(getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getX(),getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getY());
            System.out.println("x=" +getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getX() + "y=" +getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getY());
            settlers.get(i).Draw();
        }


        //materials.get(0).Draw();




//region buzisagok
        this.add(dp, BorderLayout.PAGE_END);
        controls.add(cp);
        controls.add(dp);
        this.add(controls, BorderLayout.PAGE_END);

        this.setJMenuBar(bar);
        //this.pack();
        this.setVisible(true);
//endregion
    }

    public AsteroidView getAsteroidViewByAsteroid(Asteroid a){
        for (int i=0; i<this.asteroids.size();i++){
            if(this.asteroids.get(i).getAsteroid()==a){
               // System.out.println("fasz");
                return this.asteroids.get(i);
            }

        }
        return null;
    }

    public SettlerView getSettlerViewBySettler(Settler s){
        for (int i=0; i<settlers.size();i++){
            if(settlers.get(i).getSettler()==s){
                //System.out.println("fasz");
                return settlers.get(i);
            }

        }
        return null;
    }
    public MaterialView getMaterialViewByMaterial(Material m){
        for (int i=0; i<materials.size();i++){
            if(materials.get(i).getMaterial()==m){
                //System.out.println("fasz");
                return materials.get(i);
            }

        }
        return null;
    }


    public void Load(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(this.height/12);
        this.setLayout(layout);
        LoadPanel loadpanel = new LoadPanel(this);
        this.add(loadpanel);
        this.setJMenuBar(bar);
        this.setVisible(true);

    }

    public void Settings() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(this.height/12);
        this.setLayout(layout);

       custompanel = new CustomGamePanel(this, this.coordswidth, this.coordsheight);

        this.add(custompanel);
        this.setJMenuBar(bar);
        this.setVisible(true);
    }

    public void  DrawMenu(){
        this.repaint();
        this.validate();
        this.setLayout(new FlowLayout());
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
        startgame.setEnabled(false);        //meg ez
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startgame){
            this.getContentPane().removeAll();
            Game.getInstance().c.CreateCustomMap();     //ez
            this.DrawAll();
            this.repaint();
            this.validate();
        } else if (e.getSource() == loadgame){
            this.getContentPane().removeAll();
            this.Load();
            this.repaint();
            this.validate();

        } else if (e.getSource() == settings) {
            this.getContentPane().removeAll();
            this.Settings();
            this.repaint();
            this.validate();
        }
        else if(e.getSource() == exit)
            System.exit(0);
    }

    public void addSettler(Settler s ){
        SettlerView sw = new SettlerView(s);
        settlers.add(sw);
        drawables.add(sw);
    }

    public void addAsteroid(Asteroid a ){
        AsteroidView av = new AsteroidView(a);
        asteroids.add(av);
        drawables.add(av);
    }
    public void addRobot(Model.Robot r ){
        RobotView rv = new RobotView(r);
        robots.add(rv);
        drawables.add(rv);
    }

    public void addUfo(UFO u ){
        UfoView uv = new UfoView(u);
        ufos.add(uv);
        drawables.add(uv);
    }
//    public void addSettler(Settler s ){       //#todo teleportgate view
//        SettlerView sw = new SettlerView(s);
//        settlers.add(sw);
//        drawables.add(sw);
//    }

    public void addMaterial(Material m ){
        MaterialView mv = new MaterialView(m);
        materials.add(mv);
        drawables.add(mv);
    }

    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
    }

    public int GetWidth(){
        return this.width;
    }
    public  ArrayList<SettlerView> GetSettlerView(){return settlers;}
    public  ArrayList<AsteroidView> GetAsteroidView(){return asteroids;}
    public void SetPanel(JPanel panel) {
        panel.setOpaque(false);
    }



}
