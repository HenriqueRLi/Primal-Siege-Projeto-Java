
package primalsiege.torres;

import javafx.scene.paint.Color;
import primalsiege.modelo.Torre;

public class TorrePikachu extends Torre {

    public TorrePikachu(double x, double y) {
        super(8, 150, x, y, 2, 50, Color.YELLOW, 800, 20);
    }

    @Override
    public boolean evoluir() {
        if (super.evoluir()) {

            if (getNivel() == 2) {
                definirAtributosAtaque(12, 2.5f);
                definirAtributosProjetil(Color.GOLD, 850, 22);
            }
            else if (getNivel() == 3) {
                definirAtributosAtaque(18, 3);
                definirAtributosProjetil(Color.YELLOW, 900, 25);
            }

            return true;
        }

        return false;
    }
}
