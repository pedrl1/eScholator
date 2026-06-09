public abstract class Question {

    private String text;
    private int difficulty;
    private int correctAltIndex;

    public Question(String text, String[] alternatives, int difficulty, int correctAltIndex) {
        this.text = text;
        this.difficulty = difficulty;
        this.correctAltIndex = correctAltIndex;
    }

    public abstract void showText();
    public abstract boolean evaluateAnswer(int altIndex);

    public String getText() {
        return text;
    }

    public int getDifficulty() {
        return difficulty;
    }
    
    public int getCorrectAltIndex() {
        return correctAltIndex;
    }

}
