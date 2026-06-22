import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 创建儿子
        System.out.println("--- 创建儿子 ---");
        Character kami = createCharacter(scanner, "儿子");

        // 创建老婆
        System.out.println("--- 创建老婆 ---");
        Character tata = createCharacter(scanner, "老婆");

        // 输出结果
        System.out.println("\n--- 家庭信息 ---");
        printCharacter(kami, "儿子");
        printCharacter(tata, "老婆");

        scanner.close();
    }

    // 抽取出来的方法：负责输入一个角色的信息
    static Character createCharacter(Scanner scanner, String role) {
        Character c = new Character();
        System.out.print("请输入" + role + "的名字：");
        c.name = scanner.nextLine();

        System.out.print("请输入" + role + "的年龄：");
        c.age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("请输入" + role + "的爱好：");
        c.hobby = scanner.nextLine();

        return c;
    }

    // 抽取出来的方法：负责打印一个角色的信息
    static void printCharacter(Character c, String role) {
        System.out.println(role + "的名字是 " + c.name);
        System.out.println(role + "的年龄是 " + c.age);
        System.out.println(role + "的爱好是 " + c.hobby);
    }
}

class Character {
    public String name;   // 注意：这里 static 被去掉了，否则所有对象共享一个 name
    public String hobby;
    public int age;
}