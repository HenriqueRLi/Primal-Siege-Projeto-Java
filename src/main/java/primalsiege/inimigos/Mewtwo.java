package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Mewtwo extends Inimigo {
    public Mewtwo(Mapa mapa){
        super(450, 100, 5, "Mewtwo", mapa);
        definirRecompensa(45);
    }
}
