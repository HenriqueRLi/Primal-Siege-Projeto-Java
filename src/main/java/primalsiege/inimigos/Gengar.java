package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Gengar extends Inimigo {
    public Gengar(Mapa mapa){
        super(330,170,2,"Gengar",mapa);
        definirRecompensa(15);
    }
}
