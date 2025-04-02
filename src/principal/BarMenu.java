import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarMenu extends JMenuBar {
    private String difficulty = null;
    private String game = null;
    public BarMenu(Font font) {
        JMenuBar barMenu = new JMenuBar();
        barMenu.setFont(font);

        // menu déroulant des difficultés
        JMenu difficultyMenu = new JMenu("Difficulté");

        JRadioButtonMenuItem easyItem = new JRadioButtonMenuItem("Facile");
        JRadioButtonMenuItem hardItem = new JRadioButtonMenuItem("Difficile");

        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyItem);
        difficultyGroup.add(hardItem);

        difficultyMenu.add(easyItem);
        difficultyMenu.add(hardItem);

        barMenu.add(difficultyMenu);

        easyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "easy";
            }
        });

        hardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "hard";
            }
        });

        // Ajouter les éléments au menu
        difficultyMenu.add(easyItem);
        difficultyMenu.addSeparator();
        difficultyMenu.add(hardItem);

        // Ajouter le menu à la barre de menu
        this.add(difficultyMenu);

        // menu déroulant des jeux
        JMenu gameMenu = new JMenu("Jeu");

        JRadioButtonMenuItem penduGame = new JRadioButtonMenuItem("Pendu");
        JRadioButtonMenuItem drawingGame = new JRadioButtonMenuItem("Dessin");
        JRadioButtonMenuItem calculGame = new JRadioButtonMenuItem("Calcul");

        ButtonGroup gameGroup = new ButtonGroup();
        gameGroup.add(penduGame);
        gameGroup.add(drawingGame);
        gameGroup.add(calculGame);

        gameMenu.add(penduGame);
        gameMenu.add(drawingGame);
        gameMenu.add(calculGame);

        barMenu.add(gameMenu);

        penduGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = "pendu";
                SwingUtilities.invokeLater(() -> new PenduGraphique().setVisible(true));
            }
        });

        drawingGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (difficulty == null){
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une difficulté.", "Erreur", JOptionPane.WARNING_MESSAGE);

                }else if (difficulty == "easy"){
                    SwingUtilities.invokeLater(() -> new ArdoiseMagique().setVisible(true));
                } else{
                    SwingUtilities.invokeLater(() -> new ArdoiseMagique().setVisible(true));
                }
                
                game = "drawing";
                
            }
        });

        calculGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (difficulty == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une difficulté.", "Erreur", JOptionPane.WARNING_MESSAGE);
                } else if (difficulty.equals("easy")) {
                    SwingUtilities.invokeLater(() -> new CalculFacile());
                } else {
                    SwingUtilities.invokeLater(() -> new CalculDifficile());
                }
                game = "calcul";
            }
        });

        // Ajouter les éléments au menu
        gameMenu.add(penduGame);
        gameMenu.addSeparator();
        gameMenu.add(drawingGame);
        gameMenu.addSeparator();
        gameMenu.add(calculGame);

        // Ajouter le menu à la barre de menu
        this.add(gameMenu);
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getGame() {
        return game;
    }
}