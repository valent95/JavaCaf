package calcul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Calculf {
    private JFrame frame;
    private JTextField answerField;
    private JLabel questionLabel;
    private int correctAnswer;

    public Calculf() {
        frame = new JFrame("Calcul Niveau Facile");
        frame.setSize(300, 200);
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
        int num1 = random.nextInt(10); // Nombres de 0 à 9
        int num2 = random.nextInt(10); // Nombres de 0 à 9
        boolean isAddition = random.nextBoolean();

        // Calcul : addition ou soustraction
        if (isAddition) {
            correctAnswer = num1 + num2;
            questionLabel.setText(num1 + " + " + num2 + " = ?");
        } else {
            // Assurer que le résultat soit strictement positif
            // Si num1 < num2, on inverse pour garantir un résultat positif
            if (num1 < num2) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
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
                new Calculf();
            }
        });
    }
}
