
package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Sandshrew extends Inimigo {

    public Sandshrew(Mapa mapa) {
        super(38, 200, 3, "Sandshrew", mapa);
        definirRecompensa(8);
    }
}
