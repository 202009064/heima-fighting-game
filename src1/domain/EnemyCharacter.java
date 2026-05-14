package src1.domain;

public class EnemyCharacter extends Character {
    public String skill;
    public boolean defending;
    public EnemyCharacter(){
        super();
    }
    public EnemyCharacter(String name, int HP, int attack, int defence, String skill){
        super(name, HP, attack, defence);
        this.skill = skill;
    }
    @Override
    public void takeDamage(int damage) {
        if(this.defending){
            damage = damage / 2 > 1 ? damage / 2 : 1;
            this.defending = false;
        }
        super.takeDamage(damage);
    }
}
