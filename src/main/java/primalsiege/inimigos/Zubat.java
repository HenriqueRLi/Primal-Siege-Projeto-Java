
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Zubat extends Inimigo {

    public Zubat(Mapa mapa) {
        super(50, 220, 1, "Zubat", mapa);
        definirRecompensa(8);
    }
}
