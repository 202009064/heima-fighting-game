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

        // 5. 准备战斗（依次跟多个敌人战斗）
        int count = 1; // 记录当前我是跟第几个敌人进行战斗
        int wins = 0; // 记录胜利了几场战斗

        // 游戏当中，我是依次跟多个敌人进行战斗，直到我方的生命值为0，游戏才会结束
        while(player.isAlive()){ // 我方依次和多个敌人进行战斗，直到我方的生命值为0，游戏才会结束
            // 进入循环，开始准备战斗

            // 5.1 重置敌人的属性，列表中的每个敌人属性每场HP+10, ATK+3, DEF+2（敌人：越来越难打）（第二场的时候）
            if(count > 1){ // wins != 0，两种写法都可以，都表示第二场的时候（只有第一场胜利了，才会进入第二场战斗）
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
            System.out.println("═══════════════════════════════════════════════════");
            System.out.println("⚔️ 第 " + count + " 场战斗开始！对手: " + enemy.name);
            
            // 跟当前的敌人战斗了几个回合了
            int round = 1; // 记录当前是第几个回合了
            while(player.isAlive()){ // 我方和当前敌人进行多轮回合制战斗，直到有一方的生命值为0，战斗结束。【为什么这里不需要判断敌人的生命值？因为在小循环中进行判断】
                // 显示双方当前状态（生命值）
                System.out.println("---------------------------------------");
                System.out.println("⚔️ 第" + round + " 回合开始！");
                // 打印敌我双方的血条
                System.out.println(getHealthBar(player.name, player.HP, player.maxHP));
                System.out.println(getHealthBar(enemy.name, enemy.HP, enemy.maxHP));

                // 5.4 玩家回合：选择行动（ 1普通攻击/ 2强力一击/ 3生命汲取 ）
                playerTurn(player, enemy);

                // 5.5 判断敌人是否被击败（判断敌方血量是否为0）
                if(!enemy.isAlive()){
                    System.out.println("🎉 你击败了 " + enemy.name + "!");
                    // System.out.println("💚 战斗结束！你恢复了 36 点生命值");
                    // player.heal(36);
                    // 我方胜利了，连胜的计算器会自增一次
                    wins++;
                    // System.out.println("🏆 当前胜场: " + wins);
                    // 结束小循环，继续跟下一个敌人进行战斗
                    break;
                }

                // 5.6 敌人回合：选择行动（ 50%的几率普通攻击 / 50%的几率技能攻击 / 不同的敌人采取不同的技能进行攻击）
                enemyTurn(enemy, player);

                // 5.7 判断玩家是否被击败（判断玩家血量是否为0），如果被击败，则break结束内循环，并且player.isAlive()返回false，此时进入外循环中也会被终止
                if(!player.isAlive()){
                    System.out.println("❌ " + player.name + " 被击败了！游戏结束");
                    break; // 战斗结束
                }

                // 如果我方玩家没有被击败，继续执行内循环，开始下一个回合 round++
                round++; // 回合数加1
            }
            /*
            5.3 思路：开始跟抽取到的敌人进行战斗
                回合制（我先攻击，敌人再攻击）
                我方和当前敌人进行多轮回合制战斗，直到有一方的生命值为0，战斗结束
            int round = 1; // 记录当前是第几个回合了
            while(player.isAlive()){ // 我方和当前敌人进行多轮回合制战斗，直到有一方的生命值为0，战斗结束
                // 我打敌人一下
                // 判断敌人的血量是否为0，如果为0，结束当前的战斗，继续跟下一个敌人进行战斗（结束内循环，继续执行外循环）
                // 敌人打我一下
                // 判断我方英雄血量是否为0，如果为0，结束整个游戏
                // 如果我的血量不为0，继续执行内循环，开始下一个回合 round++
            }

            // 5.8910 跟单个敌人结束战斗（结算，回血，每完成3场战斗则增加我方人物的属性点，询问y/n y）
            */

            // 5.8 跟一个敌人的战斗结束后，玩家胜利（恢复生命值继续战斗）玩家失败（游戏停止）
            if(player.isAlive()){
                // 玩家与一个敌人战斗结束，玩家胜利，恢复生命值继续下一场战斗
                // 计算玩家要恢复多少点血量[20,40]
                r = new Random();
                int healHP = r.nextInt(20, 41);
                // 玩家恢复生命值
                player.heal(healHP);
                // 显示玩家当前生命值
                System.out.println("💚 " + player.name + " 恢复了 " + healHP + " 点生命值");
                System.out.println("🏆 当前胜场: " + wins);
                System.out.println("═══════════════════════════════════════");
            }

            // 5.9 每3场胜利，提升属性
            if(player.isAlive() && wins > 0 && wins % 3 == 0){
                System.out.println("恭喜你，您获得属性提升！");
                // 提升 最大生命值、攻击力、防御力
                player.maxHP += 30;
                player.attack += 5;
                player.defence += 3;
                // 显示玩家当前属性
                System.out.println("当前属性：最大生命力+30点，攻击力+5点，防御力+3点");
                System.out.println("当前属性：" + player.show());
            }

            // 5.10 询问是否继续
            if(player.isAlive()){
                System.out.println("是否继续战斗？（y/n）");
                Scanner sc = new Scanner(System.in);
                String choose = sc.next();
                if("y".equalsIgnoreCase(choose)){
                    count++;
                    continue;
                }else if("n".equalsIgnoreCase(choose)){
                    System.out.println("游戏结束");
                    break;
                }else{
                    System.out.println("没有这个选项，默认游戏继续");
                    continue;
                }
            }

        }
        
        // 6. 整个游戏结束，最终结算
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("游戏结束，最终结算：");
        System.out.println("🏆 总胜场: " + wins + ", 总战斗场数: " + count);
        System.out.println("═══════════════════════════════════════════════════");

        // 停止虚拟机的运行
        System.exit(0);
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

    // 玩家回合：选择行动（ 1普通攻击/ 2强力一击/ 3生命汲取 ）
    // 我打敌人，玩家回合
    public void playerTurn(HeroCharacter player, EnemyCharacter enemy){
        // ===== 玩家的回合 =====
        // 1. 普通攻击
        // 2. 强力一击 (消耗10HP)
        // 3. 生命汲取 (消耗10HP，恢复生命)
        System.out.println("===== " + player.name + " 的回合 =====");
        System.out.println("请选择行动(1-3)：1普通攻击 2强力一击 3生命汲取");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            default: 
                System.out.println("没有这个操作选项，默认使用普通攻击");
            case 1: // 玩家选择了普通攻击
                // 普通攻击
                // 基础伤害公式：`伤害 = 攻击力 - 防御力 `;
                // 最小伤害：1点
                int damage1 = calculateDamage(player.attack, enemy.defence);
                System.out.println("你对" + enemy.name + "使用普通攻击，造成了" + damage1 + "点伤害");
                enemy.takeDamage(damage1); // 敌人受到伤害
                break;
            case 2: // 玩家选择了强力一击
                if(player.HP <= 10){
                    System.out.println("玩家没有足够的生命值，无法使用强力一击，攻击失败");
                }else{ 
                    // 强力一击
                    // 使用技能消耗我方10HP
                    player.takeDamage(10);
                    // - 技能伤害：`伤害 = 攻击力 * n% - 防御力`
                    int damage2 = calculateDamage((int)(player.attack * 1.8), enemy.defence);
                    System.out.println("💥消耗10HP，你对" + enemy.name + "使用强力一击，造成了" + damage2 + "点伤害");
                    enemy.takeDamage(damage2); // 敌人受到伤害
                }
                break;
            case 3: // 玩家选择了生命汲取
                if(player.HP <= 10){
                    System.out.println("玩家没有足够的生命值，无法使用生命汲取，恢复失败");
                }else{
                    // 生命汲取
                    // 使用技能消耗我方10HP
                    player.takeDamage(10);
                    // 计算恢复的血量(0,20]
                    Random r = new Random();
                    int healHP = r.nextInt(21);
                    player.heal(healHP);
                    System.out.println("你使用生命汲取，恢复了" + healHP + "点生命值");
                }
                break;
        }
    }

    // 作用：用来计算双方战斗的时候，造成的伤害
    // 普通攻击的调用方式：calculateDamage(我方攻击力,对方防御力)
    // 技能攻击的调用方式：calculateDamage(我方攻击力 * n%, 对方防御力)
    // 基础伤害公式：`伤害 = 攻击力 - 防御力 `;
    // 最小伤害：1点
    public int calculateDamage(int attack, int defence){
        return attack - defence < 1 ? 1 : attack - defence;
    }

    // 敌人回合：选择行动（ 50%的几率普通攻击 / 50%的几率技能攻击 / 不同的敌人采取不同的技能进行攻击）
    // 敌人打我，敌人回合
    public void enemyTurn(EnemyCharacter enemy, HeroCharacter player){
        // ===== 敌人的回合 =====
        System.out.println("===== " + enemy.name + " 的回合 =====");

        // 计算当前是50%的普通攻击还是50%的技能攻击，默认是普通攻击
        String action = "普通攻击";

        // 进行几率的计算
        Random r = new Random();
        int num = r.nextInt(10); // 0、1、2、3、4 （普通攻击），5、6、7、8、9 （技能攻击）
        if(num >= 5){
            action = enemy.skill; // "技能攻击"
        }
        // 根据不同的情况，采取不同的攻击手段
        switch(action){
            case "普通攻击":
                System.out.println(enemy.name + "使用普通攻击");
                // 计算敌人用普通攻击对玩家造成的伤害
                int damage1 = calculateDamage(enemy.attack, player.defence);
                System.out.println(enemy.name + "对" + player.name + "使用普通攻击，造成了" + damage1 + "点伤害");
                player.takeDamage(damage1); // 玩家受到伤害
                break;
            case "猛击":
                System.out.println(enemy.name + "使用猛击");
                // 计算敌人用猛击对玩家造成的伤害
                int damage2 = calculateDamage((int)(enemy.attack * 1.5), player.defence);
                System.out.println(enemy.name + "对" + player.name + "使用猛击，造成了" + damage2 + "点伤害");
                player.takeDamage(damage2); // 玩家受到伤害
                break;
            case "快速攻击":
                System.out.println(enemy.name + "使用快速攻击");
                // 计算敌人用快速攻击对玩家造成的伤害
                int damage3 = 0;
                for(int i = 0; i < 2; i++){
                    damage3 += calculateDamage((int)(enemy.attack * 0.5), player.defence);
                }
                System.out.println(enemy.name + "对" + player.name + "使用快速攻击，造成了" + damage3 + "点伤害");
                player.takeDamage(damage3); // 玩家受到伤害
                break;
            case "防御姿态":
                System.out.println("当前的" + enemy.name + "采用了防御姿态  buff");
                // 敌人使用防御姿态，伤害减半
                enemy.defending = true;
                System.out.println("* " + enemy.name + "摆出防御姿态，下回合伤害减半"); // 开启防御状态，下回合减伤
                break;
            case "火球术":
                System.out.println(enemy.name + "使用火球术");
                // 计算敌人用火球术对玩家造成的伤害
                int damage5 = calculateDamage((int)(enemy.attack * 1.8), player.defence);
                System.out.println(enemy.name + "对" + player.name + "使用火球术，造成了" + damage5 + "点伤害");
                player.takeDamage(damage5); // 玩家受到伤害
                break;
        }
    }

}
