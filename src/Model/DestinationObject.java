package Model;

import java.util.*;

/**
 * 
 */
public interface DestinationObject {

    public void Accept(Character c) ;
    public void HitByExplosion(Asteroid a);
}