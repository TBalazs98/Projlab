package View;

/**
 * A jatek objektumok a felhasznaloi felulet megadott X,Y koordinataba valo helyezeseert felelos a Coordinates ozstaly
 */
public class Coordinates {
    private int x;
    private int y;
    boolean toggled;

    /**
     * Coordinates osztaly publikus konstruktora
     * @param _x X koordinata pont, amely pixelbe ki fogjuk rajzolni a palyara
     * @param _y Y koordinata pont, amely pixelbe ki fogjuk rajzolni a palyara
     */
    Coordinates(int _x, int _y) {
        x = _x;
        y = _y;
        toggled = false;
    }

    /**
     * Beallitja a toogled erteket, hogy a terulet foglalva legyen mas jatek objektum szamara
     * @param istoogled igaz hogyha lefoglaljuk azt a teruletet
     */
    public void setToggled(boolean istoogled)
    {
        toggled = istoogled;
    }

    public boolean isToggled() {
        return toggled;
    }

    /**
     * @return X koordinatat megkapjuk
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y koordinatat megkapjuk
     */
    public int getY() {
        return y;
    }

    public void toggle() {
        toggled = !toggled;
    }
}
