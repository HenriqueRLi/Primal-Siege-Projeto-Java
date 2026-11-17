package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Rattata extends Inimigo{
    public Rattata(Mapa mapa){
        super(38,200,3,"Rattata",mapa);
        definirRecompensa(8);
    }
}
