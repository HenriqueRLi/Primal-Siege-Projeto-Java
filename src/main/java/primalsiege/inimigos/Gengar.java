package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Gengar extends Inimigo {
    public Gengar(Mapa mapa){
        super(55,240,1,"Gengar",mapa);
        definirRecompensa(12);
    }
}
