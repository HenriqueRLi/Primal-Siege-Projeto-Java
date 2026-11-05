public class Torre {
    private int dano;
    private double alcance;
    private double x;
    private double y;
    private double rotacao;
    private int nivel;
    private double velocidadeAtaque;
    private int custoEvolucao;

    public Torre (int dano, double alcance, double x, double y, double velocidadeAtaque, int custoEvolucao) {
        this.dano = dano;
        this.alcance = alcance;
        this.x = x;
        this.y = y;
        this.rotacao = 0.0;
        this.nivel = 1;
        this.velocidadeAtaque = velocidadeAtaque;
        this.custoEvolucao = custoEvolucao;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }
    
    public int getDano() {
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
}