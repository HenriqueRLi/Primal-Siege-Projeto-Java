package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Kadabra extends Inimigo {
    public Kadabra(Mapa mapa){
        super(120,190,1,"Kadabra",mapa);
        definirRecompensa(11);
    }
}
