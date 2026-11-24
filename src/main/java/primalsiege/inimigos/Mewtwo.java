package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Mewtwo extends Inimigo {
    public Mewtwo(Mapa mapa){
        super(2000, 100, 30, "Mewtwo", mapa);
        definirRecompensa(39);
    }
}
