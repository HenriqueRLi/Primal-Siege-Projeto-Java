package primalsiege.interfacegrafica;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MenuTorre {

    private Group MenuR;
    private boolean status;
    private int Tselect;

    private int oprad = 25;
    private int dist = 40;

    public MenuTorre() {
        this.MenuR = new Group();
        this.status = false;
    }

    public void AbrirMenu(int linha, int coluna) {

        int centroX = coluna * 40 + 20;
        int centroY = linha * 40 + 20;

        if (centroX + dist + oprad > 220 &&
            centroX - dist - oprad < 1060 &&
            centroY + dist + oprad > 615) {

            centroY = 615 - dist - oprad - 10;
        }

        Group opc1 = criarBotaoPokemon(
            centroX, centroY - dist,
            "charmander.png", 1
        );

        Group opc2 = criarBotaoPokemon(
            centroX, centroY + dist,
            "squirtle.png", 2
        );

        Group opc3 = criarBotaoPokemon(
            centroX - dist, centroY,
            "pichu.png", 3
        );

        Group opc4 = criarBotaoPokemon(
            centroX + dist, centroY,
            "bulbasaur.png", 4
        );

        Group opc0 = criarBotaoFechar(centroX, centroY);

        if (status == false) {

            MenuR.getChildren().addAll(
                opc1, opc2, opc3, opc4, opc0
            );

            FXGL.getGameScene().addUINode(MenuR);
            this.status = true;

        } else {
            fecharMenu();
        }
    }

    // Desenha a Pokébola no estilo da imagem
    private Group criarPokebola(double x, double y, double raio) {

        Group pokebola = new Group();

        Color vermelho = Color.web("#FF4D58");

        // Fundo escuro semitransparente
        Circle fundo = new Circle(x, y, raio);
        fundo.setFill(Color.rgb(20, 22, 32, 0.65));
        fundo.setStroke(vermelho);
        fundo.setStrokeWidth(2);

        // Linha horizontal da Pokébola
        Line linha = new Line(
            x - raio, y,
            x + raio, y
        );
        linha.setStroke(vermelho);
        linha.setStrokeWidth(2);

        // Círculo central
        Circle centro = new Circle(x, y, raio * 0.32);
        centro.setFill(Color.rgb(20, 22, 32, 0.9));
        centro.setStroke(vermelho);
        centro.setStrokeWidth(2);

        pokebola.getChildren().addAll(
            fundo, linha, centro
        );

        return pokebola;
    }

    // Botão com Pokémon sobre a Pokébola
    private Group criarBotaoPokemon(
        int x, int y, String imagem, int selecao
    ) {

        Group botao = new Group();

        // Pokébola atrás
        Group fundo = criarPokebola(x, y, oprad-7);

        // Imagem do Pokémon por cima
        ImageView pokemon = new ImageView(FXGL.image(imagem));

        pokemon.setFitWidth(36);
        pokemon.setFitHeight(36);
        pokemon.setPreserveRatio(true);
        pokemon.setSmooth(false);

        pokemon.setX(x - pokemon.getBoundsInLocal().getWidth() / 2);
        pokemon.setY(y - pokemon.getBoundsInLocal().getHeight() / 2);

        botao.getChildren().addAll(fundo, pokemon);

        botao.setOnMouseClicked(event -> {
            Tselect = selecao;
            event.consume();
        });

        return botao;
    }

    // Pokébola central com X
    private Group criarBotaoFechar(int x, int y) {

        Group botao = new Group();

        int raio = oprad - 12;

        Group fundo = criarPokebola(x, y, raio);

        // X branco
        Line linha1 = new Line(
            x - 4, y - 4,
            x + 4, y + 4
        );

        Line linha2 = new Line(
            x + 4, y - 4,
            x - 4, y + 4
        );

        linha1.setStroke(Color.BLACK);
        linha2.setStroke(Color.BLACK);

        linha1.setStrokeWidth(2.5);
        linha2.setStrokeWidth(2.5);

        botao.getChildren().addAll(
            fundo, linha1, linha2
        );

        botao.setOnMouseClicked(event -> {
            fecharMenu();
            event.consume();
        });

        return botao;
    }

    public int getTselect() {
        return this.Tselect;
    }

    public void resetselect() {
        this.Tselect = 0;
    }

    public void fecharMenu() {
        MenuR.getChildren().clear();
        FXGL.getGameScene().removeUINode(MenuR);
        this.status = false;
    }

    public boolean getStatus() {
        return this.status;
    }
}