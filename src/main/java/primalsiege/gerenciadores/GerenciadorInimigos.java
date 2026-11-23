package primalsiege.gerenciadores;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import primalsiege.inimigos.Arceus;
import primalsiege.inimigos.Caterpie;
import primalsiege.inimigos.Dragonite;
import primalsiege.inimigos.Eternatus;
import primalsiege.inimigos.Gengar;
import primalsiege.inimigos.Growlithe;
import primalsiege.inimigos.Gyarados;
import primalsiege.inimigos.Kadabra;
import primalsiege.inimigos.Lucario;
import primalsiege.inimigos.Machop;
import primalsiege.inimigos.Metagross;
import primalsiege.inimigos.Mewtwo;
import primalsiege.inimigos.Ninjask;
import primalsiege.inimigos.Pidgey;
import primalsiege.inimigos.Rattata;
import primalsiege.inimigos.Salamence;
import primalsiege.inimigos.Sandshrew;
import primalsiege.inimigos.Scyther;
import primalsiege.inimigos.Shinx;
import primalsiege.inimigos.Sneasel;
import primalsiege.inimigos.Snorlax;
import primalsiege.inimigos.Steelix;
import primalsiege.inimigos.Tyranitar;
import primalsiege.inimigos.Wishiwashi;
import primalsiege.inimigos.Zubat;
import primalsiege.interfacegrafica.HUDBase;
import primalsiege.interfacegrafica.HUDOnda;
import primalsiege.modelo.Base;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;
import primalsiege.modelo.Onda;

public class GerenciadorInimigos {
    private final ArrayList<Inimigo> inimigosAtivos;
    private final ArrayList<ImageView> imagensInimigos;
    private final ArrayList<Circle> circulosSlow = new ArrayList<>();
    
    private static final String PASTA_GIFS = "/assets/textures/";
    private final Map<String, Optional<Image>> cacheGifs = new HashMap<>();
    private final HUDOnda hudOnda;
    private final DropShadow efeitoQueimadura = new DropShadow(16, Color.ORANGERED);

    public GerenciadorInimigos(ArrayList<Inimigo> inimigosAtivos, ArrayList<ImageView> imagensInimigos, HUDOnda hudOnda) {
        this.inimigosAtivos = inimigosAtivos;
        this.imagensInimigos = imagensInimigos;
        this.hudOnda = hudOnda;
    }

    public void criarInimigo(Inimigo inimigo) {
        inimigosAtivos.add(inimigo);

        ImageView sprite = criarSprite(inimigo);

        Circle anelSlow = new Circle(inimigo.getX(), inimigo.getY() + 23, 17);

        anelSlow.setRadius(20);
        anelSlow.setScaleY(0.40);
        anelSlow.setStroke(Color.TRANSPARENT);

        anelSlow.setFill(new RadialGradient(
            0, 0, 0.5, 0.5, 0.5, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.rgb(50, 200, 70, 0.75)),
            new Stop(0.5, Color.rgb(40, 170, 60, 0.40)),
            new Stop(1, Color.TRANSPARENT)
        ));

        anelSlow.setEffect(new GaussianBlur(2));
        anelSlow.setMouseTransparent(true);
        anelSlow.setVisible(false);
        anelSlow.setViewOrder(1);

        circulosSlow.add(anelSlow);
        FXGL.getGameScene().addUINode(anelSlow);

        imagensInimigos.add(sprite);

