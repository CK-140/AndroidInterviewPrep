package com.example.androidinterview.binary_search;

import java.lang.reflect.Array;

public class BinarySearch {


    //The time complexity of binary search is
    //O(logn), where n is the number of elements in the sorted array.
    public static void main(String[] args){

        int target = 99;
        int[] nums = {2,4,7,23,44,55,67,89,99,112,115,177,188,199};

        System.out.println("Target found at index:" + binarySearch(nums, target));
    }

    public static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length -1;

        while(left<=right){
            int mid = left + (right - left)/2; // another way to write (left + right)/2

            if(target > nums[mid]) left = mid +1;
            else if(target < nums[mid])  right = mid -1;
            else return mid;
        }
       //return -1 if target not found
        return -1;
    }
}
