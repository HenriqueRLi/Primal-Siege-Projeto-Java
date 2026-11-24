
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Tyranitar extends Inimigo {

    public Tyranitar(Mapa mapa) {
        super(1300, 85, 25, "Tyranitar", mapa);
        definirRecompensa(33);
    }
}
