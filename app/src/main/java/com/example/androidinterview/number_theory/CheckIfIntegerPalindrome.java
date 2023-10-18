package com.example.androidinterview.number_theory;

public class CheckIfIntegerPalindrome {

    public static void main(String args[]) {
        int n = 1221;
        System.out.println(isPalindrome(n));
    }

    // the overall time complexity of the given function is O(d), where
    //d is the number of digits in the input number n.
    public static boolean isPalindrome(int n) {

        if (n < 0 || (n != 0 && n % 10 == 0)) {
            return false;
        }

        int reverse = 0;

        while (n > reverse) {

            //In case of 121
            //First time, reverse = 0x10 + 1 i.e 1, n = 12(int)
            //Second time, reverse = 1x10 + 12%10(2) i.e 12, n = 1; loop breaks but n == reverse/10 is true
            reverse = reverse * 10 + n % 10;
            n = n / 10;
        }

        return (n == reverse) || (n == reverse / 10);
    }
}
