package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Pidgey extends Inimigo {
    public Pidgey(Mapa mapa){
        super(28,170,2,"Pidgey",mapa);
        definirRecompensa(7);
    }
}
