import java.util.Random;

public class QuestionBank {

    private Question[] questions = {
        //very easy questions
        new Question("", new String[]{}, 1, 0),
        new Question("", new String[]{}, 1, 0),
        new Question("", new String[]{}, 1, 0),
        new Question("", new String[]{}, 1, 0),
        new Question("", new String[]{}, 1, 0),
        new Question("", new String[]{}, 1, 0),
        
        //easy questions
        new Question("", new String[]{}, 2, 0),
        new Question("", new String[]{}, 2, 0),
        new Question("", new String[]{}, 2, 0),
        new Question("", new String[]{}, 2, 0),
        new Question("", new String[]{}, 2, 0),
        new Question("", new String[]{}, 2, 0),
        
        //medium questions
        new Question("", new String[]{}, 3, 0),
        new Question("", new String[]{}, 3, 0),
        new Question("", new String[]{}, 3, 0),
        new Question("", new String[]{}, 3, 0),
        new Question("", new String[]{}, 3, 0),
        new Question("", new String[]{}, 3, 0),
        
        //hard questions
        new Question("", new String[]{}, 4, 0),
        new Question("", new String[]{}, 4, 0),
        new Question("", new String[]{}, 4, 0),
        new Question("", new String[]{}, 4, 0),
        new Question("", new String[]{}, 4, 0),
        new Question("", new String[]{}, 4, 0),
        
        //very hard questions
        new Question("", new String[]{}, 5, 0),
        new Question("", new String[]{}, 5, 0),
        new Question("", new String[]{}, 5, 0),
        new Question("", new String[]{}, 5, 0),
        new Question("", new String[]{}, 5, 0),
        new Question("", new String[]{}, 5, 0)
    };

    private Random random = new Random();

    public Question[] getQuestions() {
        return questions;
    }

    public Question getRandomQuestion(int difficulty) {
        java.util.List<Question> filteredQuestions = new java.util.ArrayList<>();
        
        for (Question question : questions) {
            if (question.getDifficulty() <= difficulty) {
                filteredQuestions.add(question);
            }
        }
        
        if (filteredQuestions.isEmpty()) {
            return questions[random.nextInt(questions.length)];
        }
        
        return filteredQuestions.get(random.nextInt(filteredQuestions.size()));
    }

    public void askQuestion() {
        System.out.println("Question: " + questions[0].getText());
        String[] alternatives = questions[0].getAlternatives();
        for (int i = 0; i < alternatives.length; i++) {
            System.out.println((i + 1) + ". " + alternatives[i]);  
        }
    }
}
