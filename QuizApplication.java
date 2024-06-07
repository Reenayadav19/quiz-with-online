import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static final int QUIZ_DURATION_SECONDS = 90;
    private static final int QUESTION_COUNT = 8;

    private int score;
    private int questionIndex;
    private Timer timer;

    private static final Question[] questions = {
            new Question("What is the capital of France? 1)delhi 2)london 3)kathmandu 4)parish", "Paris"),
            new Question("Which planet is known as the Red Planet? 1)jupiter 2)mars 3)earth 4)saturn", "Mars"),
            new Question("What is the largest animal? 1)giraffe 2)elephant 3)tiger 4)bear", "giraffe"),
            new Question("Who wrote 'Romeo and Juliet'?", "William Shakespeare"),
            new Question("What is the square root of 64?", "8"),
            new Question("Who is the Prime Minister of India?", "Narendra Modi"),
            new Question("Which is the Tallest building in the World?", "Burj Khalifa"),
            new Question("Which is Highest Economy in the world?", "Kuwait Dinar")
    };
           

    public QuizApplication() {
        this.score = 0;
        this.questionIndex = 0;
        this.timer = new Timer();

        startQuiz();
    }

    private void startQuiz() {
        System.out.println("Welcome to the Quiz! You have " + QUIZ_DURATION_SECONDS + " seconds to answer " +
                QUESTION_COUNT + " questions.\n");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endQuiz();
            }
        }, QUIZ_DURATION_SECONDS * 1000);

        askNextQuestion();
    }

    private void askNextQuestion() {
        if (questionIndex < QUESTION_COUNT) {
            Question currentQuestion = questions[questionIndex];

            System.out.println(currentQuestion.getQuestion());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (currentQuestion.isCorrect(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is: " + currentQuestion.getAnswer() + "\n");
            }

            questionIndex++;
            askNextQuestion();
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        timer.cancel();

        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + QUESTION_COUNT);
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}

class Question {
    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer);
    }
}