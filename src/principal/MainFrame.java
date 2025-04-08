import java.awt.*;
import javax.swing.*;

// @author valentin

// Classe principale représentant la fenêtre de l'application

public class MainFrame extends JFrame{
    private final BarMenu barMenu;
    private final FormatPage formatPage;

    // Constructeur par défaut : initialise la fenêtre avec un titre et une taille de police par défaut
    public MainFrame() throws HeadlessException {
        this("page principale", 18);
    }

    // Constructeur permettant de spécifier une taille de police personnalisée
    public MainFrame(int fontSize) {
        this("page principale", fontSize);
    }

    // Constructeur permettant de spécifier un titre personnalisé
    public MainFrame(String title) {
        this(title, 18);
    }

    // Constructeur principal : initialise la fenêtre avec un titre et une taille de police personnalisés
    public MainFrame(String title, int fontSize) throws HeadlessException {
        super(title);
        Font font = new Font("Serif", Font.BOLD, fontSize);
        this.setFont(font);
        this.barMenu = new BarMenu(font);
        this.formatPage = new FormatPage();
        initGui();
    }

    // Méthode privée pour configurer l'interface graphique
    private void initGui() {
        // Ajouter la barre de menu
        this.setJMenuBar(barMenu);
        // Créer un conteneur global avec un BorderLayout
        JPanel root = new JPanel();
        BorderLayout bl = new BorderLayout(5, 5);
        root.setLayout(bl);

        // Ajouter les conteneurs à mon conteneur de base
        root.add(formatPage, BorderLayout.NORTH);

        // Ajouter root à la fenêtre
        this.add(root);

        // Configurer le comportement de la fenêtre
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
