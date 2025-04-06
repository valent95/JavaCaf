import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManager {
    private static final String DICTIONARY_FILE = "src/pendu/dictionary.txt";
    private List<String> words = new ArrayList<>();

    public DictionaryManager() {
        loadDictionary();
    }

    public List<String> getWords() {
        return words;
    }

    public void loadDictionary() {
        words.clear();
        try {
            File file = new File(DICTIONARY_FILE);
            if (file.exists()) {
                List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        words.add(line.trim());
                    }
                }
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDictionary() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DICTIONARY_FILE), StandardCharsets.UTF_8)) {
            for (String word : words) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateWord(int index, String newWord) {
        if (index >= 0 && index < words.size()) {
            words.set(index, newWord);
        }
    }

    public void addWord(String word) {
        words.add(word);
    }
}
