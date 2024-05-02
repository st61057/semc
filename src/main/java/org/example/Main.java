package org.example;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        BinaryFileOperations bfo = new BinaryFileOperations();
        bfo.createFile();
        long startTimeSingle = System.nanoTime();
        List<Record> singleList = bfo.readFile(false);
        for (Record record : singleList) {
            System.out.println(record);
        }
        long endTimeSingle = System.nanoTime();

        long startTimeDouble = System.nanoTime();
        List<Record> doubleList = bfo.readFile(true);
        for (Record record : doubleList) {
            System.out.println(record);
        }
        long endTimeDouble = System.nanoTime();

        System.out.println("results:");

        long elapsedTime = (endTimeSingle - startTimeSingle) / 100000000;
        System.out.println(elapsedTime + " - single");

        long elapsedTimeDouble = (endTimeDouble - startTimeDouble) / 100000000;
        System.out.println(elapsedTimeDouble + " - double");

    }
}