package laba;

import java.nio.file.Files;
import java.util.Objects;

public class Application {

    public static void main(String[] args) throws Exception {

        long freeMemory = Runtime.getRuntime().freeMemory();

        MaxConsonantSubstrFinder finder;
        double memoryDelta = 0;
        String fileName = "test.txt";

        if(freeMemory * memoryDelta > Objects.requireNonNull(Application.class.getClassLoader().getResourceAsStream(fileName)).available()){
            finder = new InMemoryMaxConsonantSubstrFinder(fileName);
        } else {
            finder = new SlowMaxConsonantSubstrFinder(fileName);
        }

        finder.printWordsWithMaxConsonantSequence();
    }

}
