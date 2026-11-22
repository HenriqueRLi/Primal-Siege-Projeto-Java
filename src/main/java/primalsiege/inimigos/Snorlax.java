
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Snorlax extends Inimigo {

    public Snorlax(Mapa mapa) {
        super(38, 200, 3, "Snorlax", mapa);
        definirRecompensa(8);
    }
}
