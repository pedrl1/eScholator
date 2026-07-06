public class ScoreSystem {
    private int score = 0;
    private int correctAnswers = 0;
    private int totalAnswers = 0;

    public boolean updateScore(Question question, int answerIndex) {
        totalAnswers++;
        boolean correct = question.evaluateAnswer(answerIndex);
        if (correct) {
            score += 10;
            correctAnswers++;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }
}