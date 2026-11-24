
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Sneasel extends Inimigo {

    public Sneasel(Mapa mapa) {
        super(180, 200, 2, "Sneasel", mapa);
        definirRecompensa(12);
    }
}
