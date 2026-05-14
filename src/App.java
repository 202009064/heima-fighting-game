import ui.Login;
import ui.FightingGame;

public class App {
    public static void main(String[] args) {
        /*Login login = new Login();
        login.start(); */
        FightingGame fg = new FightingGame();
        fg.startGame("张三");
    }
}