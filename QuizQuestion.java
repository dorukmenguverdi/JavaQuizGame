public class QuizQuestion {

    private String questionText;
    private String[] options;
    private int correctIndex;

    public QuizQuestion(String questionText, String[] options, int correctIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() {
        return questionText;
    }

    public  String[] getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctIndex;
    }


}
