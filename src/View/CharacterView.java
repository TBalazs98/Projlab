package View;

import Model.*;
import Model.Character;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A belso logika altal iranyitott karakterek felhasznalo feluletre valo rajzolasaert felelos osztaly, mely ezen okbol megvalositja a IDrawable interfeszt
 */
public class CharacterView implements IDrawable{

    private ArrayList<Character> chars;
    private GUI g;

    CharacterView(ArrayList<Character> ch) {
        chars = ch;

    }

    /**
     * Az interfesz a Draw metodusanak megvalositasa
     */
    public void Draw() {
        g = Game.getInstance().c.g;

        for(int i = 0; i < chars.size(); i++) {
            if(chars.get(i) instanceof Robot) {
                Call((Robot)chars.get(i));
            }
            if(chars.get(i) instanceof UFO) {
                Call((UFO)chars.get(i));
            }

        }
    }

    /**
     * Az UFO karakterek rajzolasanak hivo fuggvenye
     * @param u az UFO jatek objektum, melyet ki szeretnenk rajolzni a palyara
     */
    private void Call(UFO u) {
        UfoView uv =Game.getInstance().c.g.getUfoViewByUfo(u);
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( u.getAsteroid());
        if(av!=null) {
            uv.SetCoords(av.getX(), av.getY());
            uv.Draw();
        }
    }

    /**
     * A Robot karakterek rajzolasanak hivo fuggvenye
     * @param r a Robot jatek objektum, melyet ki szeretnenk rajolzni a palyara
     */
    private void Call(Robot r) {
        RobotView rv = Game.getInstance().c.g.getRobotViewByRobot(r);
        AsteroidView av = Game.getInstance().c.g.getAsteroidViewByAsteroid( r.getAsteroid());
        if(av !=null) {
            rv.SetCoords(av.getX(), av.getY());
            rv.Draw();
        }
    }




}
