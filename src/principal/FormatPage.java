import javax.swing.*;
import java.awt.*;

public class FormatPage extends JPanel { // Change JFrame à JPanel
    public FormatPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Exemple de couleur de fond

        JLabel label = new JLabel("Page d'accueil", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}