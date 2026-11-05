import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Mapa {
    private double baseX;
    private double baseY;
    private ArrayList<SlotTorre> slots;
    private ArrayList<Point2D.Double> caminho;

    public Mapa (double baseX, double baseY) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.slots = new ArrayList<>();
        this.caminho = new ArrayList<>();
    }

    public Point2D.Double getPosicaoBase() {    
        return new Point2D.Double(baseX, baseY);
    }

    public ArrayList<Point2D.Double> getCaminho() {
        return caminho;
    }

    public ArrayList<SlotTorre> getSlots() {
        return slots;
    }
}