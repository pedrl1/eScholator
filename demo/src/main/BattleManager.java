public class BattleManager {

    private Round round;
    private QuestionBank questionBank;
    private ScoreSystem scoreSystem;
    private Player player;
    private Enemy enemy;
    private boolean defenseSuccessful;
    private int difficultyMultiplier;

    public BattleManager(Player player, Enemy enemy, int difficultyLevel) {
        this.round = new Round(1, 3);
        this.questionBank = new QuestionBank();
        this.scoreSystem = new ScoreSystem(0);
        this.player = player;
        this.enemy = enemy;
        this.defenseSuccessful = false;
        this.difficultyMultiplier = difficultyLevel;
    }

    public void startBattle() {
        while (player.isAlive() && enemy.isAlive()) {
            battleRound();
            if (!round.isFinalRound()) {
                round.nextRound();
            } else {
                break;
            }
        }
    }

    private void battleRound() {
        System.out.println("\n--- ROUND " + round.getRoundNumber() + " ---");
        
        attackPhase();
        
        if (!enemy.isAlive()) {
            return;
        }

        defensePhase();
        
        if (defenseSuccessful) {
            counterAttackPhase();
        }
    }
    
    private void attackPhase() {
        System.out.println("\n[PHASE 1: ATTACK]");
        
        Question attackQuestion = questionBank.getRandomQuestion(round.getRoundNumber() * difficultyMultiplier);
        
        if (attackQuestion == null) {
            System.out.println("No more questions available!");
            return;
        }
        
        System.out.println("Question: " + attackQuestion.getText());
        displayQuestionAlternatives(attackQuestion);
        
        int playerAnswer = getPlayerInput(attackQuestion.getAlternatives().length);
        
        if (attackQuestion.evaluateAnswer(playerAnswer)) {
            int damage = calculateDamage(player.getAttack(), enemy.getDefense(), difficultyMultiplier);
            enemy.takeDamage(damage);
            scoreSystem.addPoints(10 * difficultyMultiplier);
        }
        else {
            int damage = calculateDamage(enemy.getAttack(), player.getDefense(), difficultyMultiplier);
            player.takeDamage(damage);
            scoreSystem.addPoints(-10);
        }
    }

    private void defensePhase() {
        System.out.println("\n[PHASE 2: DEFENSE]");
        
        Question defenseQuestion = questionBank.getRandomQuestion(round.getRoundNumber() * difficultyMultiplier);
        
        if (defenseQuestion == null) {
            defenseSuccessful = false;
            return;
        }
        
        System.out.println("Question: " + defenseQuestion.getText());
        displayQuestionAlternatives(defenseQuestion);
        
        int playerAnswer = getPlayerInput(defenseQuestion.getAlternatives().length);
        
        if (defenseQuestion.evaluateAnswer(playerAnswer)) {
            defenseSuccessful = true;
            scoreSystem.addPoints(5 * difficultyMultiplier);
        } else {
            defenseSuccessful = false;
            int damage = calculateDamage(enemy.getAttack(), player.getDefense(), difficultyMultiplier);
            player.takeDamage(damage);
            scoreSystem.addPoints(-5);
        }
    }

    private void counterAttackPhase() {
        System.out.println("\n[PHASE 3: COUNTERATTACK]");
        
        int baseDamage = calculateDamage(player.getAttack(), enemy.getDefense(), difficultyMultiplier);
        int counterDamage = (int) (baseDamage * 1.5); 
        
        enemy.takeDamage(counterDamage);
        scoreSystem.addPoints(15 * difficultyMultiplier);
    }

    private int calculateDamage(int attackValue, int defenseValue, int difficulty) {
        int baseDamage = attackValue - (defenseValue / 2);
        int difficultyBonus = difficulty * 5;
        
        int finalDamage = Math.max(5, baseDamage + difficultyBonus);
        return finalDamage;
    }

    private void displayQuestionAlternatives(Question question) {
        String[] alternatives = question.getAlternatives();
        for (int i = 0; i < alternatives.length; i++) {
            System.out.println((i + 1) + ". " + alternatives[i]);
        }
    }

    private int getPlayerInput(int maxOptions) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int altIndex = 1;
        
        if (altIndex >= 1 && altIndex <= maxOptions) {
            System.out.print("Enter your answer (1-" + maxOptions + "): ");
            altIndex = scanner.nextInt();
        } else {
            altIndex = 1; 
        }
        return altIndex - 1; 
    }

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }

    public boolean isBattleActive() {
        return player.isAlive() && enemy.isAlive();
    }
}
