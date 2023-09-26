package com.company.Starter100;/* package codechef; // don't place package name! */
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class C
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();
        while(t-->0) {
            int n = sc.nextInt();
            String s= sc.next();
            char[] arr = s.toCharArray();

            for(int i = n-1; i >=0 ; i--) {
                if(arr[i] == '1') {
                    if(i + 1 < n) arr[i + 1] ='0';
                    if(i + 2 < n) arr[i+2] = '0';

                }


            }

            String s1 = String.valueOf(arr);
            arr = s.toCharArray();
            for(int i =0; i < n ; i++) {
                if(i + 2 < n && arr[i] == '1') {

                    for(int j = i + 1; j < n; j++){
                        arr[j] ='0';
                        i = j;
                    }
                }
            }
            System.out.println(String.valueOf(arr));

//            System.out.println(String.valueOf(arr));
        }
    }
}
