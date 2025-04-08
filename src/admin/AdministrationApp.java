import javax.swing.*;

// @author valentin

public class AdministrationApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Administration");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Affichage de la fenêtre de connexion administrateur
            AdminLoginDialog loginDialog = new AdminLoginDialog(frame);
            loginDialog.setVisible(true);

            if (loginDialog.isAuthenticated()) {
                AdminPanel adminPanel = new AdminPanel();
                frame.getContentPane().add(adminPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } else {
                // Si la connexion est annulée ou échoue, on quitte l'application
                System.exit(0);
            }
        });
    }
}
