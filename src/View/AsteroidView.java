package View;
import Controller.Main;
import Model.*;
import Model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Az AsteroidView osztaly az aszertoiak a felhasznaloi feluleten valo megjeleniteseert felelos, ezen okbol implementalja az IDrawable interfeszt
 */
public class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private Icon p;
    public JLabel l = new JLabel();
    private int x, y;
    private boolean highlight = false;
    int scaling = 130, explodingcount = -1;
    private boolean isInsusntorm = false, exploding = false;


    /**
     * AsteroidView publikus konstruktora
     * @param a az aszteroida, melyet ki szeretnenk rajzolni a felhasznaloi feluleten
     */
    public AsteroidView(Asteroid a) {
        this.asteroid = a;

        setImage();


    }

    /**
     * Az aszeteroida tulajdonsagainak megfeleloen meghivjuk az arra illeszkedo kepet, valamint a jelenleg kivalasztott aszteroida tulajdonsagainak
     * megjeleniteset az info panelben itt vegezzuk el.
     */
    public void setImage(){
        if (this.asteroid.getLayers() > 0 && !this.asteroid.GetSunProximity() && !isInsusntorm && !highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        if (this.asteroid.getLayers() > 0 && !this.asteroid.GetSunProximity() && !isInsusntorm && highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/selected_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if (this.asteroid.getLayers() > 0 && this.asteroid.GetSunProximity() && !isInsusntorm && !highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/nearsun_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        if (this.asteroid.getLayers() > 0 && this.asteroid.GetSunProximity() && !isInsusntorm && highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/selected_nearsun_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if (this.asteroid.getLayers() == 0 && !this.asteroid.GetSunProximity() && !isInsusntorm && !highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollowasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        if (this.asteroid.getLayers() == 0 && !this.asteroid.GetSunProximity() && !isInsusntorm && highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/selected_hollowasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if (this.asteroid.getLayers() == 0 && this.asteroid.GetSunProximity() && !isInsusntorm && !highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollow_nearsun_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        if (this.asteroid.getLayers() == 0 && this.asteroid.GetSunProximity() && !isInsusntorm && highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/selected_hollow_nearsun_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if (this.asteroid.getLayers() > 0 && this.asteroid.GetSunProximity() && isInsusntorm && !highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/nearsun_and_sunstorm_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        if (this.asteroid.getLayers() > 0 && this.asteroid.GetSunProximity() && isInsusntorm && highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/selected_nearsun_and_sunstorm_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if (this.asteroid.getLayers() == 0 && this.asteroid.GetSunProximity() && isInsusntorm && !highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollow_nearsun_sunstorm_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        if (this.asteroid.getLayers() == 0 && this.asteroid.GetSunProximity() && isInsusntorm && highlight)
            p = new ImageIcon(new ImageIcon("Files/Pictures/selected_hollow_nearsun_sunstorm_asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        if(this.asteroid.explodecount >=2){
            p = null;
        }
        if(this.asteroid.explodecount == 1){
            p = new ImageIcon(new ImageIcon("Files/Pictures/explosion.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            this.asteroid.explodecount++;
        }

        l.setIcon(p);
        this.l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                Game.getInstance().c.g.dp.removeAll();
                JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER));
                info.add(new JLabel("[ASTEROID]"));
                info.add(new JLabel("\nA" + (Main.asteroids.indexOf(asteroid)+1)));
                info.add(new JLabel("\nLAYERS: " + asteroid.getLayers()));
                info.add(new JLabel("\nSUNPORIMITY:" + ((asteroid.GetSunProximity()? "\nNEAR SUN" : "\nFAR FROM SUN"))));
                info.add(new JLabel("\nMATERIAL:"));
                if (asteroid.GetisEmpty())
                    info.add(new JLabel("\nEMPTY"));
                else {
                    info.add(new JLabel("\nM" + (Main.materials.indexOf(asteroid.getMaterial())+1) ));
                }
                ArrayList<String> neigh = new ArrayList<>();
                for (DestinationObject o : asteroid.GetNeighbours()) {
                    if (Main.asteroids.contains(o))
                        neigh.add("\nA" + (Main.asteroids.indexOf(o) + 1));
                    if (Main.teleportgates.contains(o))
                        neigh.add("\nG" + (Main.teleportgates.indexOf(o) + 1));
                }
                info.add(new JLabel("\nNEIGHBOURS:"));
                if(neigh.size() == 0){
                    info.add(new JLabel("\nNO NEIGHBOUR"));
                }
                else {
                    for (String s : neigh)
                        info.add(new JLabel("\n" + s));
                }
                Game.getInstance().c.g.dp.add(info);
                Game.getInstance().c.g.dp.repaint();
                Game.getInstance().c.g.dp.validate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Game.getInstance().c.g.dp.removeAll();
                Game.getInstance().c.g.dp.repaint();
                Game.getInstance().c.g.dp.validate();
            }
        });
    }

    public void SetCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() {
        return y;
    }

    /**
     * Az interfeszbol megvalositott Draw metodus, amely felrajzolja az aszteroidat a felhasznaloi feluletre
     */
    public void Draw() {
            if(p == null){
                l.setBounds(this.x,this.y,0,0);
            }else {
                l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
            }
        for(int i=0;i<asteroid.getCharacters().size();i++){
            if(asteroid.getCharacters().get(i) instanceof Model.Robot){
                Game.getInstance().c.g.getRobotViewByRobot((Model.Robot)asteroid.getCharacters().get(i)).Draw();
            }
            if(asteroid.getCharacters().get(i) instanceof UFO){
                Game.getInstance().c.g.getUfoViewByUfo((UFO)asteroid.getCharacters().get(i)).Draw();
            }
        }
            if (asteroid.getLayers() == 0) {
                Material m = asteroid.getMaterial();
                if (m != null) {
                    MaterialView mv = Game.getInstance().c.g.getMaterialViewByMaterial(m);
                    mv.SetCoords(x, y);
                    mv.Draw();
                }

            }
            for (int i = 0; i < asteroid.GetNeighbours().size(); i++) {
                if (asteroid.GetNeighbours().get(i) instanceof TeleportGate) {

                    Game.getInstance().c.g.getTeleportGateViewByTeleportGate((TeleportGate) asteroid.GetNeighbours().get(i)).Draw();
                }
            }
            Game.getInstance().c.g.repaint();
            Game.getInstance().c.g.validate();
    }

//    public void getAsteroidCoordsListener(JLabel l, AsteroidView av) {
//        l.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println("x = "+av.x+ "y = " + av.y);
//                Game.getInstance().c.MoveSetAsteroid(av);
//                Game.getInstance().c.MoveSetDestination(av.asteroid);
//
//            }
//        });
//    }

    /**
     * Az aszteoirda kijeloltsegenek beallitasaiert felelos metodus
     * @param isHiglighted a kijelolest allithatjuk be, ha igaz, ki lesz jelolve a jelenlegi aszteroida
     * @param g a megjelenito felulet ahova kirajzoljuk
     */
    public void highlight(boolean isHiglighted,GUI g){
        highlight=isHiglighted;
        setImage();
    }

    public Asteroid getAsteroid(){ return asteroid; }

    public void setInSunstorm(boolean bool) {
        this.isInsusntorm = bool;
    }

    public boolean getisInSunstorm(){
        return isInsusntorm;
    }

    public void setExploding() {
        this.exploding = true;
        this.asteroid.explodecount++;
        l.setIcon(p);
        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();
    }

    public boolean getExpoloding(){
        return this.exploding;
    }
}