        FXGL.getGameScene().addUINode(sprite);
        hudOnda.toFront();
    }

    public Inimigo instanciarInimigoDaOnda(GerenciadorOndas gerenciadorOndas, Mapa mapa, int indice) {
        String especie = gerenciadorOndas.getOndaAtual().getEspecie(indice);

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

            case "Arceus":
                return new Arceus(mapa);

            case "Caterpie":
                return new Caterpie(mapa);

            case "Zubat":
                return new Zubat(mapa);

            case "Shinx":
                return new Shinx(mapa);

            case "Sandshrew":
                return new Sandshrew(mapa);

            case "Metagross":
                return new Metagross(mapa);

            case "Tyranitar":
                return new Tyranitar(mapa);

            case "Ninjask":
                return new Ninjask(mapa);

            case "Snorlax":
                return new Snorlax(mapa);

            case "Lucario":
                return new Lucario(mapa);

            case "Scyther":
                return new Scyther(mapa);

            case "Steelix":
                return new Steelix(mapa);

            case "Sneasel":
                return new Sneasel(mapa);

            case "Growlithe":
                return new Growlithe(mapa);

            case "Salamence":
                return new Salamence(mapa);

            default:
                return null;
        }
    }

    public boolean atualizarInimigos(double tpf, Base base, HUDBase hudBase, GerenciadorEconomia gerenciadorEconomia) {

        boolean derrota = false;

        for (int i = inimigosAtivos.size() - 1; i >= 0; i--) {

            Inimigo inimigo = inimigosAtivos.get(i);

        ImageView sprite = imagensInimigos.get(i);
        Circle anelSlow = circulosSlow.get(i);

        inimigo.atualizarQueimadura(tpf);
        inimigo.atualizarLentidao(tpf);

        anelSlow.setVisible(inimigo.estaLento());

        if (inimigo.estaQueimando()) {
            sprite.setEffect(efeitoQueimadura);
        }
        else {
            sprite.setEffect(null);
        }

        if (inimigo.statusVida()) {

            double xAnterior = inimigo.getX();
            double yAnterior = inimigo.getY();

            if (inimigo.mover(tpf)) {


                FXGL.getGameScene().removeUINode(sprite);
                FXGL.getGameScene().removeUINode(anelSlow);

                if (base.diminuirVida(inimigo.danoAtaque())) {
                    derrota = true;
                }

                inimigosAtivos.remove(i);
                imagensInimigos.remove(i);
                circulosSlow.remove(i);

                hudBase.atualizarVida(base.getVida(), true);

            }
                else {
                  double dx = inimigo.getX() - xAnterior;
                  double dy = inimigo.getY() - yAnterior;

                  atualizarSprite(sprite, inimigo.getTipoInimigo(), dx, dy);

                  sprite.setLayoutX(inimigo.getX() - sprite.getFitWidth() / 2.0);
                  sprite.setLayoutY(inimigo.getY() - sprite.getFitHeight() / 2.0);

                  anelSlow.setCenterX(inimigo.getX());
                  anelSlow.setCenterY(inimigo.getY() + 23);
                }

            }
            else {

                gerenciadorEconomia.adicionarOuro(inimigo.getRecompensa());
                hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());

                FXGL.getGameScene().removeUINode(sprite);
                FXGL.getGameScene().removeUINode(anelSlow);

                inimigosAtivos.remove(i);
                imagensInimigos.remove(i);
                circulosSlow.remove(i);
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
        sprite.setViewOrder(1);

        return sprite;
    }

    public void limparInimigos() {
        for (ImageView sprite : imagensInimigos) {
            FXGL.getGameScene().removeUINode(sprite);
        }

        for (Circle anel : circulosSlow) {
            FXGL.getGameScene().removeUINode(anel);
        }

        circulosSlow.clear();
        inimigosAtivos.clear();
        imagensInimigos.clear();
    }

    public void organizarOndaPorVelocidade(GerenciadorOndas gerenciadorOndas, Mapa mapa) {

        Onda onda = gerenciadorOndas.getOndaAtual();

        Map<String, Double> velocidades = new HashMap<>();

        for (int i = 0; i < onda.getQuantidade(); i++) {

            String especie = onda.getEspecie(i);

            if (!velocidades.containsKey(especie)) {

                Inimigo inimigo = instanciarInimigoDaOnda(
                    gerenciadorOndas, mapa, i
                );

                velocidades.put(
                    especie,
                    inimigo.getVelocidadeMovimentacao()
                );
            }
        }

        onda.ordenarPorVelocidade(velocidades);
    }

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
