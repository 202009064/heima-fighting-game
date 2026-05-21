package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import domain.User;

public class Login {
    // 开始界面
    public void start() {
        System.out.println("游戏的登录注册页面已经打开");
        ArrayList<User> list = new ArrayList<User>(); // 这个集合用于存储所有的用户对象

        while (true) {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("请选择操作：1登录 2注册 3退出");

            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    login(list);
                    break;
                case "2":
                    register(list);
                    break;
                case "3":
                    System.out.println("用户选择了退出操作");
                    // 正常结束 ： System.exit(0) — 程序正常退出
                    // 异常结束 ： System.exit(1) — 通常表示程序遇到错误而退出
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }

    // 登录操作
    public void login(ArrayList<User> list) {
        System.out.println("用户选择了登录操作");

        // 判断用户名是否存在
        // 不存在：则提示未注册
        // 存在但禁用：则提示联系客服
        // 存在且正常：验证验证码（机器直接注册好了）
        // 验证密码是否正确：有3次机会，满3次账户锁定，即禁用状态。

        // 1. 键盘录入用户名
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();

        // 2. 不存在：则提示未注册。验证用户名是否存在
        if (!contains(list, username)) {
            System.out.println("用户名" + username + "不存在，请先注册，再登录~");
            return;
        }

        int index = findIndex(list, username); // 获取当前用户名对应的索引值
        User u = list.get(index); // 获取当前用户名对应的User对象

        // 3. 存在但禁用：则提示联系客服
        if (!u.isStatus()) {
            System.out.println("用户名" + username + "已被禁用，请联系客服~");
            // 如果用户名禁用，结束登录的行为，回到选择界面当中去注册
            return;
        }

        // 4. 让用户继续键盘录入验证码和密码
        String rightPassword = u.getPassword();
        for (int i = 1; i <= 3; i++) {
            System.out.println("请输入密码：");
            String password = sc.next();

            // 判断验证码是否正确
            while (true) {
                // 生成一个正确的验证码
                String rightCode = getCode();
                System.out.println("正确的验证码为：" + rightCode);

                System.out.println("请输入验证码：");
                String code = sc.next();

                if (rightCode.equalsIgnoreCase(code)) {
                    System.out.println("验证码输入正确");
                    // 如果验证码输入正确，则跳出循环，继续下一步判断，即密码是否正确
                    break;
                } else {
                    System.out.println("验证码输入错误");
                    // 如果验证码输入错误，需要重新生成一个新的验证码，并且让用户重新输入验证码
                    continue;
                }
            }

            // 验证密码是否正确
            if (rightPassword.equals(password)) {
                System.out.println("登录成功，游戏启动~");
                FightingGame fg = new FightingGame();
                fg.startGame(username);
                break;
            } else {
                System.out.println("登录失败，密码输入错误~");
                if (i == 3) {
                    // 三次机会都用完了
                    u.setStatus(false);
                    System.out.println("用户名" + username + "已被锁定，请联系客服~");
                    return;
                } else {
                    System.out.println("密码错误，您还有" + (3 - i) + "次机会重新输入密码~");
                }
            }
        }
    }

    public void register(ArrayList<User> list) {
        System.out.println("用户选择了注册操作");
        // 什么叫注册：键盘录入用户名和密码 ---> 成为User对象，保存到集合中

        // 1. 创建User对象（空参）
        User u = new User();

        Scanner sc = new Scanner(System.in);

        // 2. 键盘录入用户名
        // 键盘录入用户名：校验用户名是否符合要求（用户名唯一、长度必须在3 ~ 16位、只能由字母、数字组成，不能是纯数字）
        // 校验细节：
        // 在检验数据的时候，先检验数据格式是否正确，再检验数据是否唯一。因为在检验数据格式的时候，是不需要连网的，在本地就可以操作，但是检验数据是否唯一的时候，需要连网，需要在数据库中进行查询。
        // 先判断异常的数据，剩下来的都是正确的数据（好处：避免if的嵌套）
        while (true) { // 校验 + 录入
            System.out.println("请输入用户名：");
            String username = sc.next();

            // 校验用户名的步骤：1.长度 2.格式 3.唯一性（检测username是否在list中是否出现，出现即重复，否则为唯一）
            //                  校验顺序遵循：从成本低到成本高，从通用到具体。即先淘汰明显不合格的数据，再花力气做精细检查。
            if (!checkLen(3, 16, username)) {
                System.out.println("用户名长度必须在3 ~ 16位, 请重新输入");
                continue;
            }
            if (!checkUsername(username)) {
                System.out.println("用户名只能由字母、数字组成，不能是纯数字, 请重新输入");
                continue;
            }
            if (contains(list, username)) {
                System.out.println("用户名已存在, 请重新输入");
                continue;
            }

            // 当代码执行到这里时，表示用户名username长度、内容、唯一性都符合要求，所以可以注册成功，把用户名设置到User对象中
            u.setUsername(username);
            break;
        }

        // 3. 键盘录入密码
        // 键盘录入密码：校验密码是否符合要求（长度3 ~ 8位、只能是字母加数字的组合，不能有其他字母）
        while (true) {
            System.out.println("请输入密码：");
            String password1 = sc.next();
            System.out.println("请再一次输入密码：");
            String password2 = sc.next();

            // 校验密码的步骤：1.长度 2.格式
            if (!checkLen(3, 8, password1)) {
                System.out.println("密码长度必须在3 ~ 8位");
                continue;
            }
            if (!checkPassword(password1)) {
                System.out.println("密码只能是字母加数字的组合，不能有其他字母");
                continue;
            }
            if (!password1.equals(password2)) {
                System.out.println("两次输入的密码不一致");
                continue;
            }
            // 当代码执行到这里时，表示密码password长度、内容都符合要求，所以可以注册成功，把密码设置到User对象中
            u.setPassword(password1);
            break;
        }

        // 4. 保存User对象添加到集合中
        list.add(u);

        // 5. 注册成功
        System.out.println("用户：" + u.getUsername() + " 注册成功！");
    }

    // 判断字符串的长度是否在要求范围内
    public boolean checkLen(int minLen, int maxLen, String str) {
        return str.length() >= minLen && str.length() <= maxLen;
    }

    // 校验用户名是否符合要求（只能由字母、数字组成，不能是纯数字），所以字母至少有一个，数字可有可无，其他字符不能有字符
    public boolean checkUsername(String username) {
        int[] count = getCount(username);
        int charCount = count[0], numCount = count[1], otherCount = count[2];
        return charCount > 0 && numCount >= 0 && otherCount == 0;
    }

    // 判断用户名在集合中是否存在
    public boolean contains(ArrayList<User> list, String username) {
        for (User u : list) { // u == list.get(i)
            if (u.getUsername().equals(username)) { // Object.equals(u.getUsername(), username)
                return true; // 用户名存在
            }
        }
        return false; // 用户名不存在
    }

    // 校验密码是否符合要求（只能由字母+数字组成，不能有其他字符），所以字母至少有一个，数字至少有一个，其他字符不能有字符
    public boolean checkPassword(String password) {
        int[] count = getCount(password); // 统计密码中字母、数字、其他字符分别有多少个
        int charCount = count[0], numCount = count[1], otherCount = count[2];
        return charCount > 0 && numCount > 0 && otherCount == 0;
    }

    // 统计字符串中，字母、数字、其他字符分别有多少个
    public int[] getCount(String userinfo) {
        int charCount = 0, numCount = 0, otherCount = 0;
        for (int i = 0; i < userinfo.length(); i++) {
            char c = userinfo.charAt(i); // 获取当前字符
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                charCount++;
            } else if (c >= '0' && c <= '9') {
                numCount++;
            } else {
                otherCount++;
            }
        }
        return new int[] { charCount, numCount, otherCount };
    }

