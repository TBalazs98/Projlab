package Controller;

import Model.*;
import View.AsteroidView;
import View.GUI;
import View.MaterialView;
import View.SettlerView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public  class Controller {
    public GUI g;
    public static ArrayList<Settler> settlers=new ArrayList<>();
    public int selectedSettler=0;
    public Settler s=new Settler();
    public Asteroid a = new Asteroid();
    public ArrayList<DestinationObject> d = new ArrayList<>();
    private JPanel inventory;

    /**
     * Controller konstruktor
     */
    public Controller(){
        g=new GUI(this);
        g.DrawMenu();
    }

    /**
     * A mozgas megvalositasaert felelos metodus
     */
    public void DoTheMove(){
        s.Move(s.getAsteroid().GetNeighbourIndex(d.get(0)));
        g.DrawSettlers();
    }

    /**
     * Kivalaszt egy destination objectet, ahova a settler mozoghat
     * @param ddo cel Destination object
     */
    public void MoveSetDestination(DestinationObject ddo){
        if(Main.settlers.get(SelectedSettler()).getAsteroid().GetNeighbours().contains(ddo)){
            d.clear();
            d.add(ddo);
        }else{
            d.clear();
            d.add(Main.settlers.get(SelectedSettler()).getAsteroid().GetNeighbours().get(Main.settlers.get(SelectedSettler()).getAsteroid().GetRandNeighbour()) );
            //Ha nem jo celt jelolunk akkor random szomszedot fog kijelolni
        }

    }

    /**
     * Kivalasztja a mozgatando settlert
     * @param ss Mozgatando settler
     */
    public void MoveSetSettler(Settler ss){
        s=ss;
    }
    /**
     * Kivalaszt egy asteroid objectet, ahova a settler mozoghat
     * @param av cel AsteroidaView
     */
    public void MoveSetAsteroid(AsteroidView av){
        a=av.getAsteroid();
    }

    /**
     * A jelenleg kivalasztott settlerhez kapcsolodoan kijeloli a destination objecteket es a settlereket
     */
    public void HighlightSettlerStuff(){
        HighlightEverythingExcept(Game.getInstance().c.g.getSettlerViewBySettler(Main.settlers.get(SelectedSettler())));
        HighAsteroid(Main.settlers.get(SelectedSettler()));
    }

    /**
     * Lepteti a settlert
     */
    public void NextSettler(){
        selectedSettler++;
        if(Main.settlers.size()==0){
            Game.getInstance().LoseGame();
        }else {
            while (Main.settlers.get(SelectedSettler()) == null) {
                if(Main.settlers.size()==0){
                    Game.getInstance().LoseGame();
                    break;
                }
                selectedSettler++;
                }
                HighlightSettlerStuff();
        }
    }

    /**
     *
     * @return Visszaadja az aktualisan kijelolt settler indexet
     */
    public int SelectedSettler(){
        if(Main.settlers.size()==0){
            Game.getInstance().LoseGame();
        }
        if(selectedSettler==(Main.settlers.size())){
            selectedSettler=0;
            Game.getInstance().NextRound();
        }
        return selectedSettler;
    }

    /**
     * A Drilleles levezenyleseert felelos metodus
     */
    public void HandleDrill(){
        Main.settlers.get(SelectedSettler()).Drill();
    }

    /**
     * A Mineolas levezenyleseert felelos metodus
     */
    public void HandleMine(){
        Main.settlers.get(SelectedSettler()).Mine();
    }
    /**
     * A PlaceMaterial levezenyleseert felelos metodus
     */
    public void HandlePlaceMaterial(){
        Game.getInstance().c.g.dp.removeAll();
        ArrayList<JButton> materials = new ArrayList<>();
        ArrayList<JButton> gates = new ArrayList<>();
        ArrayList<TeleportGate> tgate = new ArrayList<>();
        ArrayList<Material> mat = new ArrayList<>();
        int db = 0;

        inventory = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inventory.setBackground(Color.GRAY);

        ImageIcon p = null;
        int scalingx= 60, scalingy = 60;
        for(Material m : Main.settlers.get(SelectedSettler()).GetInventory().GetMaterials()) {
            db++;
            mat.add(m);
            if (m.name == NormalMaterialName.IRON) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.name == NormalMaterialName.COAL) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.name == SublimableMaterialName.ICEWATER) {
                p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            if (m.name == RadioactiveMaterialName.URAN) {
                RadioactiveMaterial rm = (RadioactiveMaterial) m;
                if (rm.GetExposure() == 0)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
                else if (rm.GetExposure() == 1)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
                else if (rm.GetExposure() == 2)
                    p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            }
            JButton a = new JButton(p);
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.settlers.get(SelectedSettler()).PlaceMaterial(m);
                    a.setIcon(null);
                    inventory.removeAll();
                    Game.getInstance().c.g.update();
                    Game.getInstance().c.g.cp.InventoryPanel();
                    Game.getInstance().c.g.cp.repaint();
                    Game.getInstance().c.g.cp.validate();
                    Game.getInstance().c.g.dp.repaint();
                    Game.getInstance().c.g.dp.validate();
                        }
            });
            setbtn(a);
            materials.add(a);
            inventory.add(a);

        }

        for(TeleportGate t : Main.settlers.get(SelectedSettler()).GetGates()) {
            db++;
            p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            tgate.add(t);
            JButton a = new JButton(p);
            setbtn(a);
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.settlers.get(SelectedSettler()).PlaceGate(t);
                    Main.teleportgates.remove(tgate.get(gates.indexOf(a)));
                    Game.getInstance().AddSteppable(t);
                    a.setIcon(null);
                    Game.getInstance().c.g.addTeleportGate(t);
                    Main.settlers.get(SelectedSettler()).getAsteroid().AddNeighbour(t);
                    inventory.removeAll();
                    Game.getInstance().c.g.update();
                    Game.getInstance().c.g.cp.InventoryPanel();
                    Game.getInstance().c.g.cp.repaint();
                    Game.getInstance().c.g.cp.validate();
                    Game.getInstance().c.g.dp.repaint();
                    Game.getInstance().c.g.dp.validate();

                }
            });
            gates.add(a);
            inventory.add(a);
        }
        for(int i = 0; i < (13-db);i++){
            JButton a = new JButton();
            setbtn(a);
            inventory.add(a);
        }
        Game.getInstance().c.g.dp.add(inventory);
        Game.getInstance().c.g.cp.InventoryPanel();
        Game.getInstance().c.g.cp.repaint();
        Game.getInstance().c.g.cp.validate();
        Game.getInstance().c.g.dp.repaint();
        Game.getInstance().c.g.dp.validate();
    }

    /**
     * Ez a metodus hivodik meg, ha a jatekos nyert
     */
    public void SettlersHaveWon(){
        Game.getInstance().c.g.removeAll();
        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();
        Graphics paper = Game.getInstance().c.g.getGraphics();
        paper.clearRect(0, 0, (int)g.getSize().getWidth(), (int)g.getSize().getHeight());
        final BufferedImage image;
        try {
            image = ImageIO.read(new File("Files/Pictures/win.jpg"));
            paper.drawImage(image,0, 0,(int)g.getSize().getWidth(), (int)g.getSize().getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();
    }
    /**
     * Ez a metodus hivodik meg, ha a jatekos vesztett
     */
    public void SettlersLost(){
        Game.getInstance().c.g.removeAll();
        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();
        Graphics paper = Game.getInstance().c.g.getGraphics();
        paper.clearRect(0, 0, (int)g.getSize().getWidth(), (int)g.getSize().getHeight());
        final BufferedImage image;
        try {
            image = ImageIO.read(new File("Files/Pictures/lost.jpg"));
            paper.drawImage(image,0, 0,(int)g.getSize().getWidth(), (int)g.getSize().getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();
    }

    /**
     * Ez a metodus minden settlernel kikapcsolja a highlightot, kiveve annal, aki eppen ki van valasztva
     * @param sv Aktualis SettlerView
     */
    public void HighlightEverythingExcept(SettlerView sv){
       for(int i=0; i<Game.getInstance().c.g.GetSettlerView().size();i++){
           if(Game.getInstance().c.g.GetSettlerView().get(i).getSettler().getAsteroid()!=null) {
               if (Game.getInstance().c.g.GetSettlerView().get(i) == sv)
                   Game.getInstance().c.g.GetSettlerView().get(i).SelectHighlighted(true);
               else
                   Game.getInstance().c.g.GetSettlerView().get(i).SelectHighlighted(false);
           }
       }
    }

    /**
     * Ez a metodus highlightol minden olyan destination objectet ami a settler aszteroidajaval szomszedos
     * @param sv aktualis Settler
     */
    public void HighAsteroid(Settler sv){
        for(Asteroid a : Main.asteroids) {
            if (a != null) {
                Game.getInstance().c.g.getAsteroidViewByAsteroid(a).highlight(false, g);
            }
        }

        for(TeleportGate tg : Main.teleportgates) {
            if(tg.GetisPlaced())
                Game.getInstance().c.g.getTeleportGateViewByTeleportGate(tg).highlightt(false, g);

        }

        for(DestinationObject a : sv.getAsteroid().GetNeighbours()){
            if(a instanceof Asteroid)
                Game.getInstance().c.g.getAsteroidViewByAsteroid((Asteroid) a).highlight(true,g);
            if(a instanceof TeleportGate) {
                TeleportGate t = (TeleportGate) a;
                if(t.GetisPlaced())
                Game.getInstance().c.g.getTeleportGateViewByTeleportGate((TeleportGate) a).highlightt(true, g);
            }
        }
    }

    /**
     * A model objektumok alapjan inicializal egy view objektumot es hozzaadja a tombokhoz
     * @param g GUI
     */
    public void InitViews(GUI g){
        for(int i=0; i<Main.settlers.size();i++){
            g.addSettler(Main.settlers.get(i));
        }
        for(int i=0; i<Main.asteroids.size();i++){
            g.addAsteroid(Main.asteroids.get(i));
            Game.getInstance().AddSteppable(Main.asteroids.get(i));
        }
        for(int i=0; i<Main.robots.size();i++){
            g.addRobot(Main.robots.get(i));
            Game.getInstance().AddSteppable(Main.robots.get(i));
        }
        for(int i=0; i<Main.ufos.size();i++){
            g.addUfo(Main.ufos.get(i));
            Game.getInstance().AddSteppable(Main.ufos.get(i));
        }
        for(int i=0; i<Main.teleportgates.size();i++){
            g.addTeleportGate(Main.teleportgates.get(i));
            Game.getInstance().AddSteppable(Main.teleportgates.get(i));
        }
        for(int i=0; i<Main.materials.size();i++){
            g.addMaterial(Main.materials.get(i));
        }
    }

    /**
     * Robot epites levezenyleseert felelos metodus
     * @param prev robot epites elotti robotok szama
     */
    public void BuildRobot(int prev){
        int CurrentNumberOfRobots = Main.robots.size();
        if(CurrentNumberOfRobots > prev){
            g.addRobot(Main.robots.get(Main.robots.size()-1));
            Game.getInstance().AddSteppable(Main.robots.get(Main.robots.size()-1));
        }
        for(int i=0; i<g.GetAsteroidView().size() ;i++){
            g.GetAsteroidView().get(i).Draw();

        }
        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();

    }

    /**
     * Egyeni palya letrehozasaert felelos metodus
     */
    public void CreateCustomMap(){
        g.GetAsteroidView().clear();
        g.GetSettlerView().clear();
        Main.asteroids.clear();
        settlers.clear();
        Main.settlers.clear();
        Main.ufos.clear();
        Main.robots.clear();
        Main.materials.clear();
        Main.teleportgates.clear();
        Game.getInstance().c.g.GetAsteroidView().clear();
        ArrayList<Integer> datas = Game.getInstance().c.g.custompanel.GetCreatecount();
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

            Game.getInstance().c.g.addMaterial(m);
        }
        for(int i = 0; i< C;i++){
            Material m = new Material();
            m.setName(NormalMaterialName.COAL);
            Main.materials.add(m);
            Game.getInstance().c.g.addMaterial(m);
        }
        for(int i = 0; i< Ra;i++){
            RadioactiveMaterial rm = new RadioactiveMaterial();
            rm.setName(RadioactiveMaterialName.URAN);
            Main.materials.add(rm);
            Game.getInstance().c.g.addMaterial(rm);
        }
        for(int i = 0; i< W;i++){
            SublimableMaterial sm = new SublimableMaterial();
            sm.setName(SublimableMaterialName.ICEWATER);
            Main.materials.add(sm);
            Game.getInstance().c.g.addMaterial(sm);
        }
        //Nyersanyag END.


        //Aszteroidak letrehozas
        for(int i = 0; i< A;i++){
            Asteroid a = new Asteroid();
            Main.asteroids.add(a);
            Main.ab.AddAsteroid(a);
            Game.getInstance().c.g.addAsteroid(a);
        }
        if(materials > 0) {
            for (int i = 0; i < materials; i++) {
                Main.asteroids.get(i).SetMaterial(Main.materials.get(i));
                Main.asteroids.get(i).SetEmpty(false);
                Main.asteroids.get(i).setLayer(3);
            }
        }

        if(A > 1) {
            int rand_int, randd_int;
            for (int i = 0; i < A; i++) {
                Random randd = new Random();
                 randd_int = randd.nextInt(A/4)+1;
                for(int j=0; j<(randd_int);j++) {
                    Random rand = new Random();
                    rand_int = rand.nextInt(A);
                    int db = 0;
                    if (Main.asteroids.get(i).GetNeighbours() != null) {
                        for (DestinationObject o : Main.asteroids.get(i).GetNeighbours()) {
                            if (o == Main.asteroids.get(rand_int)) {
                                db++;
                            }
                        }
                    }

                    if (db == 0) {
                        Main.asteroids.get(i).AddNeighbour(Main.asteroids.get(rand_int));
                        Main.asteroids.get(rand_int).AddNeighbour(Main.asteroids.get(i));
                    }
                }
            }
        }
        //Aszteroida END.

        //TeleportGate letrehozas, par beallitas es elhelyezes aszteroidan
        if(T > 0) {
            for (int i = 0; i < T; i++) {
                TeleportGate t = new TeleportGate();
                Main.teleportgates.add(t);
                Game.getInstance().c.g.addTeleportGate(t);
            }

            if (T >= 2) {
                int j = 0;
                if (T % 2 == 0) {
                    while (j < T) {
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(++j));
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j - 1));
                        j++;
                    }
                } else {
                    while (j < (T - 1)) {
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(++j));
                        Main.teleportgates.get(j).setPair(Main.teleportgates.get(j-1));
                        j++;
                    }
                    Main.teleportgates.get(j).setPlaced(true);
                }

            }

            for (int i = 0; i < T; i++)
                while (Main.teleportgates.get(i).GetAsteroid() == null) {
                    Random rand = new Random();
                    int rand_int = rand.nextInt(A);
                    if (Main.teleportgates.get(i).GetPair() != null) {
                        if (Main.teleportgates.get(i).GetPair().GetAsteroid() != Main.asteroids.get(rand_int)) {
                            Main.teleportgates.get(i).setAsteroid(Main.asteroids.get(rand_int));
                            Main.asteroids.get(rand_int).AddNeighbour(Main.teleportgates.get(i));
                            Main.teleportgates.get(i).setActive(true);
                            Main.teleportgates.get(i).setPlaced(true);
                        }
                    } else {
                        Main.teleportgates.get(i).setAsteroid(Main.asteroids.get(rand_int));
                        Main.asteroids.get(rand_int).AddNeighbour(Main.teleportgates.get(i));
                        Main.teleportgates.get(i).setActive(true);
                        Main.teleportgates.get(i).setPlaced(true);
                    }

                }
        }
        //TeleportGate END.

        //Settler letrehozas, lehelyezes
        for(int i = 0; i < S;i++){
            Settler s = new Settler();
            Random rand = new Random();
            int rand_int = rand.nextInt(A);
            s.setAsteroid(Main.asteroids.get(rand_int));
            Main.asteroids.get(rand_int).setCharacter(s);
            Main.settlers.add(s);
            Game.getInstance().c.g.addSettler(s);

        }

        //Robot letrehozas, lehelyezes
        if(R > 0) {
            for (int i = 0; i < R; i++) {
                Model.Robot r = new Model.Robot();
                Random rand = new Random();
                int rand_int = rand.nextInt(A);
                r.setAsteroid(Main.asteroids.get(rand_int));
                Main.asteroids.get(rand_int).setCharacter(r);
                Main.robots.add(r);
                Game.getInstance().c.g.addRobot(r);
            }
        }

        //UFO letrehozas, lehelyezes
        if(U > 0) {
            for (int i = 0; i < U; i++) {
                UFO u = new UFO();
                Random rand = new Random();
                int rand_int = rand.nextInt(A);
                u.setAsteroid(Main.asteroids.get(rand_int));
                Main.asteroids.get(rand_int).setCharacter(u);
                Main.ufos.add(u);
                Game.getInstance().c.g.addUfo(u);
            }
        }


        Main.Randomize = (datas.get(9)==1)?true:false;

        Game.getInstance().AddSteppable(Main.ab);
    }

    /**
     * Megkeres egy material(view)-t hoz tartozo aszteroidaview-t a materialhoz tartozo aszteroida alapjan
     * @param m Materialview
     * @return AsteroidView
     */
    public AsteroidView getAsteroidViewByMaterialView(MaterialView m){
        for(int i=0;i<Main.asteroids.size();i++){
            if(Main.asteroids.get(i).getMaterial()==m.getMaterial()){
                return g.getAsteroidViewByAsteroid(Main.asteroids.get(i));
            }
        }
        return null;
    }

    /**
     * A gombok egyseges kinezet megjelenitesere szolgal
     * @param btn
     */
    public void setbtn(JButton btn){
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(60,60));
        btn.setBackground(Color.DARK_GRAY);
    }


}
