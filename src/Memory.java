enum Memorys {
    MEMORY(new Memory()),
    DEARMEMORY(new DearMemory()),
    DEATHMEORY(new DeathMeory());
    private Character character;
    Memorys(Character character) {
        this.character = character;
    }
    public Character getCharacter() {return character;}
}
class Memory extends Character{ public  Memory(){super("memory",10);}}
class DearMemory extends Character{public DearMemory (){super("dearmemory",15);}}
class DeathMeory extends Character{public DeathMeory(){super("deathmeory",20);}}