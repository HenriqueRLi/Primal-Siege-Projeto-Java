
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Salamence extends Inimigo {

    public Salamence(Mapa mapa) {
        super(38, 200, 3, "Salamence", mapa);
        definirRecompensa(8);
    }
}
