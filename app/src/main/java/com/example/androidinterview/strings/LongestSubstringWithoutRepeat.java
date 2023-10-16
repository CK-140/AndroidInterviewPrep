package com.example.androidinterview.strings;

import java.util.HashMap;

public class LongestSubstringWithoutRepeat {

    public static void main(String args[]) {
        String strs = "flowerrrbestyupo";

        System.out.println(findLongestSubStringWithoutRepeat(strs));

    }

    //Time Complexity: O(n)
    public static int findLongestSubStringWithoutRepeat(String s) {

        //Sliding window approach
        int left = 0;
        int maxLength = 0;

        //hashmap to store the character and it's index
        HashMap<Character, Integer> characterIndicesMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            if (characterIndicesMap.containsKey(s.charAt(right))) {

                //if character is already in the map that means repeat case
                //start of window should be shifted one position right to current character element(+1)
                left = Math.max(left, characterIndicesMap.get(s.charAt(right)) +1);
            }

            //put character along with it's index in map
            characterIndicesMap.put(s.charAt(right), right);

            //loop will run till right<s.length, calculate
            //the length of longest substring without repeating characters in every iteration
            maxLength = Math.max(maxLength, right - left +1);
        }

        return maxLength;

    }
}
