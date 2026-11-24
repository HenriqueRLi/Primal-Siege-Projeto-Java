package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Dragonite extends Inimigo {
    public Dragonite(Mapa mapa){
        super(850, 120, 20, "Dragonite", mapa);
        definirRecompensa(28);
    }
}
