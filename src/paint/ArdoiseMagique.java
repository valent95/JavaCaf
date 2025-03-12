package paint;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import javax.swing.*;

public class ArdoiseMagique extends JFrame {
    private Color couleurActuelle = Color.RED;  // Couleur de départ (rouge)
    private Point debut = null;
    private Stack<Image> historiqueImages = new Stack<>();
    
    public ArdoiseMagique() {
        setTitle("Ardoise Magique");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);
        
        // Zone de dessin
        DrawingArea areaDessin = new DrawingArea();
        panelPrincipal.add(areaDessin, BorderLayout.CENTER);
        
        // Barre d'outils
        JPanel toolbar = new JPanel();
        panelPrincipal.add(toolbar, BorderLayout.NORTH);
        
        // Boutons de couleur (niveau facile)
        JButton boutonRouge = new JButton("Rouge");
        boutonRouge.addActionListener(e -> couleurActuelle = Color.RED);
        toolbar.add(boutonRouge);
        
        JButton boutonVert = new JButton("Vert");
        boutonVert.addActionListener(e -> couleurActuelle = Color.GREEN);
        toolbar.add(boutonVert);
        
        JButton boutonBleu = new JButton("Bleu");
        boutonBleu.addActionListener(e -> couleurActuelle = Color.BLUE);
        toolbar.add(boutonBleu);
        
        // Ajouter un bouton pour ouvrir une palette de couleurs complète (niveau difficile)
        JButton boutonPalette = new JButton("Palette Complète");
        boutonPalette.addActionListener(e -> {
            // Ouvre un JColorChooser pour permettre à l'utilisateur de choisir n'importe quelle couleur
            couleurActuelle = JColorChooser.showDialog(this, "Choisir une couleur", couleurActuelle);
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
    
    private class DrawingArea extends JPanel {
        private boolean gommeActive = false;
        
        public DrawingArea() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(600, 300));
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    debut = e.getPoint();
                }
            });
            addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (debut != null) {
                        Graphics g = getGraphics();
                        if (gommeActive) {
                            g.setColor(Color.WHITE);  // Gomme = fond blanc
                            g.fillRect(e.getX() - 5, e.getY() - 5, 10, 10);
                        } else {
                            g.setColor(couleurActuelle);
                            g.drawLine(debut.x, debut.y, e.getX(), e.getY());
                        }
                        debut = e.getPoint();
                    }
                }
            });
        }
        
        public void clear() {
            historiqueImages.push(createImage(getWidth(), getHeight()));  // Sauvegarde l'image avant de nettoyer
            repaint();
        }
        
        public void activerGomme() {
            gommeActive = !gommeActive;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Repeindre l'historique (si besoin de restaurer l'image après effacement)
            if (!historiqueImages.isEmpty()) {
                g.drawImage(historiqueImages.peek(), 0, 0, this);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArdoiseMagique());
    }
}
