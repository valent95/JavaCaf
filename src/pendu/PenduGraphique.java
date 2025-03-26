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
    private String[] dictionnaire = {"java", "programmation", "ordinateur", "pendu"};;
    private char[] clavierAZERTY = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};

    public PenduGraphique() {
        setTitle("Jeu du Pendu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tirage du mot à deviner 
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
        clavierPanel.setLayout(new GridLayout(0, 10));
        for (int j = 0; j < clavierAZERTY.length; j++) {
            JButton bouton = new JButton(Character.toString(clavierAZERTY[j]));
            bouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        char lettre = e.getActionCommand().toLowerCase().charAt(0);
                        boolean correct = verifierLettre(lettre);
            
                        // Change la couleur du bouton en fonction de la réponse
                        if (correct) {
                            bouton.setBackground(Color.GREEN);  // deviner correctement
                        } else {
                            bouton.setBackground(Color.RED);  // pas deviner correctement
                        }
                        bouton.setEnabled(false);  // desactive le bouton après que l'on s'en soit servi
                }
            });
            clavierPanel.add(bouton);
        }
        add(clavierPanel, BorderLayout.SOUTH);
    }

    private boolean verifierLettre(char lettre) {
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
            int choix = JOptionPane.showOptionDialog(
                this,
                "Félicitations ! Vous avez gagné !",
                " ",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Rejouer", "Accueil"},
                "Rejouer"
            );
        
            if (choix == 0) {
                // Code pour redémarrer le jeu
                redemarrerJeu();
            } else {
                System.exit(0); // Ligne de code à changer, doit renvoyer a l'accueil quand on l'aura construit
            }
        } else if (erreurs >= 6) {
            int choix = JOptionPane.showOptionDialog(
                this,
                "Vous avez perdu ! Le mot était : " + mot,
                " ",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Rejouer", "Accueil"},
                "Rejouer"
            );
        
            if (choix == 0) {
                // Code pour redémarrer le jeu
                redemarrerJeu();
            } else {
                System.exit(0); // Ligne de code à changer, doit renvoyer a l'accueil quand on l'aura construit
            }
        }
        
        return trouve;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PenduGraphique jeu = new PenduGraphique();
            jeu.setVisible(true);
        });
    }

    private void redemarrerJeu() {
        erreurs = 0;
        mot = dictionnaire[new Random().nextInt(dictionnaire.length)];
        motCache = new char[mot.length()];
        for (int i = 0; i < mot.length(); i++) motCache[i] = '_';
        motLabel.setText(String.valueOf(motCache));
        dessinPenduLabel.setText("");

        //remet le clavier par default
        for (Component c : clavierPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton bouton = (JButton) c;
                bouton.setEnabled(true);
                bouton.setBackground(null);
            }
        }
    }
    
}
