import java.io.File;
import java.util.Scanner;

// 枚举管理所有命令
enum Command {
    DELETE {
        @Override
        void execute(String path) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                file.delete();
                System.out.println("文件 " + path + " 已删除");
            } else {
                System.out.println("此文件不存在或不是文件");
            }
        }
    },
    SEARCH {
        @Override
        void execute(String path) {
            File file = new File(path);
            if (file.isDirectory()) {
                System.out.println("此路径是文件夹");
            } else if (file.isFile()) {
                System.out.println("此路径是文件");
            } else {
                System.out.println("此路径不存在");
            }
        }
    },
    RENAME {
        @Override
        void execute(String path) {
            System.out.println("暂未实现重命名功能");
        }
    };

    abstract void execute(String path);  // 每个命令自己实现逻辑

    // 根据用户输入查找命令（不区分大小写）
    public static Command fromString(String input) {
        try {
            return valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

public class File_Uesi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("可用命令：" + java.util.Arrays.toString(Command.values()));
            System.out.print("请输入你要使用的功能（或 exit 退出）：");
            String commandInput = sc.nextLine();

            if ("exit".equalsIgnoreCase(commandInput)) break;

            Command command = Command.fromString(commandInput);
            if (command == null) {
                System.out.println("无效命令，请重新输入。");
                continue;
            }

            System.out.print("请输入你想操作的路径：");
            String path = sc.nextLine();

            command.execute(path);  // 直接让枚举执行
        }
        sc.close();
    }
}