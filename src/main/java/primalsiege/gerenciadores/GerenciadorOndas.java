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
        adicionarOnda(1.158, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat"}, new int[]{1, 2, 2, 2}, null);

        // Onda 5 - Chefe Caterpie
        adicionarOnda(1.145, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat"}, new int[]{2, 2, 2, 2}, "Caterpie");
    
        // Onda 6 - Pós-chefe
        adicionarOnda(1.358, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Shinx"}, new int[]{1, 1, 1, 2, 2}, null);

        // Onda 7
        adicionarOnda(1.118, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx"}, new int[]{1, 1, 1, 2, 2, 2}, null);

        // Onda 8
        adicionarOnda(1.105, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Machop"}, new int[]{1, 1, 1, 2, 2, 2, 1}, null);

        // Onda 9
        adicionarOnda(1.092, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 2, 2, 2, 1, 1}, null);

        // Onda 10 - Chefe Machop
        adicionarOnda(1.080, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Machop", "Sandshrew"}, new int[]{1, 2, 1, 2, 2, 2, 1, 1}, "Machop");

        // Onda 11 - Pós-chefe
        adicionarOnda(1.280, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 1, 2, 2, 1, 1}, null);

        // Onda 12
        adicionarOnda(1.055, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Growlithe", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 2, 2, 2, 2, 1, 1}, null);

        // Onda 13
        adicionarOnda(1.042, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Sneasel", "Growlithe", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1}, null);

        // Onda 14
        adicionarOnda(1.030, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1}, null);

        // Onda 15 - Chefe Growlithe
        adicionarOnda(1.018, new String[]{"Caterpie", "Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1}, "Growlithe");

        // Onda 16 - Pós-chefe
        adicionarOnda(1.207, new String[]{"Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew", "Gengar"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1}, null);

        // Onda 17
        adicionarOnda(0.994, new String[]{"Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew", "Gengar", "Scyther"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1}, null);

        // Onda 18
        adicionarOnda(0.983, new String[]{"Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew", "Gengar", "Scyther", "Lucario"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1}, null);

        // Onda 19
        adicionarOnda(0.971, new String[]{"Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew", "Gengar", "Scyther", "Lucario"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1}, null);

        // Onda 20 - Chefe Gengar
        adicionarOnda(0.960, new String[]{"Pidgey", "Rattata", "Zubat", "Wishiwashi", "Shinx", "Kadabra", "Ninjask", "Sneasel", "Growlithe", "Machop", "Sandshrew", "Gengar", "Scyther", "Lucario"}, new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1}, "Gengar");

        // Onda 21 - Pós-chefe
        adicionarOnda(1.138, new String[]{"Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Ninjask", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Wishiwashi", "Zubat"}, new int[]{2, 2, 1, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1}, null);

        // Onda 22
        adicionarOnda(0.938, new String[]{"Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Pidgey", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Wishiwashi", "Zubat"}, new int[]{2, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1}, null);

        // Onda 23
        adicionarOnda(0.927, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Pidgey", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Wishiwashi", "Zubat"}, new int[]{1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1}, null);

        // Onda 24
        adicionarOnda(0.916, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Wishiwashi", "Zubat"}, new int[]{1, 2, 3, 1, 2, 2, 3, 1, 2, 2, 2, 1, 2, 1, 1}, null);

        // Onda 25 - Chefe Lucario
        adicionarOnda(0.905, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Wishiwashi", "Zubat"}, new int[]{1, 2, 3, 1, 2, 2, 3, 1, 2, 3, 2, 1, 2, 1, 1}, "Lucario");

        // Onda 26 - Pós-chefe
        adicionarOnda(1.074, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Wishiwashi"}, new int[]{1, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1}, null);

        // Onda 27
        adicionarOnda(0.884, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Wishiwashi"}, new int[]{1, 3, 3, 1, 2, 2, 3, 1, 2, 1, 3, 2, 1, 2, 1, 1}, null);

        // Onda 28
        adicionarOnda(0.874, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Wishiwashi"}, new int[]{1, 3, 3, 1, 2, 2, 3, 1, 2, 1, 3, 3, 1, 2, 1, 1}, null);

        // Onda 29
        adicionarOnda(0.864, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Wishiwashi"}, new int[]{1, 3, 3, 2, 2, 3, 3, 1, 2, 1, 3, 3, 1, 2, 1, 1}, null);

        // Onda 30 - Chefe Dragonite
        adicionarOnda(0.854, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{1, 3, 2, 2, 2, 3, 3, 1, 2, 1, 3, 3, 1, 2, 1, 1, 1, 1}, "Dragonite");

        // Onda 31 - Pós-chefe
        adicionarOnda(1.215, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{1, 3, 2, 2, 1, 2, 2, 1, 1, 1, 3, 3, 1, 2, 1, 1, 1, 1}, null);

        // Onda 32
        adicionarOnda(1.000, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{2, 3, 2, 2, 1, 3, 3, 2, 2, 2, 3, 3, 1, 2, 1, 1, 1, 1}, null);

        // Onda 33
        adicionarOnda(0.989, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{2, 3, 3, 2, 1, 3, 3, 2, 2, 2, 3, 3, 1, 2, 1, 1, 1, 1}, null);

        // Onda 34
        adicionarOnda(0.977, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{2, 1, 3, 3, 2, 1, 3, 3, 2, 2, 2, 3, 3, 1, 2, 1, 1, 1, 1}, null);

        // Onda 35 - Chefe Gyarados
        adicionarOnda(0.966, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{2, 1, 3, 3, 3, 1, 4, 3, 2, 2, 2, 3, 3, 1, 2, 1, 1, 1, 1}, "Gyarados");

        // Onda 36 - Pós-chefe
        adicionarOnda(1.145, new String[]{"Dragonite", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{2, 3, 2, 2, 1, 3, 3, 2, 1, 1, 2, 3, 3, 1, 2, 1, 1, 1}, null);

        // Onda 37
        adicionarOnda(0.943, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar", "Wishiwashi"}, new int[]{2, 1, 3, 2, 3, 1, 4, 3, 3, 1, 1, 3, 3, 3, 1, 2, 2, 1, 1, 1}, null);

        // Onda 38
        adicionarOnda(0.932, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{3, 1, 3, 2, 3, 1, 4, 3, 3, 1, 1, 3, 3, 3, 1, 2, 2, 1, 2}, null);

        // Onda 39
        adicionarOnda(0.921, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{3, 1, 3, 2, 3, 1, 4, 3, 3, 1, 1, 3, 3, 3, 1, 2, 2, 2, 2}, null);

        // Onda 40 - Chefe Steelix
        adicionarOnda(0.911, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{3, 1, 4, 2, 3, 1, 4, 3, 3, 1, 1, 3, 3, 4, 1, 2, 2, 2, 2}, "Steelix");

        // Onda 41 - Pós-chefe
        adicionarOnda(1.080, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 1, 3, 2, 3, 2, 2, 2, 2}, null);

        // Onda 42
        adicionarOnda(0.890, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{3, 1, 4, 2, 4, 1, 4, 3, 4, 1, 1, 4, 3, 4, 2, 2, 2, 2}, null);

        // Onda 43
        adicionarOnda(0.879, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{4, 1, 3, 2, 4, 1, 4, 3, 4, 1, 1, 4, 3, 4, 2, 3, 2, 2}, null);

        // Onda 44
        adicionarOnda(0.869, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{4, 1, 3, 2, 4, 1, 4, 3, 4, 1, 1, 4, 3, 4, 2, 3, 2, 3}, null);

        // Onda 45 - Chefe Tyranitar
        adicionarOnda(0.859, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{4, 1, 4, 2, 4, 1, 4, 2, 4, 2, 1, 4, 3, 4, 2, 3, 3, 3}, "Tyranitar");

        // Onda 46 - Pós-chefe
        adicionarOnda(1.018, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 1, 4, 2, 3, 1, 3, 2, 3}, null);

        // Onda 47
        adicionarOnda(0.839, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{4, 2, 3, 2, 5, 1, 4, 2, 5, 2, 1, 5, 3, 3, 1, 4, 3, 3}, null);

        // Onda 48
        adicionarOnda(0.829, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{5, 2, 3, 2, 5, 1, 4, 2, 5, 2, 1, 5, 2, 3, 1, 4, 3, 4}, null);

        // Onda 49
        adicionarOnda(0.819, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{5, 2, 3, 2, 5, 1, 4, 2, 5, 2, 1, 5, 3, 3, 1, 4, 4, 4}, null);

        // Onda 50 - Chefe Mewtwo
        adicionarOnda(0.810, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Shinx", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{5, 2, 3, 2, 5, 1, 4, 2, 5, 3, 1, 5, 2, 3, 1, 1, 4, 4, 4}, "Mewtwo");

        // Onda 51 - Pós-chefe
        adicionarOnda(0.960, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{4, 2, 3, 1, 4, 1, 3, 2, 5, 2, 1, 4, 2, 3, 1, 4, 3, 4}, null);

        // Onda 52
        adicionarOnda(0.791, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{5, 3, 3, 2, 5, 1, 4, 2, 6, 3, 1, 5, 2, 3, 1, 5, 4, 5}, null);

        // Onda 53
        adicionarOnda(0.782, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 3, 3, 1, 5, 1, 4, 2, 6, 3, 1, 5, 2, 3, 1, 5, 5, 5}, null);

        // Onda 54
        adicionarOnda(0.773, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 3, 3, 1, 5, 1, 4, 2, 6, 3, 1, 6, 2, 3, 1, 5, 5, 5}, null);

        // Onda 55 - Chefe Eternatus
        adicionarOnda(0.764, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 3, 3, 1, 5, 1, 4, 2, 6, 4, 1, 6, 2, 3, 1, 5, 5, 5}, "Eternatus");

        // Onda 56 - Pós-chefe
        adicionarOnda(0.906, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{5, 3, 2, 1, 5, 1, 3, 1, 5, 3, 1, 5, 2, 2, 1, 5, 5, 5}, null);

        // Onda 57
        adicionarOnda(0.746, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Lucario", "Machop", "Metagross", "Mewtwo", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 4, 3, 1, 6, 4, 2, 6, 4, 6, 2, 3, 1, 6, 6, 6}, null);

        // Onda 58
        adicionarOnda(0.737, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 4, 3, 1, 6, 4, 1, 6, 5, 1, 6, 2, 3, 1, 6, 6, 6}, null);

        // Onda 59
        adicionarOnda(0.729, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 4, 3, 1, 6, 1, 4, 1, 6, 5, 1, 6, 2, 3, 1, 7, 6, 6}, null);

        // Onda 60 - Chefe final Arceus
        adicionarOnda(0.720, new String[]{"Dragonite", "Eternatus", "Gengar", "Growlithe", "Gyarados", "Kadabra", "Lucario", "Machop", "Metagross", "Mewtwo", "Ninjask", "Salamence", "Sandshrew", "Scyther", "Sneasel", "Snorlax", "Steelix", "Tyranitar"}, new int[]{6, 5, 2, 1, 6, 1, 3, 1, 6, 5, 1, 6, 2, 3, 1, 7, 7, 7}, "Arceus");
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
