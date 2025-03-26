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
    private String[] dictionnaire = {"java", "programmation", "ordinateur", "pendu"};
    private char[] clavierAZERTY = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
    private ImageIcon[] etapesPendu;

    public PenduGraphique() {
        setTitle("Jeu du Pendu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Charger les images pour les étapes du pendu
        etapesPendu = new ImageIcon[7];
        for (int i = 1; i < 7; i++) {
            etapesPendu[i] = new ImageIcon("src/pendu/pendu" + i + ".png");
        }

        // Tirage du mot à deviner
        mot = dictionnaire[new Random().nextInt(dictionnaire.length)];
        motCache = new char[mot.length()];
        for (int i = 0; i < mot.length(); i++) motCache[i] = '_';

        // Afficher le mot caché
        motLabel = new JLabel(String.valueOf(motCache), SwingConstants.CENTER);
        motLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(motLabel, BorderLayout.NORTH);

        // Section dessin du pendu
        dessinPenduLabel = new JLabel(etapesPendu[0], SwingConstants.CENTER);
        add(dessinPenduLabel, BorderLayout.CENTER);

        // Clavier virtuel
        clavierPanel = new JPanel();
        clavierPanel.setLayout(new GridLayout(3, 10));
        for (char c : clavierAZERTY) {
            JButton bouton = new JButton(Character.toString(c));
            bouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char lettre = e.getActionCommand().toLowerCase().charAt(0);
                    boolean correct = verifierLettre(lettre);

                    // Change la couleur du bouton en fonction de la réponse
                    if (correct) {
                        bouton.setBackground(Color.GREEN);
                    } else {
                        bouton.setBackground(Color.RED);
                    }
                    bouton.setEnabled(false);
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
            if (erreurs < etapesPendu.length) {
                dessinPenduLabel.setIcon(etapesPendu[erreurs]);
            }
        }

        motLabel.setText(String.valueOf(motCache));

        if (String.valueOf(motCache).equals(mot)) {
            int choix = JOptionPane.showOptionDialog(
                this,
                "Félicitations ! Vous avez gagné !",
                " ",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Rejouer", "Accueil"},
                "Rejouer"
            );

            if (choix == 0) {
                redemarrerJeu();
            } else {
                System.exit(0);
            }
        } else if (erreurs > 6) {
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
                redemarrerJeu();
            } else {
                System.exit(0);
            }
        }

        return trouve;
    }

    private void redemarrerJeu() {
        erreurs = 0;
        mot = dictionnaire[new Random().nextInt(dictionnaire.length)];
        motCache = new char[mot.length()];
        for (int i = 0; i < mot.length(); i++) motCache[i] = '_';
        motLabel.setText(String.valueOf(motCache));
        dessinPenduLabel.setIcon(etapesPendu[0]);
    
        for (Component c : clavierPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton bouton = (JButton) c;
                bouton.setEnabled(true); // Ensure the button is re-enabled
                bouton.setBackground(null); // Reset the background color
            }
        }
    }
    



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PenduGraphique jeu = new PenduGraphique();
            jeu.setVisible(true);
        });
    }
}
