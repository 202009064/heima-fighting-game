package src1.domain;
import java.util.ArrayList;

public class HeroCharacter extends Character {
    public ArrayList<String> skillList; // 技能列表
    public HeroCharacter(){
        super();
        skillList = new ArrayList<>();
    }
    public HeroCharacter(String name, int HP, int attack, int defence){
        super(name, HP, attack, defence);
        skillList = new ArrayList<>();
    }
    public String showSkillList(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<skillList.size(); i++){
            sb.append(skillList.get(i));
            if(i<skillList.size()-1){
                sb.append("，");
            }
        }
        return sb.toString();
    }
}
