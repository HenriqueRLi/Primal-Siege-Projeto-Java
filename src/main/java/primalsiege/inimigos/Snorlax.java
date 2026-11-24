
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Snorlax extends Inimigo {

    public Snorlax(Mapa mapa) {
        super(1200, 80, 22, "Snorlax", mapa);
        definirRecompensa(32);
    }
}
