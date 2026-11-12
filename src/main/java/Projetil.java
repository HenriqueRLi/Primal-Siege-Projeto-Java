public class Projetil {
    private double x;
    private double y;
    private Inimigo alvo;
    private int dano;
    private double velocidade;

    public Projetil(int dano, double velocidade, double x, double y, Inimigo alvo) {
        this.dano = dano;
        this.velocidade = velocidade;
        this.x = x;
        this.y = y;
        this.alvo = alvo;
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
            alvo.danoRecebido(this.dano);
            return true;
        }
        this.x += (dx/distance) * passo;
        this.y += (dy/distance) * passo;
        return false;
    }


}
