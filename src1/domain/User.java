package src1.domain;
import java.util.Random;

public class User {
    private String id;
    private String username;
    private String password;
    private boolean status;

    public User(){
        this.id = createId();
        this.status = true;
    }
    public User(String username, String password){
        this.id = createId();
        this.username = username;
        this.password = password;
        this.status = true;
    }

    public String createId(){
        StringBuilder sb = new StringBuilder("heima");

        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(r.nextInt(10));
        } // ”heima“ + 生成0-9之间的随机整数 * 5
        return sb.toString(); // 将 StringBuilder 转换为字符串
    }
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
