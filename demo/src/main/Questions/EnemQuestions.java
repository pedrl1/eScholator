import java.util.List;
import java.util.ArrayList;

public class EnemQuestions {
    private List<String> alternatives;

    public EnemQuestions(String text, List<String> alternatives, int difficulty, int correctAltIndex) {
        super(text, alternatives, difficulty, correctAltIndex);
        this.alternatives = alternatives;
    }

    @Override
    public void showText() {
        System.out.println(getText());
        for (int i = 0; i < alternatives.size(); i++) {
            System.out.println((i + 1) + ". " + alternatives.getAlternatives(i));
        }
    }

    @Override
    public boolean evaluateAnswer(String answer) {
        int answerIndex = Integer.parseInt(answer) - 1;
        return answerIndex == getCorrectAltIndex();
    }

    public List<String> getAlternatives() {
        return alternatives;
    }
}