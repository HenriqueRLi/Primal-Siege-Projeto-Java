public class Inimigo {
    private int vida;
    private double velocidadeMovimentacao;
    private int danoBase;
    private String tipoInimigo;
    private double x;
    private double y;
    private int indicePonto;
    private int recompensa;

    public Inimigo (int vida, double velocidadeMovimentacao, int danoBase, String tipoInimigo) {
        this.vida = vida;
        this.velocidadeMovimentacao = velocidadeMovimentacao;
        this.danoBase = danoBase;
        this.tipoInimigo = tipoInimigo;
        this.x = 0.0;
        this.y = 0.0;
        this.indicePonto = 0;
    }
    
    public int getRecompensa () {
        return recompensa;
    }

    public void danoRecebido(int dano){
        vida -= dano;

        if(vida < 0){
            vida = 0;
        }
    }

    public boolean statusVida(){
        if(vida <= 0){
            return false;
        }
        return true;
    }

    public int ouroDrop(int ouro){
        return ouro;
    }

    public int danoAtaque(boolean colisao){
        if(colisao){
            return danoBase;
        }
        return 0;
    }
}
