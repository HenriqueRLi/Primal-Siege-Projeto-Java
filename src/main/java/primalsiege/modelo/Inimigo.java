package primalsiege.modelo;
public abstract class Inimigo {
    private float vida;
    private double velocidadeMovimentacao;
    private int danoBase;
    private String tipoInimigo;
    private double x;
    private double y;
    private int indicePonto;
    private int recompensa;
    private Mapa mapa;
    private int danoQueimadura;
    private double tempoQueimadura;
    private double acumuladorQueimadura;
    private double percentualLentidao;
    private double tempoLentidao;

    public Inimigo (float vida, double velocidadeMovimentacao, int danoBase, String tipoInimigo, Mapa mapa) {
        this.vida = vida;
        this.velocidadeMovimentacao = velocidadeMovimentacao;
        this.danoBase = danoBase;
        this.tipoInimigo = tipoInimigo;
        this.indicePonto = 1;
        this.mapa = mapa;
        int celulaInicial = mapa.getCaminho().get(0);
        this.x = (celulaInicial % 32) * 40 + 20;
        this.y = (celulaInicial / 32) * 40 + 20;
        danoQueimadura = 0;
        tempoQueimadura = 0;
        acumuladorQueimadura = 0;
        percentualLentidao = 0;
        tempoLentidao = 0;
    }
    
    public int getRecompensa () {
        return recompensa;
    }

    protected void definirRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }

    public void danoRecebido(float dano){
        vida -= dano;

        if(vida < 0){
            vida = 0;
        }
    }

    public boolean statusVida(){
        if(vida <= 0.0){
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
        double passo = velocidadeMovimentacao * (1.0 - percentualLentidao) * tpf;
        
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

    public void aplicarQueimadura(int dano) {
        if (tempoQueimadura <= 0) {
            acumuladorQueimadura = 0;
            danoQueimadura = dano;
        }
        else {
            danoQueimadura = Math.max(danoQueimadura, dano);
        }

        tempoQueimadura = 2.0;
    }

    public void atualizarQueimadura(double tpf) {
        if (tempoQueimadura <= 0 || !statusVida()) {
            return;
        }

        double tempoPassado = Math.min(tpf, tempoQueimadura);

        tempoQueimadura -= tempoPassado;
        acumuladorQueimadura += tempoPassado;

        while (acumuladorQueimadura >= 1.0 && statusVida()) {
            danoRecebido(danoQueimadura);
            acumuladorQueimadura -= 1.0;
        }
    }

    public boolean estaQueimando() {
        return tempoQueimadura > 0 && statusVida();
    }

    public void aplicarLentidao(double percentual) {
        if (percentual <= 0 || !statusVida()) {
            return;
        }

        percentualLentidao = Math.max(percentualLentidao, percentual);
        tempoLentidao = 2.0;
    }

    public void atualizarLentidao(double tpf) {
        if (tempoLentidao > 0) {
            tempoLentidao -= tpf;

            if (tempoLentidao <= 0) {
                tempoLentidao = 0;
                percentualLentidao = 0;
            }
        }
    }

    public boolean estaLento() {
        return tempoLentidao > 0 && statusVida();
    }
}
