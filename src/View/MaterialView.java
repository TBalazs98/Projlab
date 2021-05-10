package View;
import Controller.Main;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MaterialView implements IDrawable {

    private Material material;
    private Icon p;
    public JLabel l;
    private int x,y;

    public MaterialView(Material m){
        this.material = m;
        int scalingx = 40;
        int scalingy = 40;
//        if(material.getName()==NormalMaterialName.IRON) {
////            scalingx = 110;
////            scalingy = 90;
//            p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//        }
//        if(material.getName()==NormalMaterialName.COAL) {
////            scalingx = 40;
////            scalingy = 40;
//            p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//        }
//        if(material.getName()==SublimableMaterialName.ICEWATER) {
////            scalingx = 40;
////            scalingy = 40;
//            p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//        }
//        if(material.getName()==RadioactiveMaterialName.URAN) {
////            scalingx = 120;
////            scalingy = 100;
//            RadioactiveMaterial rm = (RadioactiveMaterial)material;
//            if(rm.GetExposure()==0)
//                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            else if(rm.GetExposure()==1)
//                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//            else if(rm.GetExposure()==2)
//                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
//        }
        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
       l = new JLabel(p);
        //Game.getInstance().c.g.gamespace.add(l);
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


       /* Material mat = new Material();
        mat.setName(NormalMaterialName.COAL);
        RadioactiveMaterial rm = new RadioactiveMaterial();
        rm.setName(RadioactiveMaterialName.URAN);
        rm.SetExposure(0);
        MaterialView mv = new MaterialView(mat);
        mv.SetCoords(newasteroidx+45,newasteroidy+45); //COAL
        // mv.SetCoords(newasteroidx+45,newasteroidy+45); //ICE
        //mv.SetCoords(newasteroidx+36,newasteroidy+30); //IRON
        // mv.SetCoords(newasteroidx+42,newasteroidy-20); //URAN*/          //EZ A GUIBA VOLT, MINDEN MATERIAL ASZTEROIDÁHOZ KÉPEST RAJZOLJA KI A KÖZEPÉRE
    }

    public void SetCoords(int x, int y){
        this.x = x + 50;
        this.y = y + 50;
    }

    public void Draw(){
        for(AsteroidView av: Game.getInstance().c.g.GetAsteroidView()) {
            if(av.getAsteroid().getMaterial() == material) {
                x = av.getX()+45;
                y = av.getY()+45;
            }
        }
        Game.getInstance().c.g.gamespace.add(l);

        //Game.getInstance().c.g.gamespace.getComponent(1).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 0);
        l.setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());

    }

    public Material getMaterial(){return material;}
}
