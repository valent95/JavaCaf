//package pendu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PenduGraphique extends JFrame {
    private JPanel clavierPanel;
    private JLabel motLabel;
    private JLabel dessinPenduLabel;
    private String mot;
    private char[] motCache;
    private int erreurs = 0;

    public PenduGraphique() {
        setTitle("Jeu du Pendu");
        setSize(1400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tirage du mot
        String[] dictionnaire = {"java", "programmation", "ordinateur", "pendu"};
        mot = dictionnaire[new Random().nextInt(dictionnaire.length)];
        motCache = new char[mot.length()];
        for (int i = 0; i < mot.length(); i++) motCache[i] = '_';

        // Afficher le mot caché
        motLabel = new JLabel(String.valueOf(motCache), SwingConstants.CENTER);
        motLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(motLabel, BorderLayout.NORTH);

        // Section dessin du pendu
        dessinPenduLabel = new JLabel("", SwingConstants.CENTER);
        dessinPenduLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(dessinPenduLabel, BorderLayout.CENTER);

        // Clavier virtuel
        clavierPanel = new JPanel();
        clavierPanel.setLayout(new GridLayout(4, 7));
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton bouton = new JButton(String.valueOf(c));
            bouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verifierLettre(e.getActionCommand().toLowerCase().charAt(0));
                }
            });
            clavierPanel.add(bouton);
        }
        add(clavierPanel, BorderLayout.SOUTH);
    }

    private void verifierLettre(char lettre) {
        boolean trouve = false;
        for (int i = 0; i < mot.length(); i++) {
            if (mot.charAt(i) == lettre) {
                motCache[i] = lettre;
                trouve = true;
            }
        }

        if (!trouve) {
            erreurs++;
            dessinPenduLabel.setText("Erreurs : " + erreurs);
        }

        motLabel.setText(String.valueOf(motCache));

        if (String.valueOf(motCache).equals(mot)) {
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné !");
        } else if (erreurs >= 6) {
            JOptionPane.showMessageDialog(this, "Vous avez perdu ! Le mot était : " + mot);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PenduGraphique jeu = new PenduGraphique();
            jeu.setVisible(true);
        });
    }
}
