package org.example;

import java.io.*;

public class Utils {

    public static int blockCount = 1000;
    public static int blockSize = 1000;
    public static int keyLength = 16;


    public static byte[] writeRecord(Record record) throws IOException {
        try (ByteArrayOutputStream dos = new ByteArrayOutputStream()) {
            byte[] keyBytes = new byte[keyLength];
            byte[] actualKeyBytes = record.getKey().getBytes("utf8");
            System.arraycopy(actualKeyBytes, 0, keyBytes, 0, Math.min(actualKeyBytes.length, keyLength));
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
        int id = dis.readByte();
        byte[] dataBytes = new byte[keyLength];
        dis.readFully(dataBytes);
        String key = new String(dataBytes, "utf8").trim();

        int attribute1 = dis.readByte();
        int attribute2 = dis.readByte();
        int attribute3 = dis.readByte();
        int attribute4 = dis.readByte();
        int attribute5 = dis.readByte();
        int attribute6 = dis.readByte();
        int attribute7 = dis.readByte();
        int attribute8 = dis.readByte();
        int attribute9 = dis.readByte();
        int attribute10 = dis.readByte();

        return new Record(id, key, attribute1, attribute2, attribute3, attribute4, attribute5, attribute6, attribute7, attribute8, attribute9, attribute10);
    }
}
