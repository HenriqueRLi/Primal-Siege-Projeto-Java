package primalsiege;
import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.input.KeyCode;

import primalsiege.modelo.Base;
import primalsiege.modelo.Inimigo;
import primalsiege.modelo.Mapa;
import primalsiege.modelo.Torre;
import primalsiege.torres.TorreCharmander;
import primalsiege.torres.TorreSquirtle;
import primalsiege.torres.TorrePikachu;
import primalsiege.torres.TorreBulbassauro;
import primalsiege.gerenciadores.GerenciadorOndas;
import primalsiege.gerenciadores.GerenciadorCombate;
import primalsiege.gerenciadores.GerenciadorInimigos;
import primalsiege.gerenciadores.GerenciadorTorres;

import primalsiege.interfacegrafica.HUDBase;
import primalsiege.interfacegrafica.HUDOnda;
import primalsiege.interfacegrafica.MenuTorre;
import primalsiege.interfacegrafica.RenderizadorMapa;
import primalsiege.interfacegrafica.RenderizadorTorres;
import primalsiege.interfacegrafica.TelaVitoria;
import primalsiege.interfacegrafica.TelaDerrota;
import primalsiege.gerenciadores.GerenciadorEconomia;


public class TesteFXGL extends GameApplication {
    private static final int TAMANHO_CELULA = 40;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private Inimigo inimigo;
    private ArrayList<Inimigo> inimigosAtivos = new ArrayList<>();
    private ArrayList<Circle> circulosInimigos = new ArrayList<>();
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

        RenderizadorMapa renderizador = new RenderizadorMapa(LARGURA, ALTURA, TAMANHO_CELULA);

        renderizador.desenharCaminho(mapa.getMatriz());
        renderizador.criarGrid();
        renderizador.numerarCelulas();

        hudBase = new HUDBase(base);

