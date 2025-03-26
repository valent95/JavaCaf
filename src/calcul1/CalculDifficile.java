//package calcul1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CalculDifficile {
    private JFrame frame;
    private JTextField answerField;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private int correctAnswer;
    private int score = 0;
    private int questionsAttempted = 0;
    private boolean solutionShown = false;

    // Palette de couleurs plus "sérieuse"
    private final Color MAIN_BG = new Color(240, 240, 250);  // Bleu très très clair
    private final Color TITLE_COLOR = new Color(150, 50, 50);  // Rouge bordeaux
    private final Color SCORE_COLOR = new Color(170, 70, 70);
    private final Color BUTTON_BG1 = new Color(70, 130, 180);  // Bleu acier
    private final Color BUTTON_BG2 = new Color(200, 80, 80);  // Rouge doux
    private final Color TEXT_COLOR = new Color(60, 60, 60);  // Gris très foncé

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculDifficile());
    }

    public CalculDifficile() {
        configureFrame();
        initUI();
        generateQuestion();
        frame.setVisible(true);
    }

    private void configureFrame() {
        frame = new JFrame("Calcul Niveau Difficile - Version Expert");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(MAIN_BG);
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        mainPanel.setBackground(MAIN_BG);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Titre avec effet métal
        JLabel titleLabel = new JLabel("CALCUL DIFFICILE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 52));
        titleLabel.setForeground(TITLE_COLOR);
        titleLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Score avec style "badge"
        scoreLabel = new JLabel("Score: 0/0 (100%)", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        scoreLabel.setForeground(SCORE_COLOR);
        scoreLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SCORE_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        gbc.gridy = 1;
        mainPanel.add(scoreLabel, gbc);

        // Question avec ombre portée
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 48));
        questionLabel.setForeground(TEXT_COLOR);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(255, 255, 255, 220));
        gbc.gridy = 2;
        mainPanel.add(questionLabel, gbc);

        // Champ réponse avec style moderne
        answerField = new JTextField(12);
        answerField.setFont(new Font("Arial", Font.PLAIN, 40));
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, TITLE_COLOR),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        gbc.gridy = 3;
        mainPanel.add(answerField, gbc);

        // Boutons avec effet 3D
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        buttonPanel.setOpaque(false);
        
        buttonPanel.add(create3DButton("VÉRIFIER", BUTTON_BG1, e -> checkAnswer()));
        buttonPanel.add(create3DButton("SOLUTION", BUTTON_BG2, e -> showSolution()));

        gbc.gridy = 4;
        mainPanel.add(buttonPanel, gbc);

        frame.add(mainPanel);
    }

    private JButton create3DButton(String text, Color baseColor, ActionListener action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                
                // Effet 3D
                g2.setColor(baseColor.darker());
                g2.fillRoundRect(2, 2, getWidth(), getHeight(), 25, 25);
                g2.setColor(baseColor);
                g2.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 25, 25);
                
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 28));
        button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        button.addActionListener(action);
        
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                button.setBorder(BorderFactory.createEmptyBorder(22, 50, 18, 50));
            }
            public void mouseReleased(MouseEvent e) {
                button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            }
        });
        
        return button;
    }

    // ... (Les méthodes generateQuestion, checkAnswer, showSolution et updateScore restent identiques)
}