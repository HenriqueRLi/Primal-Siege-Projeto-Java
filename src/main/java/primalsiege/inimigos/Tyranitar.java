
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Tyranitar extends Inimigo {

    public Tyranitar(Mapa mapa) {
        super(38, 200, 3, "Tyranitar", mapa);
        definirRecompensa(8);
    }
}
