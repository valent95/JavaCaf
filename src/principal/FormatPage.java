import javax.swing.*;
import java.awt.*;

// @author valentin

public class FormatPage extends JPanel { // Change JFrame à JPanel
    private Image background;

    public FormatPage() {
        setLayout(new BorderLayout());
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        add(backgroundPanel, BorderLayout.CENTER);
    }
    
    //classe interne pour le fond d'écran
    private class BackgroundPanel extends JPanel {
        
        public BackgroundPanel() {
            try {
                ImageIcon imageUrl = new ImageIcon("src/principal/Gumball.png");
                background = imageUrl.getImage();
            } catch (Exception e) {
                System.err.println("Erreur de chargement de l'image : " + e.getMessage());
            }
        }

        //met l'image à la bonne taille
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                g.drawImage(background, 0, 0, panelWidth, panelHeight, this);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return Toolkit.getDefaultToolkit().getScreenSize(); // Adapter à la taille de l'écran
        }
    }
}
