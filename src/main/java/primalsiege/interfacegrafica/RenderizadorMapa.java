package primalsiege.interfacegrafica;
import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                if (matriz[i][j] == 1) {
                    Rectangle celula = new Rectangle(
                        tamanhoCelula,
                        tamanhoCelula,
                        Color.SANDYBROWN
                    );

                    celula.setX(j * tamanhoCelula);
                    celula.setY(i * tamanhoCelula);

                    FXGL.getGameScene().addUINode(celula);
                }
            }
        }
    }
}
