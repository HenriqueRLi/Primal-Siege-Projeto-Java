
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Sneasel extends Inimigo {

    public Sneasel(Mapa mapa) {
        super(180, 200, 6, "Sneasel", mapa);
        definirRecompensa(12);
    }
}
