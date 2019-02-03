import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class InMemoryMaxConsonantSubstrFinder implements MaxConsonantSubstrFinder {

    private String fileName;
    private Map<Integer, List<String>> map = new TreeMap<>();
    private int maxConsonants = 0;
    private static final Logger log = Logger.getLogger(InMemoryMaxConsonantSubstrFinder.class.getName());

    public InMemoryMaxConsonantSubstrFinder(String fileName) {
        this.fileName = fileName;
        log.info("Using InMemoryMaxConsonantSubstrFinder class");
    }

    @Override
    public void printWordsWithMaxConsonantSequence() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8))) {

            do {
                ch = br.read();
                if(String.valueOf((char)ch).equals(" ") || ch == -1){
                    maxConsonants = Math.max(MaxConsonantSubstrFinder.findMaxConsonantsSubstr(stringBuilder.toString()), maxConsonants);
                    saveWord(stringBuilder);
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
            while (ch != -1);
        }

        map.get(maxConsonants).forEach(System.out::println);
    }

    private void saveWord(StringBuilder stringBuilder) {
        List<String> listOfWords = map.get(maxConsonants);
        if(listOfWords != null){
            listOfWords.add(stringBuilder.toString());
        } else {
            map = new TreeMap<>();
            List<String> newList = new LinkedList<>();
            newList.add(stringBuilder.toString());
            map.put(maxConsonants, newList);
        }
    }
}
