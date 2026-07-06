public class Question {
    private String text;
    private String[] alternatives;
    private int correctIndex;
    private int difficulty;
    private QuestionType type;

    public enum QuestionType {
        ATTACK, DEFENSE, COUNTERATTACK
    }

    public Question(String text, String[] alternatives, int correctIndex, int difficulty, QuestionType type) {
        this.text = text;
        this.alternatives = alternatives;
        this.correctIndex = correctIndex;
        this.difficulty = difficulty;
        this.type = type;
    }

    public String getText() { return text; }
    public String[] getAlternatives() { return alternatives; }
    public int getCorrectIndex() { return correctIndex; }
    public int getDifficulty() { return difficulty; }
    public QuestionType getType() { return type; }

    public boolean evaluateAnswer(int answerIndex) {
        return answerIndex == correctIndex;
    }
}