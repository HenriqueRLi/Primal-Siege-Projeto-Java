package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Rattata extends Inimigo{
    public Rattata(Mapa mapa){
        super(45,200,1,"Rattata",mapa);
        definirRecompensa(8);
    }
}
