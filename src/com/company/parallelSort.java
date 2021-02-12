package com.company;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CyclicBarrier;

public class parallelSort {

    static int numberOfProcessors = Runtime.getRuntime().availableProcessors();
    static ReentrantLock reentrantLock = new ReentrantLock();
    static ReentrantLock reentrantLock_two = new ReentrantLock();
    static int[] arr;
    static CyclicBarrier barrier;
    static int maxK;

    public static void run(int k, int[] arr)
    {
        barrier = new CyclicBarrier(numberOfProcessors + 1);
        maxK = k;
        int modResult = maxK%numberOfProcessors;
        int min = Math.round(k/numberOfProcessors);
        int current = 0;
        int created = 0;

        int modResult2 = (arr.length - maxK)%numberOfProcessors;
        int min2 =(int) (arr.length - maxK)/numberOfProcessors;
        int current2 = maxK;

        while(true)
        {
            if(created < numberOfProcessors)
            {
                new Thread(new worker(created, current, current + min, current2, current2 + min2, arr)).start();
                current = current + min;
                current2 = current2 + min2;
                created = created + 1;

                if(created == (numberOfProcessors - modResult) && modResult != 0)
                    min = min + 1;
                if(created == (numberOfProcessors - modResult2) && modResult != 0)
                    min2 = min2 + 1;
            }


            else
            {
                break;
            }
        }

        try {
            barrier.await();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static class worker implements Runnable {

        int id;
        int startIndex;
        int endIndex;
        int startIndex2;
        int endIndex2;
        static int[] arr;

        public worker(int id, int startIndex, int endIndex, int startIndex2, int endIndex2, int[] arr)
        {
            this.id = id;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.startIndex2 = startIndex2;
            this.endIndex2 = endIndex2;
            this.arr = arr;
        }

        public void run()
        {
            InsertionSort(this.startIndex, this.endIndex, arr);
            findK(this.startIndex2, this.endIndex2, arr);

            try {
                barrier.await();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public static void findK(int startIndex, int endIndex, int[] arr)
        {
            for(int i = startIndex; i < endIndex; i++)
            {
                if(arr[i] > arr[maxK-1])
                {
                    singleInsertionSort(i);
                }
            }
        }

        public static void singleInsertionSort(int indexFound)
        {
            reentrantLock_two.lock();
            try
            {
                if(arr[indexFound] > arr[maxK - 1])
                {
                    int temp = arr[maxK - 1];
                    arr[maxK - 1] = arr[indexFound];
                    arr[indexFound] = temp;

                    for(int i = maxK - 1; i > 0; i--)
                    {
                        if(arr[i] > arr[i-1]){
                            int temp2 = arr[i - 1];
                            arr[i - 1] = arr[i];
                            arr[i] = temp2;
                        }
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                reentrantLock_two.unlock();
            }
        }


        public static void InsertionSort(int startIndex, int endIndex, int[] arr)
        {
            int i;

            //from 0 to k.
            for(int j = startIndex; j < endIndex; j++) {
                int currentValue = arr[j + 1];
                if(j >= 0 && arr[j] < currentValue);
                    InsertionSortShiftLeft(j);
            }
        }

        public static void InsertionSortShiftLeft(int index)
        {
            reentrantLock.lock();
            try
            {
                int currentValue = arr[index + 1];
                while(index >= 0  && arr[index] < currentValue)
                {
                    arr[index + 1] = arr[index];
                    index = index - 1;
                }
                arr[index + 1] = currentValue;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                reentrantLock.unlock();
            }

        }
    }
}
