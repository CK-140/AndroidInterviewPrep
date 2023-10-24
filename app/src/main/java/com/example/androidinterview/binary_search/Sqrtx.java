package com.example.androidinterview.binary_search;

public class Sqrtx {
    //The time complexity of binary search is
    //O(logn), where n is the number of elements in the sorted array.
    public static void main(String[] args){

        int num = 64;

        System.out.println("Square root:" + mySqrt(num));
    }

    public static int mySqrt(int x) {

        int start = 0;
        int end = x;


        while(start <= end){
            int mid = start + (end - start)/2;

            if(mid * mid > x){
                start = mid + 1;
            }

            else if(mid * mid < x ){
                end = mid - 1;
            }

            else if(mid * mid == x) {
                return mid;
            }


        }

        return (int) Math.sqrt(x);


    }
}
