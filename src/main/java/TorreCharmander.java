import javafx.scene.paint.Color;

public class TorreCharmander extends Torre{

    public TorreCharmander(double x, double y) {
        super(1, 150, x, y, 100, 50,Color.RED,1000,1);
    }
    
    @Override
    public double getTaxaCrescimento() {
        return 100;
    }


}
