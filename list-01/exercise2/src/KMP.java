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
        int i=1,j=0;
        int[] p = new int[pattern.length()];
        while (i < pattern.length()) {

            while (pattern.charAt(i) != pattern.charAt(j) && j > 0) {
                j = p[j - 1];

            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                p[i] = j + 1;
                i++;
                j++;

            } else {
                p[i] = j;
                i++;
            }
        }

        return p;
    }

}
