package com.example.androidinterview.strings;

import java.util.Arrays;

public class LongestCommonPrefix {

    public static void main(String args[]){
        String[] strs = {"flower", "flow", "flight"};

        System.out.println(findLongestCommonPrefix(strs));

    }


    //Time Complexity: O(nlogn)
    /*
    *
    * Comparing First and Last Strings: After sorting,
    * you only need to compare the first and last strings (i.e., left and right)
    * because these strings represent the extremes of the sorted array.
    * Comparing these two strings efficiently identifies
    * the longest common prefix shared by all strings in the array.*/

    public static String findLongestCommonPrefix(String[] strs){
        Arrays.sort(strs);
        StringBuilder ans = new StringBuilder();
        String left = strs[0];
        String right = strs[strs.length - 1];

        for(int i = 0; i<Math.min(left.length(), right.length()); i++){

            if(left.charAt(i) != right.charAt(i)){
                return ans.toString();
            }
            ans.append(left.charAt(i));
        }
            return ans.toString();
    }
}
