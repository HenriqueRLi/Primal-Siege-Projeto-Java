package primalsiege.interfacegrafica;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TelaVitoria extends Group {
    private Rectangle botaoJogar;
    private Rectangle botaoSairVitoria;

    public TelaVitoria() {
        
        Rectangle fundoEscuro = new Rectangle(1280, 720);
        fundoEscuro.setFill(Color.rgb(0, 0, 0, 0.70));

        Rectangle painel = new Rectangle(315, 180, 650, 370);

        painel.setFill(new LinearGradient(
            0, 0, 1, 1,
            true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#2B4050")),
            new Stop(0.45, Color.web("#142632")),
            new Stop(1, Color.web("#0C1622"))
        ));

        painel.setStroke(Color.web("#688DA2"));
        painel.setStrokeWidth(2);
        painel.setArcWidth(30);
        painel.setArcHeight(30);

        Polygon reflexo = new Polygon(
            317.0, 182.0,
            465.0, 182.0,
            345.0, 275.0,
            317.0, 275.0
        );

        reflexo.setFill(Color.rgb(255, 255, 255, 0.07));
        reflexo.setMouseTransparent(true);

        Line brilhoSuperior = new Line(455, 181, 825, 181);
        brilhoSuperior.setStroke(Color.web("#70C8D8"));
        brilhoSuperior.setStrokeWidth(2);
        brilhoSuperior.setEffect(
            new DropShadow(16, Color.web("#47DDF0"))
        );

        Line linhaEsquerda = new Line(480, 258, 548, 258);
        Line linhaDireita = new Line(732, 258, 800, 258);

        linhaEsquerda.setStroke(Color.web("#FFD34D"));
        linhaDireita.setStroke(Color.web("#FFD34D"));

        linhaEsquerda.setStrokeWidth(2);
        linhaDireita.setStrokeWidth(2);

        Group folhas = new Group();

        double[][] posicoesFolhas = {
            {601, 281, -48},
            {592, 270, -42},
            {586, 258, -30},
            {585, 245, -15},
            {589, 232, 10},
            {598, 222, 30}
        };

        for (double[] posicao : posicoesFolhas) {

            double x = posicao[0];
            double y = posicao[1];
            double rotacao = posicao[2];

            Ellipse folhaEsquerda = new Ellipse(x, y, 4, 10);
            folhaEsquerda.setRotate(rotacao);
            folhaEsquerda.setFill(Color.web("#FFD34D"));

            Ellipse folhaDireita = new Ellipse(1280 - x, y, 4, 10);
            folhaDireita.setRotate(-rotacao);
            folhaDireita.setFill(Color.web("#FFD34D"));

            folhas.getChildren().addAll(
                folhaEsquerda,
                folhaDireita
            );
        }

        folhas.setEffect(new DropShadow(7, Color.web("#B98117")));

        Arc metadeVermelha = new Arc(640, 257, 24, 24, 0, 180);
        metadeVermelha.setType(ArcType.ROUND);
        metadeVermelha.setFill(Color.web("#FF5662"));

        Arc metadeBranca = new Arc(640, 257, 24, 24, 180, 180);
        metadeBranca.setType(ArcType.ROUND);
        metadeBranca.setFill(Color.web("#EAF3F5"));

        Circle bordaBola = new Circle(640, 257, 24);
        bordaBola.setFill(Color.TRANSPARENT);
        bordaBola.setStroke(Color.web("#101B27"));
        bordaBola.setStrokeWidth(3);

        Line divisaoBola = new Line(616, 257, 664, 257);
        divisaoBola.setStroke(Color.web("#101B27"));
        divisaoBola.setStrokeWidth(5);

        Circle centroExterno = new Circle(640, 257, 9);
        centroExterno.setFill(Color.web("#101B27"));

        Circle centroInterno = new Circle(640, 257, 5);
        centroInterno.setFill(Color.WHITE);

        Group pokebola = new Group(
            metadeVermelha,
            metadeBranca,
            bordaBola,
            divisaoBola,
            centroExterno,
            centroInterno
        );

        pokebola.setEffect(new DropShadow(12, Color.web("#FFD34D")));

        Group estrelas = new Group();

        double[][] posicoesEstrelas = {
            {640, 204, 14},
            {545, 230, 9},
            {735, 230, 9}
        };

        for (double[] estrela : posicoesEstrelas) {

            double x = estrela[0];
            double y = estrela[1];
            double tamanho = estrela[2];

            Polygon brilho = new Polygon(
                x, y - tamanho,
                x + tamanho * 0.22, y - tamanho * 0.22,
                x + tamanho, y,
                x + tamanho * 0.22, y + tamanho * 0.22,
                x, y + tamanho,
                x - tamanho * 0.22, y + tamanho * 0.22,
                x - tamanho, y,
                x - tamanho * 0.22, y - tamanho * 0.22
            );

            brilho.setFill(Color.web("#FFE16B"));
            estrelas.getChildren().add(brilho);
        }

        estrelas.setEffect(new DropShadow(14, Color.web("#FFCB37")));

        QuadCurve curvaEsquerda = new QuadCurve(
            331, 413,
            435, 487,
            555, 444
        );

        curvaEsquerda.setFill(Color.TRANSPARENT);
        curvaEsquerda.setStroke(Color.web("#20EFA3"));
        curvaEsquerda.setStrokeWidth(3);

        curvaEsquerda.setEffect(new DropShadow(18, Color.web("#11E9A3")));

        QuadCurve curvaDireita = new QuadCurve(
            949, 413,
            845, 487,
            725, 444
        );

        curvaDireita.setFill(Color.TRANSPARENT);
        curvaDireita.setStroke(Color.web("#B4FF60"));
        curvaDireita.setStrokeWidth(3);

        curvaDireita.setEffect(new DropShadow(18, Color.web("#24EFA3")));

        Text tituloVitoria = new Text("VITÓRIA!");

        tituloVitoria.setFont(Font.font("Arial", FontWeight.BOLD, 60));

        tituloVitoria.setFill(Color.web("#69FFD0"));

        tituloVitoria.setX(640 - tituloVitoria.getLayoutBounds().getWidth() / 2);
        tituloVitoria.setY(376);

        tituloVitoria.setEffect(new DropShadow(24, Color.web("#00D59D")));

        Text subtitulo = new Text("Você defendeu a base com sucesso!");

        subtitulo.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        subtitulo.setFill(Color.web("#DCE8EF"));

        subtitulo.setX(640 - subtitulo.getLayoutBounds().getWidth() / 2);
        subtitulo.setY(416);

        botaoJogar = new Rectangle(340, 467, 290, 62);

        botaoJogar.setArcWidth(24);
        botaoJogar.setArcHeight(24);

        botaoJogar.setFill(new LinearGradient(
            0, 0, 0, 1,
            true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#196B52")),
            new Stop(1, Color.web("#10372F"))
        ));

        botaoJogar.setStroke(Color.web("#1DFF9A"));
        botaoJogar.setStrokeWidth(2);

        botaoJogar.setEffect(new DropShadow(14, Color.web("#00C985")));

        Text textoJogar = new Text("JOGAR NOVAMENTE");
            textoJogar.setX(394);
            textoJogar.setY(506);
            textoJogar.setFill(Color.WHITE);
            textoJogar.setFont(Font.font("Arial", FontWeight.BOLD, 17)
        );

        SVGPath iconeJogar = new SVGPath();

        iconeJogar.setContent(
            "M3 12 A9 9 0 0 1 18.36 5.64 L21 8 " +
            "M21 3 V8 H16 " +
            "M21 12 A9 9 0 0 1 5.64 18.36 L3 16 " +
            "M3 21 V16 H8"
        );

        iconeJogar.setFill(Color.TRANSPARENT);
        iconeJogar.setStroke(Color.WHITE);
        iconeJogar.setStrokeWidth(2.5);
        iconeJogar.setLayoutX(357);
        iconeJogar.setLayoutY(484);

        botaoSairVitoria = new Rectangle(650, 467, 290, 62);

        botaoSairVitoria.setArcWidth(24);
        botaoSairVitoria.setArcHeight(24);
        botaoSairVitoria.setFill(Color.web("#101B28"));
        botaoSairVitoria.setStroke(Color.web("#C7DAE8"));
        botaoSairVitoria.setStrokeWidth(2);

        Text textoSairVitoria = new Text("SAIR DO JOGO");
            textoSairVitoria.setX(722);
            textoSairVitoria.setY(506);
            textoSairVitoria.setFill(Color.WHITE);
            textoSairVitoria.setFont(
            Font.font("Arial", FontWeight.BOLD, 17)
        );

        SVGPath iconeSairVitoria = new SVGPath();

        iconeSairVitoria.setContent(
            "M9 21 H5 A2 2 0 0 1 3 19 V5 A2 2 0 0 1 5 3 H9 " +
            "M16 17 L21 12 L16 7 " +
            "M21 12 H9"
        );

        iconeSairVitoria.setFill(Color.TRANSPARENT);
        iconeSairVitoria.setStroke(Color.WHITE);
        iconeSairVitoria.setStrokeWidth(2.5);
        iconeSairVitoria.setLayoutX(672);
        iconeSairVitoria.setLayoutY(484);


        // PERMITIR CLIQUES NOS RETÂNGULOS
        textoJogar.setMouseTransparent(true);
        iconeJogar.setMouseTransparent(true);

        textoSairVitoria.setMouseTransparent(true);
        iconeSairVitoria.setMouseTransparent(true);

        botaoJogar.setOnMouseEntered(evento -> botaoJogar.setStrokeWidth(3));
        botaoJogar.setOnMouseExited(evento -> botaoJogar.setStrokeWidth(2));
        botaoSairVitoria.setOnMouseEntered(evento -> botaoSairVitoria.setStrokeWidth(3));
        botaoSairVitoria.setOnMouseExited(evento -> botaoSairVitoria.setStrokeWidth(2));

        getChildren().addAll(
            fundoEscuro,
            painel,
            reflexo,
            brilhoSuperior,
            curvaEsquerda,
            curvaDireita,
            linhaEsquerda,
            linhaDireita,
            folhas,
            pokebola,
            estrelas,
            tituloVitoria,
            subtitulo,
            botaoJogar,
            textoJogar,
            iconeJogar,
            botaoSairVitoria,
            textoSairVitoria,
            iconeSairVitoria
        );
        botaoJogar.setCursor(Cursor.HAND);
        botaoSairVitoria.setCursor(Cursor.HAND);
    }

    public Rectangle getBotaoJogar() {
        return botaoJogar;
    }

    public Rectangle getBotaoSairVitoria() {
        return botaoSairVitoria;
    }
}
