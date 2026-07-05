public class ToFQuestions extends Question {
    
    public ToFQuestions(String text, int difficulty, int correctAltIndex, QuestionType type) {
        super(text, new String[] {"True", "False"}, difficulty, correctAltIndex, type);
    }

}