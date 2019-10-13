
public class App {
    public static void main(String[] args) {
        char[] pat = "AABAACAADAABAAABAA".toCharArray();
        System.out.println(pat.length);
        for (int i = 0; i < pat.length; i++) {
            System.out.println(i + " : " + pat[i]);
        }
        char[] txt = "AABA".toCharArray();
        Automat.search(txt, pat);
    }
}