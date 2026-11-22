package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Lucario extends Inimigo {
    public Lucario(Mapa mapa){
        super(150, 130, 3, "Lucario", mapa);
        definirRecompensa(18);
    }
}
