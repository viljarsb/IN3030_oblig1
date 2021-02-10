package com.company;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Program starting execution.");
        int[] arr;
        int[] arr2;
        int[] arr3;

        for(int i = 0; i < 1000)
        SequentialSort sort = new SequentialSort();
        arr = sort.sort(arr);

        for(int element : arr) {
            System.out.println(element);
        }
    }
}
