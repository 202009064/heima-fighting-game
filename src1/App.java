package src1;
import src1.ui.Login;
import src1.ui.FightingGame;

public class App {
    public static void main(String[] args) {
        /*Login login = new Login();
        login.show(); */
        FightingGame fightingGame = new FightingGame();
        fightingGame.startGame("jinye");
    }
}
