public class inimigo {
    private int hp;
    private double mov_speed;
    private int base_damage;
    private String enemy_specie;
    private double pos_x;
    private double pos_y;
    private int mov_point;
    private boolean status;

    public void damage_received(int dano){
        hp -= dano;

        if(hp < 0){
            hp = 0;
        }
    }

    public boolean life_status(int status){
        if(hp <= 0){
            return status;
        }
    }

    public int gold_drop(int gold){
        return gold;
    }

    public int atack_damage(boolean colisao){
        if(colisao){
            return base_damage;
        }
        return 0;
    }









}
