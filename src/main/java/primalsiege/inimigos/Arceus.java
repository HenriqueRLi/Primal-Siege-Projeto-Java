package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Arceus extends Inimigo {
    public Arceus(Mapa mapa){
        super(5000, 65, 20, "Arceus", mapa);
        definirRecompensa(0);
    }
}
