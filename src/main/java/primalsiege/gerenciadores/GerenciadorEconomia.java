package primalsiege.gerenciadores;
public class GerenciadorEconomia {
    private int ouro;

    public GerenciadorEconomia () {
        this.ouro = 300;
    }

    public void adicionarOuro (int ouro) {
        this.ouro += ouro;
    }

    public boolean descontarOuro (int ouro) {
        if(this.ouro < ouro){
            return false;
        }
        this.ouro -= ouro;
        return true;

    }

    public int getOuro () {
        return this.ouro;
    }
}
