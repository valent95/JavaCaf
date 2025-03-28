//package paint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
// Removed incorrect import

/**
 *
 * @author joris
 */


public class ArdoiseMagique extends JFrame {
    private Color couleurActuelle = Color.RED;  // Couleur de départ (rouge)
    @SuppressWarnings("unused")
    private Point debut = null;
    
    public ArdoiseMagique() {
        setTitle("Ardoise Magique");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);
        
        // Zone de dessin
        DrawingArea areaDessin = new DrawingArea(this);
        panelPrincipal.add(areaDessin, BorderLayout.CENTER);
        
        // Barre d'outils
        JPanel toolbar = new JPanel();
        panelPrincipal.add(toolbar, BorderLayout.NORTH);
        
        // Boutons de couleur (niveau facile)
        JButton boutonRouge = new JButton("Rouge");
        boutonRouge.addActionListener(e ->{ 
            couleurActuelle = Color.RED;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonRouge);
        
        JButton boutonVert = new JButton("Vert");
        boutonVert.addActionListener(e ->{ 
            couleurActuelle = Color.GREEN;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonVert);
        
        JButton boutonBleu = new JButton("Bleu");
        boutonBleu.addActionListener(e ->{ 
            couleurActuelle = Color.BLUE;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonBleu);
        
        // Ajouter un bouton pour ouvrir une palette de couleurs complète (niveau difficile)
        JButton boutonPalette = new JButton("Palette Complète");
        boutonPalette.addActionListener(e -> {
            // Ouvre un JColorChooser pour permettre à l'utilisateur de choisir n'importe quelle couleur
            couleurActuelle = JColorChooser.showDialog(this, "Choisir une couleur", couleurActuelle);
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonPalette);
        
        // Bouton pour effacer
        JButton boutonEffacer = new JButton("Effacer");
        boutonEffacer.addActionListener(e ->{ 
            areaDessin.clear();
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }});
        toolbar.add(boutonEffacer);
        
        // Gomme
        JButton boutonGomme = new JButton("Gomme");
        boutonGomme.addActionListener(e -> areaDessin.activerGomme());
        toolbar.add(boutonGomme);
        
        setVisible(true);
    }
      
    public Color getCouleurActuelle() {
        return couleurActuelle;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArdoiseMagique());
    }
}
