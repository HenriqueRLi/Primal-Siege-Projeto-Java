
package primalsiege.interfacegrafica;

import java.util.Locale;

import com.almasb.fxgl.dsl.FXGL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import primalsiege.modelo.Torre;
import primalsiege.torres.TorreBulbassauro;
import primalsiege.torres.TorreCharmander;
import primalsiege.torres.TorrePikachu;
import primalsiege.torres.TorreSquirtle;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class MenuUpgrade {

    private Torre Tselect;
    private VBox MenuR;
    private boolean status;
    private int evento;
    private Group camadaMenu;

    public MenuUpgrade() {
        this.status = false;
        this.evento = 0;
    }

    public void abrirtorre(Torre torreS, int X, int Y) {

        if (status) {
            boolean mesmaTorre = (Tselect == torreS);
            fecharMenu();

            if (mesmaTorre) {
                return;
            }
        }

        this.Tselect = torreS;
        this.evento = 0;

        String nome = getNomePokemon(torreS).toUpperCase();

        MenuR = new VBox(12);
        MenuR.setPadding(new Insets(14));
        MenuR.setPrefWidth(376);
        MenuR.setMaxWidth(376);

        MenuR.setLayoutX(10);
        MenuR.setLayoutY(40);

        MenuR.setStyle(
            "-fx-background-color: #17212B;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #293847;" +
            "-fx-border-radius: 18;"
        );

        // CABEÇALHO
        Label categoria = texto(
            "PRIMAL SIEGE • TORRES", 11, "#D6A75F"
        );

        Label titulo = texto(nome, 20, "#F5F5F5");

        Label nivel = texto(
            "NÍVEL " + torreS.getNivel() + " / 3",
            12, "#99A9BA"
        );

        VBox info = new VBox(5, titulo, nivel);

        String caminhoImagem = getImagemPokemon(torreS);

        ImageView imagemPokemon = new ImageView();

        if (caminhoImagem != null) {
            Image img = new Image(getClass().getResourceAsStream(caminhoImagem));
            imagemPokemon.setImage(img);
        }

        imagemPokemon.setFitWidth(38);
        imagemPokemon.setFitHeight(38);
        imagemPokemon.setPreserveRatio(true);

        StackPane icone = new StackPane(imagemPokemon);
        icone.setPrefSize(52, 52);
        icone.setMinSize(52, 52);
        icone.setMaxSize(52, 52);

        icone.setStyle(
            "-fx-background-color: #273847;" +
            "-fx-background-radius: 13;"
        );



        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        HBox cabecalho = new HBox(10, info, espaco, icone);
        cabecalho.setAlignment(Pos.CENTER_LEFT);

        Region divisoria = new Region();
        divisoria.setPrefHeight(1);
        divisoria.setStyle("-fx-background-color: #405062;");

        // ATRIBUTOS
        HBox atributos1 = new HBox(8,
            criarAtributo("DANO",
                String.format("%.0f", torreS.getDano())),
            criarAtributo("ATAQUES/S",
                String.format(Locale.forLanguageTag("pt-BR"),
                    "%.1f", torreS.getVelocidadeAtaque()))
        );

        HBox atributos2 = new HBox(8,
            criarAtributo("ALCANCE",
                String.format("%.0f", (double) torreS.getAlcance()) + " px"),
            criarAtributo("HABILIDADE", NomePoder(torreS))
        );

        // PRÓXIMA EVOLUÇÃO
        Label evolucao = texto(
            "PRÓXIMA EVOLUÇÃO", 16, "#F5F5F5"
        );

        Label proximoNivel = texto(
            "NÍVEL " + Math.min(torreS.getNivel() + 1, 3),
            12, "#D6A75F"
        );

        Region espaco2 = new Region();
        HBox.setHgrow(espaco2, Priority.ALWAYS);

        HBox linhaEvolucao = new HBox(
            evolucao, espaco2, proximoNivel
        );

        Label descricao = texto(
            "Mais dano, maior alcance e melhoria da habilidade.",
            14, "#C6D0DA"
        );
        descricao.setWrapText(true);

        VBox quadroEvolucao = new VBox(
            12, linhaEvolucao, descricao
        );

        quadroEvolucao.setPadding(new Insets(14));
        quadroEvolucao.setStyle(
            "-fx-background-color: #273645;" +
            "-fx-background-radius: 10;"
        );

        // BOTÕES
        // BOTÕES
        int custo = getCustoEvolucao(torreS);

        Button botaoEvoluir = criarBotao(
            "EVOLUIR TORRE",
            custo == -1 ? "NÍVEL MÁXIMO" : "Custo: " + custo,
            "#AE233B",
            1
        );

        if (custo == -1) {
            botaoEvoluir.setDisable(true);
        }

        if (torreS.getNivel() >= 3) {
            botaoEvoluir.setDisable(true);
        }

        Button botaoVender = criarBotao(
            "VENDER TORRE",
            "Valor: " + (int) Math.round(torreS.getvalor() * 0.60),
            "#344C63",
            2
        );


        MenuR.getChildren().addAll(
            categoria,
            cabecalho,
            divisoria,
            atributos1,
            atributos2,
            quadroEvolucao,
            botaoEvoluir,
            botaoVender
        );

        camadaMenu = new Group();

        // Área transparente cobrindo a tela
        Rectangle fundo = new Rectangle(
            0, 0,
            FXGL.getAppWidth(),
            FXGL.getAppHeight()
        );

        fundo.setFill(Color.TRANSPARENT);

        // Clicar fora do painel fecha o menu
        fundo.setOnMouseClicked(event -> {
            evento = 0;
            fecharMenu();
        });

        // Fundo atrás, menu na frente
        camadaMenu.getChildren().addAll(fundo, MenuR);

        FXGL.getGameScene().addUINode(camadaMenu);
        this.status = true;


    }

    // CRIAR CARTÃO DE ATRIBUTO
    private VBox criarAtributo(String nome, String valor) {

        Label titulo = texto(nome, 11, "#9BAABA");
        Label numero = texto(valor, 17, "#F5F5F5");

        VBox caixa = new VBox(6, titulo, numero);
        caixa.setPadding(new Insets(10));
        caixa.setPrefWidth(165);
        caixa.setMinHeight(64);
        caixa.setMaxWidth(Double.MAX_VALUE);

        caixa.setStyle(
            "-fx-background-color: #273645;" +
            "-fx-background-radius: 10;"
        );

        HBox.setHgrow(caixa, Priority.ALWAYS);

        return caixa;
    }

    // CRIAR BOTÃO
    private Button criarBotao(
        String titulo, String subtitulo,
        String cor, int acao
    ) {

        Label nome = texto(titulo, 16, "#FFFFFF");
        Label valor = texto(subtitulo, 12, "#E0E7ED");

        VBox conteudo = new VBox(5, nome, valor);
        conteudo.setAlignment(Pos.CENTER);

        Button botao = new Button();
        botao.setGraphic(conteudo);
        botao.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        botao.setPrefHeight(76);
        botao.setMaxWidth(Double.MAX_VALUE);
        botao.setCursor(Cursor.HAND);

        botao.setStyle(
            "-fx-background-color: " + cor + ";" +
            "-fx-background-radius: 8;"
        );

        botao.setOnAction(e -> {
            evento = acao;
            fecharMenu();
        });

        return botao;
    }

    // CRIAR TEXTO
    private Label texto(String conteudo, int tamanho, String cor) {

        Label label = new Label(conteudo);

        label.setStyle(
            "-fx-text-fill: " + cor + ";" +
            "-fx-font-size: " + tamanho + "px;" +
            "-fx-font-weight: bold;"
        );

        return label;
    }

    public void fecharMenu() {
        if (camadaMenu != null) {
            FXGL.getGameScene().removeUINode(camadaMenu);
            camadaMenu = null;
        }

        MenuR = null;
        this.status = false;
    }

    public boolean getStatus() {
        return this.status;
    }

    public int getevento() {
        return this.evento;
    }

    public void resetEvento() {
        this.evento = 0;
    }

    public Torre getTselect() {
        return this.Tselect;
    }

    private int getCustoEvolucao(Torre torre) {
        int nivel = torre.getNivel();

        if (nivel >= 3) {
            return -1;
        }

        if (torre instanceof TorreCharmander) {
            if (nivel == 1) {
                return 375;
            }

            else {
                return 480;
            }
        }

        else if (torre instanceof TorreSquirtle) {
            if (nivel == 1) {
                return 440;
            }

            else {
                return 630;
            }
        }

        else if (torre instanceof TorrePikachu) {
            if (nivel == 1) {
                return 440;
            }

            else {
                return 570;
            }
        }

        else if (torre instanceof TorreBulbassauro) {
            if (nivel == 1) {
                return 360;
            }

            else {
                return 495;
            }
        }

        return -1;
    }

    private String formatarNumero(double valor) {
        DecimalFormat formato = new DecimalFormat(
            "0.##",
            DecimalFormatSymbols.getInstance(Locale.forLanguageTag("pt-BR"))
        );

        return formato.format(valor);
    }

    private String getNomePokemon(Torre torre) {

        int nivel = torre.getNivel();

        if (torre instanceof TorreCharmander) {
            if (nivel == 1) {
                return "Charmander";
            }
            else if (nivel == 2) {
                return "Charmeleon";
            }
            else {
                return "Charizard";
            }
        }
        else if (torre instanceof TorreSquirtle) {
            if (nivel == 1) {
                return "Squirtle";
            }
            else if (nivel == 2) {
                return "Wartortle";
            }
            else {
                return "Blastoise";
            }
        }
        else if (torre instanceof TorrePikachu) {
            if (nivel == 1) {
                return "Pichu";
            }
            else if (nivel == 2) {
                return "Pikachu";
            }
            else {
                return "Raichu";
            }
        }
        else if (torre instanceof TorreBulbassauro) {
            if (nivel == 1) {
                return "Bulbassauro";
            }
            else if (nivel == 2) {
                return "Ivysaur";
            }
            else {
                return "Venusaur";
            }
        }

        return "Desconhecido";
    }

    private String getImagemPokemon(Torre torre) {
        int nivel = torre.getNivel();

        if (torre instanceof TorreCharmander) {
            return switch (nivel) {
                case 1 -> "/assets/textures/charmander.png";
                case 2 -> "/assets/textures/charmeleon.png";
                case 3 -> "/assets/textures/charizard.png";
                default -> null;
            };
        }
        else if (torre instanceof TorreSquirtle) {
            return switch (nivel) {
                case 1 -> "/assets/textures/squirtle.png";
                case 2 -> "/assets/textures/wartortle.png";
                case 3 -> "/assets/textures/blastoise.png";
                default -> null;
            };
        }
        else if (torre instanceof TorrePikachu) {
            return switch (nivel) {
                case 1 -> "/assets/textures/pichu.png";
                case 2 -> "/assets/textures/pikachu.png";
                case 3 -> "/assets/textures/raichu.png";
                default -> null;
            };
        }
        else if (torre instanceof TorreBulbassauro) {
            return switch (nivel) {
                case 1 -> "/assets/textures/bulbasaur.png";
                case 2 -> "/assets/textures/ivysaur.png";
                case 3 -> "/assets/textures/venusaur.png";
                default -> null;
            };
        }

        return null;
    }

    private String NomePoder(Torre torre) {

        if (torre.getNivel() == 1) {
            return "Nenhuma";
        }

        if (torre instanceof TorreCharmander) {
            return "Queimadura";
        }
        else if (torre instanceof TorreSquirtle) {
            return "Hidro Bomba";
        }
        else if (torre instanceof TorreBulbassauro) {
            return "Lentidão";
        }
        else if (torre instanceof TorrePikachu) {
            return "Descarga Elétrica";
        }

        return "Desconhecida";
    }
}
