package com.example.androidinterview.recursion;

public class TillNWithoutLoop {

    //Time Complexity: O(n)
    static void printNos(int n)
    {
        if (n > 0) {
            printNos(n - 1);
            System.out.print(n + " ");// put this above printNos call(line9) to print N to 1
        }
        return;
    }

    // Driver Code
    public static void main(String[] args)
    {
        int n = 10;
        printNos(n);
    }
}
