import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
class Cat{
    void catac(){
        System.out.println("the cat will sleep...");

    }
}
class Eat extends Cat{
    void catac(){
        System.out.println("the cat is eating Triple Matcha Parfait");
        System.out.println("oyixi....");
    }
}
class Play extends Cat{
    void catac(){
        System.out.println("the cat will play the gete,but she foget code");
    }
}
public class cat {
    public static void main(String[] args) {
        Map<String,Cat> map =new HashMap<>();
        Scanner sc=new Scanner(System.in);
        map.put("eat",new Eat());
        map.put("play",new Play());
        while (true) {
            System.out.println("请输入你想让猫做的事情：");
            String name = sc.nextLine();
            Cat member = map.get(name);
            if (member != null) {
                show(member);
            } else {
                break;
            }
        }
    }

    public static void show(Cat a) {
        a.catac();
    }
}