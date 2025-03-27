//package principal;

import javax.swing.*;
import java.awt.*;

public class FormatPage extends JFrame {
    public FormatPage(){

        setTitle("Page d'acceuil");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormatPage acceuil = new FormatPage();
            acceuil.setVisible(true);
        });
    }
}