package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.example.Record.recordSize;
import static org.example.Utils.*;

public class BinaryFileOperations {

    private static final String FILE_NAME = "binfile.bin";

    public void readFile() throws IOException {


    }

    public void createFile() throws IOException {
        List<Record> recordList = new ArrayList<>();
        for (int i = 0; i < blockCount; i++) {
            int realCount = blockSize / recordSize;
            for (int j = 0; j < realCount; j++) {
                Record record = new Record(j, editingKey("key" + j), j, j, j, j, j, j, j, j, j, j);
                recordList.add(record);
            }
        }
        Writer writer = new Writer(FILE_NAME);
        writer.writeAll(recordList);
    }

    private String editingKey(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[16];

        if (bytes.length < 16) {
            System.arraycopy(bytes, 0, result, 0, bytes.length);
        } else {
            System.arraycopy(bytes, 0, result, 0, 16);
        }

        int i = 0;
        while (i < bytes.length && bytes[i] != 0) {
            i++;
        }
        return new String(bytes, 0, i, StandardCharsets.UTF_8);
    }


}

