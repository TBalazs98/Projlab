package Model;

import java.util.*;

/**
 * 
 */
public abstract class Worker extends Character implements drillable {

    public Worker() {
        super();
    }

    public void Drill() {
        super.getAsteroid().Drilled();
    }

   // public abstract void Explode();   //Mivel a worker osztály még absztrakt
                                         //ezért itt nem fontos ezt implementálni,
                                        // csak a Settlerben meg a robotban

}