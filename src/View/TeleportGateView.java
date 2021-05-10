package View;

import Model.Game;
import Model.TeleportGate;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TeleportGateView implements IDrawable{
    private TeleportGate tg;
    private Icon p;
    private JLabel l;
    private int x,y;
    private boolean highlight=false;

    public TeleportGateView(TeleportGate tg){
        this.tg=tg;
       // int scaling =130;
       // p=new ImageIcon("Files/Pictures/teleportgate.jpg");
        p=new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        l=new JLabel(p);
    }

    public void SetCoords(int x, int y){
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
        Random rnd = new Random();
        int offsetX = rnd.nextInt(20)+100;
        int offsetY = rnd.nextInt(20)+100;

        Game.getInstance().c.g.gamespace.add(l);
        Game.getInstance().c.g.gamespace.setComponentZOrder(l, 1);
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( tg.GetAsteroid());
        SetCoords(av.getX(),av.getY());
        l.setBounds(this.x+offsetX, this.y+offsetY, p.getIconWidth(), p.getIconWidth());


    }

    public void highlight(boolean b,GUI g){
        highlight=b;
        setImage();
    }

    private void setImage(){
        if(highlight==true) {
            int scaling = 130;
            p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            l.setIcon(p);
        }else {
            int scaling = 130;
            p = new ImageIcon(new ImageIcon("Files/Pictures/drillbtn.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
            l.setIcon(p);
        }
    }
    public TeleportGate getTg(){return this.tg;}

}
