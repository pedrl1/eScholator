import java.util.Scanner;

public class Main {
    public static void main (String [] args){
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String playerName = scanner.nextLine();

        Player player = new Player(playerName, 100, 1, 0);
        
        int battleIndex = 0;
        int totalScore = 0;
        int difficultyLevel = 1;

        while (battleIndex < 3) {

            BattleManager battleManager = new BattleManager(player, Enemy.getEnemy(difficultyLevel), difficultyLevel);
            battleManager.startBattle();
            battleIndex++;
        }
        

    }
}
