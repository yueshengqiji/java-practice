import java.util.*;
public class Main {
    private static volatile boolean keyPressed = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你还记得你自己的名字吗:");
            String name = sc.nextLine();

            System.out.println("你确定你的名字是 " + name + " 吗？ (y/n):");
            String confirm = sc.nextLine().trim().toLowerCase();

            if (confirm.equals("y") || confirm.equals("yes")) {
                System.out.println("名字确认成功...欢迎回来..." + name + "...");
                break;
            } else if (confirm.equals("n") || confirm.equals("no")) {
                System.out.println("那我们再试一次...");
            } else {
                System.out.println("很明显...我们不接受其他选项...");
            }
        }
        String[] dialogues = {"我们一直都泡在一个名为回忆的海洋中",
                "这里随着你来到这个世界的时间增长而越发辽阔",
                "但是为了防止有些人因为海洋过于辽阔而溺死",
                "神允许我们拥有遗忘的权力来回避",
                "但是你似乎滥用了这个权力",
                "你现在什么都不记得了，虽然这一切都是你的咎由自取",
                "虽然是你的一部分，但是回忆们并不希望回去",
                "或者是因为太过难堪，又或是因为无法原谅你的抛弃.....",
                "不需要担忧，取回他们吧，不择手段"
        };
        Thread inputListener = new Thread(() -> {
            sc.nextLine();  // 这个线程会一直等到用户按回车
            keyPressed = true;   // 标记为已按下
        });
        inputListener.setDaemon(true); // 设置为守护线程，主程序结束它会自动结束
        inputListener.start();
        for (String dialogue : dialogues) {
            System.out.println(dialogue);
            keyPressed = false;
            try {
                // 2. 在2秒内，每100毫秒检查一次是否有按键
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(100);
                    if (keyPressed) {
                        System.out.println("[跳过]");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("对话被打断...");
            }
        }

        System.out.println("对话结束。");
        sc.close();
    }
}