package src1.ui;
import src1.domain.HeroCharacter;

import java.util.Scanner;

import src1.domain.EnemyCharacter;
import java.util.ArrayList;

public class FightingGame {
    //
    public void startGame(String username){
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("    🎮 "+ username +"欢迎来到文字格斗游戏 🎮   ");
        System.out.println("╚════════════════════════════════════════╝");

        // 创建玩家角色
        HeroCharacter player = createPlayerCharacter(username);
        // 3. 显示玩家角色的属性和技能
        System.out.println("角色创建成功：");
        System.out.println("🌟 初始属性： " + player.show());
        System.out.println("🌟 拥有技能： " + player.showSkillList());

        ArrayList<EnemyCharacter> enemies = new ArrayList<>();
        enemies.add(new EnemyCharacter("初级战士", 80, 15, 10, "猛击"));
        enemies.add(new EnemyCharacter("敏捷刺客", 60, 20, 5, "快速攻击"));
        enemies.add(new EnemyCharacter("重装坦克", 120, 10, 20, "防御姿态"));
        enemies.add(new EnemyCharacter("神秘法师", 70, 25, 8, "火球术（180%伤害）"));
    }
    public HeroCharacter createPlayerCharacter(String username){
        System.out.println("创建您的角色：");
        System.out.println("您的角色名为：" + username);
        int points = 20;
        System.out.println("请分配属性点（共" + points + "点）");
        // 提示
        System.out.println("1. 生命值 (每点+10 HP)");
        System.out.println("2. 攻击力 (每点+2 ATK)");
        System.out.println("3. 防御力 (每点+1 DEF)");

        Scanner sc = new Scanner(System.in);

        String[] attributes = {"生命值", "攻击力", "防御力"};
        int[] values  = new int[attributes.length];
        for(int i = 0; i<attributes.length; i++){
            System.out.println("分配点数到" + attributes[i] + "（剩余点数：" + points + "）：");
            int input = sc.nextInt();
            if(input < 0){
                System.out.println("无效输入，默认" + attributes[i] + "属性为0点");
                input = 0;
            }
            if(input > points){
                System.out.println("属性点不足，剩余属性点全部分配到：" + attributes[i]);
                input = points;
            }
            points -= input;
            values[i] = input;
        }
        HeroCharacter player = new HeroCharacter(
            username,
            100 + values[0]*10,
            100 + values[1]*2,
            100 + values[2]*1
        );
        player.skillList.add("普通攻击");
        player.skillList.add("强力一击");
        player.skillList.add("生命汲取");
        return player;
    }
}
