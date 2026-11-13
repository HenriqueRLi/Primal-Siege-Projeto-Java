import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
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



public class TesteFXGL extends GameApplication {
    private static final int TAMANHO_CELULA = 40;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private Inimigo inimigo;
    private Circle circuloInimigo;
    private ArrayList<Inimigo> inimigosAtivos = new ArrayList<>();
    private ArrayList<Circle> circulosInimigos = new ArrayList<>();
    private Mapa mapa;
    private Base base;
    private Text textoVidaBase;
    private Rectangle barraVerde;
    private ArrayList<Torre> torres = new ArrayList<>();
    private ArrayList<Polygon> triangulosTorres = new ArrayList<>();
    private ArrayList<Projetil> projeteis = new ArrayList<>();
    private ArrayList<Circle> circulosProjeteis = new ArrayList<>();
    private Group hudDerrota;
    MenuTorre Mrad = new MenuTorre();
    private int linhaSelecionada;
    private int colunaSelecionada;
    private boolean statusOndas = false;
    private Group hudOnda;
    private double tempoSpawn = 0;
    private int inimigosGerados = 0;
    private GerenciadorOndas gerenciadorOndas;
    private Group hudVitoria;

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
    gerenciadorOndas = new GerenciadorOndas();

    desenharCaminho(mapa.getMatriz());  
    criarGrid();
    numerarCelulas();
    criarHUDBase();
    criarHUDOnda();
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

    private void desenharTorre(Torre torre, int select) {

        Polygon triangulo = new Polygon(
            torre.getX(),      torre.getY() - 15,  // Ponta superior
            torre.getX() - 15, torre.getY() + 15,  // Canto inferior esquerdo
            torre.getX() + 15, torre.getY() + 15   // Canto inferior direito
        );

                switch (select) {
            case 1:
                triangulo.setFill(Color.RED);
                break;
            case 2:
                triangulo.setFill(Color.BLUE);
                break;
            case 3:
                triangulo.setFill(Color.YELLOW);
                break;
            case 4:
                triangulo.setFill(Color.GREEN);
                break;
            default:
                break;
        }

        torres.add(torre);
        triangulosTorres.add(triangulo);

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
        Mrad.fecharMenu();
        
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
        botaoReiniciar.setCursor(Cursor.HAND);
        botaoReiniciar.setOnMouseEntered(evento -> botaoReiniciar.setStrokeWidth(3));
        botaoReiniciar.setOnMouseExited(evento -> botaoReiniciar.setStrokeWidth(2));
        botaoSair.setCursor(Cursor.HAND);
        botaoSair.setOnMouseEntered(evento -> botaoSair.setStrokeWidth(3));
        botaoSair.setOnMouseExited(evento -> botaoSair.setStrokeWidth(2));

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

        for(Circle circulo : circulosInimigos){
            FXGL.getGameScene().removeUINode(circulo);
        }

        inimigosAtivos.clear();
        circulosInimigos.clear();

        for(Circle projetil : circulosProjeteis){
            FXGL.getGameScene().removeUINode(projetil);
        }

        projeteis.clear();
        circulosProjeteis.clear();

        statusOndas = false;
        gerenciadorOndas = new GerenciadorOndas();
        inimigosGerados = 0;
        tempoSpawn = 0;

        if(hudVitoria != null){
            FXGL.getGameScene().removeUINode(hudVitoria);
            hudVitoria = null;
        }
        if(hudDerrota != null){
            FXGL.getGameScene().removeUINode(hudDerrota);
            hudDerrota = null;
        }
    }
    
