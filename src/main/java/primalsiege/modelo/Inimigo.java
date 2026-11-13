package primalsiege.modelo;
public abstract class Inimigo {
    private int vida;
    private double velocidadeMovimentacao;
    private int danoBase;
    private String tipoInimigo;
    private double x;
    private double y;
    private int indicePonto;
    private int recompensa;
    private Mapa mapa;

    public Inimigo (int vida, double velocidadeMovimentacao, int danoBase, String tipoInimigo, Mapa mapa) {
        this.vida = vida;
        this.velocidadeMovimentacao = velocidadeMovimentacao;
        this.danoBase = danoBase;
        this.tipoInimigo = tipoInimigo;
        this.indicePonto = 1;
        this.mapa = mapa;
        int celulaInicial = mapa.getCaminho().get(0);
        this.x = (celulaInicial % 32) * 40 + 20;
        this.y = (celulaInicial / 32) * 40 + 20;
    }
    
    public int getRecompensa () {
        return recompensa;
    }

    public void danoRecebido(float dano){
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

    public int danoAtaque() {
        return danoBase;
    }

    public boolean mover(double tpf) {
        int destino = mapa.getCaminho().get(indicePonto);
        int destinoX = (destino % 32) * 40 + 20;
        int destinoY = (destino / 32) * 40 + 20;
        double dx = destinoX - this.x;
        double dy = destinoY - this.y;
        double passo = velocidadeMovimentacao * tpf;
        
        if(dx != 0) {
            if(dx < 0) {
                this.x -= Math.min(passo, Math.abs(dx));
            }
            else
                this.x += Math.min(passo, dx);
        }
        if(dy != 0) {
            if(dy < 0)
                this.y -= Math.min(passo, Math.abs(dy));
            else
                this.y += Math.min(passo, dy);
        }
        if(dx == 0 && dy == 0) {
            indicePonto++;
            if(indicePonto == 10)
                return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndicePonto(){
        return indicePonto;
    }

    public String getTipoInimigo() {
        return tipoInimigo;
    }
}
