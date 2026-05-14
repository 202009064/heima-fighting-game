package src1.ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src1.domain.User;

public class Login {
    public void show(){
        System.out.println("游戏的登录注册页面已经打开");
        ArrayList<User> list = new ArrayList<>();

        while(true){
            System.out.println("╔════════════════════════════════╗");
            System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("请选择操作：1登录 2注册 3退出");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch(choice){
                case 1: 
                    login(list);
                    break;
                case 2: 
                    register(list);
                    break;
                case 3: 
                    System.out.println("谢谢使用，再见！");
                    System.exit(0);
                    break;
                default: 
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
    }
    // 登录方法
    private void login(List<User> list){
        System.out.println("用户选择了登录操作");
    }
    // 注册方法
    private void register(List<User> list){
        System.out.println("用户选择了注册操作");

        User u = new User();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入用户名：");
            String username = sc.next();

            if(!checkLen(3, 10, username)){
                System.out.println("用户名长度必须在3-10之间");
                continue;
            }
        }
    }
    public boolean checkLen(int min, int max, String str){
        return str.length() >= min && str.length() <= max;
    }
}
