package primalsiege.modelo;

import java.util.ArrayList;

public class Onda {
     private ArrayList<String> especies;
     private double intervaloSpawn;

     public Onda(String especie, int quantidade, double intervaloSpawn) {

     this.especies = new ArrayList<>();

     for (int i = 0; i < quantidade; i++) {
          especies.add(especie);
     }

     this.intervaloSpawn = intervaloSpawn;
     }

     public Onda(ArrayList<String> especies, double intervaloSpawn) {

     this.especies = new ArrayList<>(especies);
     this.intervaloSpawn = intervaloSpawn;
     }

     public String getEspecie() {
     return especies.get(0);
     }

     public String getEspecie(int indice) {
     return especies.get(indice);
     }

     public int getQuantidade() {
     return especies.size();
     }

     public double getIntervaloSpawn(){
          return intervaloSpawn;
     }
}