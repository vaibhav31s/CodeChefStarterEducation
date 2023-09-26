package com.company;//package com.company;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class DB22 {
    InputStream is;
    PrintWriter out;
    String INPUT = "2 \n 5 \n 1 1 1 2 1000000000 0 2 1 1";
    int mod = 1000000007;

    void solve() {
		int n = ni();
        int m = ni();
        int[] arr = na(m);
		PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> {
			if(a.time == b.time){
				return a.index - b.index;
			}else {
				return a.time - b.time;
			}
		} );
		for(int i = 0; i < m; i++){
			pq.add(new Pair(arr[i], i));
		}
		int[] answer = new int[m];
		int curTime = 0;
		while(!pq.isEmpty()){

			int min = Math.min(pq.size(), n);

			while(min-- > 0){
				Pair turn = pq.peek();
				if(curTime >= turn.time){
					answer[turn.index] = curTime+1;
					pq.poll();
				}else break;
			}
			if(!pq.isEmpty()) {
				if(curTime >= pq.peek().time){
					curTime++;
				}else {
					curTime = pq.peek().time;
				}
			}
		}

		System.out.println(Arrays.toString(answer));


    }
	private static class Pair{
		int time ;
		int index ;
		Pair(int time, int index){
			this.time = time;
			this.index = index;
		}
	}

    public static long inv(long a, int p) {
        long ret = 1;
        long mul = a;
        for (long n = p - 2; n > 0; n >>>= 1) {
            if ((n & 1) == 1) ret = ret * mul % p;
            mul = mul * mul % p;
        }
        return ret;
    }

    void run() throws Exception {
        is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        tr(System.currentTimeMillis() - s + "ms");
    }

    public static void main(String[] args) throws Exception {
        new DB22().run();
    }

    private byte[] inbuf = new byte[1024];
    private int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }

    private char nc() {
        return (char) skip();
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

    private void tr(Object... o) {
        if (!oj) System.out.println(Arrays.deepToString(o));
    }
}





//// Srikanth

//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Scanner;
//
//public class DB22 {
//    public static void main(String[] args) {
//        Scanner fs = new Scanner(System.in);
//        PrintWriter pw = new PrintWriter(System.out);
//        int n = fs.nextInt(), m = fs.nextInt();
//        ArrayList<Pair> a = new ArrayList<>();
//        for(int i = 0 ; i < m ; i++) a.add(new Pair(fs.nextInt(), i));
//        Collections.sort(a, (first, second) -> {
//            if (first.f != second.f) return Integer.compare(first.f, second.f);
//            return Integer.compare(first.s, second.s);
//        });
//        int ans[] = new int[m];
//        int timer = 0;
//        // 0 0 0 0
//        pw.close();
//    }
//
//    static class Pair {
//        int f, s;
//        public Pair(int f, int s) {
//            this.f = f;
//            this.s = s;
//        }
//    }
//}