package primalsiege.modelo;
public class Projetil {
    private double x;
    private double y;
    private Inimigo alvo;
    private float dano;
    private double velocidade;
    private double raio;
    private double taxaCrescimento;
    private int danoQueimadura;
    private int danoExtraHidroBomba;

    public Projetil(float dano, double velocidade, double x, double y, Inimigo alvo, double raio, double taxaCrescimento) {
        this.dano = dano;
        this.velocidade = velocidade;
        this.x = x;
        this.y = y;
        this.alvo = alvo;
        this.raio = raio;
        this.taxaCrescimento = taxaCrescimento;
        danoQueimadura = 0;
        danoExtraHidroBomba = 0;
    }

    public void definirDanoExtraHidroBomba(int dano) {
        this.danoExtraHidroBomba = dano;
    }
    
    public void definirDanoQueimadura(int dano) {
        this.danoQueimadura = dano;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Inimigo getAlvo(){
        return alvo;
    }

    public boolean moverTiro(double tpf){
        if(!alvo.statusVida()){
            return true;
        }
        double dx = alvo.getX() - this.x;
        double dy = alvo.getY() - this.y;

        double distance = Math.hypot(dx, dy);
        double passo = velocidade * tpf;

        if(distance <= passo){
            this.x = alvo.getX();
            this.y = alvo.getY();
            alvo.danoRecebido(this.dano + danoExtraHidroBomba);
            if (danoQueimadura > 0 && alvo.statusVida()) {
                alvo.aplicarQueimadura(danoQueimadura);
            }
            return true;
        }
        this.x += (dx/distance) * passo;
        this.y += (dy/distance) * passo;
        this.raio += taxaCrescimento * tpf;
        
        return false;
    }

    public double getRaio() {
        return raio;
    }

    public boolean ehHidroBomba() {
        return danoExtraHidroBomba > 0;
    }


}
