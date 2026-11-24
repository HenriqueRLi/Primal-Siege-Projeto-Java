package primalsiege.gerenciadores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

        // Onda 1
        adicionarOnda(1.200, new String[]{"Caterpie"}, new int[]{5}, null);
        // Onda 2
        adicionarOnda(1.186, new String[]{"Caterpie", "Pidgey"}, new int[]{2, 3}, null);
        // Onda 3
        adicionarOnda(1.172, new String[]{"Caterpie", "Pidgey", "Rattata"}, new int[]{2, 2, 2}, null);
        // Onda 4
        adicionarOnda(1.158, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata"}, new int[]{2, 1, 2, 2}, null);
        // Onda 5 - Chefe Caterpie
        adicionarOnda(1.145, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata"}, new int[]{2, 2, 2, 2}, "Caterpie");
        
        // Onda 6 - Pós-chefe
        adicionarOnda(1.358, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx"}, new int[]{2, 1, 1, 1, 2}, null);
        // Onda 7
        adicionarOnda(1.118, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Wishiwashi"}, new int[]{2, 1, 1, 1, 2, 2}, null);
        // Onda 8
        adicionarOnda(1.105, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Wishiwashi", "Machop"}, new int[]{2, 1, 1, 1, 2, 2, 1}, null);
        // Onda 9
        adicionarOnda(1.092, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Wishiwashi", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 2, 2, 1, 1}, null);
        // Onda 10 - Chefe Machop
        adicionarOnda(1.080, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Wishiwashi", "Machop", "Sandshrew"}, new int[]{2, 1, 2, 1, 2, 2, 1, 1}, "Machop");
        
        // Onda 11 - Pós-chefe
        adicionarOnda(1.250, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Kadabra", "Wishiwashi", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 2, 2, 1, 1, 1}, null);
        // Onda 12
        adicionarOnda(1.055, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Kadabra", "Wishiwashi", "Growlithe", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 2, 2, 2, 2, 1, 1}, null);
        // Onda 13
        adicionarOnda(1.042, new String[]{"Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 2, 2, 2, 1, 2, 1, 1}, null);
        // Onda 14
        adicionarOnda(1.030, new String[]{"Ninjask", "Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1}, null);
        // Onda 15 - Chefe Growlithe
        adicionarOnda(1.018, new String[]{"Ninjask", "Zubat", "Caterpie", "Pidgey", "Rattata", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 1, 2, 2, 2, 1, 2, 1, 1}, "Growlithe");
        
        // Onda 16 - Pós-chefe
        adicionarOnda(1.154, new String[]{"Ninjask", "Zubat", "Pidgey", "Rattata", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Gengar", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1}, null);
        // Onda 17
        adicionarOnda(0.986, new String[]{"Ninjask", "Zubat", "Pidgey", "Rattata", "Scyther", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Gengar", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 2, 2}, null);
        // Onda 18
        adicionarOnda(0.970, new String[]{"Ninjask", "Zubat", "Pidgey", "Rattata", "Scyther", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Gengar", "Lucario", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2}, null);
        // Onda 19
        adicionarOnda(0.953, new String[]{"Ninjask", "Zubat", "Pidgey", "Rattata", "Scyther", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Gengar", "Lucario", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2}, null);
        // Onda 20 - Chefe Gengar
        adicionarOnda(0.936, new String[]{"Ninjask", "Zubat", "Pidgey", "Rattata", "Scyther", "Shinx", "Sneasel", "Kadabra", "Wishiwashi", "Growlithe", "Gengar", "Lucario", "Machop", "Sandshrew"}, new int[]{2, 1, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2}, "Gengar");
        
        // Onda 21 - Pós-chefe
        adicionarOnda(1.060, new String[]{"Scyther", "Gengar", "Machop", "Lucario", "Sandshrew", "Gyarados"}, new int[]{3, 4, 4, 3, 3, 2}, null);
        // Onda 22
        adicionarOnda(0.907, new String[]{"Scyther", "Gengar", "Machop", "Lucario", "Sandshrew", "Gyarados", "Metagross"}, new int[]{6, 3, 5, 4, 2, 2, 1}, null);
        // Onda 23
        adicionarOnda(0.893, new String[]{"Scyther", "Gengar", "Machop", "Lucario", "Sandshrew", "Dragonite", "Gyarados", "Metagross"}, new int[]{3, 4, 4, 3, 2, 2, 3, 2}, null);
        // Onda 24
        adicionarOnda(0.878, new String[]{"Scyther", "Gengar", "Lucario", "Machop", "Sandshrew", "Dragonite", "Gyarados", "Metagross"}, new int[]{3, 3, 3, 2, 4, 3, 2, 3}, null);
        // Onda 25 - Chefe Lucario
        adicionarOnda(0.864, new String[]{"Scyther", "Gengar", "Machop", "Lucario", "Sandshrew", "Dragonite", "Gyarados", "Metagross"}, new int[]{2, 4, 4, 2, 3, 3, 3, 2}, "Lucario");
        
        // Onda 26 - Pós-chefe
        adicionarOnda(0.977, new String[]{"Scyther", "Gengar", "Lucario", "Dragonite", "Gyarados", "Metagross", "Snorlax"}, new int[]{5, 3, 2, 1, 4, 2, 3}, null);
        // Onda 27
        adicionarOnda(0.835, new String[]{"Scyther", "Gengar", "Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Snorlax"}, new int[]{4, 3, 4, 3, 2, 1, 2, 4}, null);
        // Onda 28
        adicionarOnda(0.821, new String[]{"Scyther", "Gengar", "Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Snorlax"}, new int[]{4, 4, 3, 1, 4, 2, 2, 3}, null);
        // Onda 29
        adicionarOnda(0.806, new String[]{"Scyther", "Gengar", "Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Snorlax"}, new int[]{5, 4, 2, 3, 3, 3, 3, 1}, null);
        // Onda 30 - Chefe Dragonite
        adicionarOnda(0.792, new String[]{"Scyther", "Gengar", "Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Snorlax", "Steelix"}, new int[]{3, 2, 5, 4, 2, 3, 1, 3, 1}, "Dragonite");
        
        // Onda 31 - Pós-chefe
        adicionarOnda(0.894, new String[]{"Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Tyranitar", "Snorlax", "Steelix"}, new int[]{4, 2, 2, 3, 2, 1, 2, 4}, null);
        // Onda 32
        adicionarOnda(0.763, new String[]{"Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Tyranitar", "Snorlax", "Steelix"}, new int[]{2, 1, 3, 3, 4, 3, 3, 4}, null);
        // Onda 33
        adicionarOnda(0.749, new String[]{"Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Tyranitar", "Snorlax", "Steelix"}, new int[]{5, 3, 2, 1, 2, 2, 4, 4}, null);
        // Onda 34
        adicionarOnda(0.734, new String[]{"Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{3, 2, 3, 3, 3, 2, 1, 1, 4}, null);
        // Onda 35 - Chefe Gyarados
        adicionarOnda(0.720, new String[]{"Lucario", "Salamence", "Dragonite", "Gyarados", "Metagross", "Tyranitar", "Eternatus", "Steelix"}, new int[]{4, 4, 3, 3, 2, 4, 1, 1}, "Gyarados");
        
        // Onda 36 - Pós-chefe
        adicionarOnda(0.814, new String[]{"Salamence", "Dragonite", "Gyarados", "Metagross", "Mewtwo", "Tyranitar", "Snorlax", "Eternatus", "Steelix"}, new int[]{1, 2, 2, 4, 2, 4, 2, 1, 2}, null);
        // Onda 37
        adicionarOnda(0.696, new String[]{"Salamence", "Dragonite", "Gyarados", "Mewtwo", "Metagross", "Eternatus", "Snorlax", "Steelix"}, new int[]{5, 2, 2, 3, 4, 5, 1, 2}, null);
        // Onda 38
        adicionarOnda(0.684, new String[]{"Salamence", "Dragonite", "Gyarados", "Metagross", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{3, 5, 3, 2, 1, 3, 2, 1, 4}, null);
        // Onda 39
        adicionarOnda(0.672, new String[]{"Salamence", "Dragonite", "Gyarados", "Metagross", "Mewtwo", "Tyranitar", "Snorlax", "Eternatus", "Steelix"}, new int[]{2, 2, 4, 2, 3, 5, 3, 1, 3}, null);
        // Onda 40 - Chefe Steelix
        adicionarOnda(0.660, new String[]{"Salamence", "Dragonite", "Gyarados", "Mewtwo", "Metagross", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{5, 4, 4, 4, 2, 2, 1, 1, 3}, "Steelix");
        
        // Onda 41 - Pós-chefe
        adicionarOnda(0.745, new String[]{"Salamence", "Dragonite", "Mewtwo", "Metagross", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{6, 4, 1, 2, 6, 1, 1, 1}, null);
        // Onda 42
        adicionarOnda(0.636, new String[]{"Salamence", "Dragonite", "Mewtwo", "Metagross", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{4, 3, 4, 3, 3, 3, 3, 4}, null);
        // Onda 43
        adicionarOnda(0.624, new String[]{"Salamence", "Dragonite", "Metagross", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{5, 3, 6, 3, 4, 2, 3, 2}, null);
        // Onda 44
        adicionarOnda(0.612, new String[]{"Salamence", "Dragonite", "Mewtwo", "Metagross", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{2, 4, 4, 5, 4, 3, 3, 3}, null);
        // Onda 45 - Chefe Tyranitar
        adicionarOnda(0.600, new String[]{"Salamence", "Dragonite", "Metagross", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{4, 3, 4, 3, 6, 5, 3, 1}, "Tyranitar");
        
        // Onda 46 - Pós-chefe
        adicionarOnda(0.679, new String[]{"Salamence", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{5, 3, 6, 3, 4, 4}, null);
        // Onda 47
        adicionarOnda(0.581, new String[]{"Salamence", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{3, 4, 5, 5, 6, 7}, null);
        // Onda 48
        adicionarOnda(0.571, new String[]{"Salamence", "Mewtwo", "Tyranitar", "Snorlax", "Eternatus", "Steelix"}, new int[]{6, 5, 6, 8, 2, 4}, null);
        // Onda 49
        adicionarOnda(0.562, new String[]{"Salamence", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{8, 6, 4, 5, 3, 6}, null);
        // Onda 50 - Chefe Mewtwo
        adicionarOnda(0.552, new String[]{"Salamence", "Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{3, 5, 5, 2, 7, 11}, "Mewtwo");
        
        // Onda 51 - Pós-chefe
        adicionarOnda(0.624, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{6, 7, 5, 6, 4}, null);
        // Onda 52
        adicionarOnda(0.533, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{7, 8, 6, 8, 5}, null);
        // Onda 53
        adicionarOnda(0.523, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{6, 8, 7, 7, 7}, null);
        // Onda 54
        adicionarOnda(0.514, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Snorlax", "Steelix"}, new int[]{6, 9, 5, 8, 8}, null);
        // Onda 55 - Chefe Eternatus
        adicionarOnda(0.504, new String[]{"Mewtwo", "Tyranitar", "Snorlax", "Eternatus", "Steelix"}, new int[]{5, 8, 7, 7, 9}, "Eternatus");
        
        // Onda 56 - Pós-chefe
        adicionarOnda(0.569, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Steelix"}, new int[]{7, 10, 7, 8}, null);
        // Onda 57
        adicionarOnda(0.485, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Steelix"}, new int[]{11, 10, 9, 8}, null);
        // Onda 58
        adicionarOnda(0.475, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Steelix"}, new int[]{10, 9, 9, 11}, null);
        // Onda 59
        adicionarOnda(0.466, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Steelix"}, new int[]{9, 10, 11, 10}, null);
        // Onda 60 - Chefe Arceus
        adicionarOnda(0.456, new String[]{"Mewtwo", "Tyranitar", "Eternatus", "Steelix"}, new int[]{9, 12, 8, 11}, "Arceus");
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

    public int getInimigosGerados() {
        return inimigosGerados;
    }

    private void adicionarOnda(double intervalo, String[] nomes, int[] quantidades, String chefe) {
        ArrayList<String> especies = new ArrayList<>();

        for (int i = 0; i < nomes.length; i++) {
            for (int j = 0; j < quantidades[i]; j++) {
                especies.add(nomes[i]);
            }
        }

        if (chefe != null) {
            especies.add(chefe);
        }

        Onda onda = new Onda(especies, intervalo);

        if (chefe != null) {
            onda.definirChefe(especies.size() - 1);
        }

        ondas.add(onda);
    }

}
