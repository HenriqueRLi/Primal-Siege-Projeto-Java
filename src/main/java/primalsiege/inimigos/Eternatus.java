package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Eternatus extends Inimigo {
    public Eternatus(Mapa mapa){
        super(5000, 65, 20, "Eternatus", mapa);
        definirRecompensa(0);
    }
}
