import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdminLoginDialog extends JDialog {
    private boolean authenticated = false;

    public AdminLoginDialog(Frame owner) {
        super(owner, "Administration - Connexion", true);
        initComponents();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Mot de passe administrateur:"));
        JPasswordField passwordField = new JPasswordField(20);
        inputPanel.add(passwordField);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener((ActionEvent e) -> {
            char[] password = passwordField.getPassword();
            if (checkPassword(new String(password))) {
                authenticated = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(AdminLoginDialog.this, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });

        pack();
    }

    private boolean checkPassword(String inputPassword) {
        // Hachage du mot de passe saisi et comparaison avec le hash stocké
        String inputHash = hashPassword(inputPassword);
        String storedHash = ConfigManager.getAdminPasswordHash();
        return inputHash.equals(storedHash);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
