package primalsiege.interfacegrafica;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

import primalsiege.TesteFXGL;

public class FabricaMenus extends SceneFactory {

    private TesteFXGL jogo;

    public FabricaMenus(TesteFXGL jogo) {
        this.jogo = jogo;
    }

    @Override
    public FXGLMenu newGameMenu() {
        return new TelaPausa(jogo);
    }
}