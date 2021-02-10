package com.company;

/*
    Class holding a simple method for a Sequential Sorting of an int array.
*/
public class SequentialSort {

    /*
        Method for sorting a simple int array of K numbers.
        @Param  A int[] of some size.
        @Return  A int[] sorted in descending order.
    */
    public int [] sort(int arr[]){
        int size = arr.length;

        for(int i = 0; i < size; i++) {

            int currentValue = arr[i];
            int j = i - 1;

            while(j >= 0  && arr[j] < currentValue){
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = currentValue;
        }
        return arr;
    }
}