        hudOnda = new HUDOnda();
        hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());

        hudOnda.getBotaoIniciarOnda().setOnMouseClicked(evento -> iniciarOnda());

        gerenciadorInimigos = new GerenciadorInimigos(
            inimigosAtivos,
            circulosInimigos,
            hudOnda
        );
    }

    private void sairDoJogo() {
        Platform.exit();
    }
    
    private void ReiniciarJogo() {
        gerenciadorTorres.limparTorres(torres, triangulosTorres, mapa);
        torreSelecionada = null;
        renderizadorTorres.limparImagens();
        
        base = new Base();
        hudBase.getTextoVidaBase().setText(base.getVida() + " / 20");
        hudBase.getBarraVerde().setWidth((base.getVida() / 20) * 210);

        gerenciadorInimigos.limparInimigos();

        gerenciadorCombate.limparProjeteis();

        gerenciadorOndas = new GerenciadorOndas();

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
            if(hudVitoria == null && base.getVida()!=0 && Mrad.getStatus() == false) {
                double mouseX = FXGL.getInput().getMouseXWorld();
                double mouseY = FXGL.getInput().getMouseYWorld();

                if (cliqueEmHUD(mouseX, mouseY)) {
                    return;
                }

                int coluna = (int) (mouseX / 40);
                int linha = (int) (mouseY / 40);

                if(linha >= 18 || linha < 0 || coluna >= 32 || coluna < 0){
                    return;
                }
                if(mapa.getMatriz(linha, coluna) == 2){
                    for (Torre torre : torres) {
                        if (torre.getX() == coluna * 40 + 20 && torre.getY() == linha * 40 + 20) {
                            torreSelecionada = torre;

                            System.out.println("Torre selecionada! Nivel: " + torreSelecionada.getNivel());
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
    FXGL.getInput().addAction(new UserAction("Evoluir Torre") {
            @Override
            protected void onActionBegin() {

                if (torreSelecionada != null &&
                    hudVitoria == null &&
                    base.getVida() > 0) {

                    if (torreSelecionada.evoluir()) {
                        System.out.println(
                            "Evoluiu! Nivel: " + torreSelecionada.getNivel() +
                            " | Dano: " + torreSelecionada.getDano() +
                            " | Disparos/s: " + torreSelecionada.getVelocidadeAtaque()
                        );

                        if (torreSelecionada instanceof TorreCharmander) {

                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "charmeleon.png");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "charizard.png");
                            }
                        }

                        if (torreSelecionada instanceof TorreSquirtle) {
                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "wartortle.png");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "blastoise.png");
                            }
                        }

                        if (torreSelecionada instanceof TorreBulbassauro) {
                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "ivysaur.png");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "venusaur.png");
                            }
                        }

                        if (torreSelecionada instanceof TorrePikachu) {
                            if (torreSelecionada.getNivel() == 2) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "pikachu.png");
                            }
                            else if (torreSelecionada.getNivel() == 3) {
                                renderizadorTorres.trocarSprite(torreSelecionada, torres, "raichu.png");
                            }
                        }
                    }
                    else {
                        System.out.println("Nivel maximo atingido!");
                    }
                }
            }
        }, KeyCode.E);
}

    private void iniciarOnda() {
        if (gerenciadorOndas.ondaEmAndamento() ||
            hudVitoria != null ||
            base.getVida() <= 0) {
            return;
        }

        inimigo = gerenciadorInimigos.instanciarInimigoDaOnda(
            gerenciadorOndas, mapa
        );

        gerenciadorInimigos.criarInimigo(inimigo);

        gerenciadorOndas.registrarInicioOnda();
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
 
    private void criarHUDDerrota() {
    Mrad.fecharMenu();

    TelaDerrota tela = new TelaDerrota();

    tela.getBotaoReiniciar().setOnMouseClicked(evento -> ReiniciarJogo());
    tela.getBotaoSair().setOnMouseClicked(evento -> sairDoJogo());

    hudDerrota = tela;

    FXGL.getGameScene().addUINode(hudDerrota);
}

    private void criarHUDVitoria() {
        Mrad.fecharMenu();

        TelaVitoria tela = new TelaVitoria();

        tela.getBotaoJogar().setOnMouseClicked(evento -> ReiniciarJogo());
        tela.getBotaoSairVitoria().setOnMouseClicked(evento -> sairDoJogo());

        hudVitoria = tela;

        FXGL.getGameScene().addUINode(hudVitoria);
    }
    
    @Override 
    protected void onUpdate (double tpf) {
        if (base.getVida() <= 0 || hudVitoria != null) {
            return;
        }
        if (gerenciadorOndas.verificarSpawn(tpf)) {
            gerenciadorInimigos.criarInimigo(
                gerenciadorInimigos.instanciarInimigoDaOnda(
                    gerenciadorOndas, mapa
                )
            );

            gerenciadorOndas.registrarInimigoGerado();
        }
        if (gerenciadorInimigos.atualizarInimigos(tpf, base, hudBase)) {
            criarHUDDerrota();
        }

        if (gerenciadorOndas.verificarFimOnda(inimigosAtivos.isEmpty())) {
            gerenciadorOndas.registrarFimOnda();

            if (gerenciadorOndas.avançarOnda()) {
                criarHUDVitoria();
            }
        }
        
        gerenciadorCombate.atualizarTorres(
            torres,
            triangulosTorres,
            inimigosAtivos,
            mapa,
            inimigo,
            tpf
        );
        gerenciadorCombate.atualizarProjeteis(inimigosAtivos, tpf);
        
        Torre torre = gerenciadorTorres.criarTorre(Mrad.getTselect());

        if (torre != null) {
            int custo = gerenciadorTorres.getCustoConstrucao(torre);
            if (custo >= 0 && gerenciadorEconomia.getOuro() >= custo) {
                if (gerenciadorTorres.construirTorre(torre, mapa, linhaSelecionada, colunaSelecionada)) {
                    gerenciadorEconomia.descontarOuro(custo);
                    renderizadorTorres.desenharTorre(torre, Mrad.getTselect(), torres, triangulosTorres, hudOnda);
                    hudOnda.atualizarOuro(gerenciadorEconomia.getOuro());
                }
            }
            else {
                System.out.println("Ouro insuficiente para construir a torre!");
            }

            Mrad.resetselect();
            Mrad.fecharMenu();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
