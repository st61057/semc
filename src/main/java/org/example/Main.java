package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        while (true) {
            BinaryFileOperations bfo = new BinaryFileOperations();
            Scanner sc = new Scanner(System.in);
            System.out.println("Zvolte operaci:");
            System.out.println(" 1. načtení souboru, čtení s jedním bufferem \n 2. načtení souboru, čtení s dvěma buffery \n 3. porovnání výkononosti \n 0. ukončení");
            int op = Integer.parseInt(sc.next());
            switch (op) {
                case 1: {
                    bfo.createFile();
                    long startTimeSingle = System.nanoTime();
                    List<Record> singleList = bfo.readFile(false);
//                    for (Record record : singleList) {
//                        System.out.println(record);
//                    }
                    long endTimeSingle = System.nanoTime();
                    long elapsedTime = TimeUnit.NANOSECONDS.toMillis((endTimeSingle - startTimeSingle));
                    System.out.println(elapsedTime + "ms - single");
                    break;
                }
                case 2: {
                    bfo.createFile();
                    long startTimeDouble = System.nanoTime();
                    List<Record> doubleList = bfo.readFile(true);
//                    for (Record record : doubleList) {
//                        System.out.println(record);
//                    }
                    long endTimeDouble = System.nanoTime();
                    long elapsedTimeDouble = TimeUnit.NANOSECONDS.toMillis((endTimeDouble - startTimeDouble));
                    System.out.println(elapsedTimeDouble + "ms - double");
                    break;
                }

                case 3: {
                    bfo.createFile();
                    long startTimeSingle = System.nanoTime();
                    List<Record> singleList = bfo.readFile(false);
//                    for (Record record : singleList) {
//                        System.out.println(record);
//                    }
                    long endTimeSingle = System.nanoTime();

                    long startTimeDouble = System.nanoTime();
                    List<Record> doubleList = bfo.readFile(true);
//                    for (Record record : doubleList) {
//                        System.out.println(record);
//                    }
                    long endTimeDouble = System.nanoTime();

                    System.out.println("results:");
                    long elapsedTime = TimeUnit.NANOSECONDS.toMillis((endTimeSingle - startTimeSingle));
                    System.out.println(elapsedTime + "ms - single");

                    long elapsedTimeDouble = TimeUnit.NANOSECONDS.toMillis((endTimeDouble - startTimeDouble));
                    System.out.println(elapsedTimeDouble + "ms - double");
                    break;
                }
                case 0: {
                    System.exit(130);
                }
            }
        }

    }
}