
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Steelix extends Inimigo {

    public Steelix(Mapa mapa) {
        super(1400, 75, 25, "Steelix", mapa);
        definirRecompensa(33);
    }
}
