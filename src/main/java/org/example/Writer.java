package org.example;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

import static org.example.Utils.*;

public class Writer {

    private String fileName;

    public Writer(String fileName) {
        this.fileName = fileName;
    }

    public void writeAll(List<Record> recordList) throws IOException {

        File file = new File(fileName);
        if (file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        for (int i = 0; i < recordList.size(); i += blockSize) {
            ByteBuffer byteBufferRecord = ByteBuffer.allocate(blockSize * Record.recordSize);
            List<Record> sublist = recordList.subList(i, i + blockSize);

            for (Record record : sublist) {
                byteBufferRecord.put(ByteBuffer.wrap(writeRecord(record)));
            }
            fileOutputStream.write(byteBufferRecord.array());
        }
        fileOutputStream.close();

    }

}
