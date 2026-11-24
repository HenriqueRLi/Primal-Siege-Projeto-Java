package primalsiege.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Onda {
     private ArrayList<String> especies;
     private double intervaloSpawn;
     private ArrayList<Boolean> chefes;

     public Onda(String especie, int quantidade, double intervaloSpawn) {
          this.especies = new ArrayList<>();
          this.chefes = new ArrayList<>();

          for (int i = 0; i < quantidade; i++) {
               especies.add(especie);
               chefes.add(false);
          }
          

          this.intervaloSpawn = intervaloSpawn;
     }

     public Onda(ArrayList<String> especies, double intervaloSpawn) {
          this.especies = new ArrayList<>(especies);
          this.chefes = new ArrayList<>();

          for (int i = 0; i < especies.size(); i++) {
               chefes.add(false);
          }
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

     public void ordenarPorVelocidade(Map<String, Double> velocidades) {
          for (int i = 0; i < especies.size() - 1; i++) {
               for (int j = 0; j < especies.size() - i - 1; j++) {

                    double velocidadeAtual = velocidades.get(especies.get(j));
                    double velocidadeProxima = velocidades.get(especies.get(j + 1));

                    boolean chefeAtual = chefes.get(j);
                    boolean chefeProximo = chefes.get(j + 1);

                    if ((chefeAtual && !chefeProximo) || (!chefeAtual && !chefeProximo && velocidadeAtual < velocidadeProxima)) {
                         Collections.swap(especies, j, j + 1);
                         Collections.swap(chefes, j, j + 1);
                    }
               }
          }
     }

     public void definirChefe(int indice) {
          chefes.set(indice, true);
     }

     public boolean ehChefe(int indice) {
          return chefes.get(indice);
     }
}