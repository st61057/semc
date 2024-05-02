package org.example;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.Utils.blockSize;
import static org.example.Utils.readRecord;

public class Reader {

    private ByteBuffer firstReadBuffer;
    private ByteBuffer secondReadBuffer;

    private String fileName;

    private boolean isSecondActive;
    private boolean isDoubleBuffering;

    private Future<?> processingTask;

    public Reader(String fileName, boolean isDoubleBuffering) {
        this.fileName = fileName;
        this.isDoubleBuffering = isDoubleBuffering;
        this.firstReadBuffer = ByteBuffer.allocate(blockSize * Record.recordSize);
        if (isDoubleBuffering) {
            this.secondReadBuffer = ByteBuffer.allocate(blockSize * Record.recordSize);
        }
    }

    private ByteBuffer getActiveBuffer() {

        if (!isDoubleBuffering) {
            return firstReadBuffer;
        }

        return isSecondActive ? secondReadBuffer : firstReadBuffer;
    }

    public List<Record> readAll() throws IOException, ExecutionException, InterruptedException {
        List<Record> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2); // Two threads for double buffering

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ReadableByteChannel channel = Channels.newChannel(fis);

        while (true) {
            ByteBuffer activeBuffer = getActiveBuffer();
            activeBuffer.clear();
            boolean isEOF = channel.read(activeBuffer) == -1;
            if (isEOF) {
                break;
            }

            waitForTask(list);
            processingTask = executorService.submit(() -> readByteBuffer(activeBuffer));

            if (isDoubleBuffering) {
                isSecondActive = !isSecondActive;
            }
        }
        waitForTask(list);
        return list;
    }

    private void waitForTask(List<Record> list) throws ExecutionException, InterruptedException {
        if (processingTask != null) {
            while (!processingTask.isDone()) {
//                    Vyčkává na zpracování bufferu
            }
            list.addAll((List<Record>) processingTask.get());
        }
        processingTask = null;
    }

    private List<Record> readByteBuffer(ByteBuffer byteBuffer) throws IOException {
        List<Record> recordList = new ArrayList<>();

        byte[] byteArrays = byteBuffer.array();
        int count = byteArrays.length / Record.recordSize;

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(byteArrays));
        for (int i = 0; i < count; i++) {
            recordList.add(readRecord(dis));
        }

        return recordList;
    }


}
