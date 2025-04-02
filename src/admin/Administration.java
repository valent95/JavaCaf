import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

public class Administration extends JFrame {
    public Administration() {
        this.setTitle("Administration");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Center panel for login form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.LIGHT_GRAY);
        add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Connexion", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel, BorderLayout.NORTH);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(passwordField, gbc);

        // Submit button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submitButton = new JButton("Se connecter");
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.setBackground(new Color(0, 120, 215));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());

                // Hash the username and password
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/admin/MotDePasse.txt"))) {
                    writer.write(hashedPassword); // Write hashed password on the second line
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                        Administration.this,
                        "Erreur lors de l'enregistrement des informations.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                JOptionPane.showMessageDialog(
                    Administration.this,
                    "Nom d'utilisateur et mot de passe enregistrés avec succès.",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        centerPanel.add(submitButton, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Administration());
    }
}
