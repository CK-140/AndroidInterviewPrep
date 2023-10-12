package com.example.androidinterview.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Anagram {

    public static void main(String args[]) {
        String str1 = "aaBB";
        String str2 = "abaB";

        System.out.println(isAnagramUsingHashMap(str1.toLowerCase(), str2.toLowerCase()));
    }

    //Time Complexity O(nlogn) due to sorting
    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        //sort str1
        char[] strAr1 = str1.toCharArray();
        char[] strAr2 = str2.toCharArray();

        Arrays.sort(strAr1);
        Arrays.sort(strAr2);

        return Arrays.equals(strAr1, strAr2);

    }


    //Using Hashmap to count frequency of characters
    //Time Complexity O(n)

    public static boolean isAnagramUsingHashMap(String str1, String str2){
        if (str1.length() != str2.length()) return false;

        HashMap<Character,Integer> frequencyHashmap = new HashMap<>();
        char[] strAr1 = str1.toCharArray();
        char[] strAr2 = str2.toCharArray();

        for (char c : strAr1) {

            if (frequencyHashmap.containsKey(c)) {
                frequencyHashmap.put(c, frequencyHashmap.get(c) + 1);
            } else {
                frequencyHashmap.put(c, 1);
            }
        }

        for (char c : strAr2) {
            if (frequencyHashmap.containsKey(c)) {
                frequencyHashmap.put(c, frequencyHashmap.get(c) - 1);
            } else return false;
        }

        Set<Character> keys = frequencyHashmap.keySet();
        // Loop over all keys and check if all keys are 0.
        // If so it means it is anagram.
        for (Character key : keys) {
            if (frequencyHashmap.get(key) != 0) {
                return false;
            }
        }
        // Returning True as all keys are zero
        return true;
    }
}
