import java.util.ArrayList;
import java.util.Random;

public class ToFQuestions {
    private ArrayList<Question> questions;
    private Random random;

    public ToFQuestions() {
        questions = new ArrayList<>();
        random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        String[] tf = {"True", "False"};

        // Difficulty 1
        questions.add(new Question("The JVM interprets bytecode.", tf, 0, 1, Question.QuestionType.ATTACK));
        questions.add(new Question("A constructor must have the same name as the class.", tf, 0, 1, Question.QuestionType.DEFENSE));
        questions.add(new Question("An abstract class can be instantiated.", tf, 1, 1, Question.QuestionType.COUNTERATTACK));
        questions.add(new Question("A static method can access instance variables directly.", tf, 1, 1, Question.QuestionType.ATTACK));

        // Difficulty 2
        questions.add(new Question("The 'final' keyword prevents a class from being subclassed.", tf, 0, 2, Question.QuestionType.ATTACK));
        questions.add(new Question("In Java, '==' always compares object contents.", tf, 1, 2, Question.QuestionType.DEFENSE));
        questions.add(new Question("A 'try' block must be followed by either 'catch' or 'finally'.", tf, 0, 2, Question.QuestionType.COUNTERATTACK));
        questions.add(new Question("Multiple inheritance is allowed in Java through classes.", tf, 1, 2, Question.QuestionType.ATTACK));

        // Difficulty 3
        questions.add(new Question("An interface can contain implemented methods (Java 8+).", tf, 0, 3, Question.QuestionType.ATTACK));
        questions.add(new Question("The default value of a boolean in Java is true.", tf, 1, 3, Question.QuestionType.DEFENSE));
        questions.add(new Question("A 'for' loop can always be replaced by a 'while' loop.", tf, 0, 3, Question.QuestionType.COUNTERATTACK));
        questions.add(new Question("The 'volatile' keyword guarantees atomicity of all operations.", tf, 1, 3, Question.QuestionType.ATTACK));

        // Difficulty 4
        questions.add(new Question("The Linux kernel is a microkernel.", tf, 1, 4, Question.QuestionType.ATTACK));
        questions.add(new Question("DNS uses TCP for all queries.", tf, 1, 4, Question.QuestionType.DEFENSE));
        questions.add(new Question("A race condition occurs when two threads access shared data at the same time and at least one modifies it.", tf, 0, 4, Question.QuestionType.COUNTERATTACK));
        questions.add(new Question("Hadoop HDFS is designed for high-latency batch processing.", tf, 0, 4, Question.QuestionType.ATTACK));

        // Difficulty 5
        questions.add(new Question("The CAP theorem states that a distributed system can have consistency, availability, and partition tolerance simultaneously.", tf, 1, 5, Question.QuestionType.ATTACK));
        questions.add(new Question("A Turing machine can compute everything a modern computer can.", tf, 0, 5, Question.QuestionType.DEFENSE));
        questions.add(new Question("The Byzantine Generals Problem describes the difficulty of reaching consensus when some participants may lie.", tf, 0, 5, Question.QuestionType.COUNTERATTACK));
        questions.add(new Question("LLVM is a virtual machine that executes bytecode.", tf, 1, 5, Question.QuestionType.ATTACK));
    }

    public ArrayList<Question> getQuestions() { return questions; }
    public Question getRandomQuestion() {
        if (questions.isEmpty()) return null;
        return questions.get(random.nextInt(questions.size()));
    }
}