import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Mapa {
    private double baseX;
    private double baseY;
    private ArrayList<SlotTorre> slots;
    private ArrayList<Integer> caminho;
    private int[][] matriz;

    public Mapa (double baseX, double baseY) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.slots = new ArrayList<>();
        this.caminho = new ArrayList<>();
        this.matriz = new int[18][32];
    }
    
    public Point2D.Double getPosicaoBase() {    
        return new Point2D.Double(baseX, baseY);
    }

    public ArrayList<Integer> getCaminho() {
        return caminho;
    }

    public ArrayList<SlotTorre> getSlots() {
        return slots;
    }

    public boolean colocarTorre(Torre torre,int x, int y) {
        if(x >= 0 && x < 18 && y < 32 && y >= 0) {
            if(matriz[x][y] == 0) {
                int Xtorre = y*40+20;
                int Ytorre = x*40+20;
                torre.definirPosicao(Xtorre,Ytorre);
                matriz[x][y] = 2;
                return true;
            }
        }
        return false;
    }
}