    @Override
    protected void initInput() {
    FXGL.getInput().addAction(new UserAction("Colocar Torre") {
        @Override
        protected void onActionBegin() {
            if(base.getVida()!=0 && Mrad.getStatus() == false) {
                double mouseX = FXGL.getInput().getMouseXWorld();
                double mouseY = FXGL.getInput().getMouseYWorld();

                if (cliqueEmHUD(mouseX, mouseY)) {
                    return;
                }

                int coluna = (int) (mouseX / 40);
                int linha = (int) (mouseY / 40);

                // Torre torre = new TorreCharmander(0, 0);

                Mrad.AbrirMenu(linha, coluna);

            linhaSelecionada = linha;
            colunaSelecionada = coluna;

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

    private void desenharProjetil(Projetil tiro, Color cor, int rad) {
        Circle circulo = new Circle(
            tiro.getX(),
            tiro.getY(),
            rad
        );

        circulo.setFill(cor);

        projeteis.add(tiro);
        circulosProjeteis.add(circulo);

        FXGL.getGameScene().addUINode(circulo);
    }

    private void criarHUDOnda(){
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

        Text textoNumeroOnda = new Text("1/10");
        textoNumeroOnda.setX(280);
        textoNumeroOnda.setY(687);
        textoNumeroOnda.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-font-weight: bold;");
        textoNumeroOnda.setFill(Color.WHITE);

        Rectangle botaoIniciarOnda = new Rectangle(300, 65);
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

        Text quantOuro = new Text("300");
        quantOuro.setX(890);
        quantOuro.setY(687);
        quantOuro.setFill(Color.web("#FACC15"));
        quantOuro.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-font-weight: bold;");

        textoIniciarOnda.setMouseTransparent(true);
        simboloPlay.setMouseTransparent(true);

        botaoIniciarOnda.setOnMouseClicked(evento -> iniciarOnda());

        botaoIniciarOnda.setCursor(Cursor.HAND);

        botaoIniciarOnda.setOnMouseEntered(evento -> botaoIniciarOnda.setStrokeWidth(5));

        botaoIniciarOnda.setOnMouseExited(evento -> botaoIniciarOnda.setStrokeWidth(3));

        hudOnda = new Group(
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
        FXGL.getGameScene().addUINode(hudOnda);

    }    

    private void iniciarOnda(){
        if (statusOndas || hudVitoria != null || base.getVida() <= 0) {
            return;
        }
        inimigo = instanciarInimigoDaOnda();
        criarInimigo(inimigo);

        inimigosGerados = 1;
        tempoSpawn = 0;
        statusOndas = true;
    }

    private boolean cliqueEmHUD(double mouseX, double mouseY) {
        if(mouseX >= 1000 && mouseX <= 1250){
            if(mouseY >= 15 && mouseY <= 95){
                return true;
            }
        }
        if(mouseX >= 220 && mouseX <= 1060){
            if(mouseY >= 615 && mouseY <= 720){
                return true;
            }
        }
        return false;
    }
     
    private void criarInimigo(Inimigo inimigo){
        inimigosAtivos.add(inimigo);
        Circle circulo = new Circle(inimigo.getX(),inimigo.getY(),12);
        circulosInimigos.add(circulo);
        circulo.setFill(Color.PURPLE);
        
        FXGL.getGameScene().addUINode(circulo);
        hudOnda.toFront();
    }
    
    private Inimigo instanciarInimigoDaOnda() {
        String especie = gerenciadorOndas.getOndaAtual().getEspecie();

        switch (especie) {
            case "Rattata":
                return new Rattata(mapa);
        
            case "Pidgey":
                return new Pidgey(mapa);

            default:
                return null;
        }
    }
    
    private void criarHUDVitoria() {
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

        Rectangle botaoJogar = new Rectangle(340, 467, 290, 62);

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

        Rectangle botaoSairVitoria = new Rectangle(650, 467, 290, 62);

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

        botaoJogar.setOnMouseClicked(evento -> ReiniciarJogo());
        botaoSairVitoria.setOnMouseClicked(evento -> sairDoJogo());
        botaoJogar.setOnMouseEntered(evento -> botaoJogar.setStrokeWidth(3));
        botaoJogar.setOnMouseExited(evento -> botaoJogar.setStrokeWidth(2));
        botaoSairVitoria.setOnMouseEntered(evento -> botaoSairVitoria.setStrokeWidth(3));
        botaoSairVitoria.setOnMouseExited(evento -> botaoSairVitoria.setStrokeWidth(2));

        hudVitoria = new Group(
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
        FXGL.getGameScene().addUINode(hudVitoria);
        botaoJogar.setCursor(Cursor.HAND);
        botaoSairVitoria.setCursor(Cursor.HAND);
    }

    @Override 
    protected void onUpdate (double tpf) {
        if (base.getVida() <= 0 || hudVitoria != null) {
            return;
        }
        if(statusOndas == true && inimigosGerados < gerenciadorOndas.getOndaAtual().getQuantidade()){
            tempoSpawn += tpf;
            if(tempoSpawn >= gerenciadorOndas.getOndaAtual().getIntervaloSpawn()){
                criarInimigo(instanciarInimigoDaOnda());
                inimigosGerados++;
                tempoSpawn = 0;
            }
        }
        for(int i = inimigosAtivos.size() - 1; i >= 0; i--){
            if (inimigosAtivos.get(i).statusVida()) {
                if(inimigosAtivos.get(i).mover(tpf)) {
                    FXGL.getGameScene().removeUINode(circulosInimigos.get(i));

                    if(base.diminuirVida(inimigosAtivos.get(i).danoAtaque())) {
                        textoVidaBase.setText(base.getVida()+ " / 20");
                        barraVerde.setWidth(210.0 * base.getVida() / 20);
                        criarHUDDerrota();
                    }
                    inimigosAtivos.remove(i);
                    circulosInimigos.remove(i);
                    textoVidaBase.setText(base.getVida()+ " / 20");
                    barraVerde.setWidth(210.0 * base.getVida() / 20);
                }
                else{
                    circulosInimigos.get(i).setCenterX(inimigosAtivos.get(i).getX());
                    circulosInimigos.get(i).setCenterY(inimigosAtivos.get(i).getY());
                }
            }
            else
            {
                FXGL.getGameScene().removeUINode(circulosInimigos.get(i));
                inimigosAtivos.remove(i);
                circulosInimigos.remove(i);
            }
        }
        
        if(statusOndas == true && inimigosGerados == gerenciadorOndas.getOndaAtual().getQuantidade() && inimigosAtivos.isEmpty()){
            statusOndas = false;

            if (gerenciadorOndas.avançarOnda()) {
                criarHUDVitoria();
            }
        }

        for (int i = 0; i < torres.size(); i++) {
            Torre torre = torres.get(i);
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
            if(alvoSelecionado != null){
                 torre.atualizarRotacao(alvoSelecionado.getX(), alvoSelecionado.getY());
                triangulo.setRotate(torre.getRotacao() + 90);
                Projetil tiro = torre.atirar(alvoSelecionado, tpf);

                if (tiro != null) {
                    desenharProjetil(tiro, torre.getcorProj(),torre.getProjrad(inimigo));
                }
            }
        }
        
        for (int i = projeteis.size() - 1; i >= 0; i--) {
            Projetil tiro = projeteis.get(i);
            Circle circulo = circulosProjeteis.get(i);

            if (!inimigosAtivos.contains(tiro.getAlvo()) || tiro.moverTiro(tpf)) {
                FXGL.getGameScene().removeUINode(circulo);
                projeteis.remove(i);
                circulosProjeteis.remove(i);
            } else {
                circulo.setCenterX(tiro.getX());
                circulo.setCenterY(tiro.getY());
                circulo.setRadius(tiro.getRaio());
            }
        }

        Torre torre = null;
        switch (Mrad.getTselect()) {
            case 1:
                torre = new TorreCharmander(0, 0);
                break;
            case 2:
                torre = new TorreSquirtle(0, 0);
                break;
            case 3:
                torre = new TorrePikachu(0, 0);
                break;
            case 4:
                torre = new TorreBulbassauro(0, 0);
                break;
            default:
                break;
        }

        if (torre != null) {
            if (mapa.colocarTorre(torre, linhaSelecionada, colunaSelecionada)) {
                desenharTorre(torre, Mrad.getTselect());
            }
            Mrad.resetselect();
            Mrad.fecharMenu();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
