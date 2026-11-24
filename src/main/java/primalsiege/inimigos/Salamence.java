
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Salamence extends Inimigo {

    public Salamence(Mapa mapa) {
        super(800, 130, 3, "Salamence", mapa);
        definirRecompensa(21);
    }
}
