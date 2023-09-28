package com.company.Starter102;/* package codechef; // don't place package name! */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

import static java.lang.System.out;

/* Name of the class has to be "Main" only if the class is public. */
class C
{
    public static void main (String[] args) throws Exception
    {
        // your code goes here
        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();
        outer:
        while(t-->0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int expected = (n /2) * 3;
//            System.out.println(expected);
            if(expected > k ) {
                System.out.println(-1);
                continue outer;
            }
            long expectedAns = k;
            k -= expected;
//            out.println(k);
            int[] arr  = new int[n];
            Arrays.fill(arr,1);
            for(int i = 0; i < n/2; i++) {
                arr[i]++;
            }

            if(k % 2 == 0) {
                arr[0] += k;
            } else {
                arr[n/2] += k;
            }
            long total = 0;
            for(int x : arr) total +=x;
            if(total != expectedAns) {
                out.println(-1);
            }else   print(arr);

        }
    }

    public static void print(int[] arr) {
        //for debugging only
        for (int x : arr)
            out.print(x + " ");
        out.println();
    }

}
