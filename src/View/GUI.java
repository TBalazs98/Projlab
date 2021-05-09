package View;

import javax.swing.*;
import Controller.*;
import Model.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class GUI extends JFrame implements ActionListener {
    public int x, y, width, height;
    Image img;
    JButton startgame, loadgame, settings, exit;
    MenuBar bar = new MenuBar(this);
    public JLayeredPane gamespace;
    public DetailsPanel dp;
    public CommandPanel cp ;/*= new CommandPanel(this);*/
    public Controller gc;
    private int compnum;
    public CustomGamePanel settingspanel;

    private ArrayList<IDrawable> drawables;
    private ArrayList<AsteroidView> asteroids = new ArrayList<>();
    public  ArrayList<SettlerView> settlers=new ArrayList<>();
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
        //createChar();

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

    public int GetWidth(){
        return this.width;
    }

    public void DrawAll(){
        this.setLayout(new FlowLayout());
        gamespace = new JLayeredPane();

        gamespace.setPreferredSize(new Dimension(width,height-200));
        dp = new DetailsPanel();
        cp = new CommandPanel();

        JPanel controls = new JPanel();
        controls.setBackground(new Color(0,0,0,64));
        controls.setLayout(new FlowLayout());
        //SetPanel(gamespace);
        gamespace.setLayout(null);
        //gamespace.setBackground(Color.GREEN);
        this.add(gamespace, BorderLayout.CENTER);

        /**
         * RAJZOLASOK IDE gamespacebe
         */
        //Asteroid asteroid;
        //AsteroidView a;
//        for(int i=0; i<10; i++){
//            asteroid = new Asteroid();
//            Main.asteroids.add(asteroid);
//            //asteroid.setCharacter(chars.get(i));
//            //asteroid.setCharacter(chars.get(i+1));
//            a = new AsteroidView(asteroid);
//            Coordinates coord = getLonelyCoord();
//            a.SetCoords(coord.getX(), coord.getY());
//            coord.toggle();
//            a.setCompNum(i);
//            asteroids.add(a);
//            compnum = i;
//        }


        //asteroids.get(0).highlight();

//        for(AsteroidView i : asteroids){
//            x = i.getX();
//            y = i.getY();
//            i.Draw();
//        }

        for(int i=0; i<asteroids.size();i++){
            Coordinates coordinates = getLonelyCoord();
            coordinates.toggle();

            asteroids.get(i).SetCoords(coordinates.getX(),coordinates.getY());
            //x = asteroids.get(i).getX();
            //y = asteroids.get(i).getY();
            asteroids.get(i).Draw();
        }
        for(int i=0; i<settlers.size();i++){
            settlers.get(i).SetCoords(getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getX(),getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getY());
            System.out.println("x=" +getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getX() + "y=" +getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getY());
            settlers.get(i).Draw();
        }

       // Game.getInstance().NextRound(); #REKA
//        for(IDrawable i : drawables){
//            i.Draw();
//        }

        c.printxy();
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

        this.add(loadpanel);

        this.setJMenuBar(bar);
        this.setVisible(true);

    }
    public int getCompnum() {
        return compnum;
    }

    public void Settings() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(this.height/12);
        this.setLayout(layout);

       settingspanel = new CustomGamePanel(this, this.coordswidth, this.coordsheight);

        this.add(settingspanel);
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
            this.CreateCustomMap();
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

    public  ArrayList<SettlerView> GetSettlerView(){return settlers;}
    public  ArrayList<AsteroidView> GetAsteroidView(){return asteroids;}


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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


    private void createChar() {
        chars.add(new Settler());
        chars.add(new Model.Robot());
        chars.add(new Settler());
        chars.add(new Settler());
        chars.add(new Model.Robot());
        chars.add(new UFO());
        chars.add(new UFO());
        chars.add(new Settler());
        chars.add(new Model.Robot());
        chars.add(new Settler());
        chars.add(new Model.Robot());


    }
    public void CreateCustomMap(){
        Main.asteroids.clear();
        this.settlers.clear();
        Main.settlers.clear();
        Main.ufos.clear();
        Main.robots.clear();
        Main.materials.clear();
        Main.teleportgates.clear();
        this.asteroids.clear();
        ArrayList<Integer> datas = settingspanel.GetCreatecount();
        int I = datas.get(0);
        int C = datas.get(1);
        int Ra = datas.get(2);
        int W = datas.get(3);
        int A = datas.get(4);
        int T = datas.get(5);
        int S = datas.get(6);
        int R = datas.get(7);
        int U = datas.get(8);
        int materials = I+C+Ra+W;

        //Nyersanyagok leterhozasa

        for(int i = 0; i< I;i++){
            Material m = new Material();
            m.setName(NormalMaterialName.IRON);
            Main.materials.add(m);
        }
        for(int i = 0; i< C;i++){
            Material m = new Material();
            m.setName(NormalMaterialName.COAL);
            Main.materials.add(m);
        }
        for(int i = 0; i< Ra;i++){
            RadioactiveMaterial rm = new RadioactiveMaterial();
            rm.setName(RadioactiveMaterialName.URAN);
            Main.materials.add(rm);
        }
        for(int i = 0; i< W;i++){
            SublimableMaterial sm = new SublimableMaterial();
            sm.setName(SublimableMaterialName.ICEWATER);
            Main.materials.add(sm);
        }
        //Nyersanyag END.


        //Aszteroidak letrehozas
        for(int i = 0; i< A;i++){
            Asteroid a = new Asteroid();
            Main.asteroids.add(a);
            this.addAsteroid(a);
        }
        if(materials > 0) {
            for (int i = 0; i < materials; i++) {
                Main.asteroids.get(i).SetMaterial(Main.materials.get(i));
                Main.asteroids.get(i).SetEmpty(false);
                Main.asteroids.get(i).setLayer(3);
            }
        }

        if(A > 1) {
            for (int i = 0; i < A; i++) {
                    Random rand = new Random();
                    int rand_int = rand.nextInt(Main.asteroids.size());
                    int db = 0;
                    System.out.println(i);
                    System.out.println(Main.asteroids.get(i).GetNeighbourCount());
                    if (Main.asteroids.get(i).GetNeighbourCount() != -1) {
                        for (DestinationObject o : Main.asteroids.get(i).GetNeighbours()) {
                            if (o == Main.asteroids.get(rand_int)) {
                                db++;
                            }
                        }
                    }

                    if (db == 0)
                        Main.asteroids.get(i).AddNeighbour(Main.asteroids.get(rand_int));
            }
        }
        //Aszteroida END.

        //TeleportGate letrehozas, par beallitas es elhelyezes aszteroidan
        if(T > 0) {
            for (int i = 0; i < T; i++) {
                TeleportGate t = new TeleportGate();
                Main.teleportgates.add(t);
            }

            if(T > 2) {
                int j = 0;
                if (T % 2 == 0) {
                    while (j < T) {
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j++));
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j - 1));
                        j++;
                    }
                } else {
                    while (j < T - 1) {
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j++));
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j - 1));
                        j++;
                    }
                }
            }
            for (int i = 0; i < T; i++)
                while (Main.teleportgates.get(i).GetAsteroid() != null) {
                    Random rand = new Random();
                    int rand_int = rand.nextInt(A);
                    if (Main.teleportgates.get(i).GetPair().GetAsteroid() != Main.asteroids.get(rand_int))
                        Main.teleportgates.get(i).setAsteroid(Main.asteroids.get(rand_int));
                }

        }
        //TeleportGate END.

        //Settler letrehozas, lehejezes
        for(int i = 0; i < S;i++){
            Settler s = new Settler();
            Random rand = new Random();
            int rand_int = rand.nextInt(A);
            s.setAsteroid(Main.asteroids.get(rand_int));
            Main.asteroids.get(rand_int).setCharacter(s);
            Main.settlers.add(s);
            SettlerView sv = new SettlerView(s);
            this.settlers.add(sv);
        }

        //Robot letrehozas, lehejezes
        if(R > 0) {
            for (int i = 0; i < R; i++) {
                Model.Robot r = new Robot();
                Random rand = new Random();
                int rand_int = rand.nextInt(A);
                r.setAsteroid(Main.asteroids.get(rand_int));
                Main.asteroids.get(rand_int).setCharacter(r);
                Main.robots.add(r);
            }
        }

        //UFO letrehozas, lehejezes
        if(U > 0) {
            for (int i = 0; i < U; i++) {
                UFO u = new UFO();
                Random rand = new Random();
                int rand_int = rand.nextInt(A);
                u.setAsteroid(Main.asteroids.get(rand_int));
                Main.asteroids.get(rand_int).setCharacter(u);
                Main.ufos.add(u);
            }
        }

        //lehet nem jó h melyik mi
        Main.Randomize = (datas.get(9) == 0);


    }
}
