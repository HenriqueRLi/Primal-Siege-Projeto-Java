
package primalsiege.interfacegrafica;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;

import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import primalsiege.TesteFXGL;

public class TelaPausa extends FXGLMenu {

    private final Rectangle botaoContinuar;
    private final Rectangle botaoReiniciar;
    private final Rectangle botaoOpcoes;
    private final Rectangle botaoMenuPrincipal;

    public TelaPausa(TesteFXGL jogo) {

        super(MenuType.GAME_MENU);

        Rectangle fundo = new Rectangle(1280, 720);
        fundo.setFill(Color.rgb(5, 10, 19, 0.92));

        Rectangle painel = new Rectangle(385, 105, 510, 525);
        painel.setArcWidth(30);
        painel.setArcHeight(30);
        painel.setFill(Color.web("#142230"));
        painel.setStroke(Color.web("#FF405C"));
        painel.setStrokeWidth(2);
        painel.setEffect(
            new DropShadow(25, Color.rgb(0, 0, 0, 0.7))
        );

        Text titulo = new Text("JOGO PAUSADO");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        titulo.setFill(Color.WHITE);
        titulo.setX(
            640 - titulo.getLayoutBounds().getWidth() / 2
        );
        titulo.setY(200);

        titulo.setEffect(
            new DropShadow(14, Color.web("#FF405C"))
        );

        Rectangle detalhe = new Rectangle(520, 223, 240, 4);
        detalhe.setArcWidth(4);
        detalhe.setFill(Color.web("#FF405C"));

        getContentRoot().getChildren().addAll(
            fundo, painel, titulo, detalhe
        );

        botaoContinuar = criarBotao("CONTINUAR", 270, true);
        botaoReiniciar = criarBotao("REINICIAR", 350, false);
        botaoOpcoes = criarBotao("OPÇÕES", 430, false);
        botaoMenuPrincipal = criarBotao("MENU PRINCIPAL", 510, false);

        botaoContinuar.setOnMouseClicked(evento -> fireResume());

        botaoReiniciar.setOnMouseClicked(evento -> {
            jogo.reiniciarPelaPausa();
            fireResume();
        });

        botaoOpcoes.setOnMouseClicked(evento -> {
            abrirOpcoes(jogo);
        });

        botaoMenuPrincipal.setOnMouseClicked(evento -> {
            jogo.voltarAoMenuPrincipal();
            fireResume();
        });
    }

    private Rectangle criarBotao(String nome, double y,
                                 boolean principal) {

        Rectangle botao = new Rectangle(465, y, 350, 60);

        botao.setArcWidth(20);
        botao.setArcHeight(20);

        if (principal) {
            botao.setFill(Color.web("#792336"));
            botao.setStroke(Color.web("#FF405C"));
        }
        else {
            botao.setFill(Color.web("#1D3041"));
            botao.setStroke(Color.web("#668CA5"));
        }

        botao.setStrokeWidth(2);
        botao.setCursor(Cursor.HAND);

        Text texto = new Text(nome);
        texto.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        texto.setFill(Color.WHITE);
        texto.setX(
            640 - texto.getLayoutBounds().getWidth() / 2
        );
        texto.setY(y + 38);
        texto.setMouseTransparent(true);

        botao.setOnMouseEntered(evento -> {
            botao.setStrokeWidth(3);
            botao.setEffect(
                new DropShadow(14, Color.web("#FF405C"))
            );
        });

        botao.setOnMouseExited(evento -> {
            botao.setStrokeWidth(2);
            botao.setEffect(null);
        });

        getContentRoot().getChildren().addAll(botao, texto);

        return botao;
    }

    public Rectangle getBotaoReiniciar() {
        return botaoReiniciar;
    }

    public Rectangle getBotaoOpcoes() {
        return botaoOpcoes;
    }

    public Rectangle getBotaoMenuPrincipal() {
        return botaoMenuPrincipal;
    }

    private void abrirOpcoes(TesteFXGL jogo) {

        TelaSecundaria opcoes = new TelaSecundaria(
            true,
            jogo.hudOndasVisivel()
        );

        opcoes.getBotaoHUD().setOnMouseClicked(evento -> {

            boolean visivel = jogo.alternarVisibilidadeHUD();

            opcoes.atualizarHUD(visivel);
        });

        opcoes.getBotaoVoltar().setOnMouseClicked(evento -> {
            getContentRoot().getChildren().remove(opcoes);
        });

        getContentRoot().getChildren().add(opcoes);
    }

}
