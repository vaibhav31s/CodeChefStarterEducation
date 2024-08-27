package com.company.START134B;

import java.util.Scanner;

public class ggg {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long l = sc.nextLong(), r = sc.nextLong();
    System.out.println(go(r) - go(l - 1));
  }
  public static long go(long n) {
    if (n == 0) return 0;
    long x = (long) Math.sqrt(n);
    long total = (x - 1) * 3;
    long y = x;
    while (x * y <= n) {
      total++;
      y++;
    }
    return total;
  }

}
