package View;
import Model.*;

import javax.swing.*;
import java.awt.*;

public class MaterialView implements IDrawable {

    private Material material;
    private ImageIcon p;
    private JLabel l;
    private int x,y;

    public MaterialView(Material m){
        this.material = m;
        int scalingx;
        int scalingy;
        if(material.getName()==NormalMaterialName.IRON) {
            scalingx = 110;
            scalingy = 90;
            p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
        if(material.getName()==NormalMaterialName.COAL) {
            scalingx = 40;
            scalingy = 40;
            p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
        if(material.getName()==SublimableMaterialName.ICEWATER) {
            scalingx = 40;
            scalingy = 40;
            p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
        if(material.getName()==RadioactiveMaterialName.URAN) {
            scalingx = 120;
            scalingy = 100;
            RadioactiveMaterial rm = (RadioactiveMaterial)material;
            if(rm.GetExposure()==0)
                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            else if(rm.GetExposure()==1)
                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
            else if(rm.GetExposure()==2)
                p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH));
        }
       l = new JLabel((Icon)p);


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
        this.x = x;
        this.y = y;
    }

    public void Draw(){

        Game.getInstance().c.g.gamespace.add(l);
        //Game.getInstance().c.g.gamespace.getComponent(1).setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());
        l.setBounds(this.x,this.y,p.getIconWidth(),p.getIconWidth());

    }

    public Material getMaterial(){return material;}
}
