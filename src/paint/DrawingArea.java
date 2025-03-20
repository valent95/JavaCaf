package paint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class DrawingArea extends JPanel {
    private Color couleurActuelle = Color.RED;
    private boolean gommeActive = false;
    private Point debut = null;
    private BufferedImage image;
    private Graphics2D g2d;

    public DrawingArea() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 300));

        // Initialiser l'image de dessin
        image = new BufferedImage(600, 300, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 600, 300); // Remplir l'image avec du blanc
        g2d.setColor(couleurActuelle);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                debut = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (debut != null) {
                    if (gommeActive) {
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(e.getX() - 5, e.getY() - 5, 10, 10);
                    } else {
                        g2d.setColor(couleurActuelle);
                        g2d.drawLine(debut.x, debut.y, e.getX(), e.getY());
                    }
                    debut = e.getPoint();
                    repaint(); // Redessiner la zone
                }
            }
        });
    }

    public void setCouleur(Color couleur) {
        this.couleurActuelle = couleur;
        this.gommeActive = false;
    }

    public void activerGomme() {
        gommeActive = !gommeActive;
    }

    public void clear() {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Effacer tout
        g2d.setColor(couleurActuelle);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

