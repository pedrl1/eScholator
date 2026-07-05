import java.util.ArrayList;
import java.util.Random;

public class QuestionDB {
    private ArrayList<Question> atkQuestions;
    private ArrayList<Question> defQuestions;
    private ArrayList<Question> counterAtkQuestions;
    private Random rand;

    public QuestionDB() {
        rand = new Random();
        atkQuestions = new ArrayList<>();
        defQuestions = new ArrayList<>();
        counterAtkQuestions = new ArrayList<>();

        atkQuestions.add(new ToFQuestions("Java é uma linguagem compilada?", 1, 0, Question.QuestionType.ATTACK));
        atkQuestions.add(new EnemQuestion("Qual é a capital do Brasil?",
                new String[]{"São Paulo", "Rio de Janeiro", "Brasília", "Salvador"}, 1, 2, Question.QuestionType.ATTACK));
        atkQuestions.add(new EnemQuestion("Quanto é 2+2?",
                new String[]{"3", "4", "5", "6"}, 1, 1, Question.QuestionType.ATTACK));
        atkQuestions.add(new ToFQuestions("O Sol gira em torno da Terra?", 1, 1, Question.QuestionType.ATTACK));
        atkQuestions.add(new EnemQuestion("Qual a maior cidade do mundo?",
                new String[]{"Tóquio", "Nova York", "Londres", "São Paulo"}, 2, 0, Question.QuestionType.ATTACK));

        defQuestions.add(new ToFQuestions("A água ferve a 100°C?", 1, 0, Question.QuestionType.DEFENSE));
        defQuestions.add(new EnemQuestion("Qual o maior planeta do sistema solar?",
                new String[]{"Terra", "Marte", "Júpiter", "Saturno"}, 1, 2, Question.QuestionType.DEFENSE));
        defQuestions.add(new ToFQuestions("O oxigênio é um gás nobre?", 1, 1, Question.QuestionType.DEFENSE));
        defQuestions.add(new EnemQuestion("Qual a fórmula da água?",
                new String[]{"H2O", "CO2", "NaCl", "O2"}, 1, 0, Question.QuestionType.DEFENSE));

        counterAtkQuestions.add(new ToFQuestions("O Sol é uma estrela?", 1, 0, Question.QuestionType.COUNTERATTACK));
        counterAtkQuestions.add(new EnemQuestion("Qual a raiz quadrada de 144?",
                new String[]{"10", "11", "12", "13"}, 2, 2, Question.QuestionType.COUNTERATTACK));
        counterAtkQuestions.add(new ToFQuestions("A Terra é plana?", 1, 1, Question.QuestionType.COUNTERATTACK));
        counterAtkQuestions.add(new EnemQuestion("Qual a capital da França?",
                new String[]{"Londres", "Paris", "Berlim", "Madri"}, 1, 1, Question.QuestionType.COUNTERATTACK));
    }

    public Question getRandomQuestion(Question.QuestionType type) {
        ArrayList<Question> list;
        switch (type) {
            case ATTACK: list = atkQuestions; break;
            case DEFENSE: list = defQuestions; break;
            case COUNTERATTACK: list = counterAtkQuestions; break;
            default: list = atkQuestions;
        }
        if (list.isEmpty()) return atkQuestions.get(0);
        return list.get(rand.nextInt(list.size()));
    }

    public Question getRandomQuestion() {
        ArrayList<Question> all = new ArrayList<>();
        all.addAll(atkQuestions);
        all.addAll(defQuestions);
        all.addAll(counterAtkQuestions);
        return all.get(rand.nextInt(all.size()));
    }
}