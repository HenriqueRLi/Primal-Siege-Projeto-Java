
package primalsiege.torres;

import javafx.scene.paint.Color;
import primalsiege.modelo.Torre;

public class TorreSquirtle extends Torre {

    public TorreSquirtle(double x, double y) {
        super(14, 150, x, y, 1.3f, 50, Color.BLUE, 500, 4);
    }

    @Override
    public boolean evoluir() {
        if (super.evoluir()) {

            if (getNivel() == 2) {
                definirAtributosAtaque(24, 1.5f);
                definirAtributosProjetil(Color.DODGERBLUE, 650, 6);
            }
            else if (getNivel() == 3) {
                definirAtributosAtaque(10, 6);
                definirAtributosProjetil(Color.DEEPSKYBLUE, 850, 5);
            }

            return true;
        }

        return false;
    }
}
