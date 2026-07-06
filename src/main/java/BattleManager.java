public class BattleManager {
    private QuestionDB questionDB;
    private Player player;
    private Enemy enemy;
    private int difficultyMultiplier;
    private int currentRound;
    private enum BattleState { PlayerATK, EnemyATK, PlayerCATK }
    private BattleState currentState;
    private Question currentQuestion;
    private boolean suddenDeath;

    public BattleManager(Player player, Enemy enemy, int difficultyMultiplier) {
        this.questionDB = new QuestionDB();
        this.player = player;
        this.enemy = enemy;
        this.difficultyMultiplier = difficultyMultiplier;
        this.currentRound = 0;
        this.currentState = BattleState.PlayerATK;
        this.suddenDeath = false;
    }

    public void startBattle() {
        currentRound = 0;
        currentState = BattleState.PlayerATK;
        suddenDeath = false;
        nextPhase();
    }

    public Question getCurrentQuestion() { return currentQuestion; }

    public void nextPhase() {
        if (suddenDeath) {
            currentQuestion = questionDB.getRandomQuestion();
        } else {
            switch (currentState) {
                case PlayerATK:
                    currentQuestion = questionDB.getRandomQuestion(Question.QuestionType.ATTACK);
                    break;
                case EnemyATK:
                    currentQuestion = questionDB.getRandomQuestion(Question.QuestionType.DEFENSE);
                    break;
                case PlayerCATK:
                    currentQuestion = questionDB.getRandomQuestion(Question.QuestionType.COUNTERATTACK);
                    break;
            }
        }
    }

    public boolean evaluateAnswer(int answerIndex) {
        if (currentQuestion == null) return false;
        boolean correct = currentQuestion.evaluateAnswer(answerIndex);
        if (correct) {
            enemy.takeDamage(player.getDamage());
            player.gainExperience(20);
        } else {
            player.takeDamage(enemy.getAttack());
        }

        // Transição de fases
        if (suddenDeath) {
            // Em morte súbita, após cada pergunta o round avança
            advanceRound();
        } else {
            switch (currentState) {
                case PlayerATK:
                    currentState = BattleState.EnemyATK;
                    break;
                case EnemyATK:
                    currentState = BattleState.PlayerCATK;
                    break;
                case PlayerCATK:
                    advanceRound();
                    break;
            }
        }
        if (!isBattleOver()) nextPhase();
        return correct;
    }

    private void advanceRound() {
        currentRound++;
        if (currentRound >= 3) {
            if (enemy.isAlive()) suddenDeath = true;
        } else {
            currentState = BattleState.PlayerATK;
        }
    }

    public boolean isSuddenDeath() { return suddenDeath; }
    public boolean isBattleOver() { return !player.isAlive() || !enemy.isAlive(); }
    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public int getCurrentRound() { return currentRound; }

    public String getPhaseName() {
        if (suddenDeath) return "SUDDEN_DEATH";
        switch (currentState) {
            case PlayerATK: return "ATTACK";
            case EnemyATK: return "DEFENSE";
            case PlayerCATK: return "COUNTER";
            default: return "UNKNOWN";
        }
    }
}