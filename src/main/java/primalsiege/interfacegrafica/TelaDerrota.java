package primalsiege.interfacegrafica;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

public class TelaDerrota extends Group {
    private Rectangle botaoReiniciar;
    private Rectangle botaoSair;

    public TelaDerrota() {
        
        Rectangle retanguloFundo = new Rectangle(1280,720);
        retanguloFundo.setX(0);
        retanguloFundo.setY(0);
        retanguloFundo.setFill(Color.rgb(0, 0, 0, 0.65));

        Rectangle retanguloDerrota = new Rectangle(570,300);
        retanguloDerrota.setX(355);
        retanguloDerrota.setY(210);
        retanguloDerrota.setFill(new LinearGradient(
            0, 0, 1, 1,
            true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#263544")),
            new Stop(0.5, Color.web("#17212B")),
            new Stop(1, Color.web("#101720"))
        ));
        retanguloDerrota.setStroke(Color.web("#52677A"));
        retanguloDerrota.setStrokeWidth(1);
        retanguloDerrota.setArcWidth(20);
        retanguloDerrota.setArcHeight(20);

        Text textoDerrota = new Text("DERROTA");
        textoDerrota.setX(500);
        textoDerrota.setY(345);
        textoDerrota.setFill(Color.web("#FF202D"));
        textoDerrota.setStyle("-fx-font-family: Arial; -fx-font-size: 54px; -fx-font-weight: bold;");
        DropShadow brilho = new DropShadow();
        brilho.setColor(Color.web("#8a8888"));
        brilho.setRadius(5);
        brilho.setSpread(0.2);
        textoDerrota.setEffect(brilho);

        Circle contornoBola = new Circle(640, 260, 24);
        contornoBola.setFill(Color.TRANSPARENT);
        contornoBola.setStroke(Color.web("#FF4D5A"));
        contornoBola.setStrokeWidth(4);

        Line linhaBola = new Line(616, 260, 664, 260);
        linhaBola.setStroke(Color.web("#FF4D5A"));
        linhaBola.setStrokeWidth(4);

        Circle centroBola = new Circle(640, 260, 8);
        centroBola.setFill(Color.web("#17212B"));
        centroBola.setStroke(Color.web("#FF4D5A"));
        centroBola.setStrokeWidth(4);

        Line linhaEsquerda = new Line(525, 260, 600, 260);
        Line linhaDireita = new Line(680, 260, 755, 260);
        linhaEsquerda.setStroke(Color.web("#A94F59"));
        linhaDireita.setStroke(Color.web("#A94F59"));
        linhaEsquerda.setStrokeWidth(2);
        linhaDireita.setStrokeWidth(2);

        Text subtituloDerrota = new Text("A base foi destruída!");
        subtituloDerrota.setX(545);
        subtituloDerrota.setY(380);
        subtituloDerrota.setFill(Color.web("#B8C4D0"));
        subtituloDerrota.setStyle("-fx-font-family: Arial; -fx-font-size: 18px;");

        botaoReiniciar = new Rectangle(230, 52);
        botaoReiniciar.setX(390);
        botaoReiniciar.setY(430);
        botaoReiniciar.setArcWidth(20);
        botaoReiniciar.setArcHeight(20);
        botaoReiniciar.setFill(Color.web("#293847"));
        botaoReiniciar.setStroke(Color.web("#41566A"));
        botaoReiniciar.setStrokeWidth(2);

        Text textoReiniciar = new Text("Tentar Novamente");
        textoReiniciar.setX(438);
        textoReiniciar.setY(463);
        textoReiniciar.setFill(Color.WHITE);
        textoReiniciar.setStyle("-fx-font-family: Arial; -fx-font-size: 17px; -fx-font-weight: bold;");

        SVGPath simboloReiniciar = new SVGPath();
        simboloReiniciar.setContent(
            "M3 12 A9 9 0 0 1 18.36 5.64 L21 8 " +
            "M21 3 V8 H16 " +
            "M21 12 A9 9 0 0 1 5.64 18.36 L3 16 " +
            "M3 21 V16 H8"
        );
        simboloReiniciar.setFill(Color.TRANSPARENT);
        simboloReiniciar.setStroke(Color.WHITE);
        simboloReiniciar.setStrokeWidth(2.5);
        simboloReiniciar.setLayoutX(402);
        simboloReiniciar.setLayoutY(444);

        botaoSair = new Rectangle(230, 52);
        botaoSair.setX(660);
        botaoSair.setY(430);
        botaoSair.setArcWidth(20);
        botaoSair.setArcHeight(20);
        botaoSair.setFill(Color.web("#111B25"));
        botaoSair.setStroke(Color.web("#AAB8C7"));
        botaoSair.setStrokeWidth(2);

        Text textoSair = new Text("Sair do Jogo");
        textoSair.setX(720);
        textoSair.setY(463);
        textoSair.setFill(Color.WHITE);
        textoSair.setStyle("-fx-font-family: Arial; -fx-font-size: 17px; -fx-font-weight: bold;");

        SVGPath simboloSair = new SVGPath();
        simboloSair.setContent(
            "M9 21 H5 A2 2 0 0 1 3 19 V5 A2 2 0 0 1 5 3 H9 " +
            "M16 17 L21 12 L16 7 " +
            "M21 12 H9"
        );
        simboloSair.setFill(Color.TRANSPARENT);
        simboloSair.setStroke(Color.WHITE);
        simboloSair.setStrokeWidth(2.5);
        simboloSair.setLayoutX(682);
        simboloSair.setLayoutY(444);

        textoSair.setMouseTransparent(true);
        simboloSair.setMouseTransparent(true);
        textoReiniciar.setMouseTransparent(true);
        simboloReiniciar.setMouseTransparent(true);

        botaoReiniciar.setCursor(Cursor.HAND);
        botaoReiniciar.setOnMouseEntered(evento -> botaoReiniciar.setStrokeWidth(3));
        botaoReiniciar.setOnMouseExited(evento -> botaoReiniciar.setStrokeWidth(2));
        botaoSair.setCursor(Cursor.HAND);
        botaoSair.setOnMouseEntered(evento -> botaoSair.setStrokeWidth(3));
        botaoSair.setOnMouseExited(evento -> botaoSair.setStrokeWidth(2));

        getChildren().addAll(
            retanguloFundo,
            retanguloDerrota,
            textoDerrota,
            contornoBola,
            linhaBola,
            centroBola,
            linhaEsquerda,
            linhaDireita,
            subtituloDerrota,
            botaoReiniciar,
            textoReiniciar,
            simboloReiniciar,
            botaoSair,
            simboloSair,
            textoSair
        );

    }

    public Rectangle getBotaoReiniciar() {
        return botaoReiniciar;
    }

    public Rectangle getBotaoSair() {
        return botaoSair;
    }
}
