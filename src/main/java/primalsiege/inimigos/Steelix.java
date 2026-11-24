
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Steelix extends Inimigo {

    public Steelix(Mapa mapa) {
        super(1400, 75, 3, "Steelix", mapa);
        definirRecompensa(25);
    }
}
