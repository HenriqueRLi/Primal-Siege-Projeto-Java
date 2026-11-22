
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Caterpie extends Inimigo {

    public Caterpie(Mapa mapa) {
        super(38, 200, 3, "Caterpie", mapa);
        definirRecompensa(8);
    }
}
