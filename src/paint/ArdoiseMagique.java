//package paint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author joris
 */


@SuppressWarnings("unused")
public class ArdoiseMagique extends JFrame {
    private Color couleurActuelle = Color.RED;  // Couleur de départ (rouge)
    @SuppressWarnings("FieldMayBeFinal")
    private Point debut = null;
    private float format = 2.0f;

    public ArdoiseMagique() {
        setTitle("Ardoise Magique");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        boutonRouge.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonRouge.addActionListener(e ->{ 
            couleurActuelle = Color.RED;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonRouge);
        
        JButton boutonVert = new JButton("Vert");
        boutonVert.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonVert.addActionListener(e ->{ 
            couleurActuelle = Color.GREEN;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonVert);
        
        JButton boutonBleu = new JButton("Bleu");
        boutonBleu.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonBleu.addActionListener(e ->{ 
            couleurActuelle = Color.BLUE;
        });
        toolbar.add(boutonBleu);
        
        JButton boutonFormat = new JButton("Taille");
        boutonFormat.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        toolbar.add(boutonFormat);
        
        JPopupMenu menu = new JPopupMenu();
        float[] epaisseurs = {2.0f, 4.0f, 6.0f, 8.0f, 10.0f};
        
        for (float epaisseur : epaisseurs) {
            JMenuItem choix = new JMenuItem("Crayon" + (int)(epaisseur/2));
            choix.addActionListener(e ->{ 
                format = epaisseur;
            });
            menu.add(choix);
        }

        boutonFormat.addActionListener(e -> menu.show(boutonFormat, 0, boutonFormat.getHeight()));
        toolbar.add(boutonFormat);


        // Ajouter un bouton pour ouvrir une palette de couleurs complète (niveau difficile)
        JButton boutonPalette = new JButton("Palette Complète");
        boutonPalette.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
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
        boutonEffacer.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonEffacer.addActionListener(e ->{ 
            areaDessin.clear();
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }});
        toolbar.add(boutonEffacer);
        
        // Gomme
        JButton boutonGomme = new JButton("Gomme");
        boutonGomme.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonGomme.addActionListener(e -> areaDessin.activerGomme());
        toolbar.add(boutonGomme);
        
        setVisible(true);
    }
     
    public float getFormat() {
        return format;
    }
    
    public Color getCouleurActuelle() {
        return couleurActuelle;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArdoiseMagique());
    }

}
