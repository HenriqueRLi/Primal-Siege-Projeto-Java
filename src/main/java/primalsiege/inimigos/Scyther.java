
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Scyther extends Inimigo {

    public Scyther(Mapa mapa) {
        super(38, 200, 3, "Scyther", mapa);
        definirRecompensa(8);
    }
}
