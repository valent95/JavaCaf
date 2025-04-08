import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// @author valentin

public class BarMenu extends JMenuBar {
    private String difficulty = null; // Stocke la difficulté sélectionnée
    private String game = null; // Stocke le jeu sélectionné

    public BarMenu(Font font) {
        // Création de la barre de menu
        JMenuBar barMenu = new JMenuBar();
        barMenu.setFont(font);

        // Menu déroulant pour sélectionner la difficulté
        JMenu difficultyMenu = new JMenu("Difficulté");

        // Options de difficulté
        JRadioButtonMenuItem easyItem = new JRadioButtonMenuItem("Facile");
        JRadioButtonMenuItem hardItem = new JRadioButtonMenuItem("Difficile");

        // Groupe de boutons pour s'assurer qu'une seule difficulté est sélectionnée
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyItem);
        difficultyGroup.add(hardItem);

        // Ajout des options au menu de difficulté
        difficultyMenu.add(easyItem);
        difficultyMenu.add(hardItem);

        // Ajout du menu de difficulté à la barre de menu
        barMenu.add(difficultyMenu);

        // Action pour le bouton "Facile"
        easyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "easy"; // Définit la difficulté sur "facile"
                if (game == "pendu") {
                    SwingUtilities.invokeLater(() -> new PenduGraphique().setVisible(true));
                } else if (game == "drawing") {
                    SwingUtilities.invokeLater(() -> new ArdoiseMagiqueFacile().setVisible(true));
                } else if (game == "calcul") {
                    SwingUtilities.invokeLater(() -> new CalculFacile());
                }
            }
        });

        // Action pour le bouton "Difficile"
        hardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "hard"; // Définit la difficulté sur "difficile"
                if (game == "pendu") {
                    SwingUtilities.invokeLater(() -> new PenduGraphique().setVisible(true));
                } else if (game == "drawing") {
                    SwingUtilities.invokeLater(() -> new ArdoiseMagique().setVisible(true));
                } else if (game == "calcul") {
                    SwingUtilities.invokeLater(() -> new CalculDifficile());
                }
            }
        });

        // Ajout d'un séparateur et des options au menu de difficulté
        difficultyMenu.add(easyItem);
        difficultyMenu.addSeparator();
        difficultyMenu.add(hardItem);

        // Ajout du menu de difficulté à la barre de menu
        this.add(difficultyMenu);

        // Menu déroulant pour sélectionner le jeu
        JMenu gameMenu = new JMenu("Jeu");

        // Options de jeu
        JRadioButtonMenuItem penduGame = new JRadioButtonMenuItem("Pendu");
        JRadioButtonMenuItem drawingGame = new JRadioButtonMenuItem("Dessin");
        JRadioButtonMenuItem calculGame = new JRadioButtonMenuItem("Calcul");

        // Groupe de boutons pour s'assurer qu'un seul jeu est sélectionné
        ButtonGroup gameGroup = new ButtonGroup();
        gameGroup.add(penduGame);
        gameGroup.add(drawingGame);
        gameGroup.add(calculGame);

        // Ajout des options au menu de jeu
        gameMenu.add(penduGame);
        gameMenu.add(drawingGame);
        gameMenu.add(calculGame);

        // Ajout du menu de jeu à la barre de menu
        barMenu.add(gameMenu);

        // Action pour le jeu "Pendu"
        penduGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = "pendu"; // Définit le jeu sur "pendu"
                SwingUtilities.invokeLater(() -> new PenduGraphique().setVisible(true));
            }
        });

        // Action pour le jeu "Dessin"
        drawingGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (difficulty == null) {
                    // Affiche un message d'erreur si aucune difficulté n'est sélectionnée
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une difficulté.", "Erreur", JOptionPane.WARNING_MESSAGE);
                } else if (difficulty == "easy") {
                    SwingUtilities.invokeLater(() -> new ArdoiseMagiqueFacile().setVisible(true));
                } else {
                    SwingUtilities.invokeLater(() -> new ArdoiseMagique().setVisible(true));
                }
                game = "drawing"; // Définit le jeu sur "dessin"
            }
        });

        // Action pour le jeu "Calcul"
        calculGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (difficulty == null) {
                    // Affiche un message d'erreur si aucune difficulté n'est sélectionnée
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une difficulté.", "Erreur", JOptionPane.WARNING_MESSAGE);
                } else if (difficulty.equals("easy")) {
                    SwingUtilities.invokeLater(() -> new CalculFacile());
                } else {
                    SwingUtilities.invokeLater(() -> new CalculDifficile());
                }
                game = "calcul"; // Définit le jeu sur "calcul"
            }
        });

        // Ajout d'un séparateur et des options au menu de jeu
        gameMenu.add(penduGame);
        gameMenu.addSeparator();
        gameMenu.add(drawingGame);
        gameMenu.addSeparator();
        gameMenu.add(calculGame);

        // Ajout du menu de jeu à la barre de menu
        this.add(gameMenu);

        // Menu pour l'administration
        JMenu adminMenu = new JMenu("Administration");
        JMenuItem adminItem = new JMenuItem("Accéder");
        adminMenu.add(adminItem);
        this.add(adminMenu);

        // Action pour accéder à l'administration
        adminItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupère la fenêtre parent de la barre de menu
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(BarMenu.this);
                AdminLoginDialog loginDialog = new AdminLoginDialog(parentFrame);
                loginDialog.setVisible(true);
                if (loginDialog.isAuthenticated()) {
                    // Ouvre une nouvelle fenêtre pour l'administration
                    JFrame adminFrame = new JFrame("Administration");
                    adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    adminFrame.getContentPane().add(new AdminPanel());
                    adminFrame.pack();
                    adminFrame.setLocationRelativeTo(parentFrame);
                    adminFrame.setVisible(true);
                }
            }
        });
    }

    // Retourne la difficulté sélectionnée
    public String getDifficulty() {
        return difficulty;
    }

    // Retourne le jeu sélectionné
    public String getGame() {
        return game;
    }
}