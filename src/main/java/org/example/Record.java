package org.example;

import java.io.Serializable;

public class Record implements Serializable {

    public static final int recordSize = 11 + 16;//11 att, 4 id, 16 key
    private int id;
    private String key;
    private int att01;
    private int att02;
    private int att03;
    private int att04;
    private int att05;
    private int att06;
    private int att07;
    private int att08;
    private int att09;
    private int att10;

    public Record(int id, String key, int att01, int att02, int att03, int att04, int att05, int att06, int att07, int att08, int att09, int att10) {
        this.id = id;
        this.key = key;
        this.att01 = att01;
        this.att02 = att02;
        this.att03 = att03;
        this.att04 = att04;
        this.att05 = att05;
        this.att06 = att06;
        this.att07 = att07;
        this.att08 = att08;
        this.att09 = att09;
        this.att10 = att10;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public int getAtt01() {
        return att01;
    }

    public int getAtt02() {
        return att02;
    }

    public int getAtt03() {
        return att03;
    }

    public int getAtt04() {
        return att04;
    }

    public int getAtt05() {
        return att05;
    }

    public int getAtt06() {
        return att06;
    }

    public int getAtt07() {
        return att07;
    }

    public int getAtt08() {
        return att08;
    }

    public int getAtt09() {
        return att09;
    }

    public int getAtt10() {
        return att10;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", att01=" + att01 +
                ", att02=" + att02 +
                ", att03=" + att03 +
                ", att04=" + att04 +
                ", att05=" + att05 +
                ", att06=" + att06 +
                ", att07=" + att07 +
                ", att08=" + att08 +
                ", att09=" + att09 +
                ", att10=" + att10 +
                '}';
    }
}
