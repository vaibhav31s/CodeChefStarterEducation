package com.company.ShortSEP23;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.*;
import java.lang.*;
import java.io.*;
class D {

    public static void main(String[] args) throws java.lang.Exception {
        FastScanner sc = new FastScanner();
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            for (int i = 0; i < n; i++) {
                b[i] = sc.nextInt();
            }
            constructSegmentTree(a);




//            SegmentTree seg = new SegmentTree(0, n);
//            for(int i = 0; i < n; i++) {
//                seg.update(i, a[i]);
//            }
            long answer = findRangeGcd(0,n-1,a);
            for(int i = 1; i < n - 1; i++) {
                long
                        left = findRangeGcd(0,i-1,a);

                long
                        right = findRangeGcd(i+1,n-1,a);

                long cur = gcd(left, right);

                long onlyA = gcd(a[i], cur);
                long onlyB =  gcd(b[i], cur);
                answer =  Math.max(onlyA,Math.max(onlyB, answer));
            }
            long onlyA = gcd(a[0],findRangeGcd(1,n-1,a));
            long onlyB = gcd(b[0],findRangeGcd(1,n-1,a));
            answer =  Math.max(onlyA,Math.max(onlyB, answer));

            onlyA = gcd(a[n-1],findRangeGcd(0,n-2,a));
            onlyB = gcd(b[n-1],findRangeGcd(0,n-2,a));
            answer =  Math.max(onlyA,Math.max(onlyB, answer));
            System.out.println(answer);


        }
    }


    private static int[] st; // Array to store segment tree

    /* Function to construct segment tree from given array.
       This function allocates memory for segment tree and
       calls constructSTUtil() to fill the allocated memory
     */
    public static int[] constructSegmentTree(int[] arr)
    {
        int height = (int)Math.ceil(Math.log(arr.length)
                / Math.log(2));
        int size = 2 * (int)Math.pow(2, height) - 1;
        st = new int[size];
        constructST(arr, 0, arr.length - 1, 0);
        return st;
    }

    // A recursive function that constructs Segment
    // Tree for array[ss..se]. si is index of current
    // node in segment tree st
    public static int constructST(int[] arr, int ss, int se,
                                  int si)
    {
        if (ss == se) {
            st[si] = arr[ss];
            return st[si];
        }
        int mid = ss + (se - ss) / 2;
        st[si] = gcd(
                constructST(arr, ss, mid, si * 2 + 1),
                constructST(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }

    // Function to find gcd of 2 numbers.
    private static int gcd(int a, int b)
    {
        if (a < b) {
            // If b greater than a swap a and b
            int temp = b;
            b = a;
            a = temp;
        }

        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Finding The gcd of given Range
    public static int findRangeGcd(int ss, int se,
                                   int[] arr)
    {
        int n = arr.length;

        if (ss < 0 || se > n - 1 || ss > se)
            throw new IllegalArgumentException(
                    "Invalid arguments");

        return findGcd(0, n - 1, ss, se, 0);
    }

    /*  A recursive function to get gcd of given
    range of array indexes. The following are parameters for
    this function.

    st    --> Pointer to segment tree
    si --> Index of current node in the segment tree.
    Initially 0 is passed as root is always at index 0 ss &
    se  --> Starting and ending indexes of the segment
                 represented by current node, i.e., st[si]
    qs & qe  --> Starting and ending indexes of query range
  */
    public static int findGcd(int ss, int se, int qs,
                              int qe, int si)
    {
        if (ss > qe || se < qs)
            return 0;

        if (qs <= ss && qe >= se)
            return st[si];

        int mid = ss + (se - ss) / 2;

        return gcd(
                findGcd(ss, mid, qs, qe, si * 2 + 1),
                findGcd(mid + 1, se, qs, qe, si * 2 + 2));
    }
    private static class SegmentTree
    {
        //Tlatoani's segment tree
        //iterative implementation = low constant runtime factor
        //range query, non lazy
        final long[] val;
        final int treeFrom;
        final int length;

        public SegmentTree(int treeFrom, int treeTo)
        {
            this.treeFrom = treeFrom;
            int length = treeTo - treeFrom + 1;
            int l;
            for (l = 0; (1 << l) < length; l++);
            val = new long[1 << (l + 1)];
            this.length = 1 << l;
        }
        public void update(int index, int delta)
        {
            //replaces value
            int node = index - treeFrom + length;
            val[node] = delta;
            for (node >>= 1; node > 0; node >>= 1)
                val[node] = comb(val[node << 1], val[(node << 1) + 1]);
        }
        public long query(int from, int to)
        {
            //inclusive bounds
            if (to < from)
                return 1; //0 or 1?
            from += length - treeFrom;
            to += length - treeFrom + 1;
            //0 or 1?
            long res = 0;
            for (; from + (from & -from) <= to; from += from & -from)
                res = comb(res, val[from / (from & -from)]);
            for (; to - (to & -to) >= from; to -= to & -to)
                res = comb(res, val[(to - (to & -to)) / (to & -to)]);
            return res;
        }
        public long comb(long a, long b)
        {
            //change this
            return gcd(a,b);
        }
    }
    private static long gcd(long a, long b) {
        if (a > b)
            a = (a + b) - (b = a);
        if (a == 0L)
            return b;
        return gcd(b % a, a);
    }

    private static long lcm(int x, int y) {
        return (x * (y / gcd(x, y)));
    }




    private static class FastScanner
    {
        //I don't understand how this works lmao
        private int BS = 1 << 16;
        private char NC = (char) 0;
        private byte[] buf = new byte[BS];
        private int bId = 0, size = 0;
        private char c = NC;
        private double cnt = 1;
        private BufferedInputStream in;

        public FastScanner() {
            in = new BufferedInputStream(System.in, BS);
        }

        public FastScanner(String s) {
            try {
                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
            } catch (Exception e) {
                in = new BufferedInputStream(System.in, BS);
            }
        }

        private char getChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1) return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public int[] nextInts(int N) {
            int[] res = new int[N];
            for (int i = 0; i < N; i++) {
                res[i] = (int) nextLong();
            }
            return res;
        }

        public long[] nextLongs(int N) {
            long[] res = new long[N];
            for (int i = 0; i < N; i++) {
                res[i] = nextLong();
            }
            return res;
        }

        public long nextLong() {
            cnt = 1;
            boolean neg = false;
            if (c == NC) c = getChar();
            for (; (c < '0' || c > '9'); c = getChar()) {
                if (c == '-') neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = getChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                cnt *= 10;
            }
            return neg ? -res : res;
        }

        public double nextDouble() {
            double cur = nextLong();
            return c != '.' ? cur : cur + nextLong() / cnt;
        }

        public double[] nextDoubles(int N) {
            double[] res = new double[N];
            for (int i = 0; i < N; i++) {
                res[i] = nextDouble();
            }
            return res;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c > 32) {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c != '\n') {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if (c > 32) return true;
            while (true) {
                c = getChar();
                if (c == NC) return false;
                else if (c > 32) return true;
            }
        }
    }
}
