
package primalsiege.torres;

import javafx.scene.paint.Color;
import primalsiege.modelo.Torre;

public class TorreBulbassauro extends Torre {

    public TorreBulbassauro(double x, double y) {
        super(12, 150, x, y, 1.25f, 50, Color.GREEN, 1000, 2);
    }

    @Override
    public boolean evoluir() {
        if (super.evoluir()) {

            if (getNivel() == 2) {
                definirAtributosAtaque(18, 1.5f);
                definirAtributosProjetil(Color.LIMEGREEN, 1050, 4);
            }
            else if (getNivel() == 3) {
                definirAtributosAtaque(26, 1.6f);
                definirAtributosProjetil(Color.LAWNGREEN, 1100, 7);
            }

            return true;
        }

        return false;
    }
}
