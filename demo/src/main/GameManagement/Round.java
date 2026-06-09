public class Round {
    private Question question;
    private Character player;
    private Character enemy;
    private int roundNumber;
    private int roundMaxNumber;

    public Round(Question question, Character player, Character enemy, int roundNumber, int roundMaxNumber) {
        this.question = question;
        this.player = player;
        this.enemy = enemy;
        this.roundNumber = roundNumber;
        this.roundMaxNumber = roundMaxNumber;   
    }

    public void startRound() {
        System.out.println("Round " + roundNumber + " of " + roundMaxNumber);
        question.showText();

        System.out.print("Your answer: ");
        String answer = System.console().readLine();
        if (question.evaluateAnswer(answer)) {
            System.out.println("Correct! You attack the enemy.");
            enemy.takeDamage(player.getAttack());
        } else {
            System.out.println("Wrong! The enemy attacks you.");
            player.takeDamage(enemy.getAttack());
        }
        System.out.println("Player Health: " + player.getHealth());
        System.out.println("Enemy Health: " + enemy.getHealth());

        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated!");
        } else if (enemy.getHealth() <= 0) {
            System.out.println("You have defeated the enemy!");
        }
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getRoundMaxNumber() {
        return roundMaxNumber;
    }

    public void nextRound() {
        if (roundNumber < roundMaxNumber) {
            roundNumber++;
        }
    }

    public void resetRound() {
        roundNumber = 1;
    }

    public boolean isFinalRound() {
        return roundNumber == roundMaxNumber;
    }

}
