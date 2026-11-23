
package primalsiege.interfacegrafica;

import com.almasb.fxgl.dsl.FXGL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MenuTorre {

    private static final int TAM_CELULA = 40;
    private static final int LARGURA_TELA = 1280;
    private static final int LARGURA_MENU = 300;

    private final Group MenuR = new Group();
    private final HBox[] cards = new HBox[4];

    private boolean status = false;
    private int Tselect = 0;
    private int selecaoPendente = 0;

    private int linhaSelecionada;
    private int colunaSelecionada;

    private Button botaoConfirmar;

    private VBox painel;

    private final String[] nomes = {
        "CHARMANDER",
        "SQUIRTLE",
        "PIKACHU",
        "BULBASSAURO"
    };

    private final String[] imagens = {
        "charmander.png",
        "squirtle.png",
        "pichu.png",
        "bulbasaur.png"
    };

    // Textos ilustrativos: ajustar conforme as habilidades reais.
    private final String[] efeitos = {
        "Fogo - Queimadura",
        "Agua - Lentidao",
        "Eletrico - Choque",
        "Planta - Veneno"
    };

    // Valores iniciais de exemplo.
    private final int[] custos = {100, 125, 115, 100};

    public void AbrirMenu(int linha, int coluna) {

        if (status) {
            boolean mesmoSlot =
                linha == linhaSelecionada &&
                coluna == colunaSelecionada;

            fecharMenu();

            if (mesmoSlot) {
                return;
            }
        }

        this.linhaSelecionada = linha;
        this.colunaSelecionada = coluna;

        this.Tselect = 0;
        this.selecaoPendente = 0;

        int centroX = coluna * TAM_CELULA + 20;
        int centroY = linha * TAM_CELULA + 20;

        // Indicador vermelho do local de construção
        Circle marcador = new Circle(
            centroX, centroY, 19
        );

        marcador.setFill(Color.rgb(255, 50, 65, 0.23));
        marcador.setStroke(Color.web("#FF4D58"));
        marcador.setStrokeWidth(2);

        // Não bloqueia cliques no mapa.
        marcador.setMouseTransparent(true);

        painel = criarPainel();

        painel.setLayoutX(
            LARGURA_TELA - LARGURA_MENU - 14
        );
        painel.setLayoutY(32);

        MenuR.getChildren().addAll(marcador, painel);

        FXGL.getGameScene().addUINode(MenuR);

        this.status = true;
    }

    private VBox criarPainel() {

        VBox painel = new VBox(10);

        painel.setPrefWidth(LARGURA_MENU);
        painel.setMaxWidth(LARGURA_MENU);
        painel.setPadding(new Insets(15));

        painel.setStyle(
            "-fx-background-color: #18232F;" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #283746;" +
            "-fx-border-radius: 16;" +
            "-fx-border-width: 1;"
        );

        Label marca = criarTexto(
            "PRIMAL SIEGE • TORRES", "#CBA65D", 11
        );

        Label titulo = criarTexto(
            "ESCOLHER POKÉMON", "#F4F5F7", 20
        );
        titulo.setStyle(
            titulo.getStyle() + "-fx-font-weight: bold;"
        );

        Region divisor = new Region();
        divisor.setPrefHeight(1);
        divisor.setMinHeight(1);
        divisor.setStyle(
            "-fx-background-color: #405162;"
        );

        // Lista de Pokémon
        VBox lista = new VBox(7);

        for (int i = 0; i < nomes.length; i++) {
            cards[i] = criarCard(i);
            lista.getChildren().add(cards[i]);
        }
        atualizarCards();

        botaoConfirmar = criarBotao(
            "CONFIRMAR TORRE", "#AC243A"
        );
        botaoConfirmar.setDisable(true);

        botaoConfirmar.setOnAction(event -> {

            if (selecaoPendente == 0) {
                return;
            }

            // A escolha só é liberada agora.
            Tselect = selecaoPendente;

            fecharMenu();
            event.consume();
        });

        Button cancelar = criarBotao(
            "CANCELAR", "#354D64"
        );

        cancelar.setOnAction(event -> {
            Tselect = 0;
            fecharMenu();
            event.consume();
        });

        painel.getChildren().addAll(
            marca,
            titulo,
            divisor,
            lista,
            botaoConfirmar,
            cancelar
        );

        // Impede que cliques no painel cheguem ao mapa.
        painel.setOnMouseClicked(event -> event.consume());

        return painel;
    }

    private HBox criarCard(int indice) {

        int tipo = indice + 1;

        // Miniatura do Pokémon
        ImageView pokemon = new ImageView(
            FXGL.image(imagens[indice])
        );

        pokemon.setFitWidth(46);
        pokemon.setFitHeight(46);
        pokemon.setPreserveRatio(true);
        pokemon.setSmooth(false);

        StackPane fundoImagem = new StackPane(pokemon);
        fundoImagem.setMinSize(54, 54);
        fundoImagem.setPrefSize(54, 54);

        fundoImagem.setStyle(
            "-fx-background-color: #17232F;" +
            "-fx-background-radius: 8;"
        );

        Label nome = criarTexto(
            nomes[indice], "#FFFFFF", 13
        );

        nome.setStyle(
            nome.getStyle() + "-fx-font-weight: bold;"
        );

        Label efeito = criarTexto(
            efeitos[indice], "#A7B6C6", 11
        );

        VBox informacoes = new VBox(4, nome, efeito);

        Label preco = criarTexto(
            "R$ " + custos[indice], "#E1BE78", 12
        );

        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        HBox card = new HBox(
            9, fundoImagem, informacoes, espaco, preco
        );

        card.setPadding(new Insets(7));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setMinHeight(68);
        card.setCursor(Cursor.HAND);

        card.setOnMouseClicked(event -> {

            selecionarPokemon(tipo);
            event.consume();
        });

        card.setOnMouseEntered(event -> {

            if (selecaoPendente != tipo) {
                card.setStyle(
                    "-fx-background-color: #304457;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: #52687C;" +
                    "-fx-border-radius: 10;"
                );
            }
        });

        card.setOnMouseExited(event -> {
            atualizarCards();
        });

        return card;
    }

    private void selecionarPokemon(int tipo) {
        this.selecaoPendente = tipo;

        botaoConfirmar.setDisable(false);

        atualizarCards();
    }

    private void atualizarCards() {

        for (int i = 0; i < cards.length; i++) {

            boolean selecionado =
                selecaoPendente == i + 1;

            String fundo = selecionado
                ? "#40303D"
                : "#253544";

            String borda = selecionado
                ? "#FF4D58"
                : "#344657";

            cards[i].setStyle(
                "-fx-background-color: " + fundo + ";" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: " + borda + ";" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 1.5;"
            );
        }
    }

    private Label criarTexto(
        String texto, String cor, int tamanho
    ) {

        Label label = new Label(texto);

        label.setStyle(
            "-fx-text-fill: " + cor + ";" +
            "-fx-font-size: " + tamanho + "px;" +
            "-fx-font-family: 'Arial';"
        );

        return label;
    }

    private Button criarBotao(
        String texto, String cor
    ) {

        Button botao = new Button(texto);

        botao.setMaxWidth(Double.MAX_VALUE);
        botao.setPrefHeight(49);
        botao.setCursor(Cursor.HAND);

        botao.setStyle(
            "-fx-background-color: " + cor + ";" +
            "-fx-background-radius: 9;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );

        return botao;
    }

    public void setCusto(int tipo, int valor) {
        if (tipo >= 1 && tipo <= 4) {
            custos[tipo - 1] = valor;
        }
    }

    public int getTselect() {
        return Tselect;
    }

    public void resetselect() {
        Tselect = 0;
    }

    public void fecharMenu() {

        if (status) {
            FXGL.getGameScene().removeUINode(MenuR);
        }

        MenuR.getChildren().clear();

        status = false;
        selecaoPendente = 0;
    }

    public boolean getStatus() {
        return status;
    }

    public int getLinhaSelecionada() {
        return linhaSelecionada;
    }

    public int getColunaSelecionada() {
        return colunaSelecionada;
    }

    public boolean cliqueDentroPainel(double mouseX, double mouseY) {

        if (!status || painel == null) {
            return false;
        }

        return painel.getBoundsInParent().contains(mouseX, mouseY);
    }
    
}
