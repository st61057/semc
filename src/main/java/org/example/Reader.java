package org.example;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import static org.example.Utils.blockSize;
import static org.example.Utils.readRecord;

public class Reader {

    private ByteBuffer firstReadBuffer;
    private ByteBuffer secondReadBuffer;

    private String fileName;

    private boolean isSecondActive;
    private boolean isDoubleBuffering;

    public Reader(String fileName, boolean isDoubleBuffering) {
        this.fileName = fileName;
        this.isDoubleBuffering = isDoubleBuffering;
        this.firstReadBuffer = ByteBuffer.allocate(blockSize);
        if (isDoubleBuffering) {
            this.secondReadBuffer = ByteBuffer.allocate(blockSize);
        }
    }

    private ByteBuffer getActiveBuffer() {

        if (!isDoubleBuffering) {
            return firstReadBuffer;
        }

        return isSecondActive ? secondReadBuffer : firstReadBuffer;
    }

    public List<Record> readAll() throws IOException {
        List<Record> list = new ArrayList<>();

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ReadableByteChannel channel = Channels.newChannel(fis);
        while (true) {//TODO async
            ByteBuffer activeBuffer = getActiveBuffer();
            boolean isEOF = channel.read(activeBuffer) == -1;
            if (isEOF) {
                break;
            }

            list.addAll(readByteBuffer(activeBuffer));

            if (isDoubleBuffering) {
                isSecondActive = !isSecondActive;
            }
        }
        return list;
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
