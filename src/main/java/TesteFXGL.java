import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
<<<<<<< HEAD
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
=======
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
>>>>>>> teste
import javafx.scene.text.Text;



public class TesteFXGL extends GameApplication {
    private static final int TAMANHO_CELULA = 40;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private Inimigo inimigo;
    private Mapa mapa;
    private Circle circuloInimigo;
    private boolean inimigoChegou = false;
    private Base base;
    private Text textoVidaBase;
    private Rectangle barraVerde;
    private ArrayList<Torre> torres = new ArrayList<>();
    private ArrayList<Polygon> triangulosTorres = new ArrayList<>();
    private ArrayList<Projetil> projeteis = new ArrayList<>();
    private ArrayList<Circle> circulosProjeteis = new ArrayList<>();
    private Group hudDerrota;

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
    base = new Base();

    desenharCaminho(mapa.getMatriz());  
    criarGrid();
    numerarCelulas();
    criarHUDBase();

    inimigo = new Inimigo(100, 200, 20, "Rattata", mapa);
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

        Polygon triangulo = new Polygon(
            torre.getX(),      torre.getY() - 15,  // Ponta superior
            torre.getX() - 15, torre.getY() + 15,  // Canto inferior esquerdo
            torre.getX() + 15, torre.getY() + 15   // Canto inferior direito
        );

        triangulo.setFill(Color.RED);
<<<<<<< HEAD
=======
        torres.add(torre);
        triangulosTorres.add(triangulo);
>>>>>>> teste

        FXGL.getGameScene().addUINode(triangulo);
    }

    private void criarHUDBase() {
        Rectangle fundoHUD = new Rectangle(250, 80);
        fundoHUD.setX(1000);
        fundoHUD.setY(15);
        fundoHUD.setFill(Color.web("#17212B"));
        fundoHUD.setArcWidth(20);
        fundoHUD.setArcHeight(20);

        Text tituloHUD = new Text("BASE");
        tituloHUD.setX(1020);
        tituloHUD.setY(45);
        tituloHUD.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        tituloHUD.setFill(Color.WHITE);

        textoVidaBase = new Text(base.getVida() + " / 20");
        textoVidaBase.setX(1170);
        textoVidaBase.setY(45);
        textoVidaBase.setFill(Color.WHITE);
        textoVidaBase.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Rectangle barraCinza = new Rectangle(210,12);
        barraCinza.setX(1020);
        barraCinza.setY(65);
        barraCinza.setFill(Color.web("#4B5563"));
        barraCinza.setArcWidth(20);
        barraCinza.setArcHeight(20);

        barraVerde = new Rectangle(210,12);
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
    }

    private void criarHUDDerrota() {
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

        Rectangle botaoReiniciar = new Rectangle(230, 52);
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

        Rectangle botaoSair = new Rectangle(230, 52);
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

        botaoSair.setOnMouseClicked(evento -> sairDoJogo());
        botaoReiniciar.setOnMouseClicked(evento -> ReiniciarJogo());

        hudDerrota = new Group(
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

        FXGL.getGameScene().addUINode(hudDerrota);
    }
    
    private void sairDoJogo() {
        Platform.exit();
    }
    
    private void ReiniciarJogo() {
        for (Polygon triangulo : triangulosTorres) {
            FXGL.getGameScene().removeUINode(triangulo);
        }
        triangulosTorres.clear();
        torres.clear();
        for(int i = 0; i<18; i++) {
            for(int j = 0; j < 32; j++) {
                if(mapa.getMatriz(i, j) == 2) {
                    mapa.setMatriz(i,j,0);
                }
            }
        }
        base = new Base();
        textoVidaBase.setText(base.getVida() + " / 20");
        barraVerde.setWidth((base.getVida() / 20) * 210);

        inimigo = new Inimigo(100, 200, 20, "Rattata", mapa);
        inimigoChegou = false;
        circuloInimigo.setCenterX(inimigo.getX());
        circuloInimigo.setCenterY(inimigo.getY());

        FXGL.getGameScene().addUINode(circuloInimigo);
        FXGL.getGameScene().removeUINode(hudDerrota);
    }
    @Override
    protected void initInput() {
    FXGL.getInput().addAction(new UserAction("Colocar Torre") {
        @Override
        protected void onActionBegin() {
            if(base.getVida()!=0) {
                double mouseX = FXGL.getInput().getMouseXWorld();
                double mouseY = FXGL.getInput().getMouseYWorld();

                int coluna = (int) (mouseX / 40);
                int linha = (int) (mouseY / 40);

                Torre torre = new Torre(10, 100, 0, 0, 1.0, 50);

                if (mapa.colocarTorre(torre, linha, coluna)) {
                    desenharTorre(torre);
                }
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

    private void desenharProjetil(Projetil tiro) {
    Circle circulo = new Circle(
        tiro.getX(),
        tiro.getY(),
        4
    );

    circulo.setFill(Color.YELLOW);

    projeteis.add(tiro);
    circulosProjeteis.add(circulo);

    FXGL.getGameScene().addUINode(circulo);
}

    @Override 
    protected void onUpdate (double tpf) {
        if (!inimigoChegou && inimigo.statusVida()) {
            if(inimigoChegou = inimigo.mover(tpf)) {
                FXGL.getGameScene().removeUINode(circuloInimigo);

                if(base.diminuirVida(inimigo.danoAtaque())) {
                    textoVidaBase.setText(base.getVida()+ " / 20");
                    barraVerde.setWidth(210.0 * base.getVida() / 20);
                    criarHUDDerrota();
                }
                textoVidaBase.setText(base.getVida()+ " / 20");
                barraVerde.setWidth(210.0 * base.getVida() / 20);
            }
            circuloInimigo.setCenterX(inimigo.getX());
            circuloInimigo.setCenterY(inimigo.getY());
        }

        if (!inimigoChegou && inimigo.statusVida()) {
            for (int i = 0; i < torres.size(); i++) {
                Torre torre = torres.get(i);
                Polygon triangulo = triangulosTorres.get(i);

                if (torre.estaNoAlcance(inimigo)) {
                    torre.atualizarRotacao(inimigo.getX(), inimigo.getY());
                    triangulo.setRotate(torre.getRotacao() + 90);
                }

                Projetil tiro = torre.atirar(inimigo, tpf);

                if (tiro != null) {
                    desenharProjetil(tiro);
                }
            }
        }
        
        for (int i = projeteis.size() - 1; i >= 0; i--) {
            Projetil tiro = projeteis.get(i);
            Circle circulo = circulosProjeteis.get(i);

            if (inimigoChegou || tiro.moverTiro(tpf)) {
                FXGL.getGameScene().removeUINode(circulo);
                projeteis.remove(i);
                circulosProjeteis.remove(i);
            } else {
                circulo.setCenterX(tiro.getX());
                circulo.setCenterY(tiro.getY());
            }
        }

        if (!inimigo.statusVida()) {
            FXGL.getGameScene().removeUINode(circuloInimigo);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
