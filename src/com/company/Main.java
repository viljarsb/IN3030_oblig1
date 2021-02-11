package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Program starting execution.");
        System.out.println("You have choosen N = " + args[0] + " and K = " + args[1]);
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        testA1(n, k, 7);
        testA2(n, k, 7);

    }

    public static int[] createArray(int n)
    {
        int[] arr = new int[n];
        Random random_generator = new Random(7634);
        for(int i = 0; i < n; i++)
        {
            arr[i] = random_generator.nextInt(Integer.MAX_VALUE);
        }
        return arr;
    }

    public static void testA1(int n, int k, int trials)
    {
        ArrayList<Long> timing = new ArrayList<Long>();
        for(int i = 0; i < trials; i++)
        {
            long start = System.nanoTime();
            int[] arr = createArray(n);
            Arrays.sort(arr);
            int[] largestK = new int[k];
            int counter = 0;

            for(int j = arr.length - 20; j < arr.length; j++)
            {
                largestK[counter] = arr[j];
                counter++;
            }
            long end = System.nanoTime();
            System.out.println("A1 run: " + (i + 1) + " used: " + (end-start)/100000 + " milliseconds");
            timing.add((end-start)/100000);
        }

        System.out.println("A2 had a median run time of: " + calculateMedian(timing) + " milliseconds over a: " + trials + " executions");
    }

    public static void testA2(int n, int k, int trials)
    {
        ArrayList<Long> timing = new ArrayList<Long>();

        for(int i = 0; i < trials; i++)
        {
            long start = System.nanoTime();
            int[] arr = createArray(n);
            SequentialSort.run(arr, k);
            long end = System.nanoTime();
            System.out.println("A2 run: " + (i + 1) + " used: " + (end-start)/100000 + " milliseconds");
            timing.add((end-start)/100000);
        }

        System.out.println("A2 had a median run time of: " + calculateMedian(timing) + " milliseconds over a: " + trials + " executions");
    }

    public static void testA3(int n, int k, int trials)
    {

    }

    public static long calculateMedian(ArrayList<Long> timing)
    {
        Collections.sort(timing);
        if(timing.size() % 2 == 1)
            return  timing.get((timing.size() + 1)/ 2 - 1);
        else
            return (timing.get(timing.size() / 2 - 1) + timing.get(timing.size() / 2)) / 2;
    }
}
