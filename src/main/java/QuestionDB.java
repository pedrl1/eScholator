import java.util.ArrayList;
import java.util.Random;

public class QuestionDB {
    private ArrayList<Question> questions;
    private Random random;

    public QuestionDB() {
        questions = new ArrayList<>();
        random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        ToFQuestions tof = new ToFQuestions();
        questions.addAll(tof.getQuestions());

        EnemQuestion enem = new EnemQuestion();
        questions.addAll(enem.getQuestions());
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) return null;
        return questions.get(random.nextInt(questions.size()));
    }

    public Question getRandomQuestion(Question.QuestionType type) {
        ArrayList<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            if (q.getType() == type) {
                filtered.add(q);
            }
        }
        if (filtered.isEmpty()) return null;
        return filtered.get(random.nextInt(filtered.size()));
    }

    // This is the method that was missing
    public Question getRandomQuestion(Question.QuestionType type, int difficulty) {
        ArrayList<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            if (q.getType() == type && q.getDifficulty() == difficulty) {
                filtered.add(q);
            }
        }
        if (filtered.isEmpty()) return null;
        return filtered.get(random.nextInt(filtered.size()));
    }
}