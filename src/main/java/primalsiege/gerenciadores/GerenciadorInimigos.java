package primalsiege.gerenciadores;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;
import primalsiege.inimigos.Rattata;
import primalsiege.inimigos.Pidgey;
import primalsiege.inimigos.Wishiwashi;
import primalsiege.inimigos.Machop;
import primalsiege.inimigos.Kadabra;
import primalsiege.inimigos.Gengar;
import primalsiege.inimigos.Gyarados;
import primalsiege.inimigos.Dragonite;
import primalsiege.inimigos.Mewtwo;
import primalsiege.inimigos.Eternatus;
import primalsiege.interfacegrafica.HUDOnda;
import primalsiege.modelo.Base;
import primalsiege.interfacegrafica.HUDBase;

public class GerenciadorInimigos {
    private ArrayList<Inimigo> inimigosAtivos;
    private ArrayList<Texture> imagensInimigos;
    private HUDOnda hudOnda;

    public GerenciadorInimigos(ArrayList<Inimigo> inimigosAtivos, ArrayList<Texture> imagensInimigos, HUDOnda hudOnda) {
        this.inimigosAtivos = inimigosAtivos;
        this.imagensInimigos = imagensInimigos;
        this.hudOnda = hudOnda;
    }

    public void criarInimigo(Inimigo inimigo) {
        inimigosAtivos.add(inimigo);

        Texture sprite = criarSprite(inimigo);

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
            Texture sprite = imagensInimigos.get(i);

            if (inimigo.statusVida()) {

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
                    sprite.setLayoutX(inimigo.getX() - 35);
                    sprite.setLayoutY(inimigo.getY() - 28);
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

    private Texture criarSprite(Inimigo inimigo) {

        String arquivo = inimigo.getTipoInimigo().toLowerCase() + ".png";

        Texture sprite = FXGL.getAssetLoader().loadTexture(arquivo);

        sprite.setFitWidth(64);
        sprite.setFitHeight(64);
        sprite.setSmooth(false);

        sprite.setLayoutX(inimigo.getX() - 35);
        sprite.setLayoutY(inimigo.getY() - 28);

        sprite.setMouseTransparent(true);

        return sprite;
    }

    public void limparInimigos() {
        for (Texture sprite : imagensInimigos) {
            FXGL.getGameScene().removeUINode(sprite);
        }

        inimigosAtivos.clear();
        imagensInimigos.clear();
    }
}
