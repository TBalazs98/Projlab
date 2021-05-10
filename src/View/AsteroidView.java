package View;
import Controller.Main;
import Model.*;
import Model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public  class AsteroidView implements IDrawable {

    private Asteroid asteroid;
    private Icon p;
    public JLabel l;
    private int x, y;
    private boolean highlight = false;
    int scaling = 130;
    private boolean isInsusntorm = false, exploding = false;


    public AsteroidView(Asteroid a){
        this.asteroid = a;
        //if (asteroid.GetisEmpty()) {


        if(a.getLayers()>0) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }
        else if(a.getLayers() == 0) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/hollowasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        }
//        else if(!a.GetisEmpty()){
//            p = new ImageIcon(new ImageIcon("Files/Pictures/asteroid.png").getImage().getScaledInstance(scaling,scaling,Image.SCALE_SMOOTH));
//        }
            //as = new JButton(p );
            l = new JLabel(p);

            //as.setRolloverIcon(new ImageIcon("Files/Pictures/explosion.png"));
           /* as.setBorderPainted(false);
            as.setContentAreaFilled(false);
            as.setFocusPainted(false);
            as.setOpaque(false);
*/
        //}

        getAsteroidCoordsListener(l,this);


    }

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

        if (exploding)
            p = new ImageIcon(new ImageIcon("Files/Pictures/explosion.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));

        l.setIcon(p);
        this.l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Game.getInstance().c.g.dp.removeAll();
                JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER));
                info.add(new JLabel("[ASTEROID]"));
                info.add(new JLabel("\nA" + (Main.asteroids.indexOf(asteroid)+1)));
                info.add(new JLabel("\nLAYERS: " + asteroid.getLayers()));
                info.add(new JLabel("\nMATERIAL:"));
                if (asteroid.GetisEmpty())
                    info.add(new JLabel("\nEMPTY"));
                else {
                    info.add(new JLabel("\nM" + (Main.materials.indexOf(asteroid.getMaterial())+1) ));
                    info.add(new JLabel("" +asteroid.getMaterial().name));
                }
                ArrayList<String> neigh = new ArrayList<>();
                for (DestinationObject o : asteroid.GetNeighbours()) {
                    if (Main.asteroids.indexOf(o) != -1)
                        neigh.add("\nA" + (Main.asteroids.indexOf(o) + 1));
                    if (Main.teleportgates.indexOf(o) != -1)
                        neigh.add("\nG" + (Main.teleportgates.indexOf(o) + 1));
                }
                info.add(new JLabel("\nNEIGHBOURS:"));
                if(neigh.size() == 0){
                    info.add(new JLabel("\nNO NEIGHBOUR"));
                }
                else {
                    for (int i = 0; i < neigh.size(); i++)
                        info.add(new JLabel("\n" + (neigh.get(i))));
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

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void Draw() {
        p = new ImageIcon(new ImageIcon("Files/Pictures/hollowasteroid.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
        l.setIcon(p);
//        Game.getInstance().c.g.gamespace.add(l);
        l.setBounds(this.x, this.y, p.getIconWidth(), p.getIconWidth());
        CharacterView cv = new CharacterView(asteroid.getCharacters());

        cv.Draw();
        if(asteroid.getLayers() == 0) {
            Material m = asteroid.getMaterial();
            if(m != null) {
                MaterialView mv = new MaterialView(m);
                mv.SetCoords(x, y);
                mv.Draw();
            }
        }

        for(int i = 0; i < asteroid.GetNeighbours().size(); i++) {
            if(asteroid.GetNeighbours().get(i) instanceof TeleportGate) {

                Game.getInstance().c.g.getTeleportGateViewByTeleportGate((TeleportGate)asteroid.GetNeighbours().get(i)).Draw();
            }
        }
    }


    public void getAsteroidCoordsListener(JLabel l, AsteroidView av) {
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("x = "+av.x+ "y = " + av.y);
                Game.getInstance().c.MoveSetAsteroid(av);

            }
        });
    }

    public void highlight(boolean b,GUI g){
        highlight=b;
        setImage();
    }

    public Asteroid getAsteroid(){return asteroid;}

    public void setInSunstorm(boolean bool) {
        this.isInsusntorm = bool;
    }

    public boolean getisInSunstorm(){
        return isInsusntorm;
    }

    public void setExploding() {
        this.exploding = true;
    }
}
