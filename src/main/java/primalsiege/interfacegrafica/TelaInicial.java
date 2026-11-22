
package primalsiege.interfacegrafica;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class TelaInicial extends Group {
    private Rectangle botaoJogar;
    private Rectangle botaoOpcoes;
    private Rectangle botaoComoJogar;
    private Rectangle botaoSair;

    public TelaInicial() {

        Image imagemFundo = new Image(
            getClass().getResource(
                "/assets/textures/fundo_menu.jpg"
            ).toExternalForm()
        );

        ImageView fundo = new ImageView(imagemFundo);

        fundo.setFitWidth(1280);
        fundo.setFitHeight(720);
        fundo.setPreserveRatio(false);
        fundo.setMouseTransparent(true);

        getChildren().add(fundo);

        botaoJogar = criarBotao("JOGAR", 340, true);
        botaoOpcoes = criarBotao("OPÇÕES", 415, false);
        botaoComoJogar = criarBotao("COMO JOGAR", 490, false);
        botaoSair = criarBotao("SAIR", 565, false);
    }

    private Rectangle criarBotao(String nome, double y, boolean principal) {

        Rectangle botao = new Rectangle(475, y, 330, 58);

        botao.setArcWidth(20);
        botao.setArcHeight(20);

        if (principal) {
            botao.setFill(new LinearGradient(
                0, 0, 0, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#A92C43")),
                new Stop(1, Color.web("#651A2D"))
            ));

            botao.setStroke(Color.web("#FF405C"));
        }
        else {
            botao.setFill(Color.web("#1D3041"));
            botao.setStroke(Color.web("#5D7C92"));
        }

        botao.setStrokeWidth(2);
        botao.setCursor(Cursor.HAND);

        Text texto = new Text(nome);
        texto.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        texto.setFill(Color.WHITE);
        texto.setMouseTransparent(true);

        SVGPath icone = criarIcone(nome);

        double larguraTexto = texto.getLayoutBounds().getWidth();

        double larguraConjunto = 24 + 14 + larguraTexto;

        double inicioX = 640 - larguraConjunto / 2;

        icone.setLayoutX(inicioX);
        icone.setLayoutY(y + 17);

        texto.setX(inicioX + 38);
        texto.setY(y + 37);

        botao.setOnMouseEntered(evento -> {
            botao.setStrokeWidth(3);
            botao.setEffect(new DropShadow(16, Color.web("#FF405C")));
        });

        botao.setOnMouseExited(evento -> {
            botao.setStrokeWidth(2);
            botao.setEffect(null);
        });

        getChildren().addAll(botao, icone, texto);

        return botao;
    }

    private SVGPath criarIcone(String nome) {

        SVGPath icone = new SVGPath();

        switch (nome) {

            case "JOGAR":
                icone.setContent(
                    "M8 5 L19 12 L8 19 Z"
                );
                break;

            case "OPÇÕES":
                icone.setContent(
                    "M10 2 L14 2 L14.6 4.3 L16.5 5.1 " +
                    "L18.6 3.9 L21.1 6.4 L19.9 8.5 " +
                    "L20.7 10.4 L23 11 L23 13 " +
                    "L20.7 13.6 L19.9 15.5 L21.1 17.6 " +
                    "L18.6 20.1 L16.5 18.9 L14.6 19.7 " +
                    "L14 22 L10 22 L9.4 19.7 " +
                    "L7.5 18.9 L5.4 20.1 L2.9 17.6 " +
                    "L4.1 15.5 L3.3 13.6 L1 13 " +
                    "L1 11 L3.3 10.4 L4.1 8.5 " +
                    "L2.9 6.4 L5.4 3.9 L7.5 5.1 " +
                    "L9.4 4.3 Z " +
                    "M12 9 A3 3 0 1 0 12 15 " +
                    "A3 3 0 1 0 12 9"
                );
                break;

            case "COMO JOGAR":
                icone.setContent(
                    "M12 7 C9 5 6 4 2 5 " +
                    "L2 20 C6 19 9 20 12 22 " +
                    "C15 20 18 19 22 20 " +
                    "L22 5 C18 4 15 5 12 7 Z " +
                    "M12 7 L12 22"
                );
                break;

            case "SAIR":
                icone.setContent(
                    "M10 4 H5 Q3 4 3 6 V18 " +
                    "Q3 20 5 20 H10 " +
                    "M14 7 L19 12 L14 17 " +
                    "M19 12 H9"
                );
                break;
        }

        icone.setFill(Color.TRANSPARENT);
        icone.setStroke(Color.WHITE);
        icone.setStrokeWidth(2.2);
        icone.setStrokeLineCap(StrokeLineCap.ROUND);
        icone.setStrokeLineJoin(StrokeLineJoin.ROUND);
        icone.setMouseTransparent(true);

        return icone;
    }

    public Rectangle getBotaoJogar() {
        return botaoJogar;
    }

    public Rectangle getBotaoOpcoes() {
        return botaoOpcoes;
    }

    public Rectangle getBotaoComoJogar() {
        return botaoComoJogar;
    }

    public Rectangle getBotaoSair() {
        return botaoSair;
    }
}
