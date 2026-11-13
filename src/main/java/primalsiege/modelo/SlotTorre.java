package primalsiege.modelo;
public class SlotTorre {
    private double x;
    private double y;
    private Torre torre;

    public SlotTorre (double x, double y) {
        this.x = x;
        this.y = y;
        this.torre = null;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }

    public Torre getTorre () {
        return torre;
    }

    public void setTorre (Torre torre) {
        this.torre = torre;
    }
}