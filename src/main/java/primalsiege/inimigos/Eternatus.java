package primalsiege.inimigos;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class Eternatus extends Inimigo {
    public Eternatus(Mapa mapa){
        super(2300, 80, 4, "Eternatus", mapa);
        definirRecompensa(30);
    }
}
