package laba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class SlowMaxConsonantSubstrFinder implements MaxConsonantSubstrFinder {
    private static final Logger log = Logger.getLogger(SlowMaxConsonantSubstrFinder.class.getName());

    private int maxConsonants = 0;
    private String fileName;

    public SlowMaxConsonantSubstrFinder(String fileName){
        this.fileName = fileName;
        log.info("Using laba.SlowMaxConsonantSubstrFinder class");
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
                    if(MaxConsonantSubstrFinder.findMaxConsonantsSubstr(stringBuilder.toString()) == maxConsonants) {
                        System.out.println(stringBuilder.toString());
                    }
                    stringBuilder = new StringBuilder();
                    continue;
                }

                stringBuilder.append((char)ch);
            }
            while (ch != -1);
        }
    }
}
