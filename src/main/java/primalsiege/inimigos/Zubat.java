
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Zubat extends Inimigo {

    public Zubat(Mapa mapa) {
        super(38, 200, 3, "Zubat", mapa);
        definirRecompensa(8);
    }
}
