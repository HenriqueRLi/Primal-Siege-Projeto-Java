
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;



public class TesteFXGL extends GameApplication {
    private static final int TAMANHO_CELULA = 40;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private Inimigo inimigo;
    private Mapa mapa;
    private Circle circuloInimigo;
    private boolean inimigoChegou = false;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(LARGURA);
        settings.setHeight(ALTURA);
        settings.setTitle("Primal Siege");
    }


@Override
protected void initGame() {
    FXGL.getGameScene().setBackgroundColor(Color.DARKGREEN);

    mapa = new Mapa(1180, 660);

    desenharCaminho(mapa.getMatriz());  
    criarGrid();
    numerarCelulas();

    inimigo = new Inimigo(100, 80, 1, "Rattata", mapa);
    circuloInimigo = new Circle(inimigo.getX(), inimigo.getY(), 12);
    circuloInimigo.setFill(Color.PURPLE);
    FXGL.getGameScene().addUINode(circuloInimigo);
}
    private void criarGrid() {

        // Linhas verticais
        for (int x = 0; x <= LARGURA; x += TAMANHO_CELULA) {

            Line linha = new Line(x, 0, x, ALTURA);
            linha.setStroke(Color.GRAY);

            FXGL.getGameScene().addUINode(linha);
        }

        // Linhas horizontais
        for (int y = 0; y <= ALTURA; y += TAMANHO_CELULA) {

            Line linha = new Line(0, y, LARGURA, y);
            linha.setStroke(Color.GRAY);

            FXGL.getGameScene().addUINode(linha);
        }
    }

    private void numerarCelulas() {

        int numero = 0;

        for (int y = 0; y < ALTURA; y += TAMANHO_CELULA) {
            for (int x = 0; x < LARGURA; x += TAMANHO_CELULA) {

                Text texto = new Text(String.valueOf(numero));

                texto.setFill(Color.WHITE);

                // Centraliza o número dentro da célula
                texto.setX(x + 12);
                texto.setY(y + 25);

                FXGL.getGameScene().addUINode(texto);

                numero++;
            }
        }
    }

    private void desenharTorre(Torre torre) {

    Circle bola = new Circle(
        torre.getX(),
        torre.getY(),
        15
    );
        bola.setFill(Color.RED);

        FXGL.getGameScene().addUINode(bola);
    }
   
@Override
protected void initInput() {

    FXGL.getInput().addAction(new UserAction("Colocar Torre") {

        @Override
        protected void onActionBegin() {

            double mouseX = FXGL.getInput().getMouseXWorld();
            double mouseY = FXGL.getInput().getMouseYWorld();

            int coluna = (int) (mouseX / 40);
            int linha = (int) (mouseY / 40);

            Torre torre = new Torre(10, 100, 0, 0, 1.0, 50);

            if (mapa.colocarTorre(torre, linha, coluna)) {
                desenharTorre(torre);
            }
        }

    }, MouseButton.PRIMARY);
}


    private void desenharCaminho(int[][] matriz) {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 32; j++) {
                if (matriz[i][j] == 1) {
                    Rectangle celula = new Rectangle(40, 40, Color.SANDYBROWN);

                    celula.setX(j * 40);
                    celula.setY(i * 40);

                    FXGL.getGameScene().addUINode(celula);
                }
            }
        } 
    }

    @Override 
    protected void onUpdate (double tpf) {
        if(inimigoChegou == false) {
            if(inimigoChegou = inimigo.mover(tpf)) {
                FXGL.getGameScene().removeUINode(circuloInimigo);
            }
            circuloInimigo.setCenterX(inimigo.getX());
            circuloInimigo.setCenterY(inimigo.getY());
        }      
    }

    public static void main(String[] args) {
        launch(args);
    }
}
