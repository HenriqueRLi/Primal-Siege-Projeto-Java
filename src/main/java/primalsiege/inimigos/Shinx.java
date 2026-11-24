
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Shinx extends Inimigo {

    public Shinx(Mapa mapa) {
        super(80, 200, 1, "Shinx", mapa);
        definirRecompensa(9);
    }
}
