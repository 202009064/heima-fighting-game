package domain;

public class Character {
    public String name;
    public int HP; // 当前生命值
    public int maxHP; // 最大生命值
    public int attack; // 攻击力
    public int defence; // 防御力

    // 构造方法
    public Character() {

    }
    public Character(String name, int HP, int attack, int defence) {
        this.name = name;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.defence = defence;
    }

    // 行为：判断人物是否存活、恢复血量、受到伤害、展示人物的属性值

    // 1. 判断人物是否存活
    public boolean isAlive() {
        return this.HP > 0;
    }

    // 2. 恢复血量（为什么将这个行为写在父类，因为我方角色和敌方角色都可能有恢复血量的行为，所以将这个行为写在父类）
    // 作用：恢复血量
    // 形参：具体恢复了多少血
    public void heal(int amount) {
        // this.HP += amount;
        // if (this.HP > this.maxHP) {
        //     this.HP = this.maxHP;
        // }
        this.HP = Math.min(this.HP + amount, this.maxHP);
    }

    // 3. 人物受到伤害
    // 作用：受到N点伤害之后，还有多少点血量
    // 形参：具体受到多少点伤害
    public void takeDamage(int damage) {
        // this.HP -= damage;
        // if (this.HP < 0) {
        //     this.HP = 0;
        // }
        this.HP = Math.max(this.HP - damage, 0);
    }

    // 4. 展示人物的属性值
    public String show() {
        return this.name + "[ 当前生命：" + this.HP + "，攻击力：" + this.attack + "，防御力：" + this.defence + " ]";
    }
}
