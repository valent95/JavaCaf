//package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
import javax.swing.JPanel;

public class DrawingArea extends JPanel {
    @SuppressWarnings("unused")
    private  ArdoiseMagique ardoise;  // Référence à la fenêtre principale
    @SuppressWarnings("unused")
    private  ArdoiseMagiqueFacile ardoiseFacile;
    private Point debut = null;
    private boolean gommeActive = false;
    private Stack<Image> historiqueImages = new Stack<>();
    
    public DrawingArea(ArdoiseMagique ardoise) {
        this.ardoise = ardoise;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 300));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                debut = e.getPoint();
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @SuppressWarnings("override")
            public void mouseDragged(MouseEvent e) {
                if (debut != null) {
                    Graphics g = getGraphics();
                    if (gommeActive) {
                        g.setColor(Color.WHITE);  // Gomme = fond blanc
                        Graphics2D g2 = (Graphics2D) g; 
                        g2.setStroke(new BasicStroke(ardoise.getFormat()));
                        g2.drawLine(debut.x, debut.y, e.getX(), e.getY());
                    } else {
                        // Utilisez la couleur de l'ardoise
                        g.setColor(ardoise.getCouleurActuelle());
                        Graphics2D g2 = (Graphics2D) g; 
                        g2.setStroke(new BasicStroke(ardoise.getFormat()));
                        g2.drawLine(debut.x, debut.y, e.getX(), e.getY());
                    }
                    debut = e.getPoint();
                }
            }
        });
    }
   

    // Constructeur pour le niveau facile
    public DrawingArea(ArdoiseMagiqueFacile ardoiseFacile) {
        this.ardoiseFacile = ardoiseFacile;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 300));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                debut = e.getPoint();
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (debut != null) {
                    Graphics g = getGraphics();
                    if (gommeActive) {
                        g.setColor(Color.WHITE);  // Gomme = fond blanc
                        Graphics2D g2 = (Graphics2D) g; 
                        g2.setStroke(new BasicStroke(ardoiseFacile.getFormat()));
                        g2.drawLine(debut.x, debut.y, e.getX(), e.getY());
                    } else {
                        // Utilisez la couleur de l'ardoise
                        g.setColor(ardoiseFacile.getCouleurActuelle());
                        Graphics2D g2 = (Graphics2D) g; 
                        g2.setStroke(new BasicStroke(ardoiseFacile.getFormat()));
                        g2.drawLine(debut.x, debut.y, e.getX(), e.getY());
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
    public boolean getGommeActive(){
        return gommeActive;
    }
    
   public void setGommeActive(boolean etat) {
    this.gommeActive = etat;
}
}

