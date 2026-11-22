
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Shinx extends Inimigo {

    public Shinx(Mapa mapa) {
        super(38, 200, 3, "Shinx", mapa);
        definirRecompensa(8);
    }
}
