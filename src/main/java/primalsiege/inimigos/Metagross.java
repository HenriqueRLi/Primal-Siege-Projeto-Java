
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Metagross extends Inimigo {

    public Metagross(Mapa mapa) {
        super(800, 100, 15, "Metagross", mapa);
        definirRecompensa(28);
    }
}
