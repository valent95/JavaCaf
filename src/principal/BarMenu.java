import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

/**
 *
 * @author Herbert Caffarel
 */
@SuppressWarnings("serial")
public class BarMenu extends JMenuBar {

    public BarMenu(Font font) {
        initGui();
    }

    private void initGui() {
        // Les entêtes de menu
        JMenu calculation = new JMenu("Calcul");
        calculation.setMnemonic('c');
        JMenu connection = new JMenu("Connexion");
        // On ne peut pas ajouter un ActionListener sur un JMenu
        // Soit on le transforme en JMenuItem, mais le visuel est moins bon
        // lors du clic, soit on utilise un MouseListener pour le click,
        // ce que je fais ici
        connection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String answer = JOptionPane.showInputDialog(
                    calculation,
                    "Entrez votre nom",
                    "Question",
                    JOptionPane.QUESTION_MESSAGE);
                System.out.println("Vous avez répondu : " + answer);
            }

        });
        // Les items du menu calcul
        JMenuItem newCalc = new JMenuItem("Nouveau");
        JMenuItem load = new JMenuItem("Charger");
        // ajout d'un mnélmonique et d'un accélérateur
        // Le mnémonique permet de naviguer dans les menus.
        // L'accélérateur est le raccourci clavier pour directement
        // sélectionner l'item de menu, comme l'habituel CTRL+S pour sauvegarder
        load.setMnemonic('l');
        load.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_L,
            InputEvent.CTRL_DOWN_MASK));
        load.addActionListener(e -> {
            System.out.println("Load");
        });
        JMenuItem save = new JMenuItem("Sauvegarder");
        save.setMnemonic('s');
        save.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_S,
            InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                calculation,
                "Vous avez cliqué sur sauvegarder",
                "Information",
                JOptionPane.ERROR_MESSAGE);
        });
        JMenu properties = new JMenu("Propriétés");
        properties.setMnemonic('p');
        calculation.add(newCalc);
        calculation.add(new JSeparator());
        calculation.add(load);
        calculation.add(save);
        calculation.add(new JSeparator());
        calculation.add(properties);
        // Les items du menu propriétés
        JMenuItem a = new JMenuItem("a");
        JMenuItem b = new JMenuItem("b");
        JMenuItem c = new JMenuItem("c");
        JMenuItem d = new JMenuItem("d");
        properties.add(a);
        properties.add(b);
        properties.add(c);
        properties.add(d);
        // Ajouter les mentêtes à la barre
        this.add(calculation);
        this.add(connection);
    }

}
