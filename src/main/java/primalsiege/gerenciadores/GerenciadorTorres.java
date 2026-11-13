
package primalsiege.gerenciadores;

import java.util.ArrayList;
import javafx.scene.shape.Polygon;
import com.almasb.fxgl.dsl.FXGL;

import primalsiege.modelo.Torre;
import primalsiege.torres.TorreCharmander;
import primalsiege.torres.TorreSquirtle;
import primalsiege.torres.TorrePikachu;
import primalsiege.torres.TorreBulbassauro;
import primalsiege.modelo.Mapa;

public class GerenciadorTorres {
    public Torre criarTorre(int selecionada) {
        Torre torre = null;

        switch (selecionada) {
            case 1:
                torre = new TorreCharmander(0, 0);
                break;
            case 2:
                torre = new TorreSquirtle(0, 0);
                break;
            case 3:
                torre = new TorrePikachu(0, 0);
                break;
            case 4:
                torre = new TorreBulbassauro(0, 0);
                break;
            default:
                break;
        }

        return torre;
    }

    public boolean construirTorre(Torre torre, Mapa mapa, int linha, int coluna) {
        return mapa.colocarTorre(torre, linha, coluna);
    }

    public void limparTorres(ArrayList<Torre> torres,
                            ArrayList<Polygon> triangulosTorres,
                            Mapa mapa) {

        for (Polygon triangulo : triangulosTorres) {
            FXGL.getGameScene().removeUINode(triangulo);
        }

        triangulosTorres.clear();
        torres.clear();

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 32; j++) {
                if (mapa.getMatriz(i, j) == 2) {
                    mapa.setMatriz(i, j, 0);
                }
            }
        }
    }

    public int getCustoConstrucao(Torre torre) {

        if (torre instanceof TorreCharmander) {
            return 100;
        }
        else if (torre instanceof TorreSquirtle) {
            return 125;
        }
        else if (torre instanceof TorrePikachu) {
            return 115;
        }
        else if (torre instanceof TorreBulbassauro) {
            return 100;
        }

        return -1;
    }
}
