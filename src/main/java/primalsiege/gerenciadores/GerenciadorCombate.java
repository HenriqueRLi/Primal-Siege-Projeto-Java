package primalsiege.gerenciadores;

import java.util.ArrayList;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Projetil;
import javafx.scene.shape.Polygon;
import primalsiege.modelo.Mapa;
import primalsiege.modelo.Torre;

public class GerenciadorCombate {

    private ArrayList<Projetil> projeteis = new ArrayList<>();
    private ArrayList<Circle> circulosProjeteis = new ArrayList<>();

    public void desenharProjetil(Projetil tiro, Color cor, int rad) {
        Circle circulo = new Circle(
            tiro.getX(),
            tiro.getY(),
            rad
        );

        circulo.setFill(cor);

        projeteis.add(tiro);
        circulosProjeteis.add(circulo);

        FXGL.getGameScene().addUINode(circulo);
    }

    public void atualizarProjeteis(ArrayList<Inimigo> inimigosAtivos, double tpf) {
        for (int i = projeteis.size() - 1; i >= 0; i--) {
            Projetil tiro = projeteis.get(i);
            Circle circulo = circulosProjeteis.get(i);

            if (!inimigosAtivos.contains(tiro.getAlvo()) || tiro.moverTiro(tpf)) {
                FXGL.getGameScene().removeUINode(circulo);
                projeteis.remove(i);
                circulosProjeteis.remove(i);
            } else {
                circulo.setCenterX(tiro.getX());
                circulo.setCenterY(tiro.getY());
                circulo.setRadius(tiro.getRaio());
            }
        }
    }

    public void limparProjeteis() {
        for (Circle projetil : circulosProjeteis) {
            FXGL.getGameScene().removeUINode(projetil);
        }

        projeteis.clear();
        circulosProjeteis.clear();
    }

    public void atualizarTorres(ArrayList<Torre> torres, ArrayList<Polygon> triangulosTorres,ArrayList<Inimigo> inimigosAtivos, Mapa mapa, Inimigo inimigo, double tpf) {

        for (int i = 0; i < torres.size(); i++) {
            Torre torre = torres.get(i);
            Polygon triangulo = triangulosTorres.get(i);
            Inimigo alvoSelecionado = null;

            double distanciaAlvo = Double.POSITIVE_INFINITY;
            double distanciaCandidato;
            int proximoPonto;
            double dx,dy,proximoX,proximoY;

            for(int j = 0; j < inimigosAtivos.size(); j++) {
                if (inimigosAtivos.get(j).statusVida()) {
                    if (torre.estaNoAlcance(inimigosAtivos.get(j))) {
                        if(alvoSelecionado == null || alvoSelecionado.getIndicePonto() < inimigosAtivos.get(j).getIndicePonto()){
                            alvoSelecionado = inimigosAtivos.get(j);
                            proximoPonto = alvoSelecionado.getIndicePonto();
                            proximoPonto = mapa.getCaminho().get(proximoPonto);
                            proximoX = (proximoPonto % 32 * 40) + 20;
                            proximoY = (proximoPonto / 32 * 40) + 20;

                            dx = proximoX - inimigosAtivos.get(j).getX();
                            dy = proximoY - inimigosAtivos.get(j).getY();

                            distanciaAlvo = Math.hypot(dx,dy);
                        }
                        else if(alvoSelecionado.getIndicePonto() == inimigosAtivos.get(j).getIndicePonto()){
                            proximoPonto = inimigosAtivos.get(j).getIndicePonto();
                            proximoPonto = mapa.getCaminho().get(proximoPonto);
                            proximoX = (proximoPonto % 32 * 40) + 20;
                            proximoY = (proximoPonto / 32 * 40) + 20;

                            dx = proximoX - inimigosAtivos.get(j).getX();
                            dy = proximoY - inimigosAtivos.get(j).getY();

                            distanciaCandidato = Math.hypot(dx,dy);
                            if(distanciaAlvo > distanciaCandidato){
                                alvoSelecionado = inimigosAtivos.get(j);
                                distanciaAlvo = distanciaCandidato;
                            }
                        }
                    }
                }
            }

            if(alvoSelecionado != null){
                torre.atualizarRotacao(alvoSelecionado.getX(), alvoSelecionado.getY());
                triangulo.setRotate(torre.getRotacao() + 90);
                Projetil tiro = torre.atirar(alvoSelecionado, tpf);

                if (tiro != null) {
                    desenharProjetil(tiro, torre.getcorProj(),torre.getProjrad(inimigo));
                }
            }
        }
    }

}
