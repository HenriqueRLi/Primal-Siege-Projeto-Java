
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Sandshrew extends Inimigo {

    public Sandshrew(Mapa mapa) {
        super(260, 130, 2, "Sandshrew", mapa);
        definirRecompensa(14);
    }
}
