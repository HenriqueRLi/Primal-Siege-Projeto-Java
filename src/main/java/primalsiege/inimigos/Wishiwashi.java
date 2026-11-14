package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Wishiwashi extends Inimigo {

    public Wishiwashi(Mapa mapa) {
        super(20, 120, 1, "Wishiwashi", mapa);
        definirRecompensa(6);
    }
}