package calcul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Calculd {
    private JFrame frame;
    private JTextField answerField;
    private JLabel questionLabel;
    private int correctAnswer;

    public Calculd() {
        frame = new JFrame("Calcul Niveau Difficile");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        questionLabel = new JLabel("Question : ");
        frame.add(questionLabel);

        answerField = new JTextField(10);
        frame.add(answerField);

        JButton checkButton = new JButton("Vérifier");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        frame.add(checkButton);

        JButton solutionButton = new JButton("Solution");
        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSolution();
            }
        });
        frame.add(solutionButton);

        JButton newButton = new JButton("Nouveau");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateQuestion();
                answerField.setText("");
            }
        });
        frame.add(newButton);

        frame.setVisible(true);
        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(900) + 100; // Nombres de 100 à 999
        int num2 = random.nextInt(900) + 100; // Nombres de 100 à 999
        boolean isAddition = random.nextBoolean();
        boolean isMultiplication = random.nextBoolean();

        // Calcul : addition, soustraction ou multiplication
        if (isMultiplication) {
            int num3 = random.nextInt(10) + 1; // Multiplication avec un nombre entre 1 et 9
            correctAnswer = num1 * num3;
            questionLabel.setText(num1 + " * " + num3 + " = ?");
        } else if (isAddition) {
            correctAnswer = num1 + num2;
            questionLabel.setText(num1 + " + " + num2 + " = ?");
        } else {
            correctAnswer = num1 - num2;
            questionLabel.setText(num1 + " - " + num2 + " = ?");
        }
    }

    private void checkAnswer() {
        try {
            int userAnswer = Integer.parseInt(answerField.getText());
            if (userAnswer == correctAnswer) {
                JOptionPane.showMessageDialog(frame, "Bonne réponse !");
            } else {
                JOptionPane.showMessageDialog(frame, "Mauvaise réponse. Essayez encore.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre.");
        }
    }

    private void showSolution() {
        JOptionPane.showMessageDialog(frame, "La solution est : " + correctAnswer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculd();
            }
        });
    }
}
