package primalsiege;
import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import primalsiege.gerenciadores.GerenciadorCombate;
import primalsiege.gerenciadores.GerenciadorEconomia;
import primalsiege.gerenciadores.GerenciadorInimigos;
import primalsiege.gerenciadores.GerenciadorOndas;
import primalsiege.gerenciadores.GerenciadorTorres;
import primalsiege.interfacegrafica.FabricaMenus;
import primalsiege.interfacegrafica.HUDBase;
import primalsiege.interfacegrafica.HUDOnda;
import primalsiege.interfacegrafica.MenuTorre;
import primalsiege.interfacegrafica.MenuUpgrade;
import primalsiege.interfacegrafica.RenderizadorMapa;
import primalsiege.interfacegrafica.RenderizadorTorres;
import primalsiege.interfacegrafica.TelaDerrota;
import primalsiege.interfacegrafica.TelaInicial;
import primalsiege.interfacegrafica.TelaSecundaria;
import primalsiege.interfacegrafica.TelaVitoria;
import primalsiege.modelo.Base;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;
import primalsiege.modelo.Torre;
import primalsiege.torres.TorreBulbassauro;
import primalsiege.torres.TorreCharmander;
import primalsiege.torres.TorrePikachu;
import primalsiege.torres.TorreSquirtle;
import primalsiege.interfacegrafica.HUDChefe;
import primalsiege.modelo.Onda;


public class TesteFXGL extends GameApplication {
    private static final int TAMANHO_CELULA = 40;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private Inimigo inimigo;
    private ArrayList<Inimigo> inimigosAtivos = new ArrayList<>();
    private ArrayList<ImageView> imagensInimigos = new ArrayList<>();
    private Mapa mapa;
    private Base base;
    private HUDBase hudBase;
    private ArrayList<Torre> torres = new ArrayList<>();
    private ArrayList<Polygon> triangulosTorres = new ArrayList<>();
    private GerenciadorCombate gerenciadorCombate = new GerenciadorCombate();
    private Group hudDerrota;
    MenuTorre Mrad = new MenuTorre();
    private int linhaSelecionada;
    private int colunaSelecionada;
    private HUDOnda hudOnda;
    private GerenciadorOndas gerenciadorOndas;
    private Group hudVitoria;
    private GerenciadorInimigos gerenciadorInimigos;
    private GerenciadorTorres gerenciadorTorres = new GerenciadorTorres();
    private RenderizadorTorres renderizadorTorres = new RenderizadorTorres();
    private Torre torreSelecionada;
    private GerenciadorEconomia gerenciadorEconomia = new GerenciadorEconomia();
    private MenuUpgrade telaupar = new MenuUpgrade();
    private TelaInicial telaInicial;
    private boolean jogoIniciado = false;
    private Label avisoLimite;
    private PauseTransition temporizadorAviso;
    private HUDChefe hudChefe;
    private Inimigo chefeDaOnda;
    private boolean chefeGerado = false;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(LARGURA);
        settings.setHeight(ALTURA);
        settings.setTitle("Primal Siege");
        settings.setGameMenuEnabled(true);
        settings.setSceneFactory(new FabricaMenus(this));
    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundColor(Color.DARKGREEN);

        mapa = new Mapa(1180, 660);
        mapa.bloquearBordasFloresta();
        base = new Base();
        gerenciadorOndas = new GerenciadorOndas();

        RenderizadorMapa renderizador = new RenderizadorMapa(LARGURA, ALTURA, TAMANHO_CELULA);

        renderizador.desenharCaminho(mapa.getMatriz());
        renderizador.criarGrid();
        //renderizador.numerarCelulas();

        hudBase = new HUDBase(base);

        hudOnda = new HUDOnda();

        hudChefe = new HUDChefe();
        FXGL.getGameScene().addUINode(hudChefe);

        hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());

        hudOnda.getBotaoIniciarOnda().setOnMouseClicked(evento -> iniciarOnda());

        gerenciadorInimigos = new GerenciadorInimigos(inimigosAtivos, imagensInimigos, hudOnda);

        mostrarTelaInicial();
    }

    private void abrirOpcoes() {

        TelaSecundaria opcoes = new TelaSecundaria(
            true, hudOndasVisivel()
        );

        opcoes.getBotaoHUD().setOnMouseClicked(evento -> {

            boolean visivel = alternarVisibilidadeHUD();

            hudOnda.setVisible(visivel);
            opcoes.atualizarHUD(visivel);
        });

        opcoes.getBotaoVoltar().setOnMouseClicked(evento -> FXGL.getGameScene().removeUINode(opcoes));

        FXGL.getGameScene().addUINode(opcoes);
    }

    private void abrirComoJogar() {

        TelaSecundaria comoJogar = new TelaSecundaria(
            false, hudOnda.isVisible()
        );

        comoJogar.getBotaoVoltar().setOnMouseClicked(evento ->
            FXGL.getGameScene().removeUINode(comoJogar)
        );

        FXGL.getGameScene().addUINode(comoJogar);
    }

    public void reiniciarPelaPausa() {
        ReiniciarJogo();
        jogoIniciado = true;
    }
    
    public boolean hudOndasVisivel() {
        return hudOnda.isVisible();
    }

    public boolean alternarVisibilidadeHUD() {

        hudOnda.setVisible(!hudOnda.isVisible());

        return hudOnda.isVisible();
    }

    public void voltarAoMenuPrincipal() {

        ReiniciarJogo();

        Mrad.fecharMenu();
        Mrad.resetselect();

        jogoIniciado = false;

        mostrarTelaInicial();
    }

    private void mostrarTelaInicial() {

        telaInicial = new TelaInicial();

        telaInicial.getBotaoJogar().setOnMouseClicked(evento -> {

            jogoIniciado = true;

            FXGL.getGameScene().removeUINode(telaInicial);
            telaInicial = null;
        });

        telaInicial.getBotaoSair().setOnMouseClicked(
            evento -> sairDoJogo()
        );

        telaInicial.getBotaoOpcoes().setOnMouseClicked(
            evento -> abrirOpcoes()
        );

        telaInicial.getBotaoComoJogar().setOnMouseClicked(
            evento -> abrirComoJogar()
        );

        FXGL.getGameScene().addUINode(telaInicial);
    }

    private void sairDoJogo() {
        Platform.exit();
    }
    
    private void ReiniciarJogo() {
        hudChefe.ocultar();

        chefeDaOnda = null;
        chefeGerado = false;gerenciadorTorres.limparTorres(torres, triangulosTorres, mapa);
        
        torreSelecionada = null;
        renderizadorTorres.limparImagens();
        
        base = new Base();
        hudBase.atualizarVida(base.getVida(), false);

        gerenciadorInimigos.limparInimigos();

        gerenciadorCombate.limparProjeteis();

        gerenciadorOndas = new GerenciadorOndas();
        hudOnda.atualizarNumeroOnda(gerenciadorOndas.getNumeroOnda());

        gerenciadorEconomia = new GerenciadorEconomia();
        hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());

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

        double mouseX = FXGL.getInput().getMouseXWorld();
        double mouseY = FXGL.getInput().getMouseYWorld();

        if (Mrad.getStatus()) {

            if (!Mrad.cliqueDentroPainel(mouseX, mouseY)) {
                Mrad.fecharMenu();
                Mrad.resetselect();
            }

            return;
        }

        if (jogoIniciado && hudVitoria == null && base.getVida() != 0 && telaupar.getStatus() == false) {

            if (cliqueEmHUD(mouseX, mouseY)) {
                return;
            }

            int coluna = (int) (mouseX / 40);
            int linha = (int) (mouseY / 40);

            if (linha >= 18 || linha < 0 ||
                coluna >= 32 || coluna < 0) {
                return;
            }

            if (mapa.getMatriz(linha, coluna) == 2) {

                for (Torre torre : torres) {

                    if (torre.getX() == coluna * 40 + 20 &&
                        torre.getY() == linha * 40 + 20) {

                        torreSelecionada = torre;

                        telaupar.abrirtorre(torreSelecionada, coluna, linha);

                        return;
                    }
                }

                return;
            }

            if (mapa.getMatriz(linha, coluna) != 0) {
                return;
            }

            torreSelecionada = null;

            Mrad.AbrirMenu(linha, coluna);

            linhaSelecionada = linha;
            colunaSelecionada = coluna;
        }
    }
    }, MouseButton.PRIMARY);

    FXGL.getInput().addAction(new UserAction("Alternar HUD") {

        @Override
        protected void onActionBegin() {

            if (jogoIniciado && base.getVida() > 0 && hudVitoria == null && hudDerrota == null) {

                hudOnda.alternarRecolhimento();
            }
        }

    }, KeyCode.H); 
}

    private void evoluirTorre() {
        if (jogoIniciado && torreSelecionada != null && hudVitoria == null && base.getVida() > 0) { 

                    int custo = gerenciadorTorres.getCustoEvolucao(torreSelecionada);

                    if (custo == -1) {
                        return;
                    }

                    if (gerenciadorEconomia.getOuro() < custo) {
                        mostrarAvisoLimite("Ouro insuficiente para evoluir!");
                        return;
                    }
                    if (torreSelecionada.evoluir()) {
                        gerenciadorEconomia.descontarOuro(custo);
                        torreSelecionada.somaValor(custo);
                        hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());

                        if (torreSelecionada instanceof TorreCharmander) {

                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "charmeleon_parado.gif");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "charizard_parado.gif");
                            }
                        }

                        if (torreSelecionada instanceof TorreSquirtle) {
                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "wartortle_parado.gif");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "blastoise_parado.gif");
                            }
                        }

                        if (torreSelecionada instanceof TorreBulbassauro) {
                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "ivysaur_parado.gif");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "venusaur_parado.gif");
                            }
                        }

                        if (torreSelecionada instanceof TorrePikachu) {
                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "pikachu_parado.gif");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "raichu_parado.gif");
                            }
                        }
                    }
              }
          }
  
    private void iniciarOnda() {
        if (!jogoIniciado || gerenciadorOndas.ondaEmAndamento() || hudVitoria != null || base.getVida() <= 0) {
            return;
        }

        gerenciadorInimigos.organizarOndaPorVelocidade(gerenciadorOndas, mapa);
        prepararChefe();
        
        inimigo = gerenciadorInimigos.instanciarInimigoDaOnda(gerenciadorOndas, mapa, 0);

        gerenciadorInimigos.criarInimigo(inimigo);

        gerenciadorOndas.registrarInicioOnda();
    }

    private boolean cliqueEmHUD(double mouseX, double mouseY) {
        if (mouseX >= 1000 && mouseX <= 1250 && mouseY >= 15 && mouseY <= 95) {
            return true;
        }

        if (hudOnda != null && hudOnda.isVisible()) {

            if (hudOnda.estaExpandida()) {

                if (mouseX >= 220 && mouseX <= 1060 &&
                    mouseY >= 615 && mouseY <= 720) {
                    return true;
                }

                if (mouseX >= 595 && mouseX <= 685 &&
                    mouseY >= 585 && mouseY <= 615) {
                    return true;
                }
            }
            else {

                if (mouseX >= 595 && mouseX <= 685 &&
                    mouseY >= 690 && mouseY <= 720) {
                    return true;
                }
            }
        }

        return false;
    }

    private void criarHUDDerrota() {
    Mrad.fecharMenu();
    hudChefe.ocultar();

    TelaDerrota tela = new TelaDerrota();

    tela.getBotaoReiniciar().setOnMouseClicked(evento -> ReiniciarJogo());
    tela.getBotaoSair().setOnMouseClicked(evento -> sairDoJogo());

    hudDerrota = tela;

    FXGL.getGameScene().addUINode(hudDerrota);
}

    private void criarHUDVitoria() {
        Mrad.fecharMenu();
        hudChefe.ocultar();

        TelaVitoria tela = new TelaVitoria();

        tela.getBotaoJogar().setOnMouseClicked(evento -> ReiniciarJogo());
        tela.getBotaoSairVitoria().setOnMouseClicked(evento -> sairDoJogo());

        hudVitoria = tela;

        FXGL.getGameScene().addUINode(hudVitoria);
    }
    
    private void prepararChefe() {

        chefeDaOnda = null;
        chefeGerado = false;
        hudChefe.ocultar();

        Onda onda = gerenciadorOndas.getOndaAtual();

        for (int i = 0; i < onda.getQuantidade(); i++) {

            if (onda.ehChefe(i)) {

                chefeDaOnda = gerenciadorInimigos
                    .instanciarInimigoDaOnda(
                        gerenciadorOndas, mapa, i
                    );

                hudChefe.mostrar(chefeDaOnda);
                break;
            }
        }
    }

    @Override 
    protected void onUpdate (double tpf) {
        if(telaupar.getevento() == 1){
            telaupar.resetEvento();
            evoluirTorre();
            telaupar.fecharMenu();
        }
        else if (telaupar.getevento() == 2) {
            telaupar.resetEvento();

            if (gerenciadorTorres.venderTorre(torreSelecionada, torres, triangulosTorres, mapa, renderizadorTorres)) {
                int val = (int) Math.round(torreSelecionada.getvalor() * 0.60);
                gerenciadorEconomia.adicionarOuro(val);
                hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());
            }

            telaupar.fecharMenu();
        }

        if (!jogoIniciado) {
            return;
        }

        if (base.getVida() <= 0 || hudVitoria != null) {
            return;
        }

        if (gerenciadorOndas.verificarSpawn(tpf)) {

            int indice = gerenciadorOndas.getInimigosGerados();

            if (chefeDaOnda != null && gerenciadorOndas.getOndaAtual().ehChefe(indice)) {
                gerenciadorInimigos.criarInimigo(chefeDaOnda);
                chefeGerado = true;
            }
            else {
                gerenciadorInimigos.criarInimigo(
                    gerenciadorInimigos.instanciarInimigoDaOnda(
                        gerenciadorOndas, mapa, indice
                    )
                );
            }

            gerenciadorOndas.registrarInimigoGerado();
        }

        if (gerenciadorInimigos.atualizarInimigos(tpf, base, hudBase, gerenciadorEconomia)) {
            criarHUDDerrota();
            return;
        }

        if (gerenciadorOndas.verificarFimOnda(inimigosAtivos.isEmpty())) {

            gerenciadorOndas.registrarFimOnda();

            if (gerenciadorOndas.avançarOnda()) {
                criarHUDVitoria();
            }
            else {
                hudOnda.atualizarNumeroOnda(gerenciadorOndas.getNumeroOnda());
            }
        }

        gerenciadorCombate.atualizarTorres(
            torres,
            triangulosTorres,
            inimigosAtivos,
            mapa,
            tpf
        );

        for (Torre torre : torres) {
            for (Inimigo alvo : inimigosAtivos) {

                if (torre.estaNoAlcance(alvo)) {

                    torre.atualizarRotacao(alvo.getX(), alvo.getY());

                    renderizadorTorres.atualizarRotacao(torre, torres);

                    break;
                }
            }
        }

        gerenciadorCombate.atualizarProjeteis(inimigosAtivos, tpf);

        if (chefeDaOnda != null && chefeGerado) {

            if (!chefeDaOnda.statusVida() ||
                !inimigosAtivos.contains(chefeDaOnda)) {

                hudChefe.ocultar();
                chefeDaOnda = null;
                chefeGerado = false;
            }
            else {
                hudChefe.atualizarVida(chefeDaOnda.getVida());
            }
        }

        Torre torre = gerenciadorTorres.criarTorre(Mrad.getTselect());

        if (torre != null) {
            int custo = gerenciadorTorres.getCustoConstrucao(torre);

            if (custo >= 0 && gerenciadorEconomia.getOuro() >= custo) {

                if (gerenciadorTorres.construirTorre(
                        torre, mapa, linhaSelecionada, colunaSelecionada)) {

                    gerenciadorEconomia.descontarOuro(custo);

                    renderizadorTorres.desenharTorre(
                        torre,
                        Mrad.getTselect(),
                        torres,
                        triangulosTorres,
                        hudOnda
                    );

                    hudOnda.atualizarOuro(
                        gerenciadorEconomia.getOuro()
                    );
                }
            }
            else {
                mostrarAvisoLimite("Ouro insuficiente para construir!");
            }

            Mrad.resetselect();
            Mrad.fecharMenu();
        } 
    }

    private void mostrarAvisoLimite(String mensagem) {

        if (avisoLimite == null) {

            avisoLimite = new Label();

            avisoLimite.setPrefWidth(400);
            avisoLimite.setAlignment(Pos.CENTER);

            avisoLimite.setLayoutX((LARGURA - 400) / 2);
            avisoLimite.setLayoutY(100);

            avisoLimite.setMouseTransparent(true);

            avisoLimite.setStyle(
                "-fx-background-color: #9B2335;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px;"
            );

            FXGL.getGameScene().addUINode(avisoLimite);
        }

        avisoLimite.setText(mensagem);

        if (temporizadorAviso != null) {
            temporizadorAviso.stop();
        }

        temporizadorAviso = new PauseTransition(Duration.seconds(5));

        temporizadorAviso.setOnFinished(event -> {
            FXGL.getGameScene().removeUINode(avisoLimite);
            avisoLimite = null;
        });

        temporizadorAviso.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
