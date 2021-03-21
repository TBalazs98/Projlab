package Model;

import java.util.*;

/**
 * Egy interfész, ami minden olyan dolgot reprezentál, amely körönként automatikusan csinál
 * valamit.
 */
public interface Steppable {

    /**
     * Az adott körben végrehajtandó művelet.
     */
    public void Step() ;

}