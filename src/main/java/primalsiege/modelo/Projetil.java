package primalsiege.modelo;

import java.util.ArrayList;

import javafx.geometry.Point2D;

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
    private int danoDescarga;
    private ArrayList<Point2D> pontosDescarga = new ArrayList<>();
    private ArrayList<Inimigo> alvosDescarga = new ArrayList<>();
    private double percentualLentidao;
    private double raioDescarga;
    private boolean visualFogo;
    private boolean visualAgua;
    private int nivelVisualFogo;
    private int nivelVisualAgua;

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
        danoDescarga = 0;
        percentualLentidao = 0;
        raioDescarga = 90; 
        visualFogo = false;
        visualAgua = false;
        nivelVisualFogo = 0;
        nivelVisualAgua = 0;
    }

    public void setVisualAgua(boolean visualAgua) {
        this.visualAgua = visualAgua;
    }

    public boolean ehVisualAgua() {
        return visualAgua;
    }

    public void setNivelVisualAgua(int nivel) {
        this.nivelVisualAgua = nivel;
    }

    public int getNivelVisualAgua() {
        return nivelVisualAgua;
    }

    public void setNivelVisualFogo(int nivel) {
        this.nivelVisualFogo = nivel;
    }

    public int getNivelVisualFogo() {
        return nivelVisualFogo;
    }

    public void setVisualFogo(boolean visualFogo) {
        this.visualFogo = visualFogo;
    }

    public boolean ehVisualFogo() {
        return visualFogo;
    }

    public void setRaioDescarga(double raio) {
        this.raioDescarga = raio;
    }

    public void setPercentualLentidao(double percentual) {
        this.percentualLentidao = percentual;
    }
    
    public void setDanoDescarga(int dano) {
    this.danoDescarga = dano;
}

    public void setDanoExtraHidroBomba(int dano) {
        this.danoExtraHidroBomba = dano;
    }
    
    public void setDanoQueimadura(int dano) {
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

    public ArrayList<Point2D> getPontosDescarga() {
        return pontosDescarga;
    }
    
    public ArrayList<Inimigo> getAlvosDescarga() {
        return alvosDescarga;
    }

    public void atingirInstantaneamente(ArrayList<Inimigo> inimigosAtivos) {

        if (!alvo.statusVida()) {
            return;
        }

        this.x = alvo.getX();
        this.y = alvo.getY();

        alvo.danoRecebido(this.dano + danoExtraHidroBomba);

        if (danoQueimadura > 0 && alvo.statusVida()) {
            alvo.aplicarQueimadura(danoQueimadura);
        }

        if (percentualLentidao > 0 && alvo.statusVida()) {
            alvo.aplicarLentidao(percentualLentidao);
        }

        if (danoDescarga > 0) {
            aplicarDescarga(inimigosAtivos);
        }
    }

    public boolean moverTiro(double tpf, ArrayList<Inimigo> inimigosAtivos) {
        if(!alvo.statusVida()){
            return true;
        }
        double dx = alvo.getX() - this.x;
        double dy = alvo.getY() - this.y;

        double distance = Math.hypot(dx, dy);
        double passo = velocidade * tpf;

        if (distance <= passo) {
            atingirInstantaneamente(inimigosAtivos);
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

    public boolean ehDescarga() {
        return danoDescarga > 0;
    }

private void aplicarDescarga(ArrayList<Inimigo> inimigosAtivos) {
    System.out.println("DESCARGA ATIVADA! Dano: " + danoDescarga);
    pontosDescarga.clear();
    alvosDescarga.clear();

    alvosDescarga.add(alvo);
    pontosDescarga.add(new Point2D(this.x, this.y));

    int atingidos = 0;

    if (alvo.statusVida()) {
        alvo.danoRecebido(danoDescarga);
        atingidos++;
    }

    for (Inimigo inimigo : inimigosAtivos) {

        if (atingidos >= 3) {
            break;
        }

        if (inimigo != alvo && inimigo.statusVida()) {

            double distancia = Math.hypot(
                inimigo.getX() - this.x,
                inimigo.getY() - this.y
            );

            if (distancia <= raioDescarga) {

                System.out.println("Inimigo secundario atingido! Distancia: " + distancia);
                inimigo.danoRecebido(danoDescarga);

                pontosDescarga.add(
                    new Point2D(inimigo.getX(), inimigo.getY())
                );

                alvosDescarga.add(inimigo);

                atingidos++;
            }
        }
    }
}

}
