import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MenuTorre {
    private Group MenuR;
    private boolean status;
    private int Tselect;

    public MenuTorre() {
        this.MenuR = new Group();
        
        this.status = false;
    }

    public void AbrirMenu(int linha, int coluna) {
        int centroX = coluna * 40 + 20;
        int centroY = linha * 40 + 20;

        Circle opc1 = new Circle(centroX, centroY - 60, 20);
        opc1.setFill(Color.RED);
        opc1.setOnMouseClicked(event -> {
            Tselect = 1;
        });

        Circle opc2 = new Circle(centroX, centroY + 60, 20);
        opc2.setFill(Color.BLUE);
        opc2.setOnMouseClicked(event -> {
            Tselect = 2;
        });

        Circle opc3 = new Circle(centroX - 60, centroY, 20);
        opc3.setFill(Color.YELLOW);
        opc3.setOnMouseClicked(event -> {
            Tselect = 3;
        });

        Circle opc4 = new Circle(centroX + 60, centroY, 20);
        opc4.setFill(Color.GREEN);
        opc4.setOnMouseClicked(event -> {
            Tselect = 4;
        });

        Circle opc0 = new Circle(centroX, centroY, 18);
        opc0.setFill(Color.GRAY); 


        if(status == false) {
            MenuR.getChildren().addAll(opc1,opc2,opc3,opc4,opc0);
            FXGL.getGameScene().addUINode(MenuR);
            this.status = true;
        }
        else {
            fecharMenu();
        }

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