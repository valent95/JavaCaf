import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// @author valentin

public class PenduGraphique extends JFrame {
    private JPanel clavierPanel;
    private JLabel motLabel;
    private JLabel dessinPenduLabel;
    private String mot;
    private char[] motCache;
    private int erreurs = 0;
    private ArrayList<String> dictionnaire = new ArrayList<>(); // Remplace le tableau par une liste dynamique
    private char[] clavierAZERTY = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
    private ImageIcon[] etapesPendu;
    private boolean redemarrer = false; // Variable pour indiquer si le jeu doit redémarrer

    

    public PenduGraphique() {
        setTitle("Jeu du Pendu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Charger les images pour les étapes du pendu
        etapesPendu = new ImageIcon[7];
        for (int i = 1; i < 7; i++) {
            etapesPendu[i] = new ImageIcon("src/pendu/pendu" + i + ".png");
        }

        // Charger les mots depuis le fichier dictionary.txt
        chargerDictionnaire();

        // Vérifier que le dictionnaire n'est pas vide avant de tirer un mot
        if (!dictionnaire.isEmpty()) {
            mot = dictionnaire.get(new Random().nextInt(dictionnaire.size())); // Tirage aléatoire
        } else {
            JOptionPane.showMessageDialog(this, "Le fichier dictionary.txt est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Quitte l'application si le dictionnaire est vide
        }

        motCache = new char[mot.length()];
        for (int i = 0; i < mot.length(); i++) motCache[i] = '_';

        // Afficher le mot caché
        motLabel = new JLabel(String.valueOf(motCache), SwingConstants.CENTER);
        motLabel.setFont(new Font("Arial", Font.BOLD, 24));
        motLabel.setText(formatMotAffiche());
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

                    if (redemarrer) {
                        redemarrerJeu(); // Appelle redemarrerJeu après le retour de trouve
                    }
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

        motLabel.setText(formatMotAffiche()); // Utiliser formatMotAffiche pour conserver la mise en forme

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
                redemarrer = true; // Indique que le jeu doit redémarrer
            } else {
                dispose(); // Ferme uniquement la fenêtre actuelle
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
                redemarrer = true; // Indique que le jeu doit redémarrer
            } else {
                dispose(); // Ferme uniquement la fenêtre actuelle
            }
        }

        return trouve;
    }

    private void redemarrerJeu() {
        erreurs = 0;
        mot = dictionnaire.get(new Random().nextInt(dictionnaire.size()));
        motCache = new char[mot.length()];
        for (int i = 0; i < mot.length(); i++) motCache[i] = '_';
        motLabel.setText(String.valueOf(motCache));
        dessinPenduLabel.setIcon(etapesPendu[0]);
        motLabel.setText(formatMotAffiche());
        for (Component c : clavierPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton bouton = (JButton) c;
                bouton.setEnabled(true); // Ensure the button is re-enabled
                bouton.setBackground(null); // Reset the background color
            }
        }
        redemarrer = false; // Réinitialiser la variable après redémarrage
    }
    
    //format d'affichage du mot à deviner _ _ _ _ _ _
    private String formatMotAffiche(){
        StringBuilder sb = new StringBuilder();
        for (char c : motCache){
            sb.append(c).append(" ");
        }
        return sb.toString().trim();
    }
    
    private void chargerDictionnaire() {
        try (Scanner scanner = new Scanner(new File("src/pendu/dictionary.txt"))) {
            while (scanner.hasNextLine()) {
                dictionnaire.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Fichier dictionary.txt introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Quitte l'application si le fichier est introuvable
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PenduGraphique jeu = new PenduGraphique();
            jeu.setVisible(true);
        });
    }
}
