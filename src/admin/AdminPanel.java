import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

// @author valentin

public class AdminPanel extends JPanel {
    private DictionaryManager dictionaryManager;
    private JList<String> wordList;
    private DefaultListModel<String> listModel;
    private JTextField wordField;
    private JButton saveButton;
    private JButton newButton;

    public AdminPanel() {
        dictionaryManager = new DictionaryManager();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        loadWordsIntoList();

        // Liste des mots
        wordList = new JList<>(listModel);
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(wordList);
        listScrollPane.setPreferredSize(new Dimension(200, 300));
        add(listScrollPane, BorderLayout.WEST);

        // Panneau d'édition du mot
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Mot:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        editPanel.add(label, gbc);

        wordField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        editPanel.add(wordField, gbc);

        // Bouton "Enregistrer"
        saveButton = new JButton("Enregistrer");
        saveButton.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        editPanel.add(saveButton, gbc);

        // Bouton "Nouveau"
        newButton = new JButton("Nouveau");
        gbc.gridx = 0;
        gbc.gridy = 1;
        editPanel.add(newButton, gbc);

        add(editPanel, BorderLayout.CENTER);

        // Lorsqu'un mot est sélectionné dans la liste, préremplir le champ
        wordList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedWord = wordList.getSelectedValue();
                if (selectedWord != null) {
                    wordField.setText(selectedWord);
                }
            }
        });

        // Activation/désactivation du bouton "Enregistrer" suivant le contenu du champ
        wordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleSaveButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleSaveButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleSaveButton();
            }

            private void toggleSaveButton() {
                saveButton.setEnabled(!wordField.getText().trim().isEmpty());
            }
        });

        // Action du bouton "Enregistrer"
        saveButton.addActionListener(e -> {
            String word = wordField.getText().trim();
            if (wordList.getSelectedIndex() >= 0) {
                // Si un mot est sélectionné, on le modifie
                int index = wordList.getSelectedIndex();
                dictionaryManager.updateWord(index, word);
                listModel.set(index, word);
            } else {
                // Sinon, on ajoute un nouveau mot
                dictionaryManager.addWord(word);
                listModel.addElement(word);
            }
            dictionaryManager.saveDictionary();
            JOptionPane.showMessageDialog(AdminPanel.this, "Mot enregistré!");
        });

        // Action du bouton "Nouveau" qui libère le champ pour saisir un nouveau mot
        newButton.addActionListener(e -> {
            wordList.clearSelection();
            wordField.setText("");
        });
    }

    private void loadWordsIntoList() {
        listModel.clear();
        for (String word : dictionaryManager.getWords()) {
            listModel.addElement(word);
        }
    }
}
