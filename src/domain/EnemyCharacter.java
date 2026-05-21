package domain;

// 敌方角色
public class EnemyCharacter extends Character {
    // 为什么我方角色技能用字符串列表定义数据结构，而敌方角色技能用字符串定义数据结构？
    // 因为我方角色技能是多个，而敌方角色技能是一个
    public String skill;
    public boolean defending; // 是否在防御中，true：是 false：否。默认false。

    public EnemyCharacter(){
        super();
    } // 无作用，只是为了方便调用父类的构造函数，而定义的
    public EnemyCharacter(String name, int HP, int attack, int defence, String skill){
        super(name, HP, attack, defence);
        this.skill = skill;
    }

    // 因为敌人有一个伤害减半的技能，所以重写一下父类方法
    @Override
    public void takeDamage(int damage){
        // 如果处于防御状态，伤害减半，但是不能小于1点。true：是 false：否
        if(this.defending){
            damage = damage / 2 > 1 ? damage / 2 : 1;
            // 表示防御状态只能持续一个回合
            this.defending = false;
        }
        super.takeDamage(damage);
    }
}
