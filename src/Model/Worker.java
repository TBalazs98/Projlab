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
        Logger.getInstance().printCommandCall(this);

        asteroid.Drilled();

        Logger.getInstance().printReturnCommand();
    }

    public abstract void Explode();   //Mivel a worker osztály még absztrakt
                                         //ezért itt nem fontos ezt implementálni,
                                        // csak a Settlerben meg a robotban

}