package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Lucario extends Inimigo {
    public Lucario(Mapa mapa){
        super(420, 150, 2, "Lucario", mapa);
        definirRecompensa(17);
    }
}
