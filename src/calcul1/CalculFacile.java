package calcul1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class CalculFacile {
    private JFrame frame;
    private JTextField answerField;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private int correctAnswer;
    private int score = 0;
    private int questionsAttempted = 0;
    private boolean solutionShown = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculFacile());
    }

    public CalculFacile() {
        initializeUI();
        generateQuestion();
        frame.setVisible(true);
    }

    private void initializeUI() {
        frame = new JFrame("Calcul Niveau Facile");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Titre
        JLabel titleLabel = new JLabel("CALCUL FACILE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // Score
        scoreLabel = new JLabel("Score: 0/0 (0%)");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(scoreLabel);

        // Question
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 64));
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(questionLabel);

        // Champ Réponse
        answerField = new JTextField(10);
        answerField.setFont(new Font("Arial", Font.PLAIN, 36));
        answerField.setMaximumSize(new Dimension(300, 70));
        answerField.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(answerField);

        // Boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setBackground(Color.WHITE);
        
        JButton checkButton = createButton("VÉRIFIER", new Color(0, 153, 255), 30);
        checkButton.addActionListener(e -> checkAnswer());
        
        JButton solutionButton = createButton("SOLUTION", new Color(255, 102, 0), 30);
        solutionButton.addActionListener(e -> showSolution());
        
        buttonPanel.add(checkButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        buttonPanel.add(solutionButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        mainPanel.add(buttonPanel);
        
        frame.add(mainPanel);
    }

    private JButton createButton(String text, Color bgColor, int size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, size));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(15, 40, 15, 40)
        ));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

    private void generateQuestion() {
        solutionShown = false;
        Random rand = new Random();
        int a = rand.nextInt(10);
        int b = rand.nextInt(10);
        
        if (rand.nextBoolean()) {
            correctAnswer = a + b;
            questionLabel.setText(a + " + " + b + " = ?");
        } else {
            if (a < b) { int temp = a; a = b; b = temp; }
            correctAnswer = a - b;
            questionLabel.setText(a + " - " + b + " = ?");
        }
        answerField.setText("");
        answerField.requestFocus();
    }

    private void checkAnswer() {
        if (solutionShown) return;
        
        try {
            int answer = Integer.parseInt(answerField.getText());
            questionsAttempted++;
            
            if (answer == correctAnswer) {
                score++;
                JOptionPane.showMessageDialog(frame, "Bravo ! Bonne réponse", "Résultat", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect. La bonne réponse était " + correctAnswer, "Résultat", JOptionPane.WARNING_MESSAGE);
            }
            updateScore();
            generateQuestion();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSolution() {
        if (!solutionShown) {
            questionsAttempted++;
            solutionShown = true;
            JOptionPane.showMessageDialog(frame, "Solution : " + correctAnswer, "Solution", JOptionPane.INFORMATION_MESSAGE);
            updateScore();
            generateQuestion();
        }
    }

    private void updateScore() {
        scoreLabel.setText(String.format("Score: %d/%d (%.0f%%)", 
            score, questionsAttempted, 
            questionsAttempted > 0 ? (100.0 * score / questionsAttempted) : 0));
    }
}
