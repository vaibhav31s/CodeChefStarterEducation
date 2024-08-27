package com.company.START116C;

import java.io.BufferedInputStream;import java.io.File;import java.io.FileInputStream;import java.io.InputStream;import java.util.*;import static java.lang.System.out;import java.io.*;import java.util.Arrays;

class FF {
    static String INPUT = """
           
      4
      4 4
      5 4
      1 8
      1 9
         
            """;
    
    static boolean oj = System.getProperty("ONLINE_JUDGE") != null;    static InputStream is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());    static FastScanner sc = new FastScanner(is);
    static boolean found =false;
    static long depth1 = Integer.MAX_VALUE;
    static HashMap<Integer, Integer> count = new HashMap<>();

    public static void main(String[] args) {
        int t  = sc.nextInt();
        outer:
        while(t-- > 0){
//            int n = sc.nextInt();
//            int[] arr = readIntArray(sc, 2);
             int a = sc.nextInt();
             int b = sc.nextInt();
             int n = Math.abs(a-b);
          out.println(iterativeA(n));
//            out.println(answer);
        }
    }public static int iterativeA(int n) {
    boolean[][] dp = new boolean[n + 1][(n * (n + 1) / 2) + 1];

    // Base case
    for (int i = 0; i <= n; i++) {
      dp[i][0] = true;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= i * (i + 1) / 2; j++) {
        dp[i][j] = dp[i - 1][j];
        if (j >= i) {
          dp[i][j] = dp[i][j] || dp[i - 1][j - i];
        }
      }
    }

    int result = 0;
    for (int k = 0; k <= n; k++) {
      if (dp[n][k]) {
        result = k;
        break;
      }
    }

    return result;
  }

    private static class FastScanner {              private final int BS = 1 << 16;        private final char NC = (char) 0;        private final byte[] buf = new byte[BS];        private int bId = 0, size = 0;        private char c = NC;        private double cnt = 1;        private BufferedInputStream in;        public FastScanner() {            in = new BufferedInputStream(System.in, BS);        }  public FastScanner(InputStream ff) {            in = new BufferedInputStream(ff);        }        public FastScanner(String s) {            try {                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);            } catch (Exception e) {                in = new BufferedInputStream(System.in, BS);            }        }        private char getChar() {            while (bId == size) {                try {                    size = in.read(buf);                } catch (Exception e) {                    return NC;                }                if (size == -1) return NC;                bId = 0;            }            return (char) buf[bId++];        }        public int nextInt() {            return (int) nextLong();        }        public int[] nextInts(int N) {            int[] res = new int[N];            for (int i = 0; i < N; i++) {                res[i] = (int) nextLong();            }            return res;        }        public long[] nextLongs(int N) {            long[] res = new long[N];            for (int i = 0; i < N; i++) {                res[i] = nextLong();            }            return res;        }        public long nextLong() {            cnt = 1;            boolean neg = false;            if (c == NC) c = getChar();            for (; (c < '0' || c > '9'); c = getChar()) {                if (c == '-') neg = true;            }            long res = 0;            for (; c >= '0' && c <= '9'; c = getChar()) {                res = (res << 3) + (res << 1) + c - '0';                cnt *= 10;            }            return neg ? -res : res;        }        public double nextDouble() {            double cur = nextLong();            return c != '.' ? cur : cur + nextLong() / cnt;        }        public double[] nextDoubles(int N) {            double[] res = new double[N];            for (int i = 0; i < N; i++) {                res[i] = nextDouble();            }            return res;        }        public String next() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c > 32) {                res.append(c);                c = getChar();            }            return res.toString();        }        public String nextLine() {            StringBuilder res = new StringBuilder();            while (c <= 32) c = getChar();            while (c != '\n') {                res.append(c);                c = getChar();            }            return res.toString();        }        public boolean hasNext() {            if (c > 32) return true;            while (true) {                c = getChar();                if (c == NC) return false;                else if (c > 32) return true;            }        }    }private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o));
    }
  }