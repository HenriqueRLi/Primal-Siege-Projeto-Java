package primalsiege.gerenciadores;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import primalsiege.inimigos.Dragonite;
import primalsiege.inimigos.Eternatus;
import primalsiege.inimigos.Gengar;
import primalsiege.inimigos.Gyarados;
import primalsiege.inimigos.Kadabra;
import primalsiege.inimigos.Machop;
import primalsiege.inimigos.Mewtwo;
import primalsiege.inimigos.Pidgey;
import primalsiege.inimigos.Rattata;
import primalsiege.inimigos.Wishiwashi;
import primalsiege.interfacegrafica.HUDBase;
import primalsiege.interfacegrafica.HUDOnda;
import primalsiege.modelo.Base;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;

public class GerenciadorInimigos {
    private ArrayList<Inimigo> inimigosAtivos;
    private ArrayList<ImageView> imagensInimigos = new ArrayList<>();
    private HUDOnda hudOnda;

    public GerenciadorInimigos(ArrayList<Inimigo> inimigosAtivos, ArrayList<ImageView> imagensInimigos, HUDOnda hudOnda) {
        this.inimigosAtivos = inimigosAtivos;
        this.imagensInimigos = imagensInimigos;
        this.hudOnda = hudOnda;
    }

    public void criarInimigo(Inimigo inimigo) {
        inimigosAtivos.add(inimigo);

        ImageView sprite = criarSprite(inimigo);

        imagensInimigos.add(sprite);

        FXGL.getGameScene().addUINode(sprite);
        hudOnda.toFront();
    }

    public Inimigo instanciarInimigoDaOnda(GerenciadorOndas gerenciadorOndas, Mapa mapa) {
        String especie = gerenciadorOndas.getOndaAtual().getEspecie();

        switch (especie) {
            case "Wishiwashi":
                return new Wishiwashi(mapa);

            case "Pidgey":
                return new Pidgey(mapa);

            case "Rattata":
                return new Rattata(mapa);

            case "Machop":
                return new Machop(mapa);

            case "Kadabra":
                return new Kadabra(mapa);

            case "Gengar":
                return new Gengar(mapa);

            case "Gyarados":
                return new Gyarados(mapa);

            case "Dragonite":
                return new Dragonite(mapa);

            case "Mewtwo":
                return new Mewtwo(mapa);

            case "Eternatus":
                return new Eternatus(mapa);

            default:
                return null;
        }
    }

    public boolean atualizarInimigos(double tpf, Base base, HUDBase hudBase, GerenciadorEconomia gerenciadorEconomia) {

        boolean derrota = false;

        for (int i = inimigosAtivos.size() - 1; i >= 0; i--) {

            Inimigo inimigo = inimigosAtivos.get(i);
            ImageView sprite = imagensInimigos.get(i);

            if (inimigo.statusVida()) {

                double xAnterior = inimigo.getX();
                double yAnterior = inimigo.getY();

                if (inimigo.mover(tpf)) {

                    FXGL.getGameScene().removeUINode(sprite);

                    if (base.diminuirVida(inimigo.danoAtaque())) {
                        derrota = true;
                    }

                    inimigosAtivos.remove(i);
                    imagensInimigos.remove(i);

                    hudBase.getTextoVidaBase().setText(base.getVida() + " / 20");
                    hudBase.getBarraVerde().setWidth(210.0 * base.getVida() / 20);

                }
                else {
                    double dx = inimigo.getX() - xAnterior;
                    double dy = inimigo.getY() - yAnterior;

                    atualizarSprite(sprite, inimigo.getTipoInimigo(), dx, dy);

                    sprite.setLayoutX(inimigo.getX() - sprite.getFitWidth() / 2.0);
                    sprite.setLayoutY(inimigo.getY() - sprite.getFitHeight() / 2.0);
                }

            }
            else {

                gerenciadorEconomia.adicionarOuro(inimigo.getRecompensa());
                hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());

                FXGL.getGameScene().removeUINode(sprite);
                inimigosAtivos.remove(i);
                imagensInimigos.remove(i);
            }
        }

        return derrota;
    }

    private ImageView criarSprite(Inimigo inimigo) {

        ImageView sprite = new ImageView();
        atualizarSprite(sprite, inimigo.getTipoInimigo(), 1, 0);

        sprite.setFitWidth(40);
        sprite.setFitHeight(40);
        sprite.setSmooth(false);

        sprite.setLayoutX(inimigo.getX() - sprite.getFitWidth() / 2.0);
        sprite.setLayoutY(inimigo.getY() - sprite.getFitHeight() / 2.0);

        sprite.setMouseTransparent(true);

        return sprite;
    }

    public void limparInimigos() {
        for (ImageView sprite : imagensInimigos) {
            FXGL.getGameScene().removeUINode(sprite);
        }

        inimigosAtivos.clear();
        imagensInimigos.clear();
    }

    private static final String PASTA_GIFS = "/assets/textures/";
    private final Map<String, Optional<Image>> cacheGifs = new HashMap<>();

    private void atualizarSprite(ImageView sprite, String nome, double dx, double dy) {
        if (dx == 0 && dy == 0) {
            return;
        }

        String direcao;
        if (Math.abs(dx) >= Math.abs(dy)) {
            direcao = dx > 0 ? "direita" : "esquerda";
        } else {
            direcao = dy > 0 ? "baixo" : "cima";
        }

        String arquivo = nome.toLowerCase(Locale.ROOT) + "_" + direcao + ".gif";

        Optional<Image> gif = cacheGifs.computeIfAbsent(arquivo, chave -> {
            URL url = GerenciadorInimigos.class.getResource(PASTA_GIFS + chave);
            return url == null ? Optional.empty() : Optional.of(new Image(url.toExternalForm()));
        });

        if (gif.isPresent() && sprite.getImage() != gif.get()) {
            sprite.setImage(gif.get());
        }
    }
}
