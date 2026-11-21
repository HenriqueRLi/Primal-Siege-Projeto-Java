package primalsiege.gerenciadores;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;
import primalsiege.modelo.Projetil;
import primalsiege.modelo.Torre;

import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import javafx.geometry.Point2D;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.animation.FadeTransition;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.paint.RadialGradient;

public class GerenciadorCombate {

    private ArrayList<Projetil> projeteis = new ArrayList<>();
    private ArrayList<Shape> visuaisProjeteis = new ArrayList<>();

    private void ajustarDirecaoChama(Projetil tiro, Shape visual) {

        double dx = tiro.getAlvo().getX() - tiro.getX();
        double dy = tiro.getAlvo().getY() - tiro.getY();

        double angulo = Math.toDegrees(Math.atan2(dy, dx));

        visual.setRotate(angulo);
    }
    
    public void desenharProjetil(Projetil tiro, Color cor, int rad) {
        Shape visual;

        if (tiro.ehVisualFogo()) {

            int nivel = tiro.getNivelVisualFogo();

            Polygon chama;

            if (nivel == 3) {
                chama = new Polygon(
                    -25.0, -5.0,
                    -14.0, -7.0,
                    -20.0, -13.0,
                    -4.0, -9.0,
                    3.0, -14.0,
                    17.0, -8.0,
                    27.0, 0.0,
                    17.0, 8.0,
                    3.0, 14.0,
                    -4.0, 9.0,
                    -20.0, 13.0,
                    -14.0, 7.0,
                    -25.0, 5.0
                );

            }
            else {

                chama = new Polygon(
                    -16.0, -6.0,
                    -9.0, -4.0,
                    -13.0, -11.0,
                    0.0, -7.0,
                    4.0, -11.0,
                    16.0, 0.0,
                    4.0, 11.0,
                    0.0, 7.0,
                    -13.0, 11.0,
                    -9.0, 4.0,
                    -16.0, 6.0
                );
            }

            if (nivel == 1) {

                chama.setFill(new LinearGradient(
                    0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.ORANGERED),
                    new Stop(0.5, Color.ORANGE),
                    new Stop(1, Color.YELLOW)
                ));

                chama.setStroke(Color.ORANGE);
                chama.setStrokeWidth(1);
                chama.setEffect(new DropShadow(12, Color.ORANGERED));

                chama.setScaleX(0.65);
                chama.setScaleY(0.65);

            }
            else if (nivel == 2) {

                chama.setFill(new LinearGradient(
                    0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKRED),
                    new Stop(0.35, Color.ORANGERED),
                    new Stop(0.70, Color.ORANGE),
                    new Stop(1, Color.YELLOW)
                ));

                chama.setStroke(Color.ORANGERED);
                chama.setStrokeWidth(1);
                chama.setEffect(new DropShadow(17, Color.RED));

                chama.setScaleX(0.85);
                chama.setScaleY(0.85);

            }
            else {

                chama.setFill(new LinearGradient(
                    0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKRED),
                    new Stop(0.25, Color.RED),
                    new Stop(0.55, Color.ORANGERED),
                    new Stop(0.80, Color.ORANGE),
                    new Stop(1, Color.LIGHTYELLOW)
                ));

                chama.setStroke(Color.ORANGE);
                chama.setStrokeWidth(1.5);
                chama.setEffect(new DropShadow(22, Color.ORANGERED));

