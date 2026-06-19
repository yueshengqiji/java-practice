import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
interface Band {
    void musical();
}
abstract class Bander{
    void id(){
    }
}
class Band1 extends Bander implements Band{
    void id(){
        System.out.println("你的名字是千早爱音");
    }
    public void musical(){
        System.out.println("你演奏的乐器是吉他");
    }
}
class Band2 extends Bander implements Band{
    void id(){
        System.out.println("你的名字是韦明立希");
    }
    public void musical(){
        System.out.println("你演奏的乐器是鼓");
    }
}
class Band3 extends Bander implements Band{
    void id(){
        System.out.println("你的名字是高松灯");
    }
    public void musical(){
        System.out.println("你是主唱");
    }
}
class Band4 extends Bander implements Band{
    void id(){
        System.out.println("你的名字是长期素食");
    }
    public void musical(){
        System.out.println("你演奏的乐器是吉他");
    }
}
class Band5 extends Bander implements Band{
    void id(){
        System.out.println("你的名字是耄耋");
    }
    public void musical(){
        System.out.println("你的武器是哈气");
    }
}
public class Main{
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            Map<String, Bander> members = new HashMap<>();
            members.put("千早爱音", new Band1());
            members.put("韦明李希", new Band2());
            members.put("高松灯", new Band3());
            members.put("长期素食", new Band4());
            members.put("耄耋", new Band5());

            while (true) {
                System.out.println("请输入你想成为的乐队成员：");
                String name = sc.nextLine();

                Bander member = members.get(name);

                if (member != null) {
                    member.id();
                    ((Band) member).musical();
                     } else {
                    break;
                }
            }
            sc.close();
        }
}
