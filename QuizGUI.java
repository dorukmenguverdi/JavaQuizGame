import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuizGUI extends JFrame {

    private Quiz quiz;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel scoreLabel;

    public QuizGUI() {
        // Create the quiz questions
        List<QuizQuestion> questions = new ArrayList<>();

        questions.add(new QuizQuestion("What does CPU stand for?",
                new String[]{"Central Performance Unit", "Central Processing Unit", "Computer Power Unit", "Control Processing Unit"},
                1
        ));

        questions.add(new QuizQuestion("Which of these is an operating system?",
                new String[]{"Chrome", "Google", "Windows", " Java"},
                2
        ));

        questions.add(new QuizQuestion("What is the brain of the computer?",
                new String[]{"Hard Drive", "Motherboard", "RAM", "CPU"},
                3
        ));

        questions.add(new QuizQuestion("Which language is used for web development?",
                new String[]{"Python", "HTML", "C++", "Java"},
                1
        ));

        questions.add(new QuizQuestion("Which device is used to input text?",
                new String[]{"Monitor", "Keyboard", "Mouse", "Printer"},
                1
        ));

        quiz = new Quiz(questions);

        // Set up the main frame
        JFrame frame = new JFrame();
        frame.setTitle("Quiz Game");
        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240,248,255));
        frame.setVisible(true);


        // Question Label
        questionLabel = new JLabel();
        questionLabel.setText("Question will appear here");
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        questionLabel.setForeground(new Color(25,25,112));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);


        // Answer option panel
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("SansSerif",Font.PLAIN,20));
            optionButtons[i].setBackground(new Color(70,130,180));
            optionButtons[i].setForeground(Color.WHITE);
            final int index = i;

            // Handle option button click
            optionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleAnswer(index);
                }
            });

            optionsPanel.add(optionButtons[i]);
        }

        // Score label
        scoreLabel = new JLabel();
        scoreLabel.setText("Score: 0");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the frame
        frame.add(questionLabel, BorderLayout.NORTH);
        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.add(scoreLabel, BorderLayout.SOUTH);

        // Load the first question
        loadNextQuestion();
    }

    // Load the next question or finish the quiz
    private void loadNextQuestion() {
        if (quiz.isFinished()) {
            questionLabel.setText("Quiz is over! Your score: " + quiz.getScore() + "/" + quiz.getTotalQuestions());
            for (JButton button : optionButtons) {
                button.setEnabled(false);
            }
        }
        else {
            QuizQuestion question = quiz.getCurrentQuestion();
            questionLabel.setText(question.getQuestion());

            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setEnabled(true);
            }
        }
    }

    // Process the selected answer and update the score
    private void handleAnswer(int selectedIndex) {
        quiz.answerQuestion(selectedIndex);
        scoreLabel.setText("Score: " + quiz.getScore());
        loadNextQuestion();
    }
}
