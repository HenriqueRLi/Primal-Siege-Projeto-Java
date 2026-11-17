package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Kadabra extends Inimigo {
    public Kadabra(Mapa mapa){
        super(60,220,1,"Kadabra",mapa);
        definirRecompensa(11);
    }
}
