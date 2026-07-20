abstract class Character{
    protected String name;
    protected int health;
    protected int skill;
    protected int maxhealth;
    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }
    public String getName(){return name;}
    public int getHealth(){return health;}
    public void takedamage(int damage){
        health -= damage;
        if(health<=0){health=0;}
        if(health>maxhealth){health=maxhealth;}
    }
    public boolean isAlive() { return health > 0; }

}
