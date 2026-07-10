import java.util.*;
enum Skill {
    // 技能名( 攻击力, 是否魔法伤害 )
    STRIKE_DAMAGE(15, false),
    MAGIC_DAMAGE(25, true),
    FIREBALL(40, true),
    AOE_TEXT(10, false),  // 注意这里用分号结束常量列表
    DEFENSE(0,false);
    // 字段：每个技能都有这两个属性
    private final int baseDamage;
    private final boolean isMagical;

    // 构造方法：把括号里的数值存进字段
    Skill(int baseDamage, boolean isMagical) {
        this.baseDamage = baseDamage;
        this.isMagical = isMagical;
    }

    // 公开的方法，让外界可以读取数值
    public int getBaseDamage() { return baseDamage; }
    public boolean isMagical() { return isMagical; }
}
//枚举将名字和角色关联起来这样当我们选择角色的时候就能一块创建角色不需要额外实例化
enum Npcname {
    ALICE(new Alice()),
    YEFU(new Yefu()),
    DEATH(new Death()),
    MEA(new Mea()),
    SHINKU(new Shinku());

    private Character character;
    Npcname(Character character) { this.character = character; }
    public Character getCharacter() { return character; }
}
abstract class Character{
    //使用protect来修饰变量
    protected int hp;
    protected String name;
    protected int defense;
    protected int maxHp;
    //这里使用了全参构造来获取每个角色的信息
    public Character(String name, int hp, int defense) {
        this.defense = defense;
        this.hp = hp;
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
        if (hp > maxHp) hp = maxHp;
        if (hp < 0) hp = 0;

    }
    //布尔判断血量是否是大于零
    public boolean isAlive() { return hp > 0; }
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
    public Alice() { super("Alice", 100,90); }
}
class Yefu extends Character {
    public Yefu() { super("Yefu", 100,100); }
}
class Death extends Character {
    public Death() { super("Death", 10, 0); }
}
class Mea extends Character {
    public Mea(){super("Mea",100,100);}
}
class Shinku extends Character {
    public Shinku(){super("Shinku",9999,9999);}
}
public class Game_of_review{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
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
            System.out.println("请选择技能：" + Arrays.toString(Skill.values()));
            String input = sc.nextLine().toUpperCase();
            Skill playerSkill;
            try {
                playerSkill = Skill.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("无效技能，请重新输入。");
                continue; // 跳过本回合，重新开始循环
            }

            // 随机敌人出招
            Skill[] skills = {Skill.MAGIC_DAMAGE, Skill.DEFENSE, Skill.STRIKE_DAMAGE, Skill.FIREBALL, Skill.AOE_TEXT}; // 敌人可用技能
            Skill enemySkill = skills[rand.nextInt(skills.length)];
            if (playerSkill == Skill.DEFENSE && enemySkill == Skill.DEFENSE) {
                System.out.println("你摆出了防御姿态，敌人也摆出了防御姿态，无事发生。");
                continue;
            }
            // 玩家防御
            if (playerSkill == Skill.DEFENSE) {
                System.out.println("你摆出了防御姿态，躲过了敌人的攻击！");
                int enemyRawDamage = enemySkill.getBaseDamage();
                int reducedDamage = Math.max(0, enemyRawDamage - player.getDefense());
                player.takeDamage(reducedDamage);
                System.out.println("敌人使用了 " + enemySkill + "，但只造成了 " + reducedDamage + " 点伤害！");
                System.out.println("你的HP：" + player.getHp() + " | 敌人HP：" + enemy.getHp());
                continue;
            }
            if (enemySkill == Skill.DEFENSE) {
                System.out.println("敌人摆出了防御姿态！");
                int playerRawDamage = playerSkill.getBaseDamage();
                int playerActualDamage;
                if (playerSkill.isMagical()) {
                    playerActualDamage = playerRawDamage;
                } else {
                    playerActualDamage = Math.max(1, playerRawDamage - enemy.getDefense());
                }
                enemy.takeDamage(playerActualDamage);
                System.out.println("你使用了 " + playerSkill + "，但只造成了 " + playerActualDamage + " 点伤害！");
                System.out.println("你的HP：" + player.getHp() + " | 敌人HP：" + enemy.getHp());
                continue;
            }
//            int playerRawDamage = playerSkill.getBaseDamage(); // 从枚举拿伤害值
//            boolean isPlayerMagic = playerSkill.isMagical();
//            int enemyRawDamage = enemy.calculateActualDamage(playerRawDamage, isPlayerMagic);
//            int reducedDamage = Math.max(0, enemyRawDamage - player.getDefense());
            // 显示战况
            int playerRawDamage = playerSkill.getBaseDamage();
            boolean isPlayerMagic = playerSkill.isMagical();
            int playerActualDamage = enemy.calculateActualDamage(playerRawDamage, isPlayerMagic);
            enemy.takeDamage(playerActualDamage);
            int enemyRawDamage = enemySkill.getBaseDamage();
            boolean isEnemyMagic = enemySkill.isMagical();
            int enemyActualDamage = player.calculateActualDamage(enemyRawDamage, isEnemyMagic);
            player.takeDamage(enemyActualDamage);
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