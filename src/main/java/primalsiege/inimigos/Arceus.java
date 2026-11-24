package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Arceus extends Inimigo {
    public Arceus(Mapa mapa){
        super(9000, 65, 50, "Arceus", mapa);
        definirRecompensa(64);
    }
}
