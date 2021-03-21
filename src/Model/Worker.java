package Model;

/**
 * Magát a fúrást valósítja meg, csökkenti az aszteroida köpöcsnyegét.
 */
public abstract class Worker extends Character implements drillable {

    /**
     * Konstruktor, a szüőjátől (Character) örökli
     */
    public Worker() {
        super();
    }

    /**
     * Fúrás az aszteroidán.
     */
    public void Drill() {
        Logger.getInstance().printCommandCall(this);

        asteroid.Drilled();

        Logger.getInstance().printReturnCommand();
    }

    /**
     * Mivel a worker osztály még absztrakt
     * ezért itt nem fontos ezt implementálni,
     * csak a Settlerben meg a robotban.
     */
    public abstract void Explode();

}