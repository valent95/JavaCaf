package paint;
import java.awt.*;
import javax.swing.*;


public class ArdoiseMagique extends JFrame {
    private Color couleurActuelle = Color.RED;  // Couleur de départ (rouge)
    private DrawingArea areaDessin;

    public ArdoiseMagique() {
        setTitle("Ardoise Magique");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);

        // Zone de dessin
        areaDessin = new DrawingArea();
        panelPrincipal.add(areaDessin, BorderLayout.CENTER);

        // Barre d'outils
        JPanel toolbar = new JPanel();
        panelPrincipal.add(toolbar, BorderLayout.NORTH);

        // Boutons de couleur
        JButton boutonRouge = new JButton("Rouge");
        boutonRouge.addActionListener(e -> areaDessin.setCouleur(Color.RED));
        toolbar.add(boutonRouge);

        JButton boutonVert = new JButton("Vert");
        boutonVert.addActionListener(e -> areaDessin.setCouleur(Color.GREEN));
        toolbar.add(boutonVert);

        JButton boutonBleu = new JButton("Bleu");
        boutonBleu.addActionListener(e -> areaDessin.setCouleur(Color.BLUE));
        toolbar.add(boutonBleu);

        // Bouton pour ouvrir une palette de couleurs complète
        JButton boutonPalette = new JButton("Palette Complète");
        boutonPalette.addActionListener(e -> {
            Color nouvelleCouleur = JColorChooser.showDialog(this, "Choisir une couleur", couleurActuelle);
            if (nouvelleCouleur != null) {
                areaDessin.setCouleur(nouvelleCouleur);
            }
        });
        toolbar.add(boutonPalette);

        // Bouton pour effacer
        JButton boutonEffacer = new JButton("Effacer");
        boutonEffacer.addActionListener(e -> areaDessin.clear());
        toolbar.add(boutonEffacer);

        // Gomme
        JButton boutonGomme = new JButton("Gomme");
        boutonGomme.addActionListener(e -> areaDessin.activerGomme());
        toolbar.add(boutonGomme);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArdoiseMagique());
    }
}

