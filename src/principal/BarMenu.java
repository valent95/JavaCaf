import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarMenu extends JMenuBar {
    public BarMenu(Font font) {
        // Exemple de menu "Fichier"
        JMenu fileMenu = new JMenu("Fichier");
        fileMenu.setFont(font);

        // Exemple d'éléments de menu
        JMenuItem openItem = new JMenuItem("Ouvrir");
        openItem.setFont(font);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ouvrir sélectionné !");
            }
        });

        JMenuItem exitItem = new JMenuItem("Quitter");
        exitItem.setFont(font);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ajouter les éléments au menu
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Ajouter le menu à la barre de menu
        this.add(fileMenu);
    }
}