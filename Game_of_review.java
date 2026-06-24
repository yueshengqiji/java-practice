abstract class Character{
    protected int hp;
    protected String name;
    protected int strikedamage;
    protected int magicdamage;
    protected int defense;
    public Character(String name, int strikedamage, int magicdamage, int hp, int defense) {
        this.defense = defense;
        this.hp = hp;
        this.magicdamage = magicdamage;
        this.strikedamage = strikedamage;
        this.name = name;
    }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public void takeDamage(int damage) {
        if (damage > 0) {
            hp -= damage;
            if (hp < 0) hp = 0;
        }
    }

    public boolean isAlive() { return hp > 0; }

    public int getSkillDamage(String skill) {
        if (skill.equals("strike")) return strikedamage;
        if (skill.equals("magic")) return magicdamage;
        return 0;
    }
}
class Alice extends Character {
    public Alice() { super("Alice", 100, 100, 200,90); }
}
class Yefu extends Character {
    public Yefu() { super("Yefu", 10, 10, 20,100); }
}
class Death extends Character {
    public Death() { super("Death", 200, 1000, 2000,0); }
}
