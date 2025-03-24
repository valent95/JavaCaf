package paint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
import javax.swing.JPanel;

public class DrawingArea extends JPanel {
    private final ArdoiseMagique ardoise;  // Référence à la fenêtre principale
    private Point debut = null;
    private boolean gommeActive = false;
    private Stack<Image> historiqueImages = new Stack<>();
    
    public DrawingArea(ArdoiseMagique ardoise) {
        this.ardoise = ardoise;
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
                        // Utilisez la couleur de l'ardoise
                        g.setColor(ardoise.getCouleurActuelle());
                        g.drawLine(debut.x, debut.y, e.getX(), e.getY());
                    }
                    debut = e.getPoint();
                }
            }
        });
    }
    public void clear() {
        historiqueImages.clear();
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

