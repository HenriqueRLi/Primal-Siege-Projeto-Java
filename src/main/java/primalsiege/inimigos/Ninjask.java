
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Ninjask extends Inimigo {

    public Ninjask(Mapa mapa) {
        super(38, 200, 3, "Ninjask", mapa);
        definirRecompensa(8);
    }
}
