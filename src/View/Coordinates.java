package View;

public class Coordinates {
    private int x;
    private int y;
    boolean toggled;

    Coordinates(int _x, int _y) {
        x = _x;
        y = _y;
        toggled = false;
    }

    public boolean isToggled() {
        return toggled;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void toggle() {
        toggled = !toggled;
    }
}
