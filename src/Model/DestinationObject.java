package Model;

import java.util.*;

/**
 *Egysegesiti a teleportkaput és aszteroidat. A karakterek rajtuk kozlekednek.
 */
public interface DestinationObject {

    /**
     * Objektumra lepo karakter elfogadasa.
     * @param c karakter aki az objektumra lep
     * @return objektum amin a valtozas tortent
     */
    public DestinationObject Accept(Character c) ;

    /**
     * Robbanas hatasara vegrehajtodo tortenesek az adott objektumon.
     * @param a eltavolitando aszteroida a szomszedok kozul
     */
    public void HitByExplosion(Asteroid a);

    /**
     * Napvihar altal sulytott jelenlegi objektum esemyenek kezelese
     */
    public void HitBySunstorm();

//    public DestinationObject copy();
}