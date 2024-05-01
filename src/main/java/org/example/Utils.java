package org.example;

import java.io.*;

public class Utils {

    public static int blockCount = 1000;
    public static int blockSize = 1000;


    public static byte[] writeRecord(Record record) throws IOException {
        try (ByteArrayOutputStream dos = new ByteArrayOutputStream()) {
            byte[] keyBytes = new byte[40];
            byte[] actualKeyBytes = record.getKey().getBytes("utf8");
            System.arraycopy(actualKeyBytes, 0, keyBytes, 0, Math.min(actualKeyBytes.length, 40));
            dos.write(record.getId());
            dos.write(keyBytes);
            dos.write(record.getAtt01());
            dos.write(record.getAtt02());
            dos.write(record.getAtt03());
            dos.write(record.getAtt04());
            dos.write(record.getAtt05());
            dos.write(record.getAtt06());
            dos.write(record.getAtt07());
            dos.write(record.getAtt08());
            dos.write(record.getAtt09());
            dos.write(record.getAtt10());
            return dos.toByteArray();
        }
    }

    public static Record readRecord(DataInputStream dis) throws IOException {
        byte[] dataBytes = new byte[40];
        dis.readFully(dataBytes);

        int id = dis.readInt();
        String key = new String(dataBytes, "utf8").trim();

        int attribute1 = dis.readInt();
        int attribute2 = dis.readInt();
        int attribute3 = dis.readInt();
        int attribute4 = dis.readInt();
        int attribute5 = dis.readInt();
        int attribute6 = dis.readInt();
        int attribute7 = dis.readInt();
        int attribute8 = dis.readInt();
        int attribute9 = dis.readInt();
        int attribute10 = dis.readInt();

        return new Record(id, key, attribute1, attribute2, attribute3, attribute4, attribute5, attribute6, attribute7, attribute8, attribute9, attribute10);
    }
}
