package primalsiege.modelo;
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
    private int valort;

    public Torre (float dano, double alcance, double x, double y, float velocidadeAtaque, int custoEvolucao, Color corProj, int velProj, int Projrad, int valor) {
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
        this.valort = valor;
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

    public double getRaioDescarga() {
        return 90;
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

    public void atualizarRecarga(double tpf) {
        cdr -= tpf;

        if (cdr < -tpf) {
            cdr = -tpf;
        }
    }

    public Projetil atirar(Inimigo alvo) {

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
            tiro.setDanoQueimadura(getDanoQueimadura());

            tiro.setPercentualLentidao(getPercentualLentidao());

            tiro.setRaioDescarga(getRaioDescarga());

            tiro.setVisualFogo(usaVisualFogo());
            tiro.setNivelVisualFogo(getNivel());

            tiro.setVisualAgua(usaVisualAgua());
            tiro.setNivelVisualAgua(getNivel());

            tiro.setVisualPlanta(usaVisualPlanta());
            tiro.setNivelVisualPlanta(getNivel());

            cdr += 1.0 / velocidadeAtaque;

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

    public int getDanoQueimadura() {
        return 0;
    }

    public boolean usaRaioInstantaneo() {
        return false;
    }

    public double getPercentualLentidao() {
        return 0;
    }

    protected void definirAtributosAtaque(float dano, float velocidadeAtaque) {
        this.dano = dano;
        this.velocidadeAtaque = velocidadeAtaque;
    }

    protected void definirAtributosProjetil(Color cor, int velocidade, int raio) {
        this.corProj = cor;
        this.velProj = velocidade;
        this.Projrad = raio;
    }

    public boolean usaVisualFogo() {
        return false;
    }

    public boolean usaVisualAgua() {
        return false;
    }

    public boolean usaVisualPlanta() {
        return false;
    }
    public int somaValor(int valor) {
        valort += valor;
        return valort;
    }

    public int getvalor() {
        return valort;
    }

}