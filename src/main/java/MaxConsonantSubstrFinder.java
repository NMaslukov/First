import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxConsonantSubstrFinder {
    private static final Set<Character> consonants;
    private Set<String> wordsWithMaxConsonantSubsr = new HashSet<>();

    private int maxConsonants = 0;
    private String fileFromResources;

    static {
        consonants = Stream.of("б, в, г, д, ж, з, й, к, л, м, н, п, р, с, т, ф, х, ц, ч, ш, щ").map(s -> s.split(",")).flatMap(Stream::of)
                .map(s -> s.trim().charAt(0))
                .collect(Collectors.toSet());
    }

    public MaxConsonantSubstrFinder(String fileFromResources){
        this.fileFromResources = fileFromResources;
    }

    public void printWordsWithMaxConsonantSequence() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getClassLoader().getResourceAsStream(fileFromResources)), StandardCharsets.UTF_8))) {

            do {
                ch = br.read();
                if(String.valueOf((char)ch).equals(" ") || ch == -1){
                    maxConsonants = Math.max(findMaxConsonantsSubstr(stringBuilder.toString()), maxConsonants);
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
            while (ch != -1);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getClassLoader().getResourceAsStream(fileFromResources)), StandardCharsets.UTF_8))) {
            do {
                ch = br.read();
                if(String.valueOf((char)ch).equals(" ") || ch == -1){
                    if(findMaxConsonantsSubstr(stringBuilder.toString()) == maxConsonants) wordsWithMaxConsonantSubsr.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
            while (ch != -1);
        }


        wordsWithMaxConsonantSubsr.forEach(System.out::println);
    }

    private static int findMaxConsonantsSubstr(String word) {
        int amount = 0;
        int maxSubstring = 0;
        for(int i = 0;i < word.length(); i++){
            if(consonants.contains(word.charAt(i))) amount++;
            else if (amount != 0){
                maxSubstring = Math.max(amount, maxSubstring);
                amount = 0;
            }
        }

        return Math.max(amount, maxSubstring);
    }
}
