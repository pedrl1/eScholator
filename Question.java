public abstract class Question {
    public enum QuestionType {
        ATTACK,
        DEFENSE,
        COUNTERATTACK
    }

    private String text;
    protected String[] alternatives;
    private int difficulty;
    private int correctAltIndex;
    private QuestionType type;

    public Question(String text, String[] alternatives, int difficulty, int correctAltIndex, QuestionType type) {
        this.text = text;
        this.alternatives = alternatives;
        this.difficulty = difficulty;
        this.correctAltIndex = correctAltIndex;
        this.type = type;
    }

    public String[] getAlternatives(){
        return alternatives;
    }

    public boolean evaluateAnswer(int altIndex) {
        return altIndex == correctAltIndex;
    }

    public String getText() {
        return text;
    }

    public int getDifficulty() {
        return difficulty;
    }
    
    public int getCorrectAltIndex() {
        return correctAltIndex;
    }

    public QuestionType getType(){
        return type;
    }

}
