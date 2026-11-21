package primalsiege.interfacegrafica;
import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import primalsiege.modelo.Torre;

public class MenuUpgrade {
    private double valor = 0;
    private Torre Tselect;
    private int oprad = 15;
    private int dist = 40;
    private Group MenuR;
    private boolean status;
    private int evento;


    public MenuUpgrade() {
        this.MenuR = new Group();
        this.status = false;
        this.evento = 3;
    }


    public void abrirtorre(Torre torreS, int X, int Y) {
        this.Tselect = torreS;

            int centroX = X * 40 + 20;
            int centroY = Y * 40 + 20;

            if (centroX + dist + oprad > 220 && centroX - dist - oprad < 1060 && centroY + dist + oprad > 615) {
                centroY = 615 - dist - oprad - 10;
            }

            Circle opc1 = new Circle(centroX - dist, centroY, oprad);
            opc1.setFill(Color.RED);
            opc1.setOnMouseClicked(event -> {
                evento = 1;
            });

            Circle opc2 = new Circle(centroX + dist, centroY, oprad);
            opc2.setFill(Color.BLUE);
            opc2.setOnMouseClicked(event -> {
                evento = 2;
            });

            Circle opc3 = new Circle(centroX , centroY, oprad - 5);
            opc3.setFill(Color.GREY);
            opc3.setOnMouseClicked(event -> {
                fecharMenu();
            });



            if(status == false) {
            MenuR.getChildren().addAll(opc1,opc2,opc3);
            FXGL.getGameScene().addUINode(MenuR);
            this.status = true;
        }
        else {
            fecharMenu();
        }
    }


    public void fecharMenu() {
        MenuR.getChildren().clear();
        FXGL.getGameScene().removeUINode(MenuR);
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

}