class Health{
    private int hp=100;
    public int getHp(){
        return hp;
    }
    public int takeDamage(int damage) {
        if (damage > 0) {
            hp -= damage;
            if (hp < 0) {
                hp=0;
            };
        }
        return damage;
    }
}
class not{
    public int note(){
        int noti=3;
        return noti;
    }
}
class Damedge{
    public int dg(){
        not a=new not();
        int damedge = 3* a.note();
        return damedge;
    }
}
public class Main{
     static public void main(String[]args){
        Health health=new Health();
        Damedge d= new Damedge();
        int damageValue = d.dg();
        health.takeDamage(damageValue);
        int hp = health.getHp();
        System.out.println("受到了 " + damageValue + " 点伤害，剩余血量：" + hp);
    }
}