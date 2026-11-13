
package primalsiege.gerenciadores;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;
import primalsiege.inimigos.Rattata;
import primalsiege.inimigos.Pidgey;
import primalsiege.interfacegrafica.HUDOnda;
import primalsiege.modelo.Base;
import primalsiege.interfacegrafica.HUDBase;

public class GerenciadorInimigos {
    private ArrayList<Inimigo> inimigosAtivos;
    private ArrayList<Circle> circulosInimigos;
    private HUDOnda hudOnda;

    public GerenciadorInimigos(
        ArrayList<Inimigo> inimigosAtivos,
        ArrayList<Circle> circulosInimigos,
        HUDOnda hudOnda
    ) {
        this.inimigosAtivos = inimigosAtivos;
        this.circulosInimigos = circulosInimigos;
        this.hudOnda = hudOnda;
    }

    public void criarInimigo(Inimigo inimigo) {
        inimigosAtivos.add(inimigo);

        Circle circulo = new Circle(inimigo.getX(), inimigo.getY(), 12);
        circulosInimigos.add(circulo);
        circulo.setFill(Color.PURPLE);

        FXGL.getGameScene().addUINode(circulo);
        hudOnda.toFront();
    }

    public Inimigo instanciarInimigoDaOnda(
        GerenciadorOndas gerenciadorOndas,
        Mapa mapa
    ) {
        String especie = gerenciadorOndas.getOndaAtual().getEspecie();

        switch (especie) {
            case "Rattata":
                return new Rattata(mapa);

            case "Pidgey":
                return new Pidgey(mapa);

            default:
                return null;
        }
    }

    public boolean atualizarInimigos(double tpf, Base base, HUDBase hudBase) {

        boolean derrota = false;

        for(int i = inimigosAtivos.size() - 1; i >= 0; i--){
            if (inimigosAtivos.get(i).statusVida()) {
                if(inimigosAtivos.get(i).mover(tpf)) {
                    FXGL.getGameScene().removeUINode(circulosInimigos.get(i));

                    if(base.diminuirVida(inimigosAtivos.get(i).danoAtaque())) {
                        hudBase.getTextoVidaBase().setText(base.getVida() + " / 20");
                        hudBase.getBarraVerde().setWidth(210.0 * base.getVida() / 20);
                        derrota = true;
                    }

                    inimigosAtivos.remove(i);
                    circulosInimigos.remove(i);

                    hudBase.getTextoVidaBase().setText(base.getVida() + " / 20");
                    hudBase.getBarraVerde().setWidth(210.0 * base.getVida() / 20);
                }
                else {
                    circulosInimigos.get(i).setCenterX(inimigosAtivos.get(i).getX());
                    circulosInimigos.get(i).setCenterY(inimigosAtivos.get(i).getY());
                }
            }
            else {
                FXGL.getGameScene().removeUINode(circulosInimigos.get(i));
                inimigosAtivos.remove(i);
                circulosInimigos.remove(i);
            }
        }

        return derrota;
    }

    public void limparInimigos() {
        for (Circle circulo : circulosInimigos) {
            FXGL.getGameScene().removeUINode(circulo);
        }

        inimigosAtivos.clear();
        circulosInimigos.clear();
    }
}
