import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MaxConsonantSubstrFinder {

    Set<Character> consonants = Stream.of("б, в, г, д, ж, з, й, к, л, м, н, п, р, с, т, ф, х, ц, ч, ш, щ").map(s -> s.split(",")).flatMap(Stream::of)
            .map(s -> s.trim().charAt(0))
            .collect(Collectors.toSet());

    void printWordsWithMaxConsonantSequence() throws IOException;

    static int findMaxConsonantsSubstr(String word) {
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
