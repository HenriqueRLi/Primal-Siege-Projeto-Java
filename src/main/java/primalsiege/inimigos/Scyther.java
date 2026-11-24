
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Scyther extends Inimigo {

    public Scyther(Mapa mapa) {
        super(340, 200, 2, "Scyther", mapa);
        definirRecompensa(15);
    }
}
