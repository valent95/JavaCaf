import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

// @author joris

@SuppressWarnings("unused")
public class ArdoiseMagique extends JFrame {
    private Color couleurActuelle = Color.RED;  // Couleur de départ (rouge)
    private Point debut = null;
    private float format = 2.0f;

    public ArdoiseMagique() {
        setTitle("Ardoise Magique");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        boutonRouge.setBackground(Color.RED);
        boutonRouge.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonRouge.addActionListener(e ->{ 
            couleurActuelle = Color.RED;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonRouge);
        
        JButton boutonVert = new JButton("Vert");
        boutonVert.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonVert.setBackground(Color.GREEN);
        boutonVert.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonVert.addActionListener(e ->{ 
            couleurActuelle = Color.GREEN;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonVert);
        
        JButton boutonBleu = new JButton("Bleu");
        boutonBleu.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonBleu.setBackground(Color.BLUE);
        boutonBleu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonBleu.addActionListener(e ->{ 
            couleurActuelle = Color.BLUE;
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }
        });
        toolbar.add(boutonBleu);
        
        JSlider sliderEpaisseur = new JSlider(JSlider.HORIZONTAL, 1, 21, 1);
        sliderEpaisseur.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sliderEpaisseur.setMajorTickSpacing(5);
        sliderEpaisseur.setPaintTicks(true);
        sliderEpaisseur.setPaintLabels(true);

        sliderEpaisseur.addChangeListener(e -> {
                format = sliderEpaisseur.getValue();
            });

toolbar.add(sliderEpaisseur);



    
        JButton boutonPalette = new JButton("Palette Complète");
        boutonPalette.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonPalette.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        boutonEffacer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonEffacer.addActionListener(e ->{ 
            areaDessin.clear();
            if (areaDessin.getGommeActive()){
                areaDessin.setGommeActive(false);
            }});
        toolbar.add(boutonEffacer);
        
        // Gomme
        JButton boutonGomme = new JButton("Gomme");
        boutonGomme.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boutonGomme.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
