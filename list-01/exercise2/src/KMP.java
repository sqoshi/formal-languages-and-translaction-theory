import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class KMP {
    private String pattern;
    private String text;
    private String alphabet;
    private int patternLength;
    private int textLength;
    private int[] pi;
    private ArrayList<Integer> solutions;

    KMP() {
        readData();
        textLength = text.length();
        patternLength = pattern.length();
        solutions = new ArrayList<>();
        System.out.println(Arrays.toString(computePrefix(pattern)));
        matcher(text, pattern);
        printSolutions();
    }

    private void printSolutions() {
        System.out.println();
        System.out.println("Solutions: " + solutions);
        System.out.println();
        for (int i : solutions) {
            for (int c = 0; c < textLength; c++) {
                if (c >= i && c < (i + patternLength))
                    System.out.print("\u001B[35m");
                System.out.print(text.charAt(c) + "\u001B[0m");
            }
            System.out.println();
        }
    }

    private void readData() {
        try {
            pattern = new Scanner(new FileReader("/home/piotr/Documents/formal-languages-and-translaction-theory/list-01/exercise1/src/data/pattern")).next();
            text = new Scanner(new FileReader("/home/piotr/Documents/formal-languages-and-translaction-theory/list-01/exercise1/src/data/text")).next();
            System.out.println("Files read correctly");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Pattern: " + pattern);
        System.out.println(pattern.length());
        System.out.println("Text: " + text);
    }

    private int[] computePrefix(String pattern) {
        int i = 1, k = 0;
        int[] p = new int[pattern.length()];

        while (i < pattern.length()) {
            while (pattern.charAt(i) != pattern.charAt(k) && k > 0) {
                k = p[k - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(k)) {
                p[i] = k + 1;
                i++;
                k++;
            } else {
                p[i] = k;
                i++;
            }
        }
        return p;
    }

    private void matcher(String text, String pattern) {
        pi = computePrefix(pattern);
        int i = 0;
        int q = 0;

        while (i < text.length()) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) q = pi[q - 1];
            if (pattern.charAt(q) == text.charAt(i)) q++;
            if (q == pattern.length()) {
                solutions.add((i - pattern.length() + 1));
                q = pi[q - 1];
            }
            i++;
        }
    }
}
