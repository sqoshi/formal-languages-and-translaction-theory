import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class KMP {
    private String pattern;
    private String text;
    private String alphabet;
    private int patternLength;
    private int textLength;
    private int alphabetLength;
    private int[] pi;
    private ArrayList<Integer> solutions;

    KMP() {
        readData();
        textLength = text.length();
        patternLength = pattern.length();
        alphabetLength = alphabet.length();
        solutions = new ArrayList<>();

    }

    private void readData() {
        try {
            pattern = new Scanner(new FileReader("/home/piotr/Documents/formal-languages-and-translaction-theory/list-01/exercise1/src/data/pattern")).next();
            text = new Scanner(new FileReader("/home/piotr/Documents/formal-languages-and-translaction-theory/list-01/exercise1/src/data/text")).next();
            alphabet = new Scanner(new FileReader("/home/piotr/Documents/formal-languages-and-translaction-theory/list-01/exercise1/src/data/alphabet")).next();
            System.out.println("Files read correctly");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Pattern: " + pattern);
        System.out.println(pattern.length());
        System.out.println("Text: " + text);
        System.out.println("Alphabet: " + alphabet);
    }

    private void computePrefix(String pattern) {
        pi[1] = 0;
        int k = 0;
    }
}
