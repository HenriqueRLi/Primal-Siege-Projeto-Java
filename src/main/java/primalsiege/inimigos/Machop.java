package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Machop extends Inimigo {
    public Machop(Mapa mapa){
        super(72,100,1,"Machop",mapa);
        definirRecompensa(10);
    }
}
