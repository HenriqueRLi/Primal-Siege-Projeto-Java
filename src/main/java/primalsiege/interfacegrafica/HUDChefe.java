
package primalsiege.interfacegrafica;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import primalsiege.modelo.Inimigo;

public class HUDChefe extends Group {

    private Rectangle barraVida;
    private Label nomeChefe;
    private Label textoVida;
    private float vidaMaxima;

    public HUDChefe() {

        setLayoutX(380);
        setLayoutY(10);
        setMouseTransparent(true);
        setViewOrder(-1);

        Rectangle fundo = new Rectangle(520, 80);
        fundo.setFill(Color.web("#17212B"));
        fundo.setArcWidth(20);
        fundo.setArcHeight(20);
        fundo.setStroke(Color.web("#9B2335"));

        nomeChefe = new Label();
        nomeChefe.setPrefWidth(520);
        nomeChefe.setAlignment(Pos.CENTER);
        nomeChefe.setLayoutY(5);
        nomeChefe.setStyle(
            "-fx-text-fill: #FBBF24;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Rectangle fundoBarra = new Rectangle(20, 33, 480, 18);
        fundoBarra.setFill(Color.web("#4B5563"));
        fundoBarra.setArcWidth(8);
        fundoBarra.setArcHeight(8);

        barraVida = new Rectangle(20, 33, 480, 18);
        barraVida.setFill(Color.web("#DC2626"));
        barraVida.setArcWidth(8);
        barraVida.setArcHeight(8);

        textoVida = new Label();
        textoVida.setPrefWidth(520);
        textoVida.setAlignment(Pos.CENTER);
        textoVida.setLayoutY(55);
        textoVida.setStyle(
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );

        getChildren().addAll(
            fundo, nomeChefe, fundoBarra,
            barraVida, textoVida
        );

        setVisible(false);
    }

    public void mostrar(Inimigo chefe) {

        vidaMaxima = chefe.getVida();

        nomeChefe.setText(
            "CHEFE - " + chefe.getTipoInimigo().toUpperCase()
        );

        atualizarVida(chefe.getVida());

        setVisible(true);
    }

    public void atualizarVida(float vida) {

        barraVida.setWidth(480.0 * vida / vidaMaxima);

        textoVida.setText(
            (int) Math.ceil(vida) + " / " +
            (int) vidaMaxima + " HP"
        );
    }

    public void ocultar() {
        setVisible(false);
    }
}
