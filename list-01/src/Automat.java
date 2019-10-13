class Automat {

    static int NO_OF_CHARS = 256;

    static int getNextState(char[] pat, int M,
                            int q, int x) {

        if (q < M && x == pat[q])
            return q + 1;

        int ns, i;

        for (ns = q; ns > 0; ns--) {
            if (pat[ns - 1] == x) {
                for (i = 0; i < ns - 1; i++)
                    if (pat[i] != pat[q - ns + 1 + i])
                        break;
                if (i == ns - 1)
                    return ns;
            }
        }

        return 0;
    }

    static void computeTF(char[] pat, int M, int TF[][]) {
        int q, x;
        for (q = 0; q <= M; ++q)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[q][x] = getNextState(pat, M, q, x);
    }

    static void search(char[] pat, char[] txt) {
        int M = pat.length;
        int N = txt.length;

        int[][] TF = new int[M + 1][NO_OF_CHARS];

        computeTF(pat, M, TF);

        int i, q = 0;
        for (i = 0; i < N; i++) {
            q = TF[q][txt[i]];
            if (q == M)
                System.out.println("Pattern found "
                        + "at index " + (i - M + 1));
        }
    }

}