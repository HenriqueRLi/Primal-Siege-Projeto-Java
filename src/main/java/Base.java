public class Base {

    private int vida;

    public Base () {
        this.vida = 20;
    }

    public boolean diminuirVida (int dano) {
        this.vida -= dano;
        
        if(this.vida < 1) {
            this.vida = 0;
            return true;
        }
        return false;
    }

    public int getVida () {
        return vida;
    }
}