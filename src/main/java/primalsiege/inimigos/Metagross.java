
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Metagross extends Inimigo {

    public Metagross(Mapa mapa) {
        super(38, 200, 3, "Metagross", mapa);
        definirRecompensa(8);
    }
}
