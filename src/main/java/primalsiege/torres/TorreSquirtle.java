
package primalsiege.torres;

import javafx.scene.paint.Color;
import primalsiege.modelo.Torre;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Projetil;

public class TorreSquirtle extends Torre {
    private double recargaHidroBomba;

    public TorreSquirtle(double x, double y) {
        super(15, 150, x, y, 1.3f, 50, Color.BLUE, 500, 4);
        recargaHidroBomba = 0;
    }

    @Override
    public boolean evoluir() {
        if (super.evoluir()) {

            if (getNivel() == 2) {
                definirAtributosAtaque(23, 1.5f);
                definirAtributosProjetil(Color.DODGERBLUE, 650, 6);
            }
            else if (getNivel() == 3) {
                definirAtributosAtaque(11, 6);
                definirAtributosProjetil(Color.DEEPSKYBLUE, 850, 5);
            }

            return true;
        }

        return false;
    }

    @Override
    public void atualizarRecarga(double tpf) {
        super.atualizarRecarga(tpf);

        if (recargaHidroBomba > 0) {
            recargaHidroBomba -= tpf;

            if (recargaHidroBomba < 0) {
                recargaHidroBomba = 0;
            }
        }
    }

    public int usarHidroBomba() {
        if (getNivel() < 2 || recargaHidroBomba > 0) {
            return 0;
        }

        recargaHidroBomba = 8.0;

        if (getNivel() == 2) {
            return 48;
        }

        return 110;
    }

    @Override
    public Projetil atirar(Inimigo alvo) {
        Projetil tiro = super.atirar(alvo);

        if (tiro != null) {
            int danoExtra = usarHidroBomba();

            if (danoExtra > 0) {
                System.out.println("Hidro Bomba ativada: +" + danoExtra);
                tiro.definirDanoExtraHidroBomba(danoExtra);
            }
        }

        return tiro;
    }
}
