import java.util.ArrayList;

public class GerenciadorOndas {
    private ArrayList<Onda> ondas = new ArrayList<>();
    private int ondaAtual;

    public GerenciadorOndas(){
        ondas.add(new Onda("Rattata",5,1));
        ondas.add(new Onda("Pidgey",4,0.5));
    }

    public Onda getOndaAtual(){
        return ondas.get(ondaAtual);
    }

    public boolean avançarOnda(){
        if(ondaAtual == ondas.size() - 1){
            return true;
        }
        ondaAtual++;
        return false;
    }
}
