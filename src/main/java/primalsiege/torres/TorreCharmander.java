package primalsiege.torres;
import javafx.scene.paint.Color;
import primalsiege.modelo.Torre;

public class TorreCharmander extends Torre{

    public TorreCharmander(double x, double y) {
        super(8, 150, x, y, 2, 50, Color.ORANGE, 650, 3);    }
    
    @Override
    public double getTaxaCrescimento() {
        if (getNivel() == 3) {
            return 100;
        }
        return 0;
    }

    @Override
    public boolean evoluir() {  
        if (super.evoluir()) {

            if (getNivel() == 2) {
                definirAtributosAtaque(16, 2);
                definirAtributosProjetil(Color.ORANGERED, 650, 6);
            }
            else if (getNivel() == 3) {
                definirAtributosAtaque(4, 14);
                definirAtributosProjetil(Color.RED, 800, 2);
            }

            return true;
        }
        return false;
    }

    @Override
    public int getDanoQueimadura() {
        if (getNivel() == 2) {
            return 4;
        }
        else if (getNivel() == 3) {
            return 5;
        }

        return 0;
    }

    @Override
    public boolean usaVisualFogo() {
        return true;
    }
}
