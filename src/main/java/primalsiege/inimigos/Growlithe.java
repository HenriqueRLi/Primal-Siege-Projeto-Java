
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Growlithe extends Inimigo {

    public Growlithe(Mapa mapa) {
        super(38, 200, 3, "Growlithe", mapa);
        definirRecompensa(8);
    }
}
