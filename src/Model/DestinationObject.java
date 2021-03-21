package Model;

import java.util.*;

/**
 *Egysegesiti a teleportkaput és aszteroidat. A karakterek rajtuk kozlekednek.
 */
public interface DestinationObject {

    /**
     * Objektumra lépő karakter elfogadasa.
     * @param c
     * @return
     */
    public DestinationObject Accept(Character c) ;

    /**
     * Robbanas hatasara vegrehajtodo tortenesek az adott objektumon.
     * @param a
     */
    public void HitByExplosion(Asteroid a);
}