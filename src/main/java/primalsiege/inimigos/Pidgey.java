package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Pidgey extends Inimigo {
    public Pidgey(Mapa mapa){
        super(45,200,1,"Pidgey",mapa);
        definirRecompensa(8);
    }
}
