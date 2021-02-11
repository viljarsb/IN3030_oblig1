package com.company;

import javafx.concurrent.Worker;

public static class parallelSort {

    static int numberOfProcessors = Runtime.getRuntime().availableProcessors();

    public static void sort()
    {
        for(int i = 0; i < numberOfProcessors; i++)
        {

        }
    }

    private class worker implements Runnable {

        int id;

        public Worker(int id)
        {
            this.id = id;
        }

        public void run()
        {

        }
    }
}
