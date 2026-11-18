package primalsiege.interfacegrafica;
import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class RenderizadorMapa {
    private int largura;
    private int altura;
    private int tamanhoCelula;

    public RenderizadorMapa(int largura, int altura, int tamanhoCelula) {
        this.largura = largura;
        this.altura = altura;
        this.tamanhoCelula = tamanhoCelula;
    }

    public void criarGrid() {
        for (int x = 0; x <= largura; x += tamanhoCelula) {
            Line linha = new Line(x, 0, x, altura);
            linha.setStroke(Color.GRAY);

            FXGL.getGameScene().addUINode(linha);
        }

        for (int y = 0; y <= altura; y += tamanhoCelula) {
            Line linha = new Line(0, y, largura, y);
            linha.setStroke(Color.GRAY);

            FXGL.getGameScene().addUINode(linha);
        }
    }

    public void numerarCelulas() {
        int numero = 0;

        for (int y = 0; y < altura; y += tamanhoCelula) {
            for (int x = 0; x < largura; x += tamanhoCelula) {

                Text texto = new Text(String.valueOf(numero));
                texto.setFill(Color.WHITE);

                texto.setX(x + 12);
                texto.setY(y + 25);

                FXGL.getGameScene().addUINode(texto);

                numero++;
            }
        }
    }

    public void desenharCaminho(int[][] matriz) {
        desenharGramadoPixel();
        desenharCaminhoPixel(matriz);
    }

    private void desenharCaminhoPixel(int[][] matriz) {

        Image[] tiles = new Image[16];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Image(
                getClass().getResource(
                    "/assets/textures/caminho_" + i + ".png"
                ).toExternalForm()
            );
        }

        Canvas caminho = new Canvas(largura, altura);
        GraphicsContext pincel = caminho.getGraphicsContext2D();

        pincel.setImageSmoothing(false);

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                if (matriz[i][j] != 1) {
                    continue;
                }

                int mascara = 0;

                if (i == 0 || matriz[i - 1][j] != 1) {
                    mascara += 1;
                }

                if (j == matriz[i].length - 1 ||
                    matriz[i][j + 1] != 1) {
                    mascara += 2;
                }

                if (i == matriz.length - 1 ||
                    matriz[i + 1][j] != 1) {
                    mascara += 4;
                }

                if (j == 0 || matriz[i][j - 1] != 1) {
                    mascara += 8;
                }

                pincel.drawImage(
                    tiles[mascara],
                    j * tamanhoCelula,
                    i * tamanhoCelula,
                    tamanhoCelula,
                    tamanhoCelula
                );
            }
        }

        caminho.setMouseTransparent(true);
        FXGL.getGameScene().addUINode(caminho);
    }

    private void desenharGramadoPixel() {

        Image[] gramas = new Image[4];

        for (int i = 0; i < gramas.length; i++) {
            gramas[i] = new Image(
                getClass().getResource(
                    "/assets/textures/grama_" + (i + 1) + ".png"
                ).toExternalForm()
            );
        }

        Canvas gramado = new Canvas(largura, altura);
        GraphicsContext pincel = gramado.getGraphicsContext2D();

        pincel.setImageSmoothing(false);

        Random random = new Random(42);

        for (int y = 0; y < altura; y += tamanhoCelula) {
            for (int x = 0; x < largura; x += tamanhoCelula) {

                Image grama = gramas[random.nextInt(gramas.length)];

                pincel.drawImage(
                    grama, x, y, tamanhoCelula, tamanhoCelula
                );
            }
        }

        gramado.setMouseTransparent(true);
        FXGL.getGameScene().addUINode(gramado);
    }

    private void desenharArvores(int[][] matriz) {

        Image arvore = new Image(
            getClass().getResource("/assets/textures/arvore.png").toExternalForm()
        );

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                if (matriz[i][j] == 4) {

                    ImageView sprite = new ImageView(arvore);

                    sprite.setFitWidth(tamanhoCelula);
                    sprite.setFitHeight(tamanhoCelula);
                    sprite.setLayoutX(j * tamanhoCelula);
                    sprite.setLayoutY(i * tamanhoCelula);
                    sprite.setMouseTransparent(true);

                    FXGL.getGameScene().addUINode(sprite);
                }
            }
        }
    }

}
