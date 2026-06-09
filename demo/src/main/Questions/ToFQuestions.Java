public class ToFQuestions extends Question {

    public ToFQuestions(String text, int difficulty, int correctAltIndex) {
        super(text, difficulty, correctAltIndex);
    }

    @Override
    public void showText() {
        System.out.println(getText());
        System.out.println("1. True");
        System.out.println("2. False");
    }

    @Override
    public boolean evaluateAnswer(String altIndex) {
        int playerAnswer = Integer.parseInt(altIndex) - 1; 
        return playerAnswer == getCorrectAltIndex();
    }
}