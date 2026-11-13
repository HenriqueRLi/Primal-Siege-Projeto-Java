import javafx.scene.paint.Color;

public class Torre {
    private float dano;
    private double alcance;
    private double x;
    private double y;
    private double rotacao;
    private int nivel;
    private float velocidadeAtaque;
    private int custoEvolucao;
    private double cdr;
    private Color corProj;
    private int velProj;
    private int Projrad;

    public Torre (float dano, double alcance, double x, double y, float velocidadeAtaque, int custoEvolucao, Color corProj, int velProj, int Projrad) {
        this.dano = dano;
        this.alcance = alcance;
        this.x = x;
        this.y = y;
        this.rotacao = 0.0;
        this.nivel = 1;
        this.velocidadeAtaque = velocidadeAtaque;
        this.custoEvolucao = custoEvolucao;
        this.cdr = 0;
        this.corProj = corProj;
        this.velProj = velProj;
        this.Projrad = Projrad;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }
    
    public float getDano() {
        return dano;
    }

    public double getAlcance () {
        return alcance;
    }

    public int getNivel () {
        return nivel;
    }

    public double getVelocidadeAtaque () {
        return velocidadeAtaque;
    }

    public int getCustoEvolucao () {
        return custoEvolucao;
    }

    public double getRotacao () {
        return rotacao;
    }

    public boolean evoluir () {
        if(this.nivel < 3) {
            this.nivel ++;
            return true;
        }
        return false; 
    }

    public void atualizarRotacao(double inimigoX, double inimigoY) {
        double dx = inimigoX - this.x;
        double dy = inimigoY - this.y;

        this.rotacao = Math.toDegrees(Math.atan2(dy, dx));
    }

    public void definirPosicao(double X, double Y) {
        this.x = X;
        this.y = Y;
    }

    public boolean estaNoAlcance(Inimigo inimigo) {
        double dx = inimigo.getX() - this.x;
        double dy = inimigo.getY() - this.y;

        double distance = Math.hypot(dx, dy);

        return distance <= this.alcance;
    }

    public Projetil atirar(Inimigo alvo, double tpf) {
        cdr -= tpf;

        if (cdr <= 0 && alvo.statusVida() && estaNoAlcance(alvo)) {
            Projetil tiro = new Projetil(
                this.dano,
                this.velProj,
                this.x,
                this.y,
                alvo,
                this.Projrad,
                getTaxaCrescimento()
            );

            cdr = 1.0 / velocidadeAtaque;

            return tiro;
        }

        return null;
    }

    public Color getcorProj() {
        return corProj;
    }

    public int getProjrad(Inimigo inimigo) {
        return Projrad;
    }

    public double getDist(Inimigo inimigo) {
        double dx = inimigo.getX() - this.x;
        double dy = inimigo.getY() - this.y;

        return Math.hypot(dx, dy);
    }

    public double getTaxaCrescimento() {
        return 0;
    }





}