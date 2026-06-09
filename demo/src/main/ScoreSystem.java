public class ScoreSystem {
    private int score;

    public ScoreSystem(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        score += points;
    }
    
    public void updateScore(AnswerEvaluation answerEvaluation) {
        if (answerEvaluation.evaluateAnswer(answerEvaluation.getCorrectAltIndex())) {
            addPoints(10); 
        } else {
            addPoints(-5); 
        }
    }
}
