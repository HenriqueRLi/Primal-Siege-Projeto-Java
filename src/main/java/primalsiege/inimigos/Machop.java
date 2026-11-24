package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Machop extends Inimigo {
    public Machop(Mapa mapa){
        super(240,150,8,"Machop",mapa);
        definirRecompensa(14);
    }
}
