package domain;
import java.util.ArrayList;

// 我方游戏人物的角色
public class HeroCharacter extends Character {
    public ArrayList<String> skillList; // 技能列表

    // 把集合对象也初始化了，这样子的好处是：外界要给我放角色添加技能的时候，无需考虑集合，直接add即可
    // 其实初始化写构造函数里和外部都行，只需记住，
    //                                    所有构造函数初始化结果一样 → 则在定义时进行初始化，因为简洁；
    //                                    不同构造函数需要不同的初始值 → 在构造函数中初始化。
    public HeroCharacter(){
        super();
        skillList = new ArrayList<>();
    }
    public HeroCharacter(String name, int HP, int attack, int defence){
        super(name, HP, attack, defence);
        skillList = new ArrayList<>();
    }

    // 行为：用来遍历技能列表
    public String showSkillList(){
        StringBuilder sb = new StringBuilder();
        for (String skill : skillList) {
            sb.append(skill);
            if (skillList.indexOf(skill) != skillList.size() - 1) {
                sb.append("、");
            }
        }
        return sb.toString();
    }
}
