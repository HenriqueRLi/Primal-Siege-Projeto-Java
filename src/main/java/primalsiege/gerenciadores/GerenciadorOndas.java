package primalsiege.gerenciadores;
import java.util.ArrayList;

import primalsiege.modelo.Onda;

public class GerenciadorOndas {
    private ArrayList<Onda> ondas = new ArrayList<>();
    private int ondaAtual;
    private boolean statusOndas;
    private double tempoSpawn;
    private int inimigosGerados;

    public GerenciadorOndas() {
        statusOndas = false;
        tempoSpawn = 0;
        inimigosGerados = 0;

        ondas.add(new Onda("Wishiwashi", 25, 0.65));
        ondas.add(new Onda("Pidgey", 32, 0.55));
        ondas.add(new Onda("Rattata", 40, 0.45));
        ondas.add(new Onda("Machop", 35, 0.50));
        ondas.add(new Onda("Kadabra", 45, 0.40));
        ondas.add(new Onda("Gengar", 50, 0.35));
        ondas.add(new Onda("Gyarados", 32, 0.45));
        ondas.add(new Onda("Dragonite", 28, 0.50));
        ondas.add(new Onda("Mewtwo", 18, 0.65));
        ondas.add(new Onda("Eternatus", 1, 1.0));
    }

    public int getNumeroOnda() {
        return ondaAtual + 1;
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
