public class Onda {
    private String especie;
    private int quantidade;
    private double intervaloSpawn;

    public Onda (String especie, int quantidade, double intervaloSpawn) {
        this.especie = especie;
        this.quantidade = quantidade;
        this.intervaloSpawn = intervaloSpawn;
    }

    public String getEspecie(){
        return especie;
   }

   public int getQuantidade(){
        return quantidade;
   }

   public double getIntervaloSpawn(){
        return intervaloSpawn;
   }
}