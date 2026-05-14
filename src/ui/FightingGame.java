package ui;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import domain.HeroCharacter;
import domain.EnemyCharacter;

public class FightingGame {
    // 启动游戏
    public void startGame(String username){
        // 1. 显示游戏的标题
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("    🎮 "+ username +"欢迎来到文字格斗游戏 🎮   ");
        System.out.println("╚════════════════════════════════════════╝");

        // 2. 创建玩家角色（名字 + 属性分配）
        HeroCharacter player = createPlayerCharacter(username);

        // 3. 显示玩家角色的属性和技能
        System.out.println("角色创建成功：");
        System.out.println("🌟 初始属性： " + player.show());
        System.out.println("🌟 拥有技能： " + player.showSkillList());

        // 4. 创建多个敌人列表
        // name,     HP,    attack,     defence， skill
        // 初级战士,  80,      15,         10,     猛击
        // 敏捷刺客，  60,      20,         5,     快速攻击
        // 重装坦克，  120,     10,         20,     防御姿态
        // 神秘法师，  70,      25,         8,     火球术（180%伤害）
        ArrayList<EnemyCharacter> enemyList = new ArrayList<>();
        enemyList.add(new EnemyCharacter("初级战士", 80, 15, 10, "猛击"));
        enemyList.add(new EnemyCharacter("敏捷刺客", 60, 20, 5, "快速攻击"));
        enemyList.add(new EnemyCharacter("重装坦克", 120, 10, 20, "防御姿态"));
        enemyList.add(new EnemyCharacter("神秘法师", 70, 25, 8, "火球术（180%伤害）"));

        // 准备战斗（依次跟多个敌人战斗）
        int count = 1; // 记录当前我是跟第几个敌人进行战斗
        int wins = 0; // 记录胜利了几场战斗

        // 游戏当中，我是依次跟多个敌人进行战斗，直到我方的生命值为0，游戏才会结束
        while(player.isAlive()){ // 我方依次和多个敌人进行战斗，直到我方的生命值为0，游戏才会结束
            // 进入循环，开始准备战斗

            // 5.1 重置敌人的属性，列表中的每个敌人属性每场HP+10, ATK+3, DEF+2（敌人：越来越难打）（第二场的时候）
            if(wins != 0){ // count > 1，两种写法都可以，都表示第二场的时候（只有第一场胜利了，才会进入第二场战斗）
                for(int i = 0; i < enemyList.size(); i++){
                    EnemyCharacter c = enemyList.get(i);
                    // 生命值 + 10
                    c.maxHP += 10;
                    c.HP = c.maxHP;
                    // 攻击力 + 3
                    c.attack += 3;
                    // 防御力 + 2
                    c.defence += 2;
                    // 每场战斗之前，如果有减伤的buff，需要重置
                    c.defending = false;
                }
            }

            // 5.2 随机选择敌人(Random)
            Random r = new Random();
            int index = r.nextInt(enemyList.size());
            EnemyCharacter enemy = enemyList.get(index); // 随机选择一个敌人
            System.out.println(enemy.show());

            // 5.3 战斗开始，显示双方状态（生命值）
            System.out.println("═══════════════════════════════════════");
            System.out.println("⚔️ 第 " + count + " 场战斗开始！对手: " + enemy.name);
            
            // 跟当前的敌人战斗了几个回合了
            int round = 1; // 记录当前是第几个回合了
            while(player.isAlive()){ // 我方和当前敌人进行多轮回合制战斗，直到有一方的生命值为0，战斗结束。【为什么这里不需要判断敌人的生命值？】
                // 显示双方当前状态（生命值）
                System.out.println("---------------------------------------");
                System.out.println("⚔️ 第" + round + " 回合开始！");
                // 
            }

            // 
            // 5.3 开始和抽取到的敌人进行战斗
            // 回合制（我先攻击，敌人再攻击）
            // 我方和当前敌人进行多轮回合制战斗，直到有一方的生命值为0，战斗结束

        }
    }

    // 创建玩家角色（名字 + 属性分配）
    // 参数：username 玩家角色的姓名
    // 返回值：创建好的玩家角色对象
    public HeroCharacter createPlayerCharacter(String username){
        System.out.println("创建您的角色：");
        System.out.println("您的角色名为：" + username);

        // 属性分配
        int points = 20;
        System.out.println("请分配属性点（共" + points + "点）");

        /*
        // 生命值
        System.out.println("生命值（每点 + 10HP）");
        Scanner sc = new Scanner(System.in);
        int hpPoints = sc.nextInt();
        if(hpPoints < 0){
            System.out.println("无效输入，默认生命值为0点");
            hpPoints = 0;
        }
        if(hpPoints > points){
            System.out.println("属性点不足，剩余属性点全部分配到生命值");
            hpPoints = points;
        }
        points -= hpPoints; */

        // 提示
        System.out.println("1. 生命值 (每点+10 HP)");
        System.out.println("2. 攻击力 (每点+2 ATK)");
        System.out.println("3. 防御力 (每点+1 DEF)");

        Scanner sc = new Scanner(System.in);
        
        // 定义数组，把要提示的语句存起来
        String[] attributes = {"生命值", "攻击力", "防御力"};
        // 定义数组，记录三个属性分配的属性点
        int[] values = new int[attributes.length];

        // 利用一个循环记录分配的属性点
        for(int i = 0; i < attributes.length; i++){
            System.out.println("分配点数到" + attributes[i] + "（剩余点数：" + points + "）：");
            // input 表示当前用户键盘录入的数据（要分配的属性点）
            int input = sc.nextInt();
            // 验证输入是否有效
            if(input < 0){
                System.out.println("无效输入，默认" + attributes[i] + "属性为0点");
                input = 0;
            }
            if(input > points){
                System.out.println("属性点不足，剩余属性点全部分配到：" + attributes[i]);
                input = points;
            }
            // 更新剩余属性点
            points -= input;
            // 记录分配的属性点
            values[i] = input;
        }

        // 现在我已经知道了用户要给玩家分配的属性点了 ---> values【i】

        // 创建玩家角色的对象
        HeroCharacter player = new HeroCharacter(
            username,
            100 + values[0] * 10,
            20 + values[1] * 2,
            10 + values[2] * 1
        );
        // 添加玩家的技能
        player.skillList.add("普通攻击");
        player.skillList.add("强力一击");
        player.skillList.add("生命汲取");

        return player;
    }

    // 定义一个方法打印敌我双方的血条
    // 满血：[████████████████████]
    // 半血：[██████████]
    // 残血：[█]
    //zhangsan: [█████████████████   ] 175/200 HP
    //敏捷刺客: [█████████           ] 29/60 HP
    public String getHealthBar(String name, int HP, int maxHP){
        // 满血状态下，打印20个方块
        int barLength = 20;

        // 计算当前血量占满血状态下的比例对应的方块数量
        int filled = (int)((HP * 1.0 / maxHP) * barLength); 
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("：[")
            .append("█".repeat(filled)).append(" ".repeat(barLength - filled)).append("]")
            .append(" ").append(HP).append("/").append(maxHP).append(" HP");
        return sb.toString();
    }
}
