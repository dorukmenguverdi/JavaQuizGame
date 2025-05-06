import java.util.List;

public class Quiz {

    private List<QuizQuestion> questions;
    private int currentIndex;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentIndex = 0;
        this.score = 0;
    }

    public QuizQuestion getCurrentQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex);
        }
        return null;
    }

    public void answerQuestion(int selectedIndex) {
        if (getCurrentQuestion().isCorrect(selectedIndex)){
            score++;
        }
        currentIndex++;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean isFinished() {
        return currentIndex >= questions.size();
    }

}
