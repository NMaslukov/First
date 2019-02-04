package laba;

import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

public class InMemoryMaxConsonantSubstrFinder implements MaxConsonantSubstrFinder {

    public static final String RESOURCES_DIR = "/home/dudoser/IdeaProjects/First/src/main/resources/";
    private String fileName;
    private Pair<Integer, Set<String>> consonantListPair;
    private int maxConsonants = 0;
    private static final Logger log = Logger.getLogger(InMemoryMaxConsonantSubstrFinder.class.getName());

    public InMemoryMaxConsonantSubstrFinder(String fileName) {
        this.fileName = fileName;
        log.info("Using laba.InMemoryMaxConsonantSubstrFinder class");
    }

    @Override
    public void printWordsWithMaxConsonantSequence() throws IOException {
        String[] words = new String(Files.readAllBytes(new File(RESOURCES_DIR + fileName).toPath())).replace("\n", " ").split(" ");
        for (String word : words) {
            process(word);
        }

        consonantListPair.getValue().forEach(System.out::println);
    }

    private void process(String word) {
        int maxConsonantsSubstr = MaxConsonantSubstrFinder.findMaxConsonantsSubstr(word);
        int result = Integer.compare(maxConsonantsSubstr, maxConsonants);

        switch (result) {
            case 1:  {
                maxConsonants = maxConsonantsSubstr;
                Set<String> newSet = new TreeSet<>();
                newSet.add(word);
                consonantListPair = new Pair<>(maxConsonants, newSet);
                break;
            }

            case 0: {
                consonantListPair.getValue().add(word);
                break;
            }

            default: break;
        }
    }
}
