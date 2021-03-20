package Model;

import java.util.*;

/**
 * 
 */
public interface DestinationObject {

    public DestinationObject Accept(Character c) ;
    public void HitByExplosion(Asteroid a);
}