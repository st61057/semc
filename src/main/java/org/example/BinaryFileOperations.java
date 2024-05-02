package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static org.example.Utils.*;

public class BinaryFileOperations {

    private static final String FILE_NAME = "binfile.bin";

    public List<Record> readFile(boolean isDoubleBuffering) throws IOException, ExecutionException, InterruptedException {
        Reader reader = new Reader(FILE_NAME, isDoubleBuffering);
        return reader.readAll();
    }

    public void createFile() throws IOException {
        List<Record> recordList = new ArrayList<>();
        for (int i = 0; i < blockCount; i++) {
            for (int j = 0; j < blockSize; j++) {
                Record record = new Record(/*blockSize * i +*/ j, editingKey("key" + j), j, j, j, j, j, j, j, j, j, j);
                recordList.add(record);
            }
        }
        Writer writer = new Writer(FILE_NAME);
        writer.writeAll(recordList);
    }

    private String editingKey(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[keyLength];

        if (bytes.length < keyLength) {
            System.arraycopy(bytes, 0, result, 0, bytes.length);
        } else {
            System.arraycopy(bytes, 0, result, 0, keyLength);
        }

        int i = 0;
        while (i < bytes.length && bytes[i] != 0) {
            i++;
        }
        return new String(bytes, 0, i, StandardCharsets.UTF_8);
    }


}

