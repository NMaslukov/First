import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SlowMaxConsonantSubstrFinder implements MaxConsonantSubstrFinder {
    private Set<String> wordsWithMaxConsonantSubsr = new HashSet<>();

    private int maxConsonants = 0;
    private String fileName;

    public SlowMaxConsonantSubstrFinder(String fileName){
        this.fileName = fileName;
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
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
            while (ch != -1);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8))) {
            do {
                ch = br.read();
                if(String.valueOf((char)ch).equals(" ") || ch == -1){
                    if(MaxConsonantSubstrFinder.findMaxConsonantsSubstr(stringBuilder.toString()) == maxConsonants) wordsWithMaxConsonantSubsr.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
            while (ch != -1);
        }


        wordsWithMaxConsonantSubsr.forEach(System.out::println);
    }
}
