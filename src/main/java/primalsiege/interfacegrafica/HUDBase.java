package primalsiege.interfacegrafica;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.FadeTransition;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;

import primalsiege.modelo.Base;

public class HUDBase {
private Text textoVidaBase;
private Rectangle barraVerde;
private Rectangle fundoHUD;
private FillTransition animacaoDano;
private FadeTransition alertaVida;

    public HUDBase(Base base) {

        fundoHUD = new Rectangle(250, 80);
        fundoHUD.setX(1000);
        fundoHUD.setY(15);
        fundoHUD.setFill(Color.web("#17212B"));
        fundoHUD.setArcWidth(20);
        fundoHUD.setArcHeight(20);
        fundoHUD.setStroke(Color.web("#688DA2"));
        fundoHUD.setStrokeWidth(1.5);
        fundoHUD.setEffect(
            new DropShadow(12, Color.rgb(0, 0, 0, 0.55))
        );

        Text tituloHUD = new Text("BASE");
        tituloHUD.setX(1020);
        tituloHUD.setY(45);
        tituloHUD.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        tituloHUD.setFill(Color.WHITE);

        textoVidaBase = new Text(base.getVida() + " / 250");
        textoVidaBase.setX(1170);
        textoVidaBase.setY(45);
        textoVidaBase.setFill(Color.WHITE);
        textoVidaBase.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Rectangle barraCinza = new Rectangle(210, 12);
        barraCinza.setX(1020);
        barraCinza.setY(65);
        barraCinza.setFill(Color.web("#4B5563"));
        barraCinza.setArcWidth(20);
        barraCinza.setArcHeight(20);

        barraVerde = new Rectangle(210, 12);
        barraVerde.setX(1020);
        barraVerde.setY(65);
        barraVerde.setFill(Color.web("#22C55E"));
        barraVerde.setArcWidth(20);
        barraVerde.setArcHeight(20);

        FXGL.getGameScene().addUINode(fundoHUD);
        FXGL.getGameScene().addUINode(tituloHUD);
        FXGL.getGameScene().addUINode(textoVidaBase);
        FXGL.getGameScene().addUINode(barraCinza);
        FXGL.getGameScene().addUINode(barraVerde);

        animacaoDano = new FillTransition(
            Duration.millis(130),
            fundoHUD
        );

        animacaoDano.setFromValue(Color.web("#17212B"));
        animacaoDano.setToValue(Color.web("#A12E3C"));
        animacaoDano.setCycleCount(2);
        animacaoDano.setAutoReverse(true);

        alertaVida = new FadeTransition(
            Duration.millis(550),
            barraVerde
        );

        alertaVida.setFromValue(1.0);
        alertaVida.setToValue(0.35);
        alertaVida.setCycleCount(Animation.INDEFINITE);
        alertaVida.setAutoReverse(true);
    }

    public Text getTextoVidaBase() {
        return textoVidaBase;
    }

    public Rectangle getBarraVerde() {
        return barraVerde;
    }

    public void atualizarVida(int vida, boolean recebeuDano) {

        int vidaAtual = Math.max(0, Math.min(250, vida));

        textoVidaBase.setText(vidaAtual + " / 250");
        barraVerde.setWidth(210.0 * vidaAtual / 250.0);

        if (vidaAtual <= 63) {

            barraVerde.setFill(Color.web("#EF4444"));
            textoVidaBase.setFill(Color.web("#FF7B7B"));

            if (vidaAtual > 0 &&
                alertaVida.getStatus() != Animation.Status.RUNNING) {

                alertaVida.play();
            }
        }
        else if (vidaAtual <= 125) {

            barraVerde.setFill(Color.web("#FACC15"));
            textoVidaBase.setFill(Color.web("#FACC15"));
        }
        else {

            barraVerde.setFill(Color.web("#22C55E"));
            textoVidaBase.setFill(Color.WHITE);
        }

        if (vidaAtual > 63 || vidaAtual == 0) {
            alertaVida.stop();
            barraVerde.setOpacity(1.0);
        }

        if (recebeuDano) {
            animacaoDano.playFromStart();
        }
        else {
            animacaoDano.stop();
            fundoHUD.setFill(Color.web("#17212B"));
        }
    }

}
