
package primalsiege.interfacegrafica;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import primalsiege.modelo.Torre;

public class RenderizadorTorres {
    private ArrayList<Texture> imagensTorres = new ArrayList<>();

    public void desenharTorre(Torre torre, int select,
                             ArrayList<Torre> torres,
                             ArrayList<Polygon> triangulosTorres,
                             HUDOnda hudOnda) {

        Polygon triangulo = new Polygon(
            torre.getX(), torre.getY() - 15,
            torre.getX() - 15, torre.getY() + 15,
            torre.getX() + 15, torre.getY() + 15
        );

        switch (select) {
            case 1:
                triangulo.setFill(Color.RED);
                break;
            case 2:
                triangulo.setFill(Color.BLUE);
                break;
            case 3:
                triangulo.setFill(Color.YELLOW);
                break;
            case 4:
                triangulo.setFill(Color.GREEN);
                break;
            default:
                break;
        }

        torres.add(torre);
        triangulosTorres.add(triangulo);

        FXGL.getGameScene().addUINode(triangulo);

    if (select == 1) {
        triangulo.setVisible(false);

        Texture imagem = FXGL.getAssetLoader().loadTexture("charmander.png");

        imagem.setFitWidth(56);
        imagem.setFitHeight(56);

        imagem.setLayoutX(torre.getX() - 26);
        imagem.setLayoutY(torre.getY() - 36);       
        imagem.setMouseTransparent(true);

        FXGL.getGameScene().addUINode(imagem);
        imagensTorres.add(imagem);
    }
    else if (select == 2) {
        triangulo.setVisible(false);

        Texture imagem = FXGL.getAssetLoader().loadTexture("squirtle.png");

        imagem.setFitWidth(56);
        imagem.setFitHeight(56);
        imagem.setSmooth(false);

        imagem.setLayoutX(torre.getX() - 26);
        imagem.setLayoutY(torre.getY() - 36);

        imagem.setMouseTransparent(true);

        FXGL.getGameScene().addUINode(imagem);
        imagensTorres.add(imagem);
    }
    else if (select == 3) {
        triangulo.setVisible(false);

        Texture imagem = FXGL.getAssetLoader().loadTexture("pichu.png");

        imagem.setFitWidth(56);
        imagem.setFitHeight(56);
        imagem.setSmooth(false);

        imagem.setLayoutX(torre.getX() - 26);
        imagem.setLayoutY(torre.getY() - 36);

        imagem.setMouseTransparent(true);

        FXGL.getGameScene().addUINode(imagem);
        imagensTorres.add(imagem);
    }
    else if (select == 4) {
        triangulo.setVisible(false);

        Texture imagem = FXGL.getAssetLoader().loadTexture("bulbasaur.png");

        imagem.setFitWidth(56);
        imagem.setFitHeight(56);
        imagem.setSmooth(false);

        imagem.setLayoutX(torre.getX() - 26);
        imagem.setLayoutY(torre.getY() - 36);

        imagem.setMouseTransparent(true);

        FXGL.getGameScene().addUINode(imagem);
        imagensTorres.add(imagem);
    }

    hudOnda.toFront();
    }

    public void limparImagens() {
        for (Texture imagem : imagensTorres) {
            FXGL.getGameScene().removeUINode(imagem);
        }
        imagensTorres.clear();
    }

    public void trocarSprite(Torre torre, ArrayList<Torre> torres, String arquivo) {
        int indice = torres.indexOf(torre);

        if (indice == -1) {
            return;
        }

        Texture imagem = imagensTorres.get(indice);

        imagem.setImage(
            FXGL.getAssetLoader().loadTexture(arquivo).getImage()
        );
    }
}
