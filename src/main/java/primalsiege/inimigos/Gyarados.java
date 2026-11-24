package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Gyarados extends Inimigo {
    public Gyarados(Mapa mapa){
        super(700, 110, 3, "Gyarados", mapa);
        definirRecompensa(20);
    }
}
