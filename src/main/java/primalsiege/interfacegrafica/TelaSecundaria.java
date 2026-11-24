
package primalsiege.interfacegrafica;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TelaSecundaria extends Group {

    private Rectangle botaoVoltar;
    private Rectangle botaoHUD;
    private Text textoHUD;

    public TelaSecundaria(boolean opcoes, boolean hudVisivel) {

        Rectangle fundo = new Rectangle(1280, 720);
        fundo.setFill(Color.rgb(0, 0, 0, 0.78));

        Rectangle painel = new Rectangle(370, 125, 540, 490);
        painel.setArcWidth(30);
        painel.setArcHeight(30);
        painel.setFill(Color.web("#142230"));
        painel.setStroke(Color.web("#FF405C"));
        painel.setStrokeWidth(2);

        Text titulo = new Text(opcoes ? "OPÇÕES" : "COMO JOGAR");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        titulo.setFill(Color.WHITE);
        titulo.setX(640 - titulo.getLayoutBounds().getWidth() / 2);
        titulo.setY(200);

        getChildren().addAll(fundo, painel, titulo);

        if (opcoes) {

            Text descricao = new Text("VISIBILIDADE DA HUD DE ONDAS");
            descricao.setFont(Font.font("Arial", FontWeight.BOLD, 17));
            descricao.setFill(Color.web("#DCE8EF"));
            descricao.setX(640 - descricao.getLayoutBounds().getWidth() / 2);
            descricao.setY(285);

            botaoHUD = criarBotao(475, 315, 330, 60);

            textoHUD = new Text();
            textoHUD.setFont(Font.font("Arial", FontWeight.BOLD, 19));
            textoHUD.setFill(Color.WHITE);
            textoHUD.setMouseTransparent(true);

            atualizarHUD(hudVisivel);

            Text dica = new Text("Pressione H para recolher ou expandir a HUD.");
            dica.setFont(Font.font("Arial", 15));
            dica.setFill(Color.web("#91A9BA"));
            dica.setX(640 - dica.getLayoutBounds().getWidth() / 2);
            dica.setY(410);

            getChildren().addAll(
                descricao, botaoHUD, textoHUD, dica
            );

        } else {

        String[] instrucoes = {
            "1. Clique em INICIAR ONDA para enviar os inimigos.",
            "2. Clique na grama livre para construir uma torre.",
            "3. Escolha entre as quatro torres disponíveis.",
            "4. Clique em uma torre para evoluir ou vender.",
            "5. Elimine inimigos para conseguir mais ouro.",
            "6. Não deixe a vida da base chegar a zero.",
            "7. Sobreviva às 60 ondas para vencer!",
            "8. Pressione H para recolher ou expandir a HUD."
        };

            for (int i = 0; i < instrucoes.length; i++) {

                Text linha = new Text(instrucoes[i]);
                linha.setFont(Font.font("Arial", 16));
                linha.setFill(Color.web("#DCE8EF"));
                linha.setX(400);
                linha.setY(255 + i * 34);

                getChildren().add(linha);
            }
        }

        botaoVoltar = criarBotao(515, 540, 250, 55);

        Text textoVoltar = new Text("VOLTAR");
        textoVoltar.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoVoltar.setFill(Color.WHITE);
        textoVoltar.setX(
            640 - textoVoltar.getLayoutBounds().getWidth() / 2
        );
        textoVoltar.setY(575);
        textoVoltar.setMouseTransparent(true);

        getChildren().addAll(botaoVoltar, textoVoltar);
    }

    private Rectangle criarBotao(double x, double y,
                                 double largura, double altura) {

        Rectangle botao = new Rectangle(x, y, largura, altura);

        botao.setArcWidth(20);
        botao.setArcHeight(20);
        botao.setFill(Color.web("#203447"));
        botao.setStroke(Color.web("#668CA5"));
        botao.setStrokeWidth(2);
        botao.setCursor(Cursor.HAND);

        botao.setOnMouseEntered(evento ->
            botao.setStroke(Color.web("#FF405C"))
        );

        botao.setOnMouseExited(evento ->
            botao.setStroke(Color.web("#668CA5"))
        );

        return botao;
    }

    public void atualizarHUD(boolean visivel) {

        textoHUD.setText(
            visivel ? "HUD: VISÍVEL" : "HUD: OCULTA"
        );

        textoHUD.setX(
            640 - textoHUD.getLayoutBounds().getWidth() / 2
        );

        textoHUD.setY(352);
    }

    public Rectangle getBotaoVoltar() {
        return botaoVoltar;
    }

    public Rectangle getBotaoHUD() {
        return botaoHUD;
    }
}
