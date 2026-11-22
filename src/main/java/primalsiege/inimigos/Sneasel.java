
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Sneasel extends Inimigo {

    public Sneasel(Mapa mapa) {
        super(38, 200, 3, "Sneasel", mapa);
        definirRecompensa(8);
    }
}
