package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Dragonite extends Inimigo {
    public Dragonite(Mapa mapa){
        super(260, 115, 4, "Dragonite", mapa);
        definirRecompensa(24);
    }
}
