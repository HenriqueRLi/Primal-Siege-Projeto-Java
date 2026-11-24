
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Growlithe extends Inimigo {

    public Growlithe(Mapa mapa) {
        super(200, 180, 6, "Growlithe", mapa);
        definirRecompensa(13);
    }
}
