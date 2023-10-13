package com.example.androidinterview.strings;

public class ReverseString {

    public static void main(String args[]){
        String myString = "Android";

        System.out.println(reverseMyStringOptimized(myString));
    }

   //Time Complexity of O(n^2) since we are using concatenation using + operator
    //which creates new strings n times hence it is a bad approach

    public static String reverseMyString(String str){
        int right = str.length() - 1;

        String reversedString = "";

        while(right >= 0){

            reversedString = reversedString + str.charAt(right);
            right--;

        }

        return reversedString;
    }

    //Time Complexity: O(n) since we are using StringBuilder and append
    public static String reverseMyStringOptimized(String str){
        int right = str.length() - 1;

        StringBuilder reversedString = new StringBuilder();

        while (right >= 0) {
            reversedString.append(str.charAt(right));
            right--;
        }

        return reversedString.toString();
    }
}
