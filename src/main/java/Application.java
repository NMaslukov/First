import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//TODO make OOP
public class Application {

    private static Set<Character> consonants;
    private static Set<String> wordsWithMaxConsonSubsr = new HashSet<>();

    private static int maxConsonants = 0;

    static {
        consonants = Stream.of("б, в, г, д, ж, з, й, к, л, м, н, п, р, с, т, ф, х, ц, ч, ш, щ").map(s -> s.split(",")).flatMap(Stream::of)
                .map(s -> s.trim().charAt(0))
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) throws Exception {
        start();
    }

    private static void start() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getClassLoader().getResourceAsStream("input.txt")), StandardCharsets.UTF_8))) {
            while ((ch = br.read()) != -1) {
                if(String.valueOf((char)ch).equals(" ")){
                    maxConsonants = Math.max(findMaxConsonantsSubstr(stringBuilder.toString()), maxConsonants);
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
        }
        maxConsonants = Math.max(findMaxConsonantsSubstr(stringBuilder.toString()), maxConsonants);
        stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getClassLoader().getResourceAsStream("input.txt")), StandardCharsets.UTF_8))) {
            while ((ch = br.read()) != -1) {
                if(String.valueOf((char)ch).equals(" ")){
                    if(findMaxConsonantsSubstr(stringBuilder.toString()) == maxConsonants) wordsWithMaxConsonSubsr.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
        }
        if(findMaxConsonantsSubstr(stringBuilder.toString()) == maxConsonants) wordsWithMaxConsonSubsr.add(stringBuilder.toString());

        for (String s : wordsWithMaxConsonSubsr) {
            System.out.println(s);
        }
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
