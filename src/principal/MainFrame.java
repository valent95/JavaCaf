import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
    private final BarMenu barMenu;
    private final FormatPage formatPage;

    public MainFrame() throws HeadlessException {
        this("page principale", 18);
    }

    public MainFrame(int fontSize) {
        this("page principale", fontSize);
    }

    public MainFrame(String title) {
        this(title, 18);
    }

    public MainFrame(String title, int fontSize) throws HeadlessException {
        super(title);
        Font font = new Font("Serif", Font.BOLD, fontSize);
        this.setFont(font);
        this.barMenu = new BarMenu(font);
        this.formatPage = new FormatPage();
        initGui();
    }
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
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
