package Model;

/**
 * Magat a furast valositja meg, csokkenti az aszteroida koponyeget.
 */
public abstract class Worker extends Character implements drillable {

    /**
     * Konstruktor, a szulojetol (Character) orokli
     */
    public Worker() {
        super();
    }

    /**
     * Furas az aszteroidan.
     */
    public void Drill() {
        //.getInstance().printCommandCall(this);

        asteroid.Drilled();

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Mivel a worker osztaly meg absztrakt
     * ezert itt nem fontos ezt implementalni,
     * csak a Settlerben meg a Robotban.
     */
    public abstract void Explode();

}