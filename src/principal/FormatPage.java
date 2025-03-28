import javax.swing.*;
import java.awt.*;

public class FormatPage extends JPanel { // Change JFrame à JPanel
    private Image background;
    public FormatPage() {
        setLayout(new BorderLayout());
        //setBackground(Color.WHITE); // Change la couleur de fond en blanc

        //JLabel label = new JLabel("Page d'accueil", SwingConstants.CENTER);
        //label.setFont(new Font("Arial", Font.BOLD, 24));
        //add(label, BorderLayout.CENTER);

        //ajout du fond d'écran
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
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
