import java.io.*;
import java.util.*;

public class Automatic {


    private String pattern;
    private String text;
    private String alphabet;
    private int patternLength;
    private int textLength;
    private int alphabetLength;
    private int[][] transitions;
    private ArrayList<Integer> solutions;


    Automatic() {
        readData();
        textLength = text.length();
        patternLength = pattern.length();
        alphabetLength = alphabet.length();
        transitions = new int[patternLength + 1][alphabetLength];
        solutions = new ArrayList<>();

        computeTransitionFunction();
        search();
        printTransitionTable();
        printSolutions();
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


    private void computeTransitionFunction() {
        String pq;
        int k;
        char a;
        for (int q = 0; q <= patternLength; q++) {
            for (int i = 0; i < alphabetLength; i++) {
                a = alphabet.charAt(i);
                k = Math.min(patternLength + 1, q + 2);
                pq = pattern.substring(0, q) + a;
                do {
                    k--;
                }
                while (!pq.endsWith(pattern.substring(0, k)));
                transitions[q][i] = k;
            }
        }
    }

    private void search() {
        int q = 0;
        for (int i = 0; i < textLength; i++) {
            q = deltaFunc(q, text.charAt(i));
            if (q == patternLength)
                solutions.add(i - patternLength + 1);
        }
    }

    private int deltaFunc(int q, char c) {
        for (int i = 0; i < alphabetLength; i++)
            if (c == alphabet.charAt(i))
                return transitions[q][i];
        System.out.println("Character not from the alphabet: " + c);
        return 0;
    }

    private void printTransitionTable() {
        System.out.println();
        System.out.println("Transition table: ");
        for (int i = 0; i < alphabetLength; i++) {
            System.out.print(alphabet.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i <= patternLength; i++) {
            for (int j = 0; j < alphabetLength; j++) {
                System.out.print(transitions[i][j] + " ");
            }
            System.out.println();
        }
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

}