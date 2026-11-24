
package primalsiege.torres;

import javafx.scene.paint.Color;
import primalsiege.modelo.Torre;

public class TorreBulbassauro extends Torre {

    public TorreBulbassauro(double x, double y) {
        super(25, 150, x, y, 1.5f, 50, Color.GREEN, 1000, 2, 85);
    }

    @Override
    public boolean evoluir() {
        if (super.evoluir()) {

            if (getNivel() == 2) {
                definirAtributosAtaque(36, 1.5f);
                definirAtributosProjetil(Color.LIMEGREEN, 1050, 4);
            }
            else if (getNivel() == 3) {
                definirAtributosAtaque(50, 1.6f);
                definirAtributosProjetil(Color.LAWNGREEN, 1100, 7);
            }

            return true;
        }

        return false;
    }

    @Override
    public double getPercentualLentidao() {
        if (getNivel() == 2) {
            return 0.25;
        }
        else if (getNivel() == 3) {
            return 0.30;
        }

        return 0;
    }

    @Override
    public boolean usaVisualPlanta() {
        return true;
    }
}
