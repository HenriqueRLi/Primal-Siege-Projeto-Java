
package primalsiege.gerenciadores;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.shape.Polygon;
import primalsiege.interfacegrafica.RenderizadorTorres;
import primalsiege.modelo.Mapa;
import primalsiege.modelo.Torre;
import primalsiege.torres.TorreBulbassauro;
import primalsiege.torres.TorreCharmander;
import primalsiege.torres.TorrePikachu;
import primalsiege.torres.TorreSquirtle;

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

    public int getCustoEvolucao(Torre torre) {
        int nivel = torre.getNivel();

        if (nivel >= 3) {
            return -1;
        }

        if (torre instanceof TorreCharmander) {
            if (nivel == 1) {
                return 120;
            }
            else {
                return 160;
            }
        }
        else if (torre instanceof TorreSquirtle) {
            if (nivel == 1) {
                return 140;
            }
            else {
                return 210;
            }
        }
        else if (torre instanceof TorrePikachu) {
            if (nivel == 1) {
                return 140;
            }
            else {
                return 190;
            }
        }
        else if (torre instanceof TorreBulbassauro) {
            if (nivel == 1) {
                return 115;
            }
            else {
                return 165;
            }
        }
        return -1;
    }

    public boolean venderTorre(Torre torre, ArrayList<Torre> torres,Mapa mapa, RenderizadorTorres renderizadorTorres) {
    int indice = torres.indexOf(torre);
    if (indice == -1) {
        return false;
    }

    int linha = (int) (torre.getY() / 40);
    int coluna = (int) (torre.getX() / 40);

    renderizadorTorres.removerSprite(indice);
    torres.remove(indice);
    mapa.setMatriz(linha, coluna, 0);
    return true;
}


}
