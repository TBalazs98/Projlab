package View;
import Controller.Main;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A nyersanyagok kirajzolasat kezelo osztaly
 */
public class MaterialView implements IDrawable {

    private Material material;
    private Icon p;
    public JLabel l = new JLabel();
    private int x,y, scalingx = 40,scalingy = 40;

    /**
     * A MaterialView publikus konstruktora
     * @param m nyersanyag, melyet ki akarunk rajzolni
     */
    public MaterialView(Material m){
        this.material = m;
        setImage();
    }

    /**
     * Beallitjuk a jelenlegi nyersanyag elhelyezkedeset
     * @param x X pixel koordinata
     * @param y Y pixel koordinata
     */
    public void SetCoords(int x, int y){
        this.x = x + 50;
        this.y = y + 50;
    }

    /**
     * Beallitjuk a jelenlegi nyersanyag kepet
     */
    public void setImage() {
        if(material.name==NormalMaterialName.IRON) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
        if(material.name==NormalMaterialName.COAL) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
        if(material.name==SublimableMaterialName.ICEWATER) {
            p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
        if(material.name==RadioactiveMaterialName.URAN) {

            RadioactiveMaterial rm = (RadioactiveMaterial)material;
            if(rm.GetExposure()==0)
                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            else if(rm.GetExposure()==1)
                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            else if(rm.GetExposure()==2)
                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            else if(rm.GetExposure()==3)
                p = null;
        }
        l.setIcon(p);
        l.addMouseListener(new MouseListener() {
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
                JPanel info = new JPanel(new GridLayout(1,5));
                info.add(new JLabel("[MATERIAL]"));
                info.add(new JLabel("\nM" + (Main.materials.indexOf(material)+1)));
                info.add(new JLabel("\n" + material.name));
                if(material.name == RadioactiveMaterialName.URAN) {
                    RadioactiveMaterial rm = (RadioactiveMaterial) material;
                    info.add(new JLabel("\nEXPOSURE:" +  rm.exposed));
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

    /**
     * Az interfesz Draw metodusanak megvalositasa
     */
    public void Draw(){
        setImage();

        AsteroidView av = Game.getInstance().c.getAsteroidViewByMaterialView(this);
        if(av!=null)
        SetCoords(av.getX(),av.getY());
        if(p == null){
            l.setBounds(this.x,this.y,0,0);
        }else
        l.setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
        Game.getInstance().c.g.gamespace.add(l,1);
        Game.getInstance().c.g.repaint();
        Game.getInstance().c.g.validate();
    }

    /**
     * Nyersanyag lekerdezo metodusa
     * @return nyersanyag amit lekerdezunk
     */
    public Material getMaterial(){return material;}
}
