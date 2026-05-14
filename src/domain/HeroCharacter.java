package domain;
import java.util.ArrayList;

// 我方游戏人物的角色
public class HeroCharacter extends Character {
    public ArrayList<String> skillList; // 技能列表

    // 在构造函数中把集合对象也初始化了，这样子的好处是：外界要给我放角色添加技能的时候，无需考虑集合，直接add即可
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
