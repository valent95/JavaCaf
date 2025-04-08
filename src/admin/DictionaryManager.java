import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// @author valentin

public class DictionaryManager {
    // Chemin du fichier contenant le dictionnaire
    private static final String DICTIONARY_FILE = "src/pendu/dictionary.txt";
    // Liste des mots chargés dans le dictionnaire
    private List<String> words = new ArrayList<>();

    // Constructeur : charge le dictionnaire au démarrage
    public DictionaryManager() {
        loadDictionary();
    }

    // Retourne la liste des mots du dictionnaire
    public List<String> getWords() {
        return words;
    }

    // Charge les mots du fichier dans la liste 'words'
    public void loadDictionary() {
        words.clear(); // Vide la liste avant de la recharger
        try {
            File file = new File(DICTIONARY_FILE);
            if (file.exists()) {
                List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                for (String line : lines) {
                    // Ajoute les lignes non vides à la liste
                    if (!line.trim().isEmpty()) {
                        words.add(line.trim());
                    }
                }
            } else {
                // Crée un nouveau fichier si le fichier n'existe pas
                file.createNewFile();
            }
        } catch (IOException e) {
            // Affiche une erreur si un problème survient lors de la lecture/écriture
            e.printStackTrace();
        }
    }

    // Sauvegarde la liste des mots dans le fichier
    public void saveDictionary() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DICTIONARY_FILE), StandardCharsets.UTF_8)) {
            // Écrit chaque mot dans le fichier, ligne par ligne
            for (String word : words) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            // Affiche une erreur si un problème survient lors de la sauvegarde
            e.printStackTrace();
        }
    }

    // Met à jour un mot à un index donné
    public void updateWord(int index, String newWord) {
        // Vérifie que l'index est valide avant de modifier la liste
        if (index >= 0 && index < words.size()) {
            words.set(index, newWord);
        }
    }

    // Ajoute un nouveau mot à la liste
    public void addWord(String word) {
        words.add(word);
    }
}