                chama.setScaleX(1.0);
                chama.setScaleY(0.90);
            }

        visual = chama;
        }
        else if (tiro.ehVisualAgua()) {

            int nivel = tiro.getNivelVisualAgua();

            Polygon agua = new Polygon(
                -16.0, -7.0,
                -7.0, -10.0,
                7.0, -6.0,
                17.0, 0.0,
                7.0, 6.0,
                -7.0, 10.0,
                -16.0, 7.0,
                -11.0, 0.0
            );

            double escala;
            double brilho;
            Color corBase;
            Color corCentro;
            Color corPonta;

            if (nivel == 1) {

                escala = 0.65;
                brilho = 8;

                corBase = Color.DODGERBLUE;
                corCentro = Color.DEEPSKYBLUE;
                corPonta = Color.LIGHTCYAN;

            }
            else if (nivel == 2) {

                escala = 0.85;
                brilho = 12;

                corBase = Color.BLUE;
                corCentro = Color.DEEPSKYBLUE;
                corPonta = Color.ALICEBLUE;

            }
            else {

                escala = 1.0;
                brilho = 18;

                corBase = Color.DARKBLUE;
                corCentro = Color.DEEPSKYBLUE;
                corPonta = Color.WHITE;
            }

            if (tiro.ehHidroBomba()) {

                if (nivel == 3) {
                    escala *= 1.8;
                    brilho += 16;
                }
                else {
                    escala *= 1.6;
                    brilho += 12;
                }

                corBase = Color.DODGERBLUE;
                corCentro = Color.AQUA;
                corPonta = Color.WHITE;
            }

            agua.setFill(new LinearGradient(
                0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, corBase),
                new Stop(0.55, corCentro),
                new Stop(1, corPonta)
            ));

            agua.setStroke(Color.LIGHTCYAN);
            agua.setStrokeWidth(tiro.ehHidroBomba() ? 2 : 1.2);
            agua.setEffect(new DropShadow(brilho, Color.AQUA));

            agua.setScaleX(escala);

            if (tiro.ehHidroBomba()) {
                agua.setScaleY(escala);
            }
            else {
                agua.setScaleY(escala * 0.75);
            }

            visual = agua;
        }
        else if (tiro.ehVisualPlanta()) {

            int nivel = tiro.getNivelVisualPlanta();

            Polygon planta;

            if (nivel == 1) {

                planta = new Polygon(
                    -11.0, -5.0,
                    -5.0, -8.0,
                    6.0, -6.0,
                    12.0, 0.0,
                    6.0, 6.0,
                    -5.0, 8.0,
                    -11.0, 5.0
                );

            }
            else if (nivel == 2) {

                planta = new Polygon(
                    -17.0, 0.0,
                    -8.0, -8.0,
                    4.0, -10.0,
                    17.0, 0.0,
                    4.0, 10.0,
                    -8.0, 8.0
                );

            }
            else {

                planta = new Polygon(
                    -23.0, 0.0,
                    -15.0, -7.0,
                    -18.0, -13.0,
                    -5.0, -10.0,
                    2.0, -17.0,
                    12.0, -9.0,
                    24.0, 0.0,
                    12.0, 9.0,
                    2.0, 17.0,
                    -5.0, 10.0,
                    -18.0, 13.0,
                    -15.0, 7.0
                );
            }

            double escala;
            double brilho;
            Color corBase;
            Color corPonta;

            if (nivel == 1) {

                escala = 0.65;
                brilho = 7;

                corBase = Color.FORESTGREEN;
                corPonta = Color.YELLOWGREEN;

            }
            else if (nivel == 2) {

                escala = 0.85;
                brilho = 12;

                corBase = Color.DARKGREEN;
                corPonta = Color.LIMEGREEN;

            }
            else {

                escala = 1.0;
                brilho = 18;

                corBase = Color.FORESTGREEN;
                corPonta = Color.LAWNGREEN;
            }

            planta.setFill(new LinearGradient(
                0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, corBase),
                new Stop(0.55, Color.LIMEGREEN),
                new Stop(1, corPonta)
            ));

            planta.setStroke(Color.LIGHTGREEN);
            planta.setStrokeWidth(nivel == 3 ? 1.5 : 1);
            planta.setEffect(new DropShadow(brilho, Color.LIMEGREEN));

            planta.setScaleX(escala);
            planta.setScaleY(escala);

            visual = planta;
        }
        else {

            Circle circulo = new Circle(0, 0, rad);

            circulo.setFill(cor);

            if (tiro.ehDescarga()) {
                circulo.setFill(Color.GOLD);
                circulo.setStroke(Color.LIGHTYELLOW);
                circulo.setStrokeWidth(2);
                circulo.setEffect(new DropShadow(12, Color.YELLOW));
            }

            if (tiro.ehHidroBomba()) {
                circulo.setRadius(tiro.getRaio() * 2);
                circulo.setFill(Color.DEEPSKYBLUE);
                circulo.setStroke(Color.LIGHTCYAN);
                circulo.setStrokeWidth(2);
                circulo.setEffect(new DropShadow(18, Color.AQUA));
            }

            visual = circulo;
        }

        visual.setTranslateX(tiro.getX());
        visual.setTranslateY(tiro.getY());
        visual.setMouseTransparent(true);

        if (tiro.ehVisualFogo() || tiro.ehVisualAgua() || tiro.ehVisualPlanta()) {
            ajustarDirecaoChama(tiro, visual);
        }

        projeteis.add(tiro);
        visuaisProjeteis.add(visual);

        FXGL.getGameScene().addUINode(visual);
    }
    
    private void mostrarImpactoHidroBomba(double x, double y) {

        Circle impacto = new Circle(x, y, 17);

        impacto.setFill(new RadialGradient(
            0, 0, 0.5, 0.5, 0.5, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.rgb(255, 255, 255, 0.9)),
            new Stop(0.4, Color.rgb(100, 220, 255, 0.75)),
            new Stop(1, Color.TRANSPARENT)
        ));

        impacto.setEffect(new DropShadow(15, Color.AQUA));
        impacto.setMouseTransparent(true);

        FXGL.getGameScene().addUINode(impacto);

        ScaleTransition expandir = new ScaleTransition(
            Duration.seconds(0.60), impacto
        );

        expandir.setFromX(0.6);
        expandir.setFromY(0.6);
        expandir.setToX(2.2);
        expandir.setToY(2.2);

        FadeTransition desaparecer = new FadeTransition(
            Duration.seconds(0.60), impacto
        );

        desaparecer.setFromValue(1.0);
        desaparecer.setToValue(0.0);

        ParallelTransition animacao = new ParallelTransition(
            expandir, desaparecer
        );

        animacao.setOnFinished(evento -> {
            FXGL.getGameScene().removeUINode(impacto);
        });

        animacao.play();
    }

    public void atualizarProjeteis(ArrayList<Inimigo> inimigosAtivos, double tpf) {

        for (int i = projeteis.size() - 1; i >= 0; i--) {

            Projetil tiro = projeteis.get(i);
            Shape visual = visuaisProjeteis.get(i);

            boolean alvoValido = inimigosAtivos.contains(tiro.getAlvo())
                            && tiro.getAlvo().statusVida();

            boolean acertou = alvoValido && tiro.moverTiro(tpf, inimigosAtivos);

            if (!alvoValido || acertou) {

                if (acertou && tiro.ehDescarga()) {
                    mostrarRaioDescarga(tiro.getAlvosDescarga());
                }

                if (acertou && tiro.ehHidroBomba()) {
                    mostrarImpactoHidroBomba(tiro.getX(), tiro.getY());
                }

                FXGL.getGameScene().removeUINode(visual);

                projeteis.remove(i);
                visuaisProjeteis.remove(i);
            }
            else {

                visual.setTranslateX(tiro.getX());
                visual.setTranslateY(tiro.getY());

                if (visual instanceof Circle) {

                    Circle circulo = (Circle) visual;

                    if (tiro.ehHidroBomba()) {
                        circulo.setRadius(tiro.getRaio() * 2);
                    }
                    else {
                        circulo.setRadius(tiro.getRaio());
                    }
                }

                if (tiro.ehVisualFogo() || tiro.ehVisualAgua() || tiro.ehVisualPlanta()) {
                    ajustarDirecaoChama(tiro, visual);
                }
            }
        }
    }

    public void limparProjeteis() {

        for (Shape visual : visuaisProjeteis) {
            FXGL.getGameScene().removeUINode(visual);
        }

        projeteis.clear();
        visuaisProjeteis.clear();
    }

    public void atualizarTorres(ArrayList<Torre> torres, ArrayList<Polygon> triangulosTorres,ArrayList<Inimigo> inimigosAtivos, Mapa mapa, Inimigo inimigo, double tpf) {

        for (int i = 0; i < torres.size(); i++) {
            Torre torre = torres.get(i);
            torre.atualizarRecarga(tpf);
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

            if (alvoSelecionado != null) {

<<<<<<< HEAD
            torre.atualizarRotacao(alvoSelecionado.getX(), alvoSelecionado.getY());
            triangulo.setRotate(torre.getRotacao() + 90);

            Projetil tiro = torre.atirar(alvoSelecionado);

            if (tiro != null) {

                if (torre.usaRaioInstantaneo() && torre.getNivel() == 3) {
                    System.out.println("Raichu disparou. Descarga: " + tiro.ehDescarga());
                }
                if (torre.usaRaioInstantaneo()) {
                    mostrarRaioEletrico(
                        torre.getX(),
                        torre.getY(),
                        alvoSelecionado,
                        torre.getNivel()
                    );

                    tiro.atingirInstantaneamente(inimigosAtivos);

                    if (tiro.ehDescarga()) {
                        mostrarRaioDescarga(tiro.getAlvosDescarga());
                    }

                }
                else {
                    desenharProjetil(tiro, torre.getcorProj(), torre.getProjrad(inimigo));
=======
                if (tiro != null) {
                    desenharProjetil(tiro, torre.getcorProj(), torre.getProjrad(alvoSelecionado));
>>>>>>> c9004da (interface upgrade e venda)
                }
            }
        }
        }
    }

    private void mostrarRaioDescarga(ArrayList<Inimigo> alvos) {

        if (alvos.size() < 2) {
            return;
        }

        for (int i = 0; i < alvos.size() - 1; i++) {

            Inimigo origem = alvos.get(i);
            Inimigo destino = alvos.get(i + 1);

            double distancia = Math.hypot(
                destino.getX() - origem.getX(),
                destino.getY() - origem.getY()
            );

            int segmentos = Math.max(3, (int) (distancia / 16));

            Path raio = new Path();

            raio.getElements().add(new MoveTo());

            for (int j = 0; j < segmentos; j++) {
                raio.getElements().add(new LineTo());
            }

            raio.setStroke(Color.LIGHTYELLOW);
            raio.setStrokeWidth(3);
            raio.setFill(null);
            raio.setEffect(new DropShadow(15, Color.YELLOW));
            raio.setMouseTransparent(true);

            AnimationTimer acompanharAlvos = new AnimationTimer() {

                @Override
                public void handle(long agora) {

                    double x1 = origem.getX() - 3;
                    double y1 = origem.getY() + 4;

                    double x2 = destino.getX() - 3;
                    double y2 = destino.getY() + 4;

                    MoveTo inicio = (MoveTo) raio.getElements().get(0);

                    inicio.setX(x1);
                    inicio.setY(y1);

                    double dx = x2 - x1;
                    double dy = y2 - y1;
                    double distanciaAtual = Math.hypot(dx, dy);

                    if (distanciaAtual < 1) {
                        return;
                    }

                    for (int j = 1; j < segmentos; j++) {

                        double t = (double) j / segmentos;

                        double desvio = (j % 2 == 0 ? 1 : -1)
                                * (5 + (j % 3) * 2);

                        double x = x1 + dx * t
                                - (dy / distanciaAtual) * desvio;

                        double y = y1 + dy * t
                                + (dx / distanciaAtual) * desvio;

                        LineTo ponto = (LineTo) raio.getElements().get(j);

                        ponto.setX(x);
                        ponto.setY(y);
                    }

                    LineTo ponta = (LineTo) raio.getElements().get(segmentos);

                    ponta.setX(x2);
                    ponta.setY(y2);
                }
            };

            acompanharAlvos.handle(0);

            FXGL.getGameScene().addUINode(raio);

            acompanharAlvos.start();

            FadeTransition desaparecer = new FadeTransition(
                Duration.seconds(0.45), raio
            );

            desaparecer.setFromValue(1.0);
            desaparecer.setToValue(0.0);
            desaparecer.setDelay(Duration.seconds(0.15));

            desaparecer.setOnFinished(evento -> {
                acompanharAlvos.stop();
                FXGL.getGameScene().removeUINode(raio);
            });

            desaparecer.play();
        }
    }

    private void mostrarRaioEletrico(double x1, double y1, Inimigo alvo, int nivel) {
        double x2 = alvo.getX() - 3;
        double y2 = alvo.getY() + 4;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distancia = Math.hypot(dx, dy);

        if (distancia < 1) {
            return;
        }

        Path raio = new Path();
        raio.getElements().add(new MoveTo(x1, y1));

        int segmentos = Math.max(3, (int) (distancia / 18));

        for (int i = 1; i < segmentos; i++) {

            double t = (double) i / segmentos;
            double desvio = (i % 2 == 0 ? 1 : -1) * 5;

            double x = x1 + dx * t - (dy / distancia) * desvio;
            double y = y1 + dy * t + (dx / distancia) * desvio;

            raio.getElements().add(new LineTo(x, y));
        }

        raio.getElements().add(new LineTo(x2, y2));

        if (nivel == 3) {
            raio.getElements().add(new MoveTo());
            raio.getElements().add(new LineTo());

            raio.getElements().add(new MoveTo());
            raio.getElements().add(new LineTo());
        }

        if (nivel == 1) {
            raio.setStroke(Color.YELLOW);
            raio.setStrokeWidth(2);
            raio.setEffect(new DropShadow(8, Color.YELLOW));
        }
        else if (nivel == 2) {
            raio.setStroke(Color.LIGHTYELLOW);
            raio.setStrokeWidth(3.5);
            raio.setEffect(new DropShadow(14, Color.GOLD));
        }
        else {
            raio.setStroke(Color.WHITE);
            raio.setStrokeWidth(4);
            raio.setEffect(new DropShadow(22, Color.YELLOW));
        }

        raio.setFill(null);
        raio.setMouseTransparent(true);

        FXGL.getGameScene().addUINode(raio);

        AnimationTimer acompanharAlvo = new AnimationTimer() {

            @Override
            public void handle(long agora) {

                if (!alvo.statusVida()) {
                    return;
                }

                double xAtual = alvo.getX() - 3;
                double yAtual = alvo.getY() + 4;

                double dxAtual = xAtual - x1;
                double dyAtual = yAtual - y1;

                double distanciaAtual = Math.hypot(dxAtual, dyAtual);

                if (distanciaAtual < 1) {
                    return;
                }

                for (int i = 1; i < segmentos; i++) {

                    double t = (double) i / segmentos;
                    double desvio = (i % 2 == 0 ? 1 : -1) * 5;

                    double x = x1 + dxAtual * t
                            - (dyAtual / distanciaAtual) * desvio;

                    double y = y1 + dyAtual * t
                            + (dxAtual / distanciaAtual) * desvio;

                    LineTo ponto = (LineTo) raio.getElements().get(i);

                    ponto.setX(x);
                    ponto.setY(y);
                }

                LineTo ponta = (LineTo) raio.getElements().get(segmentos);

                ponta.setX(xAtual);
                ponta.setY(yAtual);

                if (nivel == 3) {

                    double perpendicularX = -dyAtual / distanciaAtual;
                    double perpendicularY = dxAtual / distanciaAtual;

                    MoveTo inicioRamo1 = (MoveTo) raio.getElements().get(segmentos + 1);
                    LineTo fimRamo1 = (LineTo) raio.getElements().get(segmentos + 2);

                    inicioRamo1.setX(x1 + dxAtual * 0.40);
                    inicioRamo1.setY(y1 + dyAtual * 0.40);

                    fimRamo1.setX(x1 + dxAtual * 0.47 + perpendicularX * 14);
                    fimRamo1.setY(y1 + dyAtual * 0.47 + perpendicularY * 14);

                    MoveTo inicioRamo2 = (MoveTo) raio.getElements().get(segmentos + 3);
                    LineTo fimRamo2 = (LineTo) raio.getElements().get(segmentos + 4);

                    inicioRamo2.setX(x1 + dxAtual * 0.70);
                    inicioRamo2.setY(y1 + dyAtual * 0.70);

                    fimRamo2.setX(x1 + dxAtual * 0.77 - perpendicularX * 14);
                    fimRamo2.setY(y1 + dyAtual * 0.77 - perpendicularY * 14);
                }
            }
        };

        acompanharAlvo.handle(0);
        acompanharAlvo.start();

        FadeTransition desaparecer = new FadeTransition(Duration.seconds(nivel == 1 ? 0.30 : 0.40),raio);

        desaparecer.setFromValue(1.0);
        desaparecer.setToValue(0.0);
        desaparecer.setDelay(Duration.seconds(nivel == 1 ? 0.10 : 0.12));

        desaparecer.setOnFinished(evento -> {
            acompanharAlvo.stop();
            FXGL.getGameScene().removeUINode(raio);
        });

        desaparecer.play();
    }
}