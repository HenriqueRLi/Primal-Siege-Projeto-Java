
package primalsiege.interfacegrafica;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class HUDOnda extends Group {
    private Rectangle botaoIniciarOnda;
    private Text  quantOuro;
    private Text textoNumeroOnda;
    private boolean recolhida = false;
    private SVGPath setaAba;

    public HUDOnda() {

        Rectangle retanguloMaiorOnda = new Rectangle(840,105);
        retanguloMaiorOnda.setX(220);
        retanguloMaiorOnda.setY(615);
        retanguloMaiorOnda.setFill(Color.web("#17212B"));
        retanguloMaiorOnda.setArcWidth(25);
        retanguloMaiorOnda.setArcHeight(25);

        Rectangle retanguloContOndas = new Rectangle(170,65);
        retanguloContOndas.setX(250);
        retanguloContOndas.setY(635);
        retanguloContOndas.setFill(Color.web("#202D3A"));
        retanguloContOndas.setStroke(Color.web("#FF4053"));
        retanguloContOndas.setStrokeWidth(2);
        retanguloContOndas.setArcWidth(25);
        retanguloContOndas.setArcHeight(25);

        Text textoOnda = new Text("ONDA");
        textoOnda.setX(280);
        textoOnda.setY(657);
        textoOnda.setStyle("-fx-font-family: Arial; -fx-font-size: 13px; -fx-font-weight: bold;");
        textoOnda.setFill(Color.WHITE);

        textoNumeroOnda = new Text("1/10");
        textoNumeroOnda.setX(280);
        textoNumeroOnda.setY(687);
        textoNumeroOnda.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-font-weight: bold;");
        textoNumeroOnda.setFill(Color.WHITE);

        botaoIniciarOnda = new Rectangle(300,65);
        botaoIniciarOnda.setX(490);
        botaoIniciarOnda.setY(635);
        botaoIniciarOnda.setFill(Color.web("#76232D"));
        botaoIniciarOnda.setStroke(Color.web("#FF4053"));
        botaoIniciarOnda.setStrokeWidth(3);
        botaoIniciarOnda.setArcWidth(25);
        botaoIniciarOnda.setArcHeight(25);

        SVGPath simboloPlay = new SVGPath();
        simboloPlay.setContent("M8 5 L19 12 L8 19 Z");
        simboloPlay.setFill(Color.TRANSPARENT);
        simboloPlay.setStroke(Color.WHITE);
        simboloPlay.setStrokeWidth(2.5);
        simboloPlay.setLayoutX(520);
        simboloPlay.setLayoutY(655);

        Text textoIniciarOnda = new Text("INICIAR ONDA");
        textoIniciarOnda.setX(565);
        textoIniciarOnda.setY(675);
        textoIniciarOnda.setFill(Color.WHITE);
        textoIniciarOnda.setStyle("-fx-font-family: Arial; -fx-font-size: 21px; -fx-font-weight: bold;");

        Rectangle retanguloOuro = new Rectangle(170,65);
        retanguloOuro.setX(860);
        retanguloOuro.setY(635);
        retanguloOuro.setFill(Color.web("#202D3A"));
        retanguloOuro.setStroke(Color.web("#D4A63A"));
        retanguloOuro.setStrokeWidth(2);
        retanguloOuro.setArcWidth(25);
        retanguloOuro.setArcHeight(25);

        Text textoOuro = new Text("OURO");
        textoOuro.setX(890);
        textoOuro.setY(657);
        textoOuro.setFill(Color.WHITE);
        textoOuro.setStyle("-fx-font-family: Arial; -fx-font-size: 13px; -fx-font-weight: bold;");

        quantOuro = new Text("300");
        quantOuro.setX(890);
        quantOuro.setY(687);
        quantOuro.setFill(Color.web("#FACC15"));
        quantOuro.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-font-weight: bold;");

        textoIniciarOnda.setMouseTransparent(true);
        simboloPlay.setMouseTransparent(true);

        botaoIniciarOnda.setCursor(Cursor.HAND);
        botaoIniciarOnda.setOnMouseEntered(evento -> botaoIniciarOnda.setStrokeWidth(5));
        botaoIniciarOnda.setOnMouseExited(evento -> botaoIniciarOnda.setStrokeWidth(3));

        getChildren().addAll(
            retanguloMaiorOnda,
            retanguloContOndas,
            textoOnda,
            textoNumeroOnda,
            botaoIniciarOnda,
            simboloPlay,
            textoIniciarOnda,
            retanguloOuro,
            textoOuro,
            quantOuro
        );

        Rectangle aba = new Rectangle(595, 585, 90, 30);

        aba.setFill(Color.web("#17212B"));
        aba.setStroke(Color.web("#FF4053"));
        aba.setStrokeWidth(2);
        aba.setArcWidth(14);
        aba.setArcHeight(14);
        aba.setCursor(Cursor.HAND);

        setaAba = new SVGPath();
        setaAba.setContent("M2 4 L12 13 L22 4");
        setaAba.setFill(Color.TRANSPARENT);
        setaAba.setStroke(Color.WHITE);
        setaAba.setStrokeWidth(3);
        setaAba.setLayoutX(628);
        setaAba.setLayoutY(589);
        setaAba.setMouseTransparent(true);

        aba.setOnMouseClicked(evento -> alternarRecolhimento());

        getChildren().addAll(aba, setaAba);

        FXGL.getGameScene().addUINode(this);
    }

    public boolean estaExpandida() {
        return !recolhida;
    }

    public void alternarRecolhimento() {

        recolhida = !recolhida;

        TranslateTransition animacao = new TranslateTransition(
            Duration.millis(300),
            this
        );

        if (recolhida) {
            animacao.setToY(105);
            setaAba.setRotate(180);
        }
        else {
            animacao.setToY(0);
            setaAba.setRotate(0);
        }

        animacao.play();
    }

    public void atualizarNumeroOnda(int numero) {
        textoNumeroOnda.setText(numero + "/10");
    }
    
    public void atualizarOuro(int quantidade) {
        quantOuro.setText(String.valueOf(quantidade));
    }

    public Rectangle getBotaoIniciarOnda() {
        return botaoIniciarOnda;
    }
}
