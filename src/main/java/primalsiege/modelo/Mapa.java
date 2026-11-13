package primalsiege.modelo;
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
        criarCaminho();
    }
    
    public int getMatriz(int i, int j) {
        return matriz[i][j];
    }

    public void setMatriz(int i, int j, int valor) {
        matriz[i][j] = valor;
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

        caminho.add(3);
        caminho.add(163);
        caminho.add(177);
        caminho.add(369);
        caminho.add(358);
        caminho.add(486);
        caminho.add(505);
        caminho.add(281);
        caminho.add(285);
        caminho.add(573);

        matriz[17][29] = 3;
    }

    public int[][] getMatriz() {
        return matriz;
    }
}