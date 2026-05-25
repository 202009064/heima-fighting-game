package domain;

import java.util.Random;

public class User {
    // 属性：id、用户名、密码、状态
    private String id;
    private String username;
    private String password;
    private boolean status; // true：正常 false：禁用

    // 构造方法
    public User() {
        // 调用 createId 方法，设置 id 属性
        this.id = createId();
        // 默认状态为正常，因为布尔类型默认值为 false
        this.status = true;
    }

    public User(String username, String password) {
        this.id = createId();
        this.username = username;
        this.password = password;
        this.status = true;
    }

    // id：用户无法设置，是自动生成的，格式为：heima+5位数字的随机数
    public String createId() {
        StringBuilder sb = new StringBuilder("heima");

        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(r.nextInt(10));
        } // ”heima“ + 生成0-9之间的随机整数 * 5

        return sb.toString(); // 将 StringBuilder 转换为字符串
    }
    // public String createId(){
    //     return UUID.randomUUID().toString(); // 	工业标准
    //}
    // public String createId(){
    //     return "heima" + System.currentTimeMillis(); // 	简单可靠
    //}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
