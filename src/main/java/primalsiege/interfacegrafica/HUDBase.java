package primalsiege.interfacegrafica;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import primalsiege.modelo.Base;

public class HUDBase {

    private Text textoVidaBase;
    private Rectangle barraVerde;

    public HUDBase(Base base) {

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

        Rectangle barraCinza = new Rectangle(210, 12);
        barraCinza.setX(1020);
        barraCinza.setY(65);
        barraCinza.setFill(Color.web("#4B5563"));
        barraCinza.setArcWidth(20);
        barraCinza.setArcHeight(20);

        barraVerde = new Rectangle(210, 12);
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

    public Text getTextoVidaBase() {
        return textoVidaBase;
    }

    public Rectangle getBarraVerde() {
        return barraVerde;
    }
}
