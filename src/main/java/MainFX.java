// MainFX.java - Abilities area disappears after answering, reappears on next question
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class MainFX extends Application {
    private QuestionDB questionDB;
    private ScoreSystem scoreSystem;
    private Question currentQuestion;
    private Player player;
    private ArrayList<Enemy> enemies;
    private int enemyIndex = 0;
    private int enemyHP, maxEnemyHP;
    private int playerHP, maxPlayerHP;

    private int currentRound;
    private enum BattlePhase { ATTACK, DEFENSE, COUNTERATTACK }
    private BattlePhase currentPhase;
    private static final int MAX_ROUNDS = 3;

    private StackPane root;
    private VBox startScreen;
    private VBox gameScreen;
    private VBox pauseScreen;

    private Label questionLabel;
    private VBox alternativesBox;
    private Label feedbackLabel;
    private Label scoreLabel;
    private Label enemyNameLabel;
    private ProgressBar enemyHPBar;
    private Label enemyHPLabel;
    private ProgressBar playerHPBar;
    private Label playerHPLabel;
    private Label roomLabel;
    private Label opponentProgressLabel;
    private Label difficultyLabel;
    private Label levelLabel;
    private Label expLabel;
    private ArrayList<Ability> abilities;
    private VBox abilitiesBox;
    private Label abilityFeedbackLabel;
    private ArrayList<Button> abilityButtons;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        questionDB = new QuestionDB();
        scoreSystem = new ScoreSystem();
        enemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemies.add(Enemy.getEnemy(i));
        }

        root = new StackPane();
        createStartScreen();
        createGameScreen();
        createPauseScreen();

        root.getChildren().addAll(gameScreen, pauseScreen, startScreen);
        gameScreen.setVisible(false);
        pauseScreen.setVisible(false);
        startScreen.setVisible(true);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("eScholator");
        stage.show();
    }

    private void createStartScreen() {
        startScreen = new VBox(30);
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setStyle("-fx-background-color: #1a1a2e;");
        startScreen.setPadding(new Insets(50));

        Label title = new Label("eScholator");
        title.setFont(Font.font(36));
        title.setTextFill(Color.GOLD);

        Label subtitle = new Label("Study hard, answer questions, and crush your academic rivals!");
        subtitle.setFont(Font.font(18));
        subtitle.setTextFill(Color.LIGHTGRAY);

        Button startBtn = new Button("BEGIN EXAM");
        startBtn.setFont(Font.font(20));
        startBtn.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-padding: 15 40;");
        startBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("Scholar");
            dialog.setTitle("Student Registration");
            dialog.setHeaderText("Enter your student name:");
            dialog.setContentText("Name:");
            dialog.showAndWait().ifPresent(name -> {
                player = new Player(name, 100, 1, 0);
                startBattle();
            });
        });

        startScreen.getChildren().addAll(title, subtitle, startBtn);
    }

    private void createGameScreen() {
        gameScreen = new VBox(10);
        gameScreen.setStyle("-fx-background-color: #16213e;");
        gameScreen.setPadding(new Insets(20));

        HBox topBar = createTopBar();
        VBox questionArea = createQuestionArea();
        VBox abilitiesArea = createAbilitiesArea();

        Button pauseBtn = new Button("Pause");
        pauseBtn.setFont(Font.font(14));
        pauseBtn.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white;");
        pauseBtn.setOnAction(e -> showPause());

        HBox bottomBar = new HBox(10, pauseBtn);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);

        gameScreen.getChildren().addAll(topBar, questionArea, abilitiesArea, bottomBar);
    }

    private HBox createTopBar() {
        VBox enemyBox = new VBox(5);
        enemyNameLabel = new Label("???");
        enemyNameLabel.setFont(Font.font(20));
        enemyNameLabel.setTextFill(Color.RED);

        opponentProgressLabel = new Label("");
        opponentProgressLabel.setFont(Font.font(12));
        opponentProgressLabel.setTextFill(Color.WHITE);

        enemyHPBar = new ProgressBar(1);
        enemyHPBar.setPrefWidth(200);
        enemyHPBar.setStyle("-fx-accent: red;");

        enemyHPLabel = new Label("HP: ?/?");
        enemyHPLabel.setTextFill(Color.WHITE);
        enemyBox.getChildren().addAll(enemyNameLabel, opponentProgressLabel, enemyHPBar, enemyHPLabel);

        VBox playerBox = new VBox(5);
        Label playerNameLabel = new Label();
        playerNameLabel.setFont(Font.font(20));
        playerNameLabel.setTextFill(Color.LIGHTGREEN);

        playerHPBar = new ProgressBar(1);
        playerHPBar.setPrefWidth(200);
        playerHPBar.setStyle("-fx-accent: green;");

        playerHPLabel = new Label("HP: ?/?");
        playerHPLabel.setTextFill(Color.WHITE);

        levelLabel = new Label("Lv: 1");
        levelLabel.setFont(Font.font(14));
        levelLabel.setTextFill(Color.YELLOW);
        expLabel = new Label("EXP: 0/100");
        expLabel.setFont(Font.font(12));
        expLabel.setTextFill(Color.LIGHTYELLOW);

        playerBox.getChildren().addAll(playerNameLabel, playerHPBar, playerHPLabel, levelLabel, expLabel);
        playerBox.setUserData(playerNameLabel);

        VBox infoBox = new VBox(5);
        scoreLabel = new Label("KP: 0");
        scoreLabel.setFont(Font.font(16));
        scoreLabel.setTextFill(Color.GOLD);

        roomLabel = new Label("Room: ???");
        roomLabel.setFont(Font.font(14));
        roomLabel.setTextFill(Color.CYAN);
        infoBox.getChildren().addAll(scoreLabel, roomLabel);

        HBox topBar = new HBox(50, enemyBox, playerBox, infoBox);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #1a1a2e; -fx-border-color: #0f3460; -fx-border-width: 2;");
        return topBar;
    }

    private VBox createQuestionArea() {
        VBox area = new VBox(15);
        area.setAlignment(Pos.CENTER);
        area.setPadding(new Insets(20));

        questionLabel = new Label("Waiting for the next exam question...");
        questionLabel.setFont(Font.font(18));
        questionLabel.setTextFill(Color.WHITE);
        questionLabel.setWrapText(true);

        difficultyLabel = new Label("");
        difficultyLabel.setFont(Font.font(14));
        difficultyLabel.setTextFill(Color.LIGHTYELLOW);

        alternativesBox = new VBox(10);
        alternativesBox.setAlignment(Pos.CENTER);

        feedbackLabel = new Label("");
        feedbackLabel.setFont(Font.font(16));
        feedbackLabel.setVisible(false);

        area.getChildren().addAll(questionLabel, difficultyLabel, alternativesBox, feedbackLabel);
        return area;
    }

    private VBox createAbilitiesArea() {
        abilitiesBox = new VBox(5);
        abilitiesBox.setAlignment(Pos.CENTER);
        abilitiesBox.setPadding(new Insets(10));
        abilitiesBox.setStyle("-fx-background-color: #1a1a2e; -fx-border-color: #0f3460; -fx-border-width: 1;");
        Label title = new Label("Abilities (Cost EXP)");
        title.setFont(Font.font(14));
        title.setTextFill(Color.CYAN);
        abilitiesBox.getChildren().add(title);

        abilityFeedbackLabel = new Label("");
        abilityFeedbackLabel.setFont(Font.font(12));
        abilityFeedbackLabel.setTextFill(Color.ORANGE);
        abilityFeedbackLabel.setVisible(false);
        abilitiesBox.getChildren().add(abilityFeedbackLabel);

        return abilitiesBox;
    }

    private void createPauseScreen() {
        pauseScreen = new VBox(30);
        pauseScreen.setAlignment(Pos.CENTER);
        pauseScreen.setStyle("-fx-background-color: rgba(0,0,0,0.8);");
        pauseScreen.setPadding(new Insets(50));

        Label pauseTitle = new Label("STUDY BREAK");
        pauseTitle.setFont(Font.font(40));
        pauseTitle.setTextFill(Color.WHITE);

        Button resumeBtn = new Button("RESUME STUDYING");
        resumeBtn.setFont(Font.font(20));
        resumeBtn.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white; -fx-padding: 15 40;");
        resumeBtn.setOnAction(e -> hidePause());

        Button quitBtn = new Button("DROP OUT");
        quitBtn.setFont(Font.font(18));
        quitBtn.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-padding: 10 30;");
        quitBtn.setOnAction(e -> goToStart());

        pauseScreen.getChildren().addAll(pauseTitle, resumeBtn, quitBtn);
    }

    private void startBattle() {
        startScreen.setVisible(false);
        gameScreen.setVisible(true);
        enemyIndex = 0;
        scoreSystem = new ScoreSystem();
        currentRound = 1;
        currentPhase = BattlePhase.ATTACK;

        updateOpponentDisplay();

        maxPlayerHP = player.getMaxHealth();
        playerHP = maxPlayerHP;
        playerHPBar.setProgress(1);
        playerHPLabel.setText("HP: " + playerHP + "/" + maxPlayerHP);

        HBox topBar = (HBox) gameScreen.getChildren().get(0);
        VBox playerBox = (VBox) topBar.getChildren().get(1);
        Label nameLbl = (Label) playerBox.getUserData();
        nameLbl.setText(player.getName());

        initializeAbilities();
        updatePlayerStats();
        loadNewQuestion();
    }

    private void initializeAbilities() {
        abilities = new ArrayList<>();
        abilities.add(new PenAbility("Pen", "Weak boost (0.75x EXP)", 10));
        abilities.add(new NotebookAbility("Notebook", "Moderate boost (1x EXP)", 20));
        abilities.add(new CalcAbility("Calculator", "Strong boost (1.25x EXP)", 30));
        abilities.add(new PcAbility("PC", "Powerful boost (2x EXP)", 50));

        abilitiesBox.getChildren().removeIf(node -> node instanceof Button);

        abilityButtons = new ArrayList<>();
        for (Ability ability : abilities) {
            Button btn = new Button(formatButtonText(ability));
            btn.setFont(Font.font(12));
            btn.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white; -fx-padding: 5 15;");
            btn.setTooltip(new Tooltip(ability.getDescription()));
            btn.setOnAction(e -> useAbility(ability, btn));
            abilitiesBox.getChildren().add(btn);
            abilityButtons.add(btn);
        }
        abilitiesBox.setVisible(true);
    }

    private String formatButtonText(Ability ability) {
        return ability.getName() + " (" + ability.getCost() + " EXP) [" +
               ability.getRemainingUses() + "/" + ability.getMaxUses() + "]";
    }

    private void useAbility(Ability ability, Button btn) {
        if (!ability.canUse()) {
            abilityFeedbackLabel.setText("No uses remaining!");
            abilityFeedbackLabel.setTextFill(Color.RED);
            abilityFeedbackLabel.setVisible(true);
            return;
        }
        if (player.getExperience() < ability.getCost()) {
            abilityFeedbackLabel.setText("Not enough EXP!");
            abilityFeedbackLabel.setTextFill(Color.RED);
            abilityFeedbackLabel.setVisible(true);
            return;
        }
        ability.useAbility();
        ((Power) ability).abilityPower(player, player.getExperience());
        abilityFeedbackLabel.setText(ability.getName() + " activated! Damage increased.");
        abilityFeedbackLabel.setTextFill(Color.LIME);
        abilityFeedbackLabel.setVisible(true);
        updatePlayerStats();
        btn.setText(formatButtonText(ability));
        // Disable this ability button for the rest of the question
        btn.setDisable(true);
    }

    private void updatePlayerStats() {
        levelLabel.setText("Lv: " + player.getLevel());
        int exp = player.getExperience();
        int nextLevel = 100;
        expLabel.setText("EXP: " + exp + "/" + nextLevel);
    }

    private void updateOpponentDisplay() {
        Enemy currentEnemy = enemies.get(enemyIndex);
        enemyNameLabel.setText(currentEnemy.getName());
        opponentProgressLabel.setText("Rival " + (enemyIndex + 1) + " of " + enemies.size() +
                " (Difficulty " + currentEnemy.getDifficulty() + ")");
        maxEnemyHP = currentEnemy.getMaxHealth();
        enemyHP = maxEnemyHP;
        enemyHPBar.setProgress(1);
        enemyHPLabel.setText("HP: " + enemyHP + "/" + maxEnemyHP);
    }

    private void loadNewQuestion() {
        // Show abilities area again
        abilitiesBox.setVisible(true);
        // Re-enable buttons that still have uses
        for (int i = 0; i < abilityButtons.size(); i++) {
            Ability ability = abilities.get(i);
            Button btn = abilityButtons.get(i);
            if (ability.canUse()) {
                btn.setDisable(false);
            }
            btn.setText(formatButtonText(ability));
        }

        // Determine required question type
        Question.QuestionType requiredType = null;
        switch (currentPhase) {
            case ATTACK:
                requiredType = Question.QuestionType.ATTACK;
                break;
            case DEFENSE:
                requiredType = Question.QuestionType.DEFENSE;
                break;
            case COUNTERATTACK:
                requiredType = Question.QuestionType.COUNTERATTACK;
                break;
        }

        int enemyDifficulty = enemies.get(enemyIndex).getDifficulty();

        currentQuestion = questionDB.getRandomQuestion(requiredType, enemyDifficulty);
        if (currentQuestion == null) {
            currentQuestion = questionDB.getRandomQuestion(requiredType);
        }
        if (currentQuestion == null) {
            currentQuestion = questionDB.getRandomQuestion();
        }

        if (currentQuestion == null) {
            questionLabel.setText("No questions available!");
            alternativesBox.getChildren().clear();
            difficultyLabel.setText("");
            feedbackLabel.setVisible(false);
            return;
        }

        questionLabel.setText(currentQuestion.getText());
        String diffText = "Difficulty: " + currentQuestion.getDifficulty();
        switch (currentQuestion.getDifficulty()) {
            case 1: diffText += " (Easy)"; break;
            case 2: diffText += " (Medium)"; break;
            case 3: diffText += " (Hard)"; break;
            case 4: diffText += " (Very Hard)"; break;
            case 5: diffText += " (Expert)"; break;
        }
        difficultyLabel.setText(diffText);

        updateAlternatives(currentQuestion);

        String phaseName = "";
        switch (currentPhase) {
            case ATTACK: phaseName = "Exam"; break;
            case DEFENSE: phaseName = "Study Hall"; break;
            case COUNTERATTACK: phaseName = "Extra Credit"; break;
        }
        roomLabel.setText("Round " + currentRound + " - " + phaseName);

        feedbackLabel.setVisible(false);
    }

    private void updateAlternatives(Question q) {
        alternativesBox.getChildren().clear();
        String[] alternatives = q.getAlternatives();
        for (int i = 0; i < alternatives.length; i++) {
            Button btn = new Button(alternatives[i]);
            btn.setFont(Font.font(16));
            btn.setStyle("-fx-background-color: #0f3460; -fx-text-fill: white; -fx-padding: 10 30;");
            btn.setPrefWidth(400);
            int index = i;
            btn.setOnAction(e -> handleAnswer(index));
            alternativesBox.getChildren().add(btn);
        }
    }

    private void handleAnswer(int answerIndex) {
        if (currentQuestion == null) return;

        // Hide abilities area after answering
        abilitiesBox.setVisible(false);

        // Disable answer buttons
        alternativesBox.getChildren().forEach(node -> {
            if (node instanceof Button) ((Button) node).setDisable(true);
        });

        boolean correct = scoreSystem.updateScore(currentQuestion, answerIndex);
        scoreLabel.setText("KP: " + scoreSystem.getScore());

        if (correct) {
            feedbackLabel.setText("Correct! You aced it! -" + currentQuestion.getDifficulty() * 10 + " HP to your rival!");
            feedbackLabel.setTextFill(Color.LIGHTGREEN);
            enemyHP -= currentQuestion.getDifficulty() * 10;
            if (enemyHP < 0) enemyHP = 0;
            player.gainExperience(20);
            updatePlayerStats();
        } else {
            feedbackLabel.setText("Wrong! You missed that one and lost 10 HP!");
            feedbackLabel.setTextFill(Color.RED);
            playerHP -= 10;
            if (playerHP < 0) playerHP = 0;
        }
        feedbackLabel.setVisible(true);

        enemyHPBar.setProgress((double) enemyHP / maxEnemyHP);
        enemyHPLabel.setText("HP: " + enemyHP + "/" + maxEnemyHP);
        playerHPBar.setProgress((double) playerHP / maxPlayerHP);
        playerHPLabel.setText("HP: " + playerHP + "/" + maxPlayerHP);

        if (enemyHP <= 0) {
            feedbackLabel.setText("Rival defeated! Next opponent is approaching...");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> {
                enemyIndex++;
                if (enemyIndex < enemies.size()) {
                    updateOpponentDisplay();
                    currentRound = 1;
                    currentPhase = BattlePhase.ATTACK;
                    loadNewQuestion();
                } else {
                    questionLabel.setText("Congratulations! You graduated with top honors and defeated all rivals!");
                    alternativesBox.getChildren().clear();
                    difficultyLabel.setText("");
                    feedbackLabel.setVisible(false);
                }
            });
            delay.play();
            return;
        }

        if (playerHP <= 0) {
            feedbackLabel.setText("You flunked the exam. Game Over.");
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> goToStart());
            delay.play();
            return;
        }

        advancePhase();
    }

    private void advancePhase() {
        if (currentPhase == BattlePhase.COUNTERATTACK) {
            currentRound++;
            if (currentRound > MAX_ROUNDS) {
                feedbackLabel.setText("Time's up! You couldn't defeat the rival in 3 rounds. You lose!");
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(e -> goToStart());
                delay.play();
                return;
            }
            currentPhase = BattlePhase.ATTACK;
        } else if (currentPhase == BattlePhase.ATTACK) {
            currentPhase = BattlePhase.DEFENSE;
        } else if (currentPhase == BattlePhase.DEFENSE) {
            currentPhase = BattlePhase.COUNTERATTACK;
        }

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> loadNewQuestion());
        delay.play();
    }

    private void showPause() { pauseScreen.setVisible(true); }
    private void hidePause() { pauseScreen.setVisible(false); }

    private void goToStart() {
        pauseScreen.setVisible(false);
        gameScreen.setVisible(false);
        startScreen.setVisible(true);
    }
}