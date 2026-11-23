package primalsiege.gerenciadores;

import java.util.ArrayList;
import java.util.Collections;

import primalsiege.modelo.Onda;

public class GerenciadorOndas {
    private final ArrayList<Onda> ondas = new ArrayList<>();
    private int ondaAtual;
    private boolean statusOndas;
    private double tempoSpawn;
    private int inimigosGerados;

    public GerenciadorOndas() {

        statusOndas = false;
        tempoSpawn = 0;
        inimigosGerados = 0;

        String[] muitoFracos = {"Wishiwashi", "Pidgey", "Rattata"};

        String[] fracos = {"Machop", "Kadabra"};

        String[] medios = {"Gengar"};

        String[] fortes = {"Gyarados", "Dragonite"};

        String[] chefes = {"Mewtwo", "Eternatus"};

        ondas.add(criarOndaMista(25, 0.65, 2, muitoFracos));
        ondas.add(criarOndaMista(32, 0.55, 2, muitoFracos));
        ondas.add(criarOndaMista(40, 0.45, 2, muitoFracos));

        ondas.add(criarOndaMista(35, 0.50, 2, muitoFracos, fracos));
        ondas.add(criarOndaMista(45, 0.40, 2, fracos));
        ondas.add(criarOndaMista(50, 0.35, 2, fracos, medios));

        ondas.add(criarOndaMista(32, 0.45, 2, medios, fortes));
        ondas.add(criarOndaMista(28, 0.50, 2, fortes));
        ondas.add(criarOndaMista(18, 0.65, 2, fortes, new String[]{"Mewtwo"}));

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


    private Onda criarOndaMista(int quantidade, double intervaloSpawn, int qtdEspecies, String[]... grupos) {

        ArrayList<String> disponiveis = new ArrayList<>();
        ArrayList<String> escolhidas = new ArrayList<>();

        for (String[] grupo : grupos) {

            ArrayList<String> lista = new ArrayList<>();
            Collections.addAll(lista, grupo);
            Collections.shuffle(lista);

            for (String especie : lista) {
                if (!disponiveis.contains(especie)) {
                    disponiveis.add(especie);
                }
            }

            for (String especie : lista) {
                if (!escolhidas.contains(especie)) {
                    escolhidas.add(especie);
                    break;
                }
            }
        }

        if (qtdEspecies < 1 || qtdEspecies < grupos.length || qtdEspecies > quantidade || qtdEspecies > disponiveis.size() || escolhidas.size() < grupos.length) {

            throw new IllegalArgumentException("Quantidade de espécies inválida");
        }

        Collections.shuffle(disponiveis);

        for (String especie : disponiveis) {

            if (escolhidas.size() >= qtdEspecies) {
                break;
            }

            if (!escolhidas.contains(especie)) {
                escolhidas.add(especie);
            }
        }

        Collections.shuffle(escolhidas);

        ArrayList<String> composicao = new ArrayList<>();

        int quantidadePorGrupo = quantidade / qtdEspecies;
        int restante = quantidade % qtdEspecies;

        for (int i = 0; i < escolhidas.size(); i++) {

            int total = quantidadePorGrupo;

            if (i < restante) {
                total++;
            }

            for (int j = 0; j < total; j++) {
                composicao.add(escolhidas.get(i));
            }
        }

        return new Onda(composicao, intervaloSpawn);
    }


    public int getInimigosGerados() {
        return inimigosGerados;
    }

}
