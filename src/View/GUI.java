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
    public JLayeredPane gamespace= new JLayeredPane();
    public DetailsPanel dp;
    public CommandPanel cp ;
    public CustomGamePanel custompanel;
    public LoadPanel loadpanel;


    private ArrayList<IDrawable> drawables;
    public  ArrayList<SettlerView> settlers=new ArrayList<>();
    private ArrayList<AsteroidView> asteroids = new ArrayList<>();
    private ArrayList<RobotView> robots = new ArrayList<>();
    public  ArrayList<UfoView> ufos=new ArrayList<>();
    public ArrayList<TeleportGateView> teleportgates = new ArrayList<>();
    public  ArrayList<MaterialView> materials=new ArrayList<>();
    private Coordinates[][] coords;
    private final int coordswidth;
    private final int coordsheight;
    private Random gen = new Random();
    private ArrayList<Model.Character> chars = new ArrayList<>();


    /**
     * konstruktor
     * @param _c
     */
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
        ImageIcon img = new ImageIcon("Files/Pictures/icon.jpg");
        this.setIconImage(img.getImage());

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

    }

    /**
     * Minden koordinatat szabadda teszunk
     */
    public void clearCoords()
    {
        for(int i=0; i<coordswidth;i++)
            for(int j=0; j<coordsheight;j++)
                coords[j][i].setToggled(false);
    }


    /**
     * Getter egy olyan kordinatara ami nem foglalt
     * @return pozicio ahova lehet rajzolni
     */
    private Coordinates getLonelyCoord() {
        int x = gen.nextInt(coordsheight);
        int y = gen.nextInt(coordswidth);

        while(coords[x][y].isToggled() ||coords[(x-1 < 0) ? 0 : x-1][y].isToggled() || coords[x][(y-1 < 0) ? 0 : y-1].isToggled() || coords[x][(y+1 >= coordswidth) ? y : y+1].isToggled() || coords[(x+1 >= coordsheight) ?  x : x+1][y].isToggled()) {
            x = gen.nextInt(coordsheight);
            y = gen.nextInt(coordswidth);
        }
        return coords[x][y];
    }


    /**
     * Kezdeti inicializalo metodus
     */
    public void DrawAll(){
        this.setLayout(new FlowLayout());
        gamespace.removeAll();
        gamespace.setPreferredSize(new Dimension(width,height-250));
        dp = new DetailsPanel();
        cp = new CommandPanel();
        JPanel controls = new JPanel();
        controls.setBackground(new Color(0,0,0,64));
        controls.setLayout(new FlowLayout());
        gamespace.setLayout(null);
        this.add(gamespace, BorderLayout.CENTER);


        /**
         * RAJZOLASOK IDE gamespacebe
         */

        for(int i=0; i<asteroids.size();i++){
            Coordinates coordinates = getLonelyCoord();
            coordinates.toggle();
            asteroids.get(i).SetCoords(coordinates.getX(),coordinates.getY());
            gamespace.add(asteroids.get(i).l,2);
            asteroids.get(i).Draw();
        }

        for(int i=0; i<this.settlers.size();i++){
            settlers.get(i).SetCoords(getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getX(),getAsteroidViewByAsteroid( settlers.get(i).getSettler().getAsteroid()).getY());
            gamespace.add(settlers.get(i).l,1);
            settlers.get(i).Draw();
        }

        Game.getInstance().c.HighlightSettlerStuff();

        this.add(dp, BorderLayout.PAGE_END);
        controls.add(cp);
        controls.add(dp);
        this.add(controls, BorderLayout.PAGE_END);

        this.setJMenuBar(bar);
        this.setVisible(true);

    }

    /**
     * Kirajzolja a settlereket
     */
    public void DrawSettlers(){
        for(int i=0; i<this.settlers.size();i++){
            if(settlers.get(i)!=null) {
                settlers.get(i).SetCoords(getAsteroidViewByAsteroid(settlers.get(i).getSettler().getAsteroid()).getX(), getAsteroidViewByAsteroid(settlers.get(i).getSettler().getAsteroid()).getY());
                settlers.get(i).Draw();
            }
        }
    }

    /**
     * Megkeres egy aszteroida View-t egy aszteroida alapjan
     * @param a asteroida
     * @return Asteroid View
     */
    public AsteroidView getAsteroidViewByAsteroid(Asteroid a){
        for (int i=0; i<this.asteroids.size();i++){
            if(this.asteroids.get(i)!=null) {
                if (this.asteroids.get(i).getAsteroid() == a) {
                    return this.asteroids.get(i);
                }
            }
        }
        return null;
    }
    /**
     * Megkeres egy settler View-t egy settler alapjan
     * @param s settler
     * @return settler View
     */
    public SettlerView getSettlerViewBySettler(Settler s){
        for (int i=0; i<settlers.size();i++){
            if(settlers.get(i).getSettler()==s){
                return settlers.get(i);
            }

        }
        return null;
    }
    /**
     * Megkeres egy TG View-t egy TG alapjan
     * @param tg asteroida
     * @return TG View
     */
    public TeleportGateView getTeleportGateViewByTeleportGate(TeleportGate tg){
        for (int i=0; i<teleportgates.size();i++){
            if(teleportgates.get(i).getTg()==tg){
                return teleportgates.get(i);
            }

        }
        return null;
    }
    /**
     * Megkeres egy UFO View-t egy UFO alapjan
     * @param u asteroida
     * @return UFO View
     */
    public UfoView getUfoViewByUfo(UFO u){
        for (int i=0; i<ufos.size();i++){
            if(ufos.get(i).getUfo()==u){
                return ufos.get(i);
            }

        }
        return null;
    }
    /**
     * Megkeres egy MAterial View-t egy Material alapjan
     * @param m asteroida
     * @return Material View
     */
    public MaterialView getMaterialViewByMaterial(Material m){
        for (int i=0; i<materials.size();i++){
            if(materials.get(i).getMaterial()==m){
                return materials.get(i);
            }

        }
        return null;
    }

    /**
     * Megkeres egy Robot View-t egy Tobot alapjan
     * @param r asteroida
     * @return Robot View
     */
    public RobotView getRobotViewByRobot(Model.Robot r){
        for (int i=0; i<robots.size();i++){
            if(robots.get(i).getRobot()==r){
                return robots.get(i);
            }

        }
        return null;
    }

    /**
     * load panel megjelenitese
     */
    public void Load(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(this.height/12);
        this.setLayout(layout);
        loadpanel = new LoadPanel(this);
        this.add(loadpanel);
        this.setJMenuBar(bar);
        this.setVisible(true);

    }

    /**
     * settings panel megjelenitese
     */
    public void Settings() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(this.height/12);
        this.setLayout(layout);

       custompanel = new CustomGamePanel(this, this.coordswidth, this.coordsheight);

        this.add(custompanel);
        this.setJMenuBar(bar);
        this.setVisible(true);
    }

    /**
     * megjeleniti a menut
     */
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
        settings = new JButton(new ImageIcon("Files/Pictures/bcustom_light.png"));
        settings.setRolloverIcon(new ImageIcon("Files/Pictures/bcustom-game_dark.png"));
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
            Game.getInstance().c.InitViews(this);
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

    /**
     * letrehoz és hozzaad a settler view tombhoz egyet egy settler apajan
     * @param s settler
     */
    public void addSettler(Settler s ){
        SettlerView sw = new SettlerView(s);
        settlers.add(sw);
        drawables.add(sw);
    }
    /**
     * letrehoz és hozzaad a aszteroidat view tombhoz egyet egy aszteroida apajan
     * @param a aszteroida
     */
    public void addAsteroid(Asteroid a ){
        AsteroidView av = new AsteroidView(a);
        asteroids.add(av);
        drawables.add(av);
    }
    /**
     * letrehoz és hozzaad a robot view tombhoz egyet egy tobot apajan
     * @param r robot
     */
    public void addRobot(Model.Robot r ){
        RobotView rv = new RobotView(r);
        robots.add(rv);
        drawables.add(rv);
    }
    /**
     * letrehoz és hozzaad a ufo view tombhoz egyet egy ufo apajan
     * @param u settler
     */
    public void addUfo(UFO u ){
        UfoView uv = new UfoView(u);
        ufos.add(uv);
        drawables.add(uv);
    }
    /**
     * letrehoz és hozzaad a tg view tombhoz egyet egy tg apajan
     * @param tg tg
     */
    public void addTeleportGate(TeleportGate tg ){       //#todo teleportgate view
        TeleportGateView tgv = new TeleportGateView(tg);
        teleportgates.add(tgv);
        drawables.add(tgv);
    }

    /**
     * letrehoz és hozzaad a material view tombhoz egyet egy material apajan
     * @param m material
     */
    public void addMaterial(Material m ){
        MaterialView mv = new MaterialView(m);
        materials.add(mv);
        drawables.add(mv);
    }

    /**
     * a gombok egyseges megjelenitesere
     * @param button gomb
     */
    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
    }

    /**
     * ablak szelessege
     * @return szeleseeg
     */
    public int GetWidth(){
        return this.width;
    }

    /**
     * visszaadja a settlerview tombot
     * @return settlerview
     */
    public  ArrayList<SettlerView> GetSettlerView(){return settlers;}
    /**
     * visszaadja a aszteroid tombot
     * @return aszteroid
     */
    public  ArrayList<AsteroidView> GetAsteroidView(){return asteroids;}
    public void SetPanel(JPanel panel) {
        panel.setOpaque(false);
    }

    /**
     * updateli az aszteroidakat es ezaltal meg sokmindent
     */
    public void update() {

        for(AsteroidView av: asteroids) {
                if (av.getAsteroid() != null) {
                    av.Draw();
                }
        }

        gamespace.repaint();
        gamespace.validate();
    }

}