    // 生成随机验证码
    public static String getCode() {
        // 长度为5
        // 由4位大写或者小写字母和1位数字组成，同一个字母可重复
        // 数字可以出现在任意位置
        // 比如：aQa1K

        ArrayList<Character> list = new ArrayList<>(); // 容器
        // 1. 把所有的大写和小写字母都放在同一个容器中
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }

        // 2. 从集合当中随机抽取字母（4次）
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            // 随机获取集合中的索引
            int index = r.nextInt(list.size());
            char ch = list.get(index);
            sb.append(ch); // 将随机抽取的字母添加到 StringBuilder 中
        }

        // 3. 生成一个随机的数字
        sb.append(r.nextInt(10)); // 将随机抽取的数字添加到 StringBuilder 中

        // 4. 数字的位置可以是任意的
        // 先把sb变成字符串，再变成字符串数组
        char[] arr = sb.toString().toCharArray();
        // 把最大索引上的数字，跟另外一个随机索引进行交换
        int i = r.nextInt(arr.length);
        // 交换：最大索引（arr.length-1）,随机索引（i）
        char temp = arr[arr.length - 1];
        arr[arr.length - 1] = arr[i];
        arr[i] = temp;

        return new String(arr);
    }

    // 查找用户名在集合中的索引值：如果用户名不存在，返回-1；如果用户名存在，返回索引值
    public int findIndex(ArrayList<User> list, String username) {
        for (User u : list) { // u == list.get(i)
            if (u.getUsername().equals(username)) {
                return list.indexOf(u); // 用户名存在，返回索引值
            }
        }
        return -1; // 用户名不存在，返回-1表示不存在
    }
}
