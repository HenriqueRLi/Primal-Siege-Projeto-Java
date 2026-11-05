import java.util.ArrayList;
import java.awt.geom.Point2D;

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
        criarCaminho();
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

    private void criarCaminho() {
        for (int i = 0; i < 6; i++) {
            matriz[i][3] = 1;
        }

        for (int j = 3; j < 18; j++) {
            matriz[5][j] = 1;
        }

        for (int i = 5; i < 12; i++) {
            matriz[i][17] = 1;
        }

        for (int j = 17; j > 5; j--) {
            matriz[11][j] = 1;
        }

        for (int i = 11; i < 16; i++) {
            matriz[i][6] = 1;
        }

        for (int j = 6; j < 26; j++) {
            matriz[15][j] = 1;
        }

        for (int i = 15; i > 7; i--) {
            matriz[i][25] = 1;
        }

        for (int j = 25; j < 30; j++) {
            matriz[8][j] = 1;
        }
        
        for (int i = 8; i < 17; i++) {
            matriz[i][29] = 1;
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }
}