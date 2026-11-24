
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Caterpie extends Inimigo {

    public Caterpie(Mapa mapa) {
        super(40, 210, 1, "Caterpie", mapa);
        definirRecompensa(7);
    }
}
