
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Ninjask extends Inimigo {

    public Ninjask(Mapa mapa) {
        super(130, 240, 5, "Ninjask", mapa);
        definirRecompensa(11);
    }
}
