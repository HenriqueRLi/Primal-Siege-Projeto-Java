package primalsiege.gerenciadores;
import java.util.ArrayList;

import primalsiege.modelo.Onda;

public class GerenciadorOndas {
    private ArrayList<Onda> ondas = new ArrayList<>();
    private int ondaAtual;
    private boolean statusOndas;
    private double tempoSpawn;
    private int inimigosGerados;

    public GerenciadorOndas(){
        statusOndas = false;
        tempoSpawn = 0;
        inimigosGerados = 0;
        ondas.add(new Onda("Rattata",5,1));
        ondas.add(new Onda("Pidgey",4,0.5));
    }

    public Onda getOndaAtual(){
        return ondas.get(ondaAtual);
    }

    public boolean avançarOnda(){
        if(ondaAtual == ondas.size() - 1){
            return true;
        }
        ondaAtual++;
        return false;
    }

    public boolean ondaEmAndamento() {
        return statusOndas;
    }

    public void registrarInicioOnda() {
        inimigosGerados = 1;
        tempoSpawn = 0;
        statusOndas = true;
    }

    public boolean verificarSpawn(double tpf) {
        if (statusOndas == true &&
            inimigosGerados < getOndaAtual().getQuantidade()) {

            tempoSpawn += tpf;

            if (tempoSpawn >= getOndaAtual().getIntervaloSpawn()) {
                return true;
            }
        }

        return false;
    }

    public void registrarInimigoGerado() {
        inimigosGerados++;
        tempoSpawn = 0;
    }

    public boolean verificarFimOnda(boolean inimigosVazios) {
        return statusOndas == true &&
            inimigosGerados == getOndaAtual().getQuantidade() &&
            inimigosVazios;
    }

    public void registrarFimOnda() {
        statusOndas = false;
    }

}
