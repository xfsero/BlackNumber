package com.stupidwind.com.blacknumber;

/**
 * BlackNumber实体类
 * Created by 蠢风 on 2017/3/29.
 */

public class BlackNumber {

    private int id;

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlackNumber() { }

    public BlackNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }
}
