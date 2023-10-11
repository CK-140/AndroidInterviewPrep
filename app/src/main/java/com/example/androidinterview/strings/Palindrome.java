package com.example.androidinterview.strings;

public class Palindrome {

    public static void main(String args[]) {
        String str = "level";

        // Convert the string to lowercase
        str = str.toLowerCase();
        boolean A = isPalindromeTwoPointer(str);
        System.out.println(A);
    }

    //Naive method
    public static boolean isPalindrome(String str) {
        // Initializing an empty string to store the reverse
        // of the original str
        String rev = "";

        // Initializing a new boolean variable for the
        // answer
        boolean ans = false;

        for (int i = str.length() - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);
        }

        // Checking if both the strings are equal
        if (str.equals(rev)) {
            ans = true;
        }
        return ans;
    }


    //Two pointer method
    public static boolean isPalindromeTwoPointer(String str) {
        int left = 0;
        int right = str.length() - 1;

        while(left<right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
                left++;
                right--;

        }
        return true;
    }

}