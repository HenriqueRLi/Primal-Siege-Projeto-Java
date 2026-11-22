
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Steelix extends Inimigo {

    public Steelix(Mapa mapa) {
        super(38, 200, 3, "Steelix", mapa);
        definirRecompensa(8);
    }
}
