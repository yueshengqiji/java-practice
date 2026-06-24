import java.util.*;

//枚举将名字和角色关联起来这样当我们选择角色的时候就能一块创建角色不需要额外实例化
enum Npcname {
    ALICE(new Alice()),
    YEFU(new Yefu()),
    DEATH(new Death()),
    MEA(new mea()),
    SHINKU(new shinku());

    private Character character;
    Npcname(Character character) { this.character = character; }
    public Character getCharacter() { return character; }
}
abstract class Character{
    //使用protect来修饰变量
    protected int hp;
    protected String name;
    protected int strikedamage;
    protected int magicdamage;
    protected int defense;
    protected int maxHp;
    //这里使用了全参构造来获取每个角色的信息
    public Character(String name, int strikedamage, int magicdamage, int hp, int defense) {
        this.defense = defense;
        this.hp = hp;
        this.magicdamage = magicdamage;
        this.strikedamage = strikedamage;
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
    }
    //get来获取名字和血量后面的信息显示用
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getDefense(){return defense;}
    //造成的伤害独立计算
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;

    }
    //布尔判断血量是否是大于零
    public boolean isAlive() { return hp > 0; }

    public int getSkillDamage(String skill) {
        if (skill.equals("strike")) return strikedamage;
        if (skill.equals("magic")) return magicdamage;
        if (skill.equals("defense"))return 0;
        return 0;
    }
    public int calculateActualDamage(int rawDamage, boolean isMagical) {
        if (isMagical) {
            // 魔法伤害：完全无视防御，真实伤害的意思就是这个游戏最真实的伤害
            return rawDamage;
        } else {
            // 物理伤害：需要减去防御值，但至少造成1点伤害，1点有什么用吗我请问了
            int reducedDamage = rawDamage - defense;
            return Math.max(reducedDamage, 1);
        }
    }
}
//使用传说中的传回参数来明确角色信息
//使用了super来向父类传回参数
class Alice extends Character {
    public Alice() { super("Alice", 100, 100, 200,90); }
}
class Yefu extends Character {
    public Yefu() { super("Yefu", 10, 10, 20,100); }
}
class Death extends Character {
    public Death() { super("Death", 10, 1000, 2000,0); }
}
class mea extends Character {
    public mea(){super("mea",100,100,100,100);}
}
class shinku extends Character {
    public shinku(){super("shinku",-9999,-9999,9999,9999);}
}
public class Game_of_review{
    public static void main(String[]args){
        //先使用重构来构建一个map
        Scanner sc=new Scanner(System.in);
        Map<String, Character> map=new HashMap<>();
        Random rand=new Random();
        //通过Arrays将枚举的角色都列出来
        System.out.println("请选择角色：" + Arrays.toString(Npcname.values()));
        //请玩家输入想成为的角色并且将字母全变为大写
        Npcname playerChoice = Npcname.valueOf(sc.nextLine().toUpperCase());
        Character player = playerChoice.getCharacter();
        Npcname[] enemies = Arrays.stream(Npcname.values())
                .filter(n -> n != playerChoice)
                .toArray(Npcname[]::new);
        Character enemy = enemies[rand.nextInt(enemies.length)].getCharacter();
        System.out.println("你遇到了 " + enemy.getName() + "！开始战斗！");
        //战斗循环
        while (player.isAlive() && enemy.isAlive()) {
            // 玩家选择技能
            System.out.println("我的回合，豇豆！");
            String playerSkill=sc.nextLine();
            int playerRawDamage = player.getSkillDamage(playerSkill);
            boolean isPlayerMagic = playerSkill.equals("magic"); // 魔法攻击为true,真实伤害的意思就是这个游戏最真实的伤害
            int playerActualDamage = enemy.calculateActualDamage(playerRawDamage, isPlayerMagic);
            enemy.takeDamage(playerActualDamage);
            //随机我们可爱敌方的出招
            String []skill={"magic","defanse","strike"};
            String enemySkill=skill[rand.nextInt(skill.length)];
            int enemyRawDamage = enemy.getSkillDamage(enemySkill);
            boolean isEnemyMagic = enemySkill.equals("magic");
            int enemyActualDamage = player.calculateActualDamage(enemyRawDamage, isEnemyMagic);
            player.takeDamage(enemyActualDamage);
            if (playerSkill.equals("defense")) {
                System.out.println("你摆出了防御姿态，躲过了敌人的攻击！");
                // 玩家本回合不攻击，但敌人仍会攻击，可以减免部分伤害
                enemyActualDamage = Math.max(0, enemy.getSkillDamage(enemySkill) - player.getDefense());
                player.takeDamage(enemyActualDamage);
                continue;
            }
            if (enemySkill.equals("defense")) {
                System.out.println("敌人摆出了防御姿态，妄想躲过了你的攻击！");
                // 玩家本回合不攻击，但敌人仍会攻击，可以减免部分伤害
                enemyActualDamage = Math.max(0, enemy.getSkillDamage(enemySkill) - player.getDefense());
                player.takeDamage(enemyActualDamage);
                continue;
            }
            // 显示战况
            System.out.println("你使用了 " + playerSkill + "，造成 " + playerActualDamage + " 点伤害!!");
            System.out.println("敌人使用了 " + enemySkill + "，造成 " + enemyActualDamage + " 点伤害!!");
            System.out.println("你的HP：" + player.getHp() + " | 敌人HP：" + enemy.getHp());
        }
        //判断胜负
        if (player.isAlive()) {
            System.out.println("WIN！");
        } else {
            System.out.println("YOU ARE DEAD...");
        }
    }
}