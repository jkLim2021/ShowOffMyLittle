package com.jk.showoffmylittle;

public class MarketItem {
    int no;
    String name;
    String title;
    String msg;
    String nicknamedb;
    String file;
    int favor;
    String date;

    public MarketItem() {
    }

    public MarketItem(int no, String name, String title, String msg, String nicknamedb, String file, int favor, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.nicknamedb = nicknamedb;
        this.file = file;
        this.favor = favor;
        this.date = date;
    }
}
