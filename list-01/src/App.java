
public class App {
    public static void main(String[] args) {
        char[] pat = "AABA".toCharArray();
        char[] txt = "AABAACAADAABAAABAA".toCharArray();


        for (int i = 0; i < txt.length; i++) {
            System.out.print("[" + i + ":" + txt[i] + "] ");
            if (i != 0 && i % 10 == 0) {
                System.out.println();
            }
        }

    }
